import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * <p>
 *
 * </p>
 *
 * @author: 刘英豪
 * @Date: 2022/7/12 14:28
 * @modifyrecord：
 */
public class test {
    public static void main(String[] args) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ");
//
//        Calendar cal2 = Calendar.getInstance();
//        cal2.add(Calendar.DATE, -2);
//        String beforeYesterday = simpleDateFormat.format(cal2.getTime());
//
//        System.out.println(("2022-07-10").equals(beforeYesterday));
        //创建一个包含十个随机数的数
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
//打印排序前的数组

        System.out.println("排序前的数组为：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
//调用排序方法
        bubbleSort(arr);
//打印排序后的数组
        System.out.println("排序后的数组为：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    //冒泡排序
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
