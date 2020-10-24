package com.example.demo.services;

import java.io.IOException;
import java.io.InputStream;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StorageService {

    @Value("${amazon.aws.bucket}")
    private String bucket;

    @Autowired
    AmazonS3 s3;

    public void storeFile(String fileName, InputStream fileContent) {
        s3.putObject(new PutObjectRequest(bucket, fileName, fileContent, new ObjectMetadata()));
    }

    public byte[] getFile(String fileName) throws IOException {
        S3Object object = s3.getObject(new GetObjectRequest(bucket, fileName));
        return object.getObjectContent().readAllBytes();
    }
}
