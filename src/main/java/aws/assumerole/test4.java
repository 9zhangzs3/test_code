package aws.assumerole;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest;
import software.amazon.awssdk.services.sts.model.AssumeRoleResponse;
import software.amazon.awssdk.services.sts.model.Credentials;

/**
 * <p>
 *
 * </p>
 *
 * @author: 刘英豪
 * @Date: 2022/11/24 10:54
 * @modifyrecord：
 */
public class test4 {
    public static void main(String[] args) {
        String accessKey = "AKIA5JCJCSMIBFLDHKXL";
        String secretKey = "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";

//        String roleArn = "arn:aws:iam::345596710354:role/OrganizationAccountAccessRole";
        String roleArn = "arn:aws-cn:iam::345596710354:role/OrganizationAccountAccessRole";
//        String externalId = "eZ8EwAvS";
        AwsBasicCredentials basicCredentials = AwsBasicCredentials.create(accessKey, secretKey);
        StsClient stsClient = StsClient.builder().credentialsProvider(StaticCredentialsProvider.create(basicCredentials))
                .region(Region.CN_NORTHWEST_1).build();
        AssumeRoleRequest roleRequest = AssumeRoleRequest.builder()
                .roleArn(roleArn)
                .durationSeconds(3600)
                .roleSessionName(String.valueOf(System.currentTimeMillis()))
                .build();
        AssumeRoleResponse roleResponse = stsClient.assumeRole(roleRequest);
        Credentials credentials = roleResponse.credentials();
    }
}
