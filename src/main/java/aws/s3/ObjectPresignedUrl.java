package aws.s3;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.time.Duration;

/**
 * <p>
 *
 * </p>
 *
 * @author: 刘英豪
 * @Date: 2022/11/23 10:04
 * @modifyrecord：
 */
public class ObjectPresignedUrl {
    public static void main(String[] args) {
        String accessKey = "AKIAS7C25MRXQSGXLKFK";
        String secretKey = "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";
        AwsBasicCredentials basicCredentials = AwsBasicCredentials.create(accessKey, secretKey);
        StaticCredentialsProvider staticCredentialsProvider = StaticCredentialsProvider.create(basicCredentials);
        S3Presigner presigner = S3Presigner.builder().credentialsProvider(staticCredentialsProvider)
                .region(Region.CN_NORTHWEST_1)
                .build();
        GetObjectRequest getObjectRequest =
                GetObjectRequest.builder()
                        .bucket("goclouds-game-script-dev")
                        .key("cd4fee0c2b7447efa926d1ebe3c19fc0/detail/readme.md")
                        .build();
        GetObjectPresignRequest getObjectPresignRequest =  GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofHours(2L))
                .getObjectRequest(getObjectRequest)
                .build();

        // Generate the presigned request
        PresignedGetObjectRequest presignedGetObjectRequest =
                presigner.presignGetObject(getObjectPresignRequest);
        System.out.println(presignedGetObjectRequest.url().toString());
    }
}
