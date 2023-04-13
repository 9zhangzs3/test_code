package aws.assumerole;

import cn.hutool.json.JSONObject;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * <p>
 *
 * </p>
 *
 * @author: 刘英豪
 * @Date: 2022/7/25 18:19
 * @modifyrecord：
 */
public class test3 {
    public static void main(String[] args) throws IOException {
        //2042
//        String accessKey = "AKIAS7C25MRX4XZ2N2N4";
//        String secretKey = "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";
//
//        String roleArn = "arn:aws-cn:iam::722869710786:role/test-gameday-role";
//        String externalId = "616495";

        String accessKey = "AKIATLADR5NCE3XUKV6R";
        String secretKey = "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";

        String roleArn = "arn:aws:iam::751212643473:role/AI_EDU_ASSUME_ROLE";
        String externalId = "eZ8EwAvS";
        AwsBasicCredentials basicCredentials = AwsBasicCredentials.create(accessKey, secretKey);
        StsClient stsClient = StsClient.builder().credentialsProvider(StaticCredentialsProvider.create(basicCredentials))
                .region(Region.US_EAST_1).build();

        AssumeRoleRequest assumeRoleRequest = AssumeRoleRequest.builder()
                .roleArn(roleArn)
                .externalId(externalId)
                .durationSeconds(1800)
                .roleSessionName("liuyinghao-test")
                .build();
        AssumeRoleResponse assumeRoleResponse = stsClient.assumeRole(assumeRoleRequest);

//        String issuerURL = "localhost";
//        String consoleURL = "https://console.amazonaws.cn/console/home";
//        String signInURL = "https://signin.amazonaws.cn/federation";

        String issuerURL = "localhost";
        String consoleURL = "https://console.aws.amazon.com/console/home";
        String signInURL = "https://signin.aws.amazon.com/federation";

        Credentials credentials = assumeRoleResponse.credentials();
        String sessionJson = String.format(
                "{\"%1$s\":\"%2$s\",\"%3$s\":\"%4$s\",\"%5$s\":\"%6$s\"}",
                "sessionId", credentials.accessKeyId(),
                "sessionKey", credentials.secretAccessKey(),
                "sessionToken", credentials.sessionToken());

        String getSigninTokenURL = signInURL +
                "?Action=getSigninToken" +
                "&DurationSeconds=43200" +
                "&SessionType=json&Session=" +
                URLEncoder.encode(sessionJson,"UTF-8");

        URL url = new URL(getSigninTokenURL);

        URLConnection conn = url.openConnection();

        BufferedReader bufferReader = new BufferedReader(new
                InputStreamReader(conn.getInputStream()));

        String returnContent = bufferReader.readLine();

        String signinToken = new JSONObject(returnContent).getStr("SigninToken");

        String signinTokenParameter = "&SigninToken=" + URLEncoder.encode(signinToken,"UTF-8");

        String issuerParameter = "&Issuer=" + URLEncoder.encode(issuerURL, "UTF-8");

        String destinationParameter = "&Destination=" + URLEncoder.encode(consoleURL,"UTF-8");
        String loginURL = signInURL + "?Action=login" +
                signinTokenParameter + issuerParameter + destinationParameter;
        System.out.println("loginURL:" + loginURL);
    }
}
