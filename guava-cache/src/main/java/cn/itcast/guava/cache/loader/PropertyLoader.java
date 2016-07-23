package cn.itcast.guava.cache.loader;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

import cn.itcast.guava.cache.annotation.PropertyConfigiration;
import cn.itcast.guava.cache.annotation.Value;
import cn.itcast.guava.cache.utils.PropertiesUtils;

/**
 * ClassName: PropertyLoader  
 * (属性加载器)
 * @author zhangtian  
 * @version
 */
public class PropertyLoader {
	public static Object loadProperty(Class<?> clazz) {
		try {
			Object object = clazz.newInstance() ;
			if(clazz.isAnnotationPresent(PropertyConfigiration.class)) {
				// 获取类注解
				PropertyConfigiration propertyConfigiration = clazz.getAnnotation(PropertyConfigiration.class) ;
				String location = propertyConfigiration.location() ;
				if(location.equals("")) {
					throw new RuntimeException("guava-cache.properties配置文件无法找到......") ;
				} else {
					Properties properties = PropertiesUtils.loadProperties(location) ;
					if(properties == null)
						return null ;
						
					Field[] fields = clazz.getDeclaredFields() ;
					for(Field field : fields) {
						if(field.isAnnotationPresent(Value.class)){
							Value value = field.getAnnotation(Value.class) ;
							field.setAccessible(true);
							String v = value.value() ;
							String propertyVallue = properties.getProperty(v.substring(2, v.length()-1)) ;
							if(v.startsWith("${") && v.endsWith("}")) {
								if(field.getType().isPrimitive()) {
									if(field.getType().isAssignableFrom(int.class)) {
										setProperty(object, field.getName(), Integer.parseInt(propertyVallue));
									} else if(field.getType().isAssignableFrom(boolean.class)) {
										setProperty(object, field.getName(), Boolean.parseBoolean(propertyVallue));
									} else if(field.getType().isAssignableFrom(long.class)) {
										setProperty(object, field.getName(), Long.parseLong(propertyVallue));
									} else {
										throw new RuntimeException("类型转换异常......") ;
									}
								} else {
									if(field.getType().isAssignableFrom(String.class)) {
										setProperty(object, field.getName(), String.valueOf(propertyVallue));
									} else {
										throw new RuntimeException("类型转换异常......") ;
									}
								}
							} else {
								throw new RuntimeException("数据格式错误......") ;
							}
						}
					}
				}
				return object ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * JavaBean内省处理Bean
	 * @param bean
	 * @param fieldName
	 * @param value
	 * @throws Exception
	 */
	private static void setProperty(Object bean, String fieldName, Object value) throws Exception {
        // 创建属性描述器
        PropertyDescriptor descriptor = new PropertyDescriptor(fieldName, bean.getClass());
        // 获得 写方法
        Method writeMethod = descriptor.getWriteMethod();
        // 调用 写方法        
        writeMethod.invoke(bean, value);
    }
}
