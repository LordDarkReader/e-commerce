package com.czak.ecommerce.storage;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioStorageConfig {

//    @Value("${minio.url}")
//    private String url;
    private String url = "http://localhost:9000";

//    @Value("${minio.access.key}")
//    private String accessKey;
    private String accessKey = "URc2UUgycaYmivkKIVTC";

//    @Value("${minio.access.secret}")
//    private String accessSecret;
    private String accessSecret = "cb7yz90x1eO9YmyODYoGXNwv2979Qr5XMsj9CMGe";

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, accessSecret)
                .build();
    }
}
