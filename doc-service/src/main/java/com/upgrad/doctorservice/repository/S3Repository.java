package com.upgrad.doctorservice.repository;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class S3Repository {
    private AmazonS3 s3Client;
    private String BUCKET_NAME = "bmc.shreyansh.doctor.documents";

    @Autowired
    ObjectMetadata objMetaData;

    @PostConstruct
    public void init(){
        String accessKey = "AKIAWRNF45VVHPOIG5U6";
        String secretKey = "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";
        AWSCredentials credentials = new BasicAWSCredentials(accessKey,secretKey);
        s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    public void uploadFiles(String userId, MultipartFile file) throws IOException {
        String key = userId +"/" +file.getOriginalFilename();
        if(!s3Client.doesBucketExistV2(BUCKET_NAME)){
            s3Client.createBucket(BUCKET_NAME);
        }
        s3Client.putObject(BUCKET_NAME, key, file.getInputStream(), objMetaData);
    }
}
