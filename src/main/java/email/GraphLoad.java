//package email;
//
//import com.azure.identity.ClientSecretCredential;
//import com.azure.identity.ClientSecretCredentialBuilder;
//import com.microsoft.aad.msal4j.ClientCredentialFactory;
//import com.microsoft.aad.msal4j.ClientCredentialParameters;
//import com.microsoft.aad.msal4j.ConfidentialClientApplication;
//import com.microsoft.aad.msal4j.IAuthenticationResult;
//import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
//import com.microsoft.graph.models.Message;
//import com.microsoft.graph.models.Recipient;
//import com.microsoft.graph.requests.GraphServiceClient;
//import com.microsoft.graph.requests.MessageCollectionPage;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//
//
//class GraphLoad {
//
//    private static String authority = "https://login.microsoftonline.com/你的TENANT_ID/";
//    private static String tenantId = "你的";
//    private static String clientId = "你的";
//    private static String secret = "填写自己的";
//    private static String accessToken = "";
//    private static String mailbox = "指定的邮箱";
//
//    private static List<String> scope = Arrays.asList("https://graph.microsoft.com/.default".split(","));
//
//    private static final ClientSecretCredential clientSecretCredential = null;
//
//    private static final TokenCredentialAuthProvider tokenCredAuthProvider = null;
//
//    private static final GraphServiceClient graphClient = null;
//
//    public static void main(String args[]) throws Exception {
//
//        MessageCollectionPage messages = listMessages(graphClient, mailbox);
//
//        for (Message message:messages.getCurrentPage()){
//            System.out.println("Id--->"+m.id);
//            String senderName = message.from.emailAddress.name;
//            String senderAddress = message.from.emailAddress.address;
//            String subject = message.subject;
//            String importance = message.importance.name();
//            String webLink = message.webLink;
//            String bodyContent = message.body.content;
//            LocalDateTime receivedDateTime = message.receivedDateTime.toLocalDateTime();
//            LocalDateTime sentDateTime = message.sentDateTime.toLocalDateTime();
//            List<Recipient> toRecipients  = message.toRecipients;
//            System.out.println(toRecipients.listIterator().next().emailAddress.address);
//
//        }
//
//
//    } catch (Exception ex) {
//        System.out.println("Exception message - " + ex.getMessage());
//        throw ex;
//    }
//}
//
//    public static Message getMessageById(GraphServiceClient graphClient,String mailbox,String mailId) throws IOException {
//        Message message = graphClient.users(mailbox).messages(mailId)
//                .buildRequest()
//                .get();
//        return message;
//    }
//
//    public static String getAccessToken() throws IOException {
//        ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder().clientId(clientId)
//                .tenantId(tenantId).clientSecret(secret).build();
//
//        TokenCredentialAuthProvider tokenCredAuthProvider = new TokenCredentialAuthProvider(scope,
//                clientSecretCredential);
//
//        GraphServiceClient graphClient = GraphServiceClient.builder().authenticationProvider(tokenCredAuthProvider)
//                .buildClient();
//
//        IAuthenticationResult result = getAccessTokenByClientCredentialGrant();
//        return accessToken = result.accessToken();
//
//    }
//
//    public static MessageCollectionPage listMessages(GraphServiceClient graphClient,String mailbox) throws IOException {
//        MessageCollectionPage messages = graphClient.users(mailbox).messages()
//                .buildRequest()
//                .get();
//        return messages;
//    }
//
//    public static IAuthenticationResult getAccessTokenByClientCredentialGrant() throws Exception {
//
//        ConfidentialClientApplication app = ConfidentialClientApplication.builder(
//                clientId,
//                ClientCredentialFactory.createFromSecret(secret))
//                .authority(authority)
//                .build();
//
//        // With client credentials flows the scope is ALWAYS of the shape "resource/.default", as the
//        // application permissions need to be set statically (in the portal), and then granted by a tenant administrator
//        ClientCredentialParameters clientCredentialParam = ClientCredentialParameters.builder(
//                Collections.singleton(scope))
//                .build();
//
//        CompletableFuture<IAuthenticationResult> future = app.acquireToken(clientCredentialParam);
//        return future.get();
//
//    }
//
//}