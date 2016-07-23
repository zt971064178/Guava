package cn.itcast.guava.cache.test;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cn.itcast.guava.cache.loader.PropertyLoader;
import cn.itcast.guava.cache.properties.GuavaCacheProperties;

public class PropertiesLoaderTest {

	@Test
	public void testPropertiesLoader() {
		GuavaCacheProperties guavaCacheProperties = (GuavaCacheProperties) PropertyLoader.loadProperty(GuavaCacheProperties.class) ;
		System.out.println(JSONObject.toJSON(guavaCacheProperties));
	}
}
