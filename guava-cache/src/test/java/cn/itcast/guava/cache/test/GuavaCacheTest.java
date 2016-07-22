package cn.itcast.guava.cache.test;

import org.junit.Test;

import cn.itcast.guava.cache.service.BaseUserService;

/**
 * ClassName: GuavaCacheTest  
 * (缓存单元测试，测试Guava的缓存使用)
 * @author zhangtian  
 * @version
 */
public class GuavaCacheTest {
	@Test
	public void testCache() throws InterruptedException {
		BaseUserService baseUserService = new BaseUserService() ;
		baseUserService.getAreaById(111) ;
		Thread.sleep(2000);
		baseUserService.getAreaById(111) ;
		Thread.sleep(2000);
		baseUserService.getAreaById(111) ;
		Thread.sleep(2000);
		baseUserService.getAreaById(111) ;
		Thread.sleep(2000);
		baseUserService.getAreaById(111) ;
	}
}
