package email;

import com.azure.identity.*;
import com.microsoft.aad.msal4j.*;
import com.microsoft.graph.authentication.IAuthenticationProvider;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.models.*;
import com.microsoft.graph.requests.GraphServiceClient;
import com.microsoft.graph.requests.MessageCollectionPage;
import com.microsoft.graph.requests.UserCollectionRequestBuilder;

import java.net.MalformedURLException;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @Description
 * @Author zhuJiaHao
 * @Date 2022/9/28 10:55
 * @Version 1.0
 **/
public class sendMailTest {

    void sendMail () {

       final ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder()
                .clientId("b02b6137-6b7b-44c2-8146-7396c773b5db")
                .clientSecret("8-U8Q~q.4E_U6u_Ae4dntK8pLHka74J3.fp3jcvE")
                .tenantId("6a8094dc-7834-411c-8109-48c35613d19d")
                .build();

        List<String> scopes = Arrays.asList("https://hhsp@xyc15.onmicrosoft.com/.default");
        final TokenCredentialAuthProvider tokenCredentialAuthProvider = new TokenCredentialAuthProvider(scopes, clientSecretCredential);

        final GraphServiceClient graphClient =
                GraphServiceClient
                        .builder()
                        .authenticationProvider(tokenCredentialAuthProvider)
                        .buildClient();

   /*  final AuthorizationCodeCredential authCodeCredential = new AuthorizationCodeCredentialBuilder()
             .clientId("c7364528-0343-4c61-9a25-7edf6d616330")
             .clientSecret("0Cu8Q~jZo8wOT2V4WkaGXzV46KZ5ZODJyB7GSa7k") //required for web apps, do not set for native apps
             .authorizationCode("M.R3_BAY.cd6f09c0-9517-71de-286d-68c3e32dab58")
             .redirectUrl("https://login.microsoftonline.com/common/oauth2/nativeclient")
             .build();

     List<String> scopes = Arrays.asList("https://graph.microsoft.com/.default");
     final TokenCredentialAuthProvider tokenCredentialAuthProvider = new TokenCredentialAuthProvider(scopes, authCodeCredential);

     final GraphServiceClient graphClient =
             GraphServiceClient
                     .builder()
                     .authenticationProvider(tokenCredentialAuthProvider)
                     .buildClient();*/

    /* final UsernamePasswordCredential usernamePasswordCredential = new UsernamePasswordCredentialBuilder()
             .clientId("e727284f-9815-4dff-9f38-5d7e6f653405")
             .username("it.cost@envision-energy.com")
             .password("n2b.X*DDDCozaZkQ1")
             .build();

     List<String> scopes = Arrays.asList("https://graph.microsoft.com/.default");
     final TokenCredentialAuthProvider tokenCredentialAuthProvider = new TokenCredentialAuthProvider(scopes, usernamePasswordCredential);

     final GraphServiceClient graphClient =
             GraphServiceClient
                     .builder()
                     .authenticationProvider(tokenCredentialAuthProvider)
                     .buildClient();
*/
        Message message = new Message();
        message.subject = "Meet for lunch?";
        ItemBody body = new ItemBody();
        body.contentType = BodyType.TEXT;
        body.content = "The new cafeteria is open-------.";
        message.body = body;
        LinkedList<Recipient> toRecipientsList = new LinkedList<Recipient>();
        Recipient toRecipients = new Recipient();
        EmailAddress emailAddress = new EmailAddress();
        emailAddress.address = "zhujiahao@goclouds.cn";
        toRecipients.emailAddress = emailAddress;
        toRecipientsList.add(toRecipients);
        message.toRecipients = toRecipientsList;
       /* LinkedList<Recipient> ccRecipientsList = new LinkedList<Recipient>();
        Recipient ccRecipients = new Recipient();
        EmailAddress emailAddress1 = new EmailAddress();
        emailAddress1.address = "liuyinghao@goclouds.cn";
        ccRecipients.emailAddress = emailAddress1;
        ccRecipientsList.add(ccRecipients);
        message.ccRecipients = ccRecipientsList;
*/
        boolean saveToSentItems = false;

        graphClient.me()
                .sendMail(UserSendMailParameterSet
                        .newBuilder()
                        .withMessage(message)
                        .withSaveToSentItems(saveToSentItems)
                        .build())
                .buildRequest()
                .post();

    }
}
