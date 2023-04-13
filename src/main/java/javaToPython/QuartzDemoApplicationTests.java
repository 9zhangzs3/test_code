package javaToPython;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
class QuartzDemoApplicationTests {
    public static void main(String[] args) throws InterruptedException, IOException {

        String aws_secret_access_key = "A3l9XZFDbjrOQZB6yxk4cWRL4C1lIbWYSdQmT9lu";
        String aws_access_key_id = "AKIAS7C25MRX6QI66VVE";
        // python执行命令，有的可能设置的是python3,就需要改为python3访问
        String exe = "/Library/Frameworks/Python.framework/Versions/3.9/bin/python3.9";
//        String exe = "python3";
        // python文件地址
//        String command="/Users/liuyinghao/Downloads/ng-file-upload-master/test_code/src/main/java/javaToPython/AWSTest1.py";
        String command="/Users/liuyinghao/PycharmProjects/pythonProject/AWSTest1.py";
        // 参数
        String [] argv = new String[]{exe, command,aws_access_key_id, aws_secret_access_key};
        // 调用Runtime.getRuntime().exec()执行python文件
        Process process = Runtime.getRuntime().exec(argv);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line= "";
        while ((line = bufferedReader.readLine()) != null){
            System.out.println(line);
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
    }

}
