package gamedaydemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <p>
 *
 * </p>
 *
 * @author: 刘英豪
 * @Date: 2022/8/25 15:55
 * @modifyrecord：
 */

public class RequestTool {
    private static String exe = "/Library/Frameworks/Python.framework/Versions/3.9/bin/python3.9";
    private static String command = "/Users/liuyinghao/PycharmProjects/pythonProject/RequestTest4-ad.py";

    public static String  execute(String url, int num) throws IOException, InterruptedException {
        String [] argv = new String[]{exe, command,url,num+""};
        // 调用Runtime.getRuntime().exec()执行python文件
        Process process = Runtime.getRuntime().exec(argv);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String result = "";
        String line= "";
        while ((line = bufferedReader.readLine()) != null){
            result = result + line;
        }
        // 关闭流
        bufferedReader.close();

        //process.waitFor()是脚本的执行状态，0 正常，1 失败，2 文件不存在或路径错误  等等等
        int status = process.waitFor();

        if (status == 0) {
            System.out.println("执行成功");
        } else {
            System.out.println("执行失败:"+status);
            BufferedReader bufferedReaderError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String lineError= "";
            while ((lineError = bufferedReaderError.readLine()) != null){
                System.out.println(lineError);
            }
        }
        return result;
    }
}
