package cn.itcast.guava.cache.manager;

import cn.itcast.guava.cache.GuavaAbstractLoadingCache;
import cn.itcast.guava.cache.ILocalCache;
/**
 * @author zhangtian
 * MapObject类型数据存储
 */
public class MapObjectManager extends GuavaAbstractLoadingCache<String, Object> implements ILocalCache<String, Object>  {
	private MapObjectManager() {
		
	}
	
	// 静态内部类创建单例  线程安全
	private static class SingletonHolder {
		private final static MapObjectManager INSTANCE = new MapObjectManager();
	}
	
	public static final MapObjectManager getInstance() {
		return MapObjectManager.SingletonHolder.INSTANCE ;
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
        } catch (Exception e) { 
        	e.printStackTrace();
            return null;  
        } 
	}
}
