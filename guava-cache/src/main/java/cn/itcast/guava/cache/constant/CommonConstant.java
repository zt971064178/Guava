package cn.itcast.guava.cache.constant;

public interface CommonConstant {
	// 缓存过期时间类型
	public static final String TIMETYPE_SECOND = "second" ; 
	public static final String TIMETYPE_MINUTE = "minute" ;
	public static final String TIMETYPE_HOUR = "hour" ;
	public static final String TIMETYPE_DAY = "day" ;
	
	// 缓存自动清除类型
	public static final String INVIDATE_SOFT = "soft" ;
	public static final String INVIDATE_KEYS = "keys" ;
	public static final String INVIDATE_VALUES = "values" ;
}
