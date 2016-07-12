package cn.itcast.guava.splitter.test;

import java.util.Map;

import org.junit.Test;

import com.google.common.base.Splitter;
import com.google.common.base.Splitter.MapSplitter;
/*
  Splitter功能与Joiner相反，其对字符串进行分割操作。
 */
public class SplitterTest {
	// 分割字符串，返回Iterable<String>对象
	@Test
	public void testSplitterString() {
		String str = "try,do,you,best" ;
		Splitter splitter = Splitter.on(",").trimResults() ;// 用逗号分割且去掉每个字符串周围的空格
		// splitter.trimResults() ;// 样是不会去掉各个元素空格的, 它仅返回一个新的Splitter
		Iterable<String> res = splitter.split(str) ;
		System.out.println(res);
	}
	
	// 使用MapSplitter分割字符串，返回Map<String, String>对象
	@Test
	public void testMapSplitter() {
		String str = "key3=value3,key2=value2,key1=value1" ;
		MapSplitter mapSplitter = Splitter.on(",").withKeyValueSeparator("=") ;
		Map<String, String> map = mapSplitter.split(str) ;
		System.out.println(map);
	}
}
