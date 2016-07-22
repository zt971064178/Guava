package cn.itcast.guava.cache;
/**
 * ClassName: ILocalCache  
 * (本地缓存接口)
 * @author zhangtian  
 * @version
 */
public interface ILocalCache<K, V> {
	/**
	 *  get:(从缓存中获取数据). 
	 *  @return_type:V
	 *  @author zhangtian  
	 *  @param key
	 *  @return
	 */
	public V get(K key) ;
}
