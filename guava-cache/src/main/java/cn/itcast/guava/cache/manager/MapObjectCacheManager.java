package cn.itcast.guava.cache.manager;

import cn.itcast.guava.cache.GuavaCacheManager;
/**
 * @author zhangtian
 * MapObject类型数据存储
 */
public class MapObjectCacheManager extends GuavaCacheManager<String, Object> {
	private MapObjectCacheManager() {
		
	}
	
	// 静态内部类创建单例  线程安全
	private static class SingletonHolder {
		private final static MapObjectCacheManager INSTANCE = new MapObjectCacheManager();
	}
	
	public static final MapObjectCacheManager getInstance() {
		return MapObjectCacheManager.SingletonHolder.INSTANCE ;
	}
}
