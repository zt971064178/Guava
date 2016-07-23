package cn.itcast.guava.cache.manager;

import cn.itcast.guava.cache.GuavaAbstractLoadingCache;
import cn.itcast.guava.cache.ILocalCache;

/**
 * @author zhangtian
 * MapString类型数据存储
 */
public class MapStringManager extends GuavaAbstractLoadingCache<String, String> implements ILocalCache<String, String> {
	
	private MapStringManager() {
		
	}
	
	// 静态内部类创建单例  线程安全
	private static class SingletonHolder {
		private final static MapStringManager INSTANCE = new MapStringManager();
	}
	
	public static final MapStringManager getInstance() {
		return MapStringManager.SingletonHolder.INSTANCE ;
	}

	@Override
	protected String fetchData(String key) {
		System.out.println("缓存数据不存在，模拟数据库获取空值");
		return "" ;
	}

	@Override
	public String get(String key) {
		try {  
			return getValue(key);  
        } catch (Exception e) { 
        	e.printStackTrace();
            return null;  
        } 
	}
}
