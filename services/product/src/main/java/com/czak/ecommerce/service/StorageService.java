package com.czak.ecommerce.service;

import io.minio.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class StorageService {

    private final MinioClient minioClient;
    private String  bucketName = "images";

    public String uploadFile(String objectName, InputStream inputStream, String contentType) {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());

            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            ObjectWriteResponse objectWriteResponse = minioClient.putObject(
                            PutObjectArgs.builder()
                                    .bucket(bucketName)
                                    .object(objectName)
                                    .stream(inputStream, inputStream.available(), -1)
                                    .contentType(contentType)
                                    .build());
            log.info("objectWriteResponse.versionId(): " + objectWriteResponse.versionId());
           return objectWriteResponse.versionId();
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }
}
