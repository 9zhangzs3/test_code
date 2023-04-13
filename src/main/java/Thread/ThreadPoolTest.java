package Thread;

import java.util.concurrent.*;

/**
 * <p>
 *
 * </p>
 *
 * @author: 刘英豪
 * @Date: 2022/7/27 10:51
 * @modifyrecord：
 */
public class ThreadPoolTest{
    private static final int corePoolSize = 4;
    private static final int maximumPoolSize = 6;
    private static final long keepAliveTime = 2;
    private static final TimeUnit unit = TimeUnit.SECONDS;
    private static final int QueueSize = 5;//将参数设定为固定值

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                   corePoolSize,
                   maximumPoolSize,
                   keepAliveTime,
                   unit,
                   new ArrayBlockingQueue<>(QueueSize),
                   new ThreadPoolExecutor.CallerRunsPolicy()
        );//构造线程池
        for(int i = 0; i < 10; i++){
            Work w = new Work(i);
            Future submit = executor.submit(w);
            System.out.println(submit.get().toString()+"============");
        }//传入10个任务
        executor.shutdown();//关闭线程池
        while(!executor.isTerminated()){
        }
        System.out.println("finish");
    }
}
