package cn.itcast.guava.token.limit;

/**
 * Created by zhangtian on 2017/5/5.
 */
public interface LifeCycle {
    public void start() ;

    public void stop() ;

    public boolean isStarted() ;
}
