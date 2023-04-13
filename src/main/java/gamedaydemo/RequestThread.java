package gamedaydemo;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * <p>
 *
 * </p>
 *
 * @author: 刘英豪
 * @Date: 2022/8/25 15:53
 * @modifyrecord：
 */
public class RequestThread implements Runnable {
    static Logger log = Logger.getLogger(RequestThread.class);

    private String name;
    private String id;
    private int num;
    private CyclicBarrier cyclicBarrier;

    public RequestThread(String id,String name,int num,CyclicBarrier cyclicBarrier){
        this.id = id;
        this.name = name;
        this.num = num;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run(){
        int frnshu = 0;
        //查询对应参数选手的url
        String url = "http://10.100.2.88:18080/gcmp/#!/GCP/home";
        try {
            log.info(name+"准备好了==========");
            int await = cyclicBarrier.await();
            //执行脚本
            log.info(await+"发送时间为:"+System.currentTimeMillis());
            String a = RequestTool.execute(url,num);

            log.info(name+"请求执行完成了==========响应时间为："+a);
            //执行加减分操作
            frnshu++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
