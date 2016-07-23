package cn.itcast.guava.cache.example.service;

import cn.itcast.guava.cache.example.domain.BaseArea;
import cn.itcast.guava.cache.example.manager.LCAreaCacheManager;

/**
 * ClassName: BaseUserService  
 * (调用缓存)
 * @author zhangtian  
 * @version
 */
public class BaseUserService {
	private static final LCAreaCacheManager lcAreaCacheManager = LCAreaCacheManager.getInstance() ;
	
	public BaseArea getAreaById(int areaId) {  
        return lcAreaCacheManager.get(areaId);  
    }  
	
	public void putCache(Integer key, BaseArea baseArea) {
		lcAreaCacheManager.putCache(key, baseArea);
	}
}
