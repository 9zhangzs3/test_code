package aws.assumerole;

import cn.hutool.json.JSONObject;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
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
public class test2 {
    //雪花算法

    public static void main(String[] args) throws IOException {
        String accessKey = "AKIAS7C25MRX4XZ2N2N4";
        String secretKey = "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";

        AwsBasicCredentials basicCredentials = AwsBasicCredentials.create(accessKey, secretKey);
//        String roleArn = "arn:aws:iam::403875752214:role/AI_EDU_ASSUME_ROLE" ;
//        String externalId = "jE7N914i";
//        AwsSessionCredentials basicCredentials = getAwsCredentials4Role(roleArn, externalId, "global");
        StsClient stsClient = StsClient.builder().credentialsProvider(StaticCredentialsProvider.create(basicCredentials))
                .region(Region.CN_NORTHWEST_1).build();

        String policy = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Action\":\"*\"," +
                "\"Effect\":\"Allow\",\"Resource\":\"*\"}]}";

//        PolicyDescriptorType policyDescriptorType = PolicyDescriptorType.builder().arn("arn:aws:iam::aws:policy/AdministratorAccess").build();
        GetFederationTokenRequest getFederationTokenRequest = GetFederationTokenRequest.builder()
                .durationSeconds(1800)
                .name("UserName")
//                .policyArns(policyDescriptorType)
                .policy(policy)
                .build();
        GetFederationTokenResponse federationToken = stsClient.getFederationToken(getFederationTokenRequest);

        Credentials credentials = federationToken.credentials();

//        String issuerURL = "https://mysignin.internal.mycompany.com/";
//        String consoleURL = "https://console.aws.amazon.com/console/home";
//        String signInURL = "https://signin.aws.amazon.com/federation";

        String issuerURL = "https://mysignin.internal.mycompany.com/";
        String consoleURL = "https://console.amazonaws.cn/console/home";
        String signInURL = "https://signin.amazonaws.cn/federation";

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

        String issuerParameter = "&Issuer=" + URLEncoder.encode("localhost", "UTF-8");

        String destinationParameter = "&Destination=" + URLEncoder.encode(consoleURL,"UTF-8");
        String loginURL = signInURL + "?Action=login" +
                signinTokenParameter + issuerParameter + destinationParameter;
        System.out.println("loginURL:" + loginURL);
    }

    public static AwsSessionCredentials getAwsCredentials4Role(String roleArn, String externalId, String regionType) {
        String accessKey = "AKIATLADR5NCFMO43YX5";
        String secretKey = "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";

        AwsBasicCredentials basicCredentials = AwsBasicCredentials.create(accessKey, secretKey);

        StsClient stsClient = StsClient.builder().credentialsProvider(StaticCredentialsProvider.create(basicCredentials))
                .region(Region.US_EAST_1).build();

        AssumeRoleRequest roleRequest = AssumeRoleRequest.builder()
                .roleArn(roleArn)
                .externalId(externalId)
                .durationSeconds(3600)
                .roleSessionName(externalId)
                .build();
        AssumeRoleResponse roleResponse = stsClient.assumeRole(roleRequest);
        Credentials credentials = roleResponse.credentials();
        AwsSessionCredentials awsSessionCredentials = AwsSessionCredentials.create(credentials.accessKeyId(), credentials.secretAccessKey(), credentials.sessionToken());
        return awsSessionCredentials;
    }


}
