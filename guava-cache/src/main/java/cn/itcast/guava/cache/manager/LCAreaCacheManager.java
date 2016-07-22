package cn.itcast.guava.cache.manager;

import cn.itcast.guava.cache.GuavaAbstractLoadingCache;
import cn.itcast.guava.cache.ILocalCache;
import cn.itcast.guava.cache.domain.BaseArea;

/**
 * ClassName: LCAreaCacheManager  
 * (区域缓存数据)
 * @author zhangtian  
 * @version
 */
public class LCAreaCacheManager extends GuavaAbstractLoadingCache<Integer, BaseArea> implements ILocalCache<Integer, BaseArea> {

	public LCAreaCacheManager() {
		setMaximumSize(3000); //最大缓存条数  
	}
	
	@Override
	public BaseArea get(Integer key) {
		try {  
			return getValue(key);  
        } catch (Exception e) { 
        	e.printStackTrace();
            return null;  
        }  
	}
	
	@Override
	public void putCache(Integer key, BaseArea value) {
		super.putCache(key, value);
	}

	@Override
	protected BaseArea fetchData(Integer key) {
		System.out.println("调用数据库获取数据Key:"+key);
		//测试专用，实际项目使用areaDao从数据库中获取数据  
        BaseArea a = new BaseArea();  
        a.setCode(key);  
        a.setId(key);  
        a.setName("地区："+key);  
        a.setPinyin("pinyin:"+key);  
        a.setType(0); 
		return a;
	}
}
