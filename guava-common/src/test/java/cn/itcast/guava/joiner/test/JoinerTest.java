package cn.itcast.guava.joiner.test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;

public class JoinerTest {
	
	// 将字符串列表通过一个分隔符链接起来，以前的方式就是迭代，append等操作，使用Joiner可以更方便。
	// Joiner一旦创建不可变，符合不变性，因此线程安全。
 	/*
 	 * 链接字符串列表
 	 */
	@Test
	public void testJoinStringList() {
 		List<String> langs = new ArrayList<String>() ;
 		langs.add("我") ;
 		langs.add("爱北京") ;
 		langs.add("天安门") ;
 		langs.add("哈哈") ;
 		langs.add(null);
 		
 		String delimiter = "," ;
 		Joiner joiner = Joiner.on(delimiter) ;
 		// Joiner类一旦创建则不可变，满足不可变性，因此线程安全
 		
 		// 忽略null
 		String excludeNullString = joiner.skipNulls().join(langs) ;
 		// 将null替换为empty字符串
 		String replaceNullString = joiner.useForNull("empty").join(langs) ;
 		System.out.println("excludeNullString:"+excludeNullString);
 		System.out.println("replaceNullString:"+replaceNullString);
 		
 		// 不对null处理则会抛出空指针异常NullPointerException
 		String defaultNullString = joiner.join(langs) ;
 		System.out.println("defaultNullString:"+defaultNullString);
 	}
 	
 	/*
 	 * 链接多个字符串
 	 */
 	@Test
 	public void testJoinMultiString() {
 		String delimiter = "," ;
 		Joiner joiner = Joiner.on(delimiter).skipNulls() ;
 	
 		// joiner.useForNull("empty") ;// 重复定义null操作会抛出异常
 		String res = joiner.join(null, "foo", "bar") ;
 		System.out.println(res);
 	}
 	
 	/*
 	 * appendTo到实现了Appendable接口的类中
 	 */
 	@Test
 	public void testAppendString() throws IOException {
 		//  append到StringBulider
 		StringBuilder stringBuilder = new StringBuilder() ;
 		Joiner joiner = Joiner.on(",").skipNulls() ;
 		joiner.appendTo(stringBuilder, "appendTo", "StringBulider") ;
 		System.out.println(stringBuilder);
 		
 		// append到输出流
 		FileWriter fileWriter = new FileWriter("D:\\append_text.txt") ;
 		joiner.appendTo(fileWriter, "appendTo", "FileWriter") ;
 		fileWriter.close();
 	}
 	
 	/*
 	 * 通过MapJoiner链接Map对象
 	 */
 	@Test
 	public void testMapJoin() {
 		Map<String, String> map = new HashMap<String, String>() ;
 		map.put("key1", "value1") ;
 		map.put("key2", "value2") ;
 		map.put("key3", "value3") ;
 		MapJoiner mapJoiner = Joiner.on(",").withKeyValueSeparator("=") ;
 		String str = mapJoiner.join(map) ;
 		System.out.println(str);// 结果如:key3=value3,key2=value2,key1=value1
 	}
}
