package cn.itcast.guava.cache.utils;
/**
 * ClassName: SplitCharacter  
 * (分隔符)
 * @author zhangtian  
 * @version
 */
public enum SplitCharacter {
	SPLIT_COMMA(",", "逗号") ,
	SPLIT_UNDERLINE("_","下划线") ,
	SPLIT_LINE("-", "横向分割") ,
	SPLIT_COLON(":", "冒号") ,
	SPLIT_DOUBLE("∷","四桶特殊符号") ,
	SPLIT_TILDE("~", "波浪号") ,
	SPLIT_BLANK(" ", "空格") ,
	SPLIT_SPECIAL_A("#", "#号") ,
	SPLIT_SPECIAL_B("&", "&号") ,
	SPLIT_SPECIAL_C("@", "@号") ,
	SPLIT_SPECIAL_D("*", "*号") ,
	SPLIT_SPECIAL_E("◎◎", "◎◎号") ;
	
	public String key ;
	public String value ;
	
	private SplitCharacter(String key, String value) {
		this.key = key ;
		this.value = value ;
	}
}
