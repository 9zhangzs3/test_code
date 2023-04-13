package gamedaydemo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * </p>
 *
 * @author: 刘英豪
 * @Date: 2022/8/25 11:55
 * @modifyrecord：
 */
public class ThreadPoolFactory {
    private static final int maximumPoolSize = 100;
    private static final long keepAliveTime = 2;
    private static final TimeUnit unit = TimeUnit.SECONDS;
    private static final int QueueSize = 1;//将参数设定为固定值

    public static Map<String, ThreadPoolExecutor> threadPoolMap = new ConcurrentHashMap<>();


    public static void creatPool(String id,int corePoolSize) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                new ArrayBlockingQueue<>(QueueSize),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );//构造线程池
        threadPoolMap.put(id,executor);
    }
}
