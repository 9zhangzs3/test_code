package aws.price;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.Base64;

/**
 * <p>
 *
 * </p>
 *
 * @author: 刘英豪
 * @Date: 2022/7/28 16:41
 * @modifyrecord：
 */
public class price {
    /**
     * 用于建立十六进制字符的输出的小写字符数组
     */
    private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /**
     * 用于建立十六进制字符的输出的大写字符数组
     */
    private static final char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    public static void main(String[] args) throws Exception {
//        String key = "wJalrXUtnFEMI/K7MDENG+bPxRfiCYEXAMPLEKEY";
//        String dateStamp = "20120215";
//        String regionName = "us-east-1";
//        String serviceName = "iam";

        String key = "A3l9XZFDbjrOQZB6yxk4cWRL4C1lIbWYSdQmT9lu";
        String dateStamp = "20220729";
        String regionName = "us-east-1";
        String serviceName = "price";
        byte[] signatureKey = getSignatureKey(key, dateStamp, regionName, serviceName);

//        ByteArrayInputStream bais = new ByteArrayInputStream(signatureKey);
//        DataInputStream dis = new DataInputStream(bais);
        String string = Base64.getEncoder().encodeToString(signatureKey);
        String s = new String(encodeHex(signatureKey, DIGITS_LOWER));
        System.out.println(string);
        System.out.println(s);
    }
     static char[] encodeHex(byte[] data, char[] toDigits) {
        int l = data.length;
        char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return out;
    }
    static byte[] HmacSHA256(String data, byte[] key) throws Exception {
        String algorithm="HmacSHA256";
        Mac mac = Mac.getInstance(algorithm);
        mac.init(new SecretKeySpec(key, algorithm));
        return mac.doFinal(data.getBytes("UTF-8"));
    }

    static byte[] getSignatureKey(String key, String dateStamp, String regionName, String serviceName) throws Exception {
        byte[] kSecret = ("AWS4" + key).getBytes("UTF-8");
        byte[] kDate = HmacSHA256(dateStamp, kSecret);
        byte[] kRegion = HmacSHA256(regionName, kDate);
        byte[] kService = HmacSHA256(serviceName, kRegion);
        byte[] kSigning = HmacSHA256("aws4_request", kService);
        return kSigning;
    }
}
