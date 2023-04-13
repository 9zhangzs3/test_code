package gcp.storage;

import com.google.auth.oauth2.GoogleCredentials;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

/**
 * <p>
 *
 * </p>
 *
 * @author: 刘英豪
 * @Date: 2022/11/7 14:14
 * @modifyrecord：
 */
public class SigneUrl {
    public static void main(String[] args) throws IOException {
        String jsonPath = "/Users/liuyinghao/GCPKEY/gocmp-345612-99f51c25897e.json";
        // String projectId = "my-project-id";
         String bucketName = "region_day_cost_csv_dev";
         String objectName = "3.云加科技Cost+云成本管理平台白皮书.docx";
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(jsonPath));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        // Define resource
        BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(bucketName, objectName)).build();

        URL url =
                storage.signUrl(blobInfo, 15, TimeUnit.MINUTES, Storage.SignUrlOption.withV4Signature());

        System.out.println("Generated GET signed URL:");
        System.out.println(url);
        System.out.println("You can use this URL with any user agent, for example:");
        System.out.println("curl '" + url + "'");
    }
}
