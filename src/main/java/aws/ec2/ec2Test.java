package aws.ec2;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
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
 * @Date: 2022/8/31 17:00
 * @modifyrecord：
 */
public class ec2Test {
    public static void main(String[] args) {
        String accessKey = "AKIAS7C25MRX4XZ2N2N4";
        String secretKey = "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";

        String roleArn = "arn:aws-cn:iam::722869710786:role/test-gameday-role";
        String externalId = "616495";
        AwsBasicCredentials basicCredentials = AwsBasicCredentials.create(accessKey, secretKey);
        StsClient stsClient = StsClient.builder().credentialsProvider(StaticCredentialsProvider.create(basicCredentials))
                .region(Region.CN_NORTHWEST_1).build();

        AssumeRoleRequest assumeRoleRequest = AssumeRoleRequest.builder()
                .roleArn(roleArn)
                .externalId(externalId)
                .durationSeconds(1800)
                .roleSessionName("liuyinghao-test")
                .build();
        AssumeRoleResponse assumeRoleResponse = stsClient.assumeRole(assumeRoleRequest);
        Credentials credentials = assumeRoleResponse.credentials();
        AwsSessionCredentials awsSessionCredentials = AwsSessionCredentials.create(credentials.accessKeyId(), credentials.secretAccessKey(), credentials.sessionToken());
        Ec2Client ec2Client = Ec2Client.builder().credentialsProvider(StaticCredentialsProvider.create(awsSessionCredentials)).build();
    }
}
