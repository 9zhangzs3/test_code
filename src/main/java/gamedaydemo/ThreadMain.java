package gamedaydemo;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 *
 * </p>
 *
 * @author: 刘英豪
 * @Date: 2022/8/25 15:53
 * @modifyrecord：
 */
public class ThreadMain {
    static Logger log = Logger.getLogger(ThreadMain.class);

    public static void main(String[] args) {

        //这里的数据应该是从数据库读出来的比赛数据  可以将数据放到redis中
        int num = 10;
        int pnum = 100;
        List<Map<String,String>> list = new ArrayList();
        Map<String,String> student1 = new HashMap<>();
        student1.put("id","000001");
        student1.put("name","张三");
        list.add(student1);
        Map<String,String> student2 = new HashMap<>();
        student2.put("id","000002");
        student2.put("name","李四");
        list.add(student2);
        Map<String,String> student3 = new HashMap<>();
        student3.put("id","000003");
        student3.put("name","王五");
        list.add(student3);

        //根据比赛ID获取线程池
        ThreadPoolExecutor executor = ThreadPoolFactory.threadPoolMap.get("test");
        CyclicBarrier cyclicBarrier = new CyclicBarrier(pnum,()->{
            log.info("放行!!!");
        });

//        list.forEach(student ->{
//            String id = student.get("id");
//            String name = student.get("name");
//            RequestThread requestThread = new RequestThread(id,name,num,cyclicBarrier);
//            executor.execute(requestThread);
//        });
        for(int i = 0;i<pnum;i++){
            RequestThread requestThread = new RequestThread(i+"id",i+"-name",num,cyclicBarrier);
            executor.execute(requestThread);
//            Thread t= new Thread(new RequestThread(i+"id",i+"-name",num,cyclicBarrier));
//            t.start();
        }
        executor.shutdown();//关闭线程池
        while(!executor.isTerminated()){
        }
        System.out.println("finish");
    }
}
