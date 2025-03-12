package com.kcare.kcare.File;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@RequiredArgsConstructor
@Getter
@Setter

public class R2FileStorageService {

    @Value("${cloudflare.r2.bucket-name}")
    private String bucketName;

    @Value("${cloudflare.r2.domain-name}")
    private String r2DomainUrl;

    // @Value("${cloudflare.r2.domain-name}")

    private final S3Client s3Client;

    public String uploadFile(MultipartFile file) throws IOException {

        String key = String.valueOf(System.currentTimeMillis());

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                .contentLength(null)
                .build();

        s3Client.putObject(putObjectRequest,
                RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        return key;

    }

    public byte[] downloadFile(String key) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        try {
            return s3Client.getObject(getObjectRequest)
                    .readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException("Failed to download file", e);
        }
    }

    public String getImageUrl(String imagePath) {

        return "http://" + r2DomainUrl + "/" + imagePath;

    }

    // List<String> imageUrl = imagePath.stream().map(image -> {
    // return "http://" + r2DomainUrl + "/" + image;

    // }).collect(Collectors.toList());
    // return "http://" + r2DomainUrl + "/" + imagePath;

    // }).collect(Collectors.toList());

    // }

}
