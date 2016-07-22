package cn.itcast.guava.cache;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;

/**
 * ClassName: GuavaCacheManager  
 * (缓存管理类)
 * @author zhangtian  
 * @version
 */
public class GuavaCacheManager {
	// LoadingCache是Cache的扩展类，具有自加载功能。
	private GuavaCacheManager() {
		
	}
	
	// 静态内部类创建单例  线程安全
	/*private static class SingletonHolder {
		private final static LoadingCache INSTANCE = new HttpClientComponent();
	}*/
	
	// CacheBuilder通过构建器模式构建Cache和LoadingCache实例
	public static LoadingCache<String, String> getLoadingCacheByCacheBuilder(RemovalListener<String, String> removalListener) {
		LoadingCache<String, String> traLoadingCache = CacheBuilder.newBuilder()
															.expireAfterAccess(5L, TimeUnit.MINUTES)// 5分钟后缓存失效
															.maximumSize(5000)// 最大缓存5000个对象
															.removalListener(removalListener)// //注册缓存对象移除监听器
															.ticker(Ticker.systemTicker())// 定义缓存对象失效的时间精度为纳秒级
															.build(new CacheLoader<String, String>(){
																@Override
																public String load(String key) throws Exception {
																	// load a new TradeAccount not exists in cache
																	return null;
																}
															}) ;
	
	
		return traLoadingCache ;
	}
	
	// 通过SoftReference对象实现自动回收
	public static LoadingCache<String, String> getLoadingCacheBySoftReference(RemovalListener<String, String> removalListener) {
		LoadingCache<String, String> traLoadingCache = CacheBuilder.newBuilder()
															.expireAfterAccess(5L, TimeUnit.MINUTES) //5分钟后缓存失效
															.softValues()// 使用SoftReference对象封装value, 使得内存不足时，自动回收
															.removalListener(removalListener) // 注册缓存对象移除监听器
															.ticker(Ticker.systemTicker())// 定义缓存对象失效的时间精度为纳秒级
															.build(new CacheLoader<String, String>(){
																@Override
																public String load(String key) throws Exception {
																	// load a new TradeAccount not exists in cache
																	return null;
																}
															}) ;
		
		return traLoadingCache ;
	}
	
	// 通过SoftReference对象实现自动回收
	public static LoadingCache<String, String> getLoadingCacheByLoadingCache(RemovalListener<String, String> removalListener) {
		LoadingCache<String, String> traLoadingCache = CacheBuilder.newBuilder()
															.concurrencyLevel(10)// 允许同时最多10个线程并发修改
															.refreshAfterWrite(5L, TimeUnit.SECONDS)// 5秒中后自动刷新
															.removalListener(removalListener)// 注册缓存对象移除监听器
															.ticker(Ticker.systemTicker())// 定义缓存对象失效的时间精度为纳秒级
															.build(new CacheLoader<String, String>(){
																@Override
																public String load(String key) throws Exception {
																	// load a new TradeAccount not exists in cache
																	return null;
																}
															}) ;
			
		return  traLoadingCache ;
	}
	
}
