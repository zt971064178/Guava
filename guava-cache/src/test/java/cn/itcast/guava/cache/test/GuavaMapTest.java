package cn.itcast.guava.cache.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

import cn.itcast.guava.cache.manager.MapObjectCacheManager;
import cn.itcast.guava.cache.manager.MapStringCacheManager;

public class GuavaMapTest {
	@Test
	public void testMapString() throws ExecutionException {
		MapStringCacheManager mapStringManager = MapStringCacheManager.getInstance() ;
		mapStringManager.putCache("name", "zhangtian");
		
		String name = mapStringManager.getValue("name") ;
		System.out.println("Validate:"+name);
		
		// mapStringManager.refresh("name");
		mapStringManager.invalidate("name");
		name = mapStringManager.getValue("name") ;
		System.out.println("Invalidate:"+name);
		
		/*Map<String, String> map = new HashMap<String, String>() ;
		map.put("a", "aa") ;
		map.put("b", "bb") ;
		map.put("c", "cc") ;
		map.put("d", "dd") ;
		mapStringManager.batchPutCache(map);
		
		System.out.println(mapStringManager.get("b"));*/
		
		// 测试从缓存中获取数据
		System.out.println("============== 缓存 =============");
		String getName = mapStringManager.getCache().get("name", new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("从数据库中获取数据。。。。。");
				return "莫言";
			}
		}) ;
		System.out.println(getName);
		System.out.println(mapStringManager.getValue("name"));
	}
	
	@Test
	public void testMapObject() {
		MapObjectCacheManager mapObjectManager = MapObjectCacheManager.getInstance() ;
		mapObjectManager.putCache("hello", "hello");
		
		Object name = mapObjectManager.getValue("hello") ;
		System.out.println(name);
		
		Map<String, Object> map = new HashMap<String, Object>() ;
		map.put("a", "aa") ;
		map.put("b", "bb") ;
		map.put("c", "cc") ;
		map.put("d", "dd") ;
		mapObjectManager.batchPutCache(map);
		
		System.out.println(mapObjectManager.getValue("d"));
	}
}
