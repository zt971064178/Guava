package cn.itcast.guava.cache.manager;

import cn.itcast.guava.cache.GuavaCacheManager;

/**
 * @author zhangtian
 * MapString类型数据存储
 */
public class MapStringCacheManager extends GuavaCacheManager<String, String>{
	
	private MapStringCacheManager() {
		
	}
	
	// 静态内部类创建单例  线程安全
	private static class SingletonHolder {
		private final static MapStringCacheManager INSTANCE = new MapStringCacheManager();
	}
	
	public static final MapStringCacheManager getInstance() {
		return MapStringCacheManager.SingletonHolder.INSTANCE ;
	}
}
