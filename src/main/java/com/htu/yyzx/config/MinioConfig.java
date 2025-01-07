package com.htu.yyzx.config;


import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //用于将该类中定义的属性注入到其他组件中使用
@ConfigurationProperties(prefix = "minio")
// 最适合具有相同前缀的分层属性 Spring 框架使用标准的 Java Bean Setter，因此必须为每个属性声明 Setter 方法
@Data
public class MinioConfig {

    private String url;

    private String accessKey;

    private String secretKey;

    private String bucketName;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, secretKey)
                .build();
    }
}
