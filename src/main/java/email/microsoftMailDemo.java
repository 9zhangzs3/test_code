package email;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.models.*;
import com.microsoft.graph.requests.GraphServiceClient;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: 刘英豪
 * @Date: 2022/10/10 10:51
 * @modifyrecord：
 */
public class microsoftMailDemo {
    public static void main(String[] args) {

         ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder()
                 .clientId("c7364528-0343-4c61-9a25-7edf6d616330")
                 .clientSecret("0Cu8Q~jZo8wOT2V4WkaGXzV46KZ5ZODJyB7GSa7k")
                 .tenantId("20e8dfa9-4087-428e-865a-329bac3297da")
                 .build();

        TokenCredentialAuthProvider tokenCredAuthProvider =
                new TokenCredentialAuthProvider(clientSecretCredential);

         GraphServiceClient graphClient = GraphServiceClient
                .builder()
                .authenticationProvider(tokenCredAuthProvider)
                .buildClient();

        Message message = new Message();
        message.subject = "测试邮件";
        ItemBody body = new ItemBody();
        body.contentType = BodyType.TEXT;
        body.content = "这是一封测试邮件"+new Date();
        message.body = body;
        LinkedList<Recipient> toRecipientsList = new LinkedList<Recipient>();
        Recipient toRecipients = new Recipient();
        EmailAddress emailAddress = new EmailAddress();
        emailAddress.address = "liuyinghao@goclouds.cn";
        toRecipients.emailAddress = emailAddress;
        toRecipientsList.add(toRecipients);
        message.toRecipients = toRecipientsList;

        boolean saveToSentItems = false;

        graphClient.users("it.cost@envision-energy.com")
//        graphClient.me()
                .sendMail(UserSendMailParameterSet
                        .newBuilder()
                        .withMessage(message)
                        .withSaveToSentItems(saveToSentItems)
                        .build())
                .buildRequest()
                .post();
    }
}
