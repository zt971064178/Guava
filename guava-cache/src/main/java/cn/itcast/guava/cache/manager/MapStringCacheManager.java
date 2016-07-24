package cn.itcast.guava.cache.manager;

import com.google.common.cache.CacheLoader.InvalidCacheLoadException;

import cn.itcast.guava.cache.GuavaAbstractLoadingCache;
import cn.itcast.guava.cache.ILocalCache;

/**
 * @author zhangtian
 * MapString类型数据存储
 */
public class MapStringCacheManager extends GuavaAbstractLoadingCache<String, String> implements ILocalCache<String, String> {
	
	private MapStringCacheManager() {
		
	}
	
	// 静态内部类创建单例  线程安全
	private static class SingletonHolder {
		private final static MapStringCacheManager INSTANCE = new MapStringCacheManager();
	}
	
	public static final MapStringCacheManager getInstance() {
		return MapStringCacheManager.SingletonHolder.INSTANCE ;
	}

	@Override
	protected String fetchData(String key) {
		System.out.println("缓存数据不存在，模拟数据库获取空值");
		return null ;
	}

	@Override
	public String get(String key) {
		try {  
			return getValue(key);  
        } catch (InvalidCacheLoadException e) { 
            return null;  
        } catch(Exception e) {
        	e.printStackTrace();
            return null;  
        }
	}
}
