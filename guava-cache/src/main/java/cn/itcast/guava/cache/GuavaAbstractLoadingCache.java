package cn.itcast.guava.cache;

import cn.itcast.guava.cache.constant.CommonConstant;
import cn.itcast.guava.cache.loader.PropertyLoader;
import cn.itcast.guava.cache.properties.GuavaCacheProperties;
import com.google.common.base.Ticker;
import com.google.common.cache.*;
import com.google.common.collect.ImmutableMap;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: GuavaAbstractLoadingCache  
 * 抽象Guava缓存类、缓存模板。
 * 子类需要实现fetchData(key)，从数据库或其他数据源（如Redis）中获取数据。
 * 子类调用getValue(key)方法，从缓存中获取数据，并处理不同的异常，比如value为null时的InvalidCacheLoadException异常。 
 * (缓存辅助类,包括：Cache创建、从数据源获取数据、定义过时策略、等)
 * @author zhangtian  
 * @version @param <K>
 * @version @param <V>
 */
public abstract class GuavaAbstractLoadingCache<K, V> {
	private Date resetTime;     // Cache初始化或被重置的时间  
    private long highestSize=0; // 历史最高记录数  
    private Date highestTime;   // 创造历史记录的时间  
    private LoadingCache<K, V> cache ;  
    
    public static GuavaCacheProperties guavaCacheProperties = null ;
    
    static {
    	guavaCacheProperties = (GuavaCacheProperties) PropertyLoader.loadProperty(GuavaCacheProperties.class) ;
    }
	
    /**
     *  getCache:(通过调用getCache().get(key)来获取数据 ). 
     *  @return_type:LoadingCache<K,V>
     *  @author zhangtian  
     *  @return
     */
    public LoadingCache<K, V> getCache() {
    	if(cache == null){  //使用双重校验锁保证只有一个cache实例  
            synchronized (this) {  
                if(cache == null){
                	cache = getCacheBuilder()
                			.removalListener(new RemovalListener<K, V>() {
								@Override
								public void onRemoval(RemovalNotification<K, V> notification) {
									System.out.println(notification.getKey()+"被移除了...");
								}
							})
                            .build(new CacheLoader<K, V>() {  
                                @Override  
                                public V load(K key) throws Exception { 
                                    return fetchData(key);  
                                }  
                            });  
                    this.resetTime = new Date();  
                    this.highestTime = new Date();  
                }
            }
    	}
    	return cache; 
    }
    
    /** 
     * 根据key从数据库或其他数据源中获取一个value，并被自动保存到缓存中。 
     * @param key 
     * @return value,连同key一起被加载到缓存中的。  
     */  
    protected abstract V fetchData(K key);  
    
    /**
     *  putCache:(数据加入缓存). 
     *  @return_type:void
     *  @author zhangtian  
     *  @param key
     *  @param value
     */
    public void putCache(K key , V value) {
    	this.getCache().put(key, value);
    }
    
    /**
     *  batchPutCache:(批量插入缓存). 
     *  @return_type:void
     *  @author zhangtian  
     *  @param map
     */
    public void batchPutCache(Map<K, V> map) {
    	this.getCache().putAll(map);
    }
    
    /**
     *  invalidate:(废弃缓存). 
     *  @return_type:void
     *  @author zhangtian  
     *  @param key
     */
    public void invalidate(K key) {
    	this.getCache().invalidate(key);	
    }
    
    /**
     *  invalidateAll:(废弃所有缓存). 
     *  @return_type:void
     *  @author zhangtian
     */
    public void invalidateAll() {
    	this.getCache().invalidateAll();
    }
    
    /**
     *  invalidateAll:(废弃keys对应的缓存). 
     *  @return_type:void
     *  @author zhangtian  
     *  @param keys
     */
    public void invalidateAll(Iterable<K> keys) {
    	this.getCache().invalidateAll(keys);
    }
    
    /**
     *  refresh:(刷新Key对应的缓存). 
     *  @return_type:void
     *  @author zhangtian  
     *  @param key
     */
    public void refresh(K key) {
    	this.getCache().refresh(key);
    }
    
    /** 
     * 从缓存中获取数据（第一次自动调用fetchData从外部获取数据），并处理异常 
     * @param key 
     * @return Value 
     * @throws ExecutionException  
     */  
    protected V getValue(K key) throws ExecutionException { 
        V result = getCache().get(key);  
		if(getCache().size() > highestSize){  
		    highestSize = getCache().size();  
		    highestTime = new Date();  
		}
    return result;  
    }
    
