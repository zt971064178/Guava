package cn.itcast.guava.cache.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.itcast.guava.cache.manager.MapObjectManager;
import cn.itcast.guava.cache.manager.MapStringManager;

public class GuavaMapTest {
	@Test
	public void testMapString() {
		MapStringManager mapStringManager = MapStringManager.getInstance() ;
		mapStringManager.putCache("name", "zhangtian");
		
		String name = mapStringManager.get("name") ;
		System.out.println(name);
		
		Map<String, String> map = new HashMap<String, String>() ;
		map.put("a", "aa") ;
		map.put("b", "bb") ;
		map.put("c", "cc") ;
		map.put("d", "dd") ;
		mapStringManager.batchPutCache(map);
		
		System.out.println(mapStringManager.get("b"));
	}
	
	@Test
	public void testMapObject() {
		MapObjectManager mapObjectManager = MapObjectManager.getInstance() ;
		mapObjectManager.putCache("hello", "hello");
		
		Object name = mapObjectManager.get("hello") ;
		System.out.println(name);
		
		Map<String, Object> map = new HashMap<String, Object>() ;
		map.put("a", "aa") ;
		map.put("b", "bb") ;
		map.put("c", "cc") ;
		map.put("d", "dd") ;
		mapObjectManager.batchPutCache(map);
		
		System.out.println(mapObjectManager.get("d"));
	}
}
