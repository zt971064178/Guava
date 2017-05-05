package cn.itcast.guava.token.limit;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 令牌桶限流
 * http://www.cnblogs.com/exceptioneye/p/4783904.html
 * Created by zhangtian on 2017/5/5.
 */
public class TestTokenBucket {
    @Test
    public void testTokenBucket() throws IOException, InterruptedException {
        tokenTest();
        // arrayTest() ;
    }

    private static void arrayTest() {
        ArrayBlockingQueue<Integer> tokenQueue = new ArrayBlockingQueue<Integer>(10);
        tokenQueue.offer(1);
        tokenQueue.offer(1);
        tokenQueue.offer(1);
        System.out.println(tokenQueue.size());
        System.out.println(tokenQueue.remainingCapacity());
    }

    private void tokenTest() throws InterruptedException, IOException{
        TokenBucket tokenBucket = TokenBucket
                .newBuilder()
                .avgFlowRate(25)
                .maxFlowRate(30)
                .build() ;

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\zhangtian\\Desktop\\sss.txt"))) ;
        String data = "xxxx" ; // 四个字节

        for (int i = 1; i <= 1000; i++) {
            Random random = new Random() ;
            int i1 = random.nextInt(10) ;
            boolean tokens = tokenBucket.getTokens(tokenBucket.stringCopy(data, i1).getBytes());

            TimeUnit.MILLISECONDS.sleep(100);
            if (tokens) {
                bufferedWriter.write("token pass --- index:" + i1);
                System.out.println("token pass --- index:" + i1);
            } else {
                bufferedWriter.write("token rejuect --- index" + i1);
                System.out.println("token rejuect --- index" + i1);
            }

            bufferedWriter.newLine();
            bufferedWriter.flush();
        }

        bufferedWriter.close();
    }
}