    /**
     *  getAllValue:(获取多个key的缓存，并返回不可变map对象). 
     *  @return_type:ImmutableMap<K,V>
     *  @author zhangtian  
     *  @param keys
     *  @return
     *  @throws ExecutionException
     */
    protected ImmutableMap<K, V> getAllValue(Iterable<K> keys) throws ExecutionException {  
    	ImmutableMap<K, V> result = getCache().getAll(keys) ;
        if(getCache().size() > highestSize){  
            highestSize = getCache().size();  
            highestTime = new Date();  
        }  
        return result;  
    }
    
    /**
     * 构造CacheBuilder
     * @return
     */
    private CacheBuilder<Object, Object> getCacheBuilder() {
    	CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder() ;
    	if(guavaCacheProperties.isRefreshAfterWrite()) {
    		switch (guavaCacheProperties.getRefreshTimeType().toLowerCase()) {
			case CommonConstant.TIMETYPE_SECOND:
				cacheBuilder.refreshAfterWrite(guavaCacheProperties.getRefreshTime(), TimeUnit.SECONDS) ;
				break;
			case CommonConstant.TIMETYPE_MINUTE:
				cacheBuilder.refreshAfterWrite(guavaCacheProperties.getRefreshTime(), TimeUnit.MINUTES) ;
				break;
			case CommonConstant.TIMETYPE_HOUR:
				cacheBuilder.refreshAfterWrite(guavaCacheProperties.getExpireTime(), TimeUnit.HOURS) ;
				break;
			default:
				break;
			}
    	}
    	
    	if(guavaCacheProperties.isExpireAfterWrite()) {
    		// -1表示永不失效
    		if(guavaCacheProperties.getExpireTime() != -1) {
    			switch (guavaCacheProperties.getExpireTimeType().toLowerCase()) {
    			case CommonConstant.TIMETYPE_SECOND:
    				cacheBuilder.expireAfterWrite(guavaCacheProperties.getExpireTime(), TimeUnit.SECONDS) ;
    				break;
    			case CommonConstant.TIMETYPE_MINUTE:
    				cacheBuilder.expireAfterWrite(guavaCacheProperties.getExpireTime(), TimeUnit.MINUTES) ;
    				break;
    			case CommonConstant.TIMETYPE_HOUR:
    				cacheBuilder.expireAfterWrite(guavaCacheProperties.getExpireTime(), TimeUnit.HOURS) ;
    				break;
    			case CommonConstant.TIMETYPE_DAY:
    				cacheBuilder.expireAfterWrite(guavaCacheProperties.getExpireTime(), TimeUnit.DAYS) ;
    				break;
    			default:
    				break;
    			}
    		} 
    	}
    	
    	if(guavaCacheProperties.isExpireAfterAccess()) {
    		if(guavaCacheProperties.getExpireTime() != -1) {
    			switch (guavaCacheProperties.getExpireTimeType().toLowerCase()) {
    			case CommonConstant.TIMETYPE_SECOND:
    				cacheBuilder.expireAfterAccess(guavaCacheProperties.getExpireTime(), TimeUnit.SECONDS) ;
    				break;
    			case CommonConstant.TIMETYPE_MINUTE:
    				cacheBuilder.expireAfterAccess(guavaCacheProperties.getExpireTime(), TimeUnit.MINUTES) ;
    				break;
    			case CommonConstant.TIMETYPE_HOUR:
    				cacheBuilder.expireAfterAccess(guavaCacheProperties.getExpireTime(), TimeUnit.HOURS) ;
    				break;
    			case CommonConstant.TIMETYPE_DAY:
    				cacheBuilder.expireAfterAccess(guavaCacheProperties.getExpireTime(), TimeUnit.DAYS) ;
    				break;
    			default:
    				break;
    			}
    		}
    	}
    	
    	if(guavaCacheProperties.isAutoInvalidate()) {
    		switch (guavaCacheProperties.getValidateType()) {
			case CommonConstant.INVIDATE_KEYS:
				cacheBuilder.weakKeys() ;
				break;
			case CommonConstant.INVIDATE_VALUES:
				cacheBuilder.weakValues() ;		
				break;
			case CommonConstant.INVIDATE_SOFT:
				cacheBuilder.softValues() ;
				break;
			default:
				break;
			}
    	}
    	
    	if(guavaCacheProperties.isTicker()) {
    		cacheBuilder.ticker(Ticker.systemTicker()) ;
    	}
    	
    	cacheBuilder.initialCapacity(guavaCacheProperties.getInitialCapacity()) ;
    	cacheBuilder.maximumSize(guavaCacheProperties.getMaximumSize()) ;
    	cacheBuilder.concurrencyLevel(guavaCacheProperties.getConcurrencyLevel()) ;
    	
    	if(guavaCacheProperties.isStats()) {
    		cacheBuilder.recordStats() ;
    	}
    	
    	return cacheBuilder ;
    }

	public Date getResetTime() {
		return resetTime;
	}

	public long getHighestSize() {
		return highestSize;
	}

	public Date getHighestTime() {
		return highestTime;
	}
}
