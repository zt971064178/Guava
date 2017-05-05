package cn.itcast.guava.token.limit;

/**
 * Created by zhangtian on 2017/5/5.
 */
public class TokenProducer implements Runnable{

    private int avgFlowRate;
    private TokenBucket tokenBucket;

    public TokenProducer(int avgFlowRate, TokenBucket tokenBucket) {
        this.avgFlowRate = avgFlowRate;
        this.tokenBucket = tokenBucket;
    }

    @Override
    public void run() {
        tokenBucket.addTokens(avgFlowRate);
    }
}
