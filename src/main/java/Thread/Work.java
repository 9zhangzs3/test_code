package Thread;

import java.util.concurrent.Callable;

/**
 * <p>
 *
 * </p>
 *
 * @author: 刘英豪
 * @Date: 2022/7/27 13:59
 * @modifyrecord：
 */
public class Work implements Callable {
    private int num;
    public Work(int num){
        this.num = num;
    }
    @Override
    public String call() throws Exception {
        String result = "《"+num+"》号线程已经执行了,<"+Thread.currentThread().getName()+">";
        System.out.println(result);
        return result;
    }
}
