package cn.itcast.guava.cache.utils;

import java.io.InputStream;
import java.util.Properties;

import cn.itcast.guava.cache.properties.GuavaCacheProperties;

/**
 * 加载属性文件
 */
public class PropertiesUtils {
	public static Properties loadProperties(String propertiesName) {
		Properties prop = new Properties();
		InputStream stream = null;
		try {
			stream = PropertiesUtils.class.getClassLoader().getResourceAsStream("/"+propertiesName) ;
			if(stream == null) {
				stream = GuavaCacheProperties.class.getResourceAsStream(propertiesName) ;
			}
			prop.load(stream);
			stream.close();
			return prop;
		} catch (Exception e) {
			return null;
		}
	}
}
