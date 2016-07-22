package cn.itcast.guava.cache.service;

import cn.itcast.guava.cache.domain.BaseArea;
import cn.itcast.guava.cache.manager.LCAreaCacheManager;

/**
 * ClassName: BaseUserService  
 * (调用缓存)
 * @author zhangtian  
 * @version
 */
public class BaseUserService {
	private static final LCAreaCacheManager lcAreaCacheManager = new LCAreaCacheManager() ;
	
	public BaseArea getAreaById(int areaId) {  
        return lcAreaCacheManager.get(areaId);  
    }  
	
	public void putCache(Integer key, BaseArea baseArea) {
		lcAreaCacheManager.putCache(key, baseArea);
	}
}
