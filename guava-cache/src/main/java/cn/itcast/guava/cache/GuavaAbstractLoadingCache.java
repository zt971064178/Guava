package cn.itcast.guava.cache;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;

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
	//用于初始化cache的参数及其缺省值 
	// 最大缓存条数，子类在构造方法中调用setMaximumSize(int size)来更改  
	private int maximumSize = 1000 ;
	// 数据存在时长，子类在构造方法中调用setExpireAfterWriteDuration(int duration)来更改  
	private int expireAfterWriteDuration = 60;      
	// 时间单位（分钟）  
	private TimeUnit timeUnit = TimeUnit.MINUTES;   
	private Date resetTime;     // Cache初始化或被重置的时间  
    private long highestSize=0; // 历史最高记录数  
    private Date highestTime;   // 创造历史记录的时间  
    private LoadingCache<K, V> cache ;  
	
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
                	cache = CacheBuilder.newBuilder().maximumSize(maximumSize)      //缓存数据的最大条目，也可以使用.maximumWeight(weight)代替  
                            .expireAfterWrite(expireAfterWriteDuration, timeUnit)   //数据被创建多久后被移除  
                            .recordStats()                                          //启用统计  
                            .build(new CacheLoader<K, V>() {  
                                @Override  
                                public V load(K key) throws Exception { 
                                    return fetchData(key);  
                                }  
                            });  
                    this.resetTime = new Date();  
                    this.highestTime = new Date();  
                    System.out.println("本地化缓存初始化成功......");
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
    	this.cache.put(key, value);
    }
    
    /**
     *  batchPutCache:(批量插入缓存). 
     *  @return_type:void
     *  @author zhangtian  
     *  @param map
     */
    public void batchPutCache(Map<K, V> map) {
    	this.cache.putAll(map);
    }
    
    /**
     *  invalidate:(废弃缓存). 
     *  @return_type:void
     *  @author zhangtian  
     *  @param key
     */
    public void invalidate(K key) {
    	this.cache.invalidate(key);	
    }
    
    /**
     *  invalidateAll:(废弃所有缓存). 
     *  @return_type:void
     *  @author zhangtian
     */
    public void invalidateAll() {
    	this.cache.invalidateAll();
    }
    
    /**
     *  invalidateAll:(废弃keys对应的缓存). 
     *  @return_type:void
     *  @author zhangtian  
     *  @param keys
     */
    public void invalidateAll(Iterable<K> keys) {
    	this.cache.invalidateAll(keys);
    }
    
    /**
     *  refresh:(刷新Key对应的缓存). 
     *  @return_type:void
     *  @author zhangtian  
     *  @param key
     */
    public void refresh(K key) {
    	this.cache.refresh(key);
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
  
        System.out.println("从缓存中获取数据Key:"+result.toString());
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

	public int getMaximumSize() {
		return maximumSize;
	}

	public void setMaximumSize(int maximumSize) {
		this.maximumSize = maximumSize;
	}

	public int getExpireAfterWriteDuration() {
		return expireAfterWriteDuration;
	}

	public void setExpireAfterWriteDuration(int expireAfterWriteDuration) {
		this.expireAfterWriteDuration = expireAfterWriteDuration;
	}

	public Date getResetTime() {
		return resetTime;
	}

	public void setResetTime(Date resetTime) {
		this.resetTime = resetTime;
	}

	public long getHighestSize() {
		return highestSize;
	}

	public void setHighestSize(long highestSize) {
		this.highestSize = highestSize;
	}

	public Date getHighestTime() {
		return highestTime;
	}

	public void setHighestTime(Date highestTime) {
		this.highestTime = highestTime;
	}
    
}
