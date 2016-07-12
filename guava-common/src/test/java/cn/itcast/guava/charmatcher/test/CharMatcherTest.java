package cn.itcast.guava.charmatcher.test;

import org.junit.Test;

import com.google.common.base.CharMatcher;

/*
 * 使用CharMatcher类
 */
public class CharMatcherTest {
	// 替换多个连续的空格为单个空格
	@Test
	public void testReplaceMultiBlank() {
		String tabsAndSpaces = "String  with      spaces     and tabs";
		//将多个连续的空格替换为一个
		String scrubbed = CharMatcher.WHITESPACE.collapseFrom(tabsAndSpaces,' ');
		System.out.println(scrubbed); //String with spaces and tabs
	}
	
	// 将头尾的空格去掉
	@Test
	public void testStartEndBlank() {
		String tabsAndSpaces = "    String  with     spaces     and tabs";
		String scrubbed = CharMatcher.WHITESPACE.trimAndCollapseFrom(tabsAndSpaces, ' ');
		System.out.println(scrubbed); //String with spaces and tabs
	}
	
	// 保留制定字符，如数字，字母等
	@Test
	public void testCharNum() {
		String lettersAndNumbers = "foo989yxbar234";
		String retained = CharMatcher.JAVA_DIGIT.retainFrom(lettersAndNumbers); //保留数字
		System.out.println(retained); //989234
	}
	
	// 通过Or组合多个CharMatcher
	@Test
	public void testOr() {
		CharMatcher cm = CharMatcher.JAVA_DIGIT.or(CharMatcher.WHITESPACE);
		String retained = cm.retainFrom("foo9 89y xbar 234");//保留数字和空格
		System.out.println(retained); //9 89  234
	}
}
