package OpenApi;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description 鉴权工具测试类
 * @Author zhuJiaHao
 * @Date 2022/11/8 14:46
 * @Version 1.0
 **/
public class AuthenticationTool {

    // 客户id
    private static String clientId = "ae3ff012-59ea-4e1b-ad2b-be4e9de9181b";
    // 秘钥
    private static String secretKey = "jFVFDn3M9D8MjdmiULqa";
    // 接口ip和端口
    private static String ipAndPort = "http://api.cloudplus1.com:48100/cmp-openapi/";

    public static Map<String,Object> signedUrlTool(String token, String path, Map<String, Object> params){

        RestTemplate restTemplate = new RestTemplate();

        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);

        // token 放置在请求头中
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);//设置参数类型和编码
        headers.add("Authorization","Bearer " + token);

        // 当前时间精确到秒
        Integer currentTime = Math.toIntExact(System.currentTimeMillis() / 1000);
        // 签名
        String str = params + secretKey;
        String signature = DigestUtils.md5DigestAsHex(str.getBytes());
        // 其他参数放置请求体
        Map<String,Object> map = new HashMap<>();
        map.put("params", params);// 参数
        map.put("timestamp", currentTime); // 时间戳
        map.put("signature", signature); // 签名
        HttpEntity<Map<String,Object>> request = new HttpEntity<>(map, headers);
        ResponseEntity<Map> mapResponseEntity = null;

        mapResponseEntity = restTemplate.postForEntity(ipAndPort + path, request, Map.class);

        return mapResponseEntity.getBody();
    }
    public static String encrypt(String str){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5"); md.update(str.getBytes());
            byte[]byteDigest = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset]; if (i < 0)
                    i += 256; if (i < 16)
                    buf.append("0"); buf.append(Integer.toHexString(i));
            }
            return buf.toString();

        } catch (NoSuchAlgorithmException e) { e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) throws ParseException {

//        String encrypt = AuthenticationTool.encrypt("X7dCGu7Bragygrk7nJQi/tuyou");
//        System.out.println(encrypt);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
//        Date date = new Date();
//
//        System.out.println("当前时间是：" + dateFormat.format(date));
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date); // 设置为当前时间
//        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
//        date = calendar.getTime();
//
//        System.out.println("上一个月的时间： " + dateFormat.format(date));
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
//        Date date = formatter.parse("20230201");
//
//        System.out.println(date);
//        for (int i = 0; i < 10; i++) {
////            try {
//                int a = 1/0;
////            }catch (Exception e) {
////                e.printStackTrace();
////            }
//        }
//        Map<String, Object> paramToken = new HashMap<>();
//        paramToken.put("clientId", clientId);
//        Map<String, Object> body = signedUrlTool(null, "auth", paramToken);
//
//        if ("00000".equals(body.get("code"))) {
//            Object data = body.get("data");
//            System.out.println(data);
//        } else {
//            System.out.println((String) body.get("info"));
//        }

//        Map<String, Object> paramUrl = new HashMap<>();
//        paramUrl.put("month", "202211");
//        paramUrl.put("rootAccountId", "01D6A5-923F95-C0B35F");
//        Map<String, Object> objectMap = signedUrlTool("eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl9jbGllbnRfa2V5IjoiYmU0ZTI0ZGUtZWQ2ZS00MmExLTgyMWUtNmI3ZTNjYzA2MmFhIiwiZXhwIjoxNjY4NDgzMTI1LCJpYXQiOjE2Njg0Nzk1MjV9.ZpFnfIEM3BgOnR9kYIXF5uSyqabArOQpwX63_wwbxwKyPypue2HSmQO1KJO9Aq6WV77k4TaC-uv4r0dlm_dOyw", "/cost/report/byregion", paramUrl);
//        if ("00000".equals(objectMap.get("code"))) {
//            Object data = objectMap.get("data");
//            System.out.println(data);
//        } else {
//            System.out.println((String) objectMap.get("info"));
//        }
    }
}


