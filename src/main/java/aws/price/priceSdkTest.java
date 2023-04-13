package aws.price;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.pricing.PricingClient;
import software.amazon.awssdk.services.pricing.model.*;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: 刘英豪
 * @Date: 2022/7/29 16:45
 * @modifyrecord：
 */
public class priceSdkTest {
    public static void main(String[] args) {
//        String accessKey = "AKIAS7C25MRX4XZ2N2N4";
//        String secretKey = "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";
        String accessKey = "AKIATLADR5NCPZGCV7TK\n";
        String secretKey = "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";
        AwsBasicCredentials basicCredentials = AwsBasicCredentials.create(accessKey, secretKey);
        PricingClient pricingClient = PricingClient.builder().credentialsProvider(StaticCredentialsProvider.create(basicCredentials))
                .region(Region.US_EAST_1)
                .build();

//        DescribeServicesRequest describeServicesRequest = DescribeServicesRequest.builder()
//                .serviceCode("AmazonEC2")
//                .formatVersion("aws_v1")
//                .maxResults(1)
//                .build();
//        DescribeServicesResponse describeServicesResponse = pricingClient.describeServices(describeServicesRequest);
//        List<Service> services = describeServicesResponse.services();
//        services.forEach(service -> {
//            System.out.println(service.serviceCode());
//        });
        Filter filter = Filter.builder().type(FilterType.TERM_MATCH)
                .field("regionCode").value(Region.CN_NORTHWEST_1.id()).build();
        GetProductsRequest getProductsRequest = GetProductsRequest.builder().serviceCode("AmazonEC2").filters(filter).build();
        GetProductsResponse products = pricingClient.getProducts(getProductsRequest);
        List<String> list = products.priceList();
        list.forEach(price->{
            System.out.println(price);
        });
    }
}
