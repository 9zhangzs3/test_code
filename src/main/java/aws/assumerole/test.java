//package aws.assumerole;
//
//import cn.hutool.json.JSONObject;
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.auth.PropertiesCredentials;
//import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
//import com.amazonaws.services.securitytoken.model.Credentials;
//import com.amazonaws.services.securitytoken.model.GetFederationTokenRequest;
//import com.amazonaws.services.securitytoken.model.GetFederationTokenResult;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.net.URLEncoder;
//
///**
// * <p>
// *
// * </p>
// *
// * @author: 刘英豪
// * @Date: 2022/7/25 18:04
// * @modifyrecord：
// */
//public class test {
//    public static void main(String[] args) throws IOException {
//
////        AWSCredentials credentials =
////                new PropertiesCredentials(
////                        AwsConsoleApp.class.getResourceAsStream("AwsCredentials.properties"));
//        String ak = "AKIAS7C25MRX6QI66VVE";
//        String sk = "A3l9XZFDbjrOQZB6yxk4cWRL4C1lIbWYSdQmT9lu";
//        AWSCredentials credentials =
//                new BasicAWSCredentials(ak,sk);
//
////        AWSSecurityTokenServiceClient stsClient =
////                new AWSSecurityTokenServiceClient(credentials);
//        AWSSecurityTokenServiceClient stsClient = AWSSecurityTokenServiceClientProvider
////                new AWSSecurityTokenServiceClient(credentials);
//
//        GetFederationTokenRequest getFederationTokenRequest =
//                new GetFederationTokenRequest();
//        getFederationTokenRequest.setDurationSeconds(1800);
//        getFederationTokenRequest.setName("UserName");
//
//
//        String policy = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Action\":\"sns:*\"," +
//                "\"Effect\":\"Allow\",\"Resource\":\"*\"}]}";
//
//        getFederationTokenRequest.setPolicy(policy);
//
//        GetFederationTokenResult federationTokenResult =
//                stsClient.getFederationToken(getFederationTokenRequest);
//
//        Credentials federatedCredentials = federationTokenResult.getCredentials();
//
//
//        String issuerURL = "https://mysignin.internal.mycompany.com/";
//        String consoleURL = "https://console.aws.amazon.com/sns";
//        String signInURL = "https://signin.aws.amazon.com/federation";
//
//        String sessionJson = String.format(
//                "{\"%1$s\":\"%2$s\",\"%3$s\":\"%4$s\",\"%5$s\":\"%6$s\"}",
//                "sessionId", federatedCredentials.getAccessKeyId(),
//                "sessionKey", federatedCredentials.getSecretAccessKey(),
//                "sessionToken", federatedCredentials.getSessionToken());
//
//
//        String getSigninTokenURL = signInURL +
//                "?Action=getSigninToken" +
//                "&DurationSeconds=43200" +
//                "&SessionType=json&Session=" +
//                URLEncoder.encode(sessionJson,"UTF-8");
//
//        URL url = new URL(getSigninTokenURL);
//
//        URLConnection conn = url.openConnection();
//
//        BufferedReader bufferReader = new BufferedReader(new
//                InputStreamReader(conn.getInputStream()));
//        String returnContent = bufferReader.readLine();
//
//        String signinToken = new JSONObject(returnContent).getStr("SigninToken");
//
//        String signinTokenParameter = "&SigninToken=" + URLEncoder.encode(signinToken,"UTF-8");
//
//// The issuer parameter is optional, but recommended. Use it to direct users
//// to your sign-in page when their session expires.
//
//        String issuerParameter = "&Issuer=" + URLEncoder.encode(issuerURL, "UTF-8");
//
//// Finally, present the completed URL for the AWS console session to the user
//
//        String destinationParameter = "&Destination=" + URLEncoder.encode(consoleURL,"UTF-8");
//        String loginURL = signInURL + "?Action=login" +
//                signinTokenParameter + issuerParameter + destinationParameter;
//        System.out.println("loginURL:" + loginURL);
//    }
//}
