package cn.itcast.guava.cache.manager;

import com.google.common.cache.CacheLoader.InvalidCacheLoadException;

import cn.itcast.guava.cache.GuavaAbstractLoadingCache;
import cn.itcast.guava.cache.ILocalCache;
/**
 * @author zhangtian
 * MapObject类型数据存储
 */
public class MapObjectCacheManager extends GuavaAbstractLoadingCache<String, Object> implements ILocalCache<String, Object>  {
	private MapObjectCacheManager() {
		
	}
	
	// 静态内部类创建单例  线程安全
	private static class SingletonHolder {
		private final static MapObjectCacheManager INSTANCE = new MapObjectCacheManager();
	}
	
	public static final MapObjectCacheManager getInstance() {
		return MapObjectCacheManager.SingletonHolder.INSTANCE ;
	}

	@Override
	protected Object fetchData(String key) {
		System.out.println("缓存数据不存在，模拟数据库获取空值");
		return "" ;
	}

	@Override
	public Object get(String key) {
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
