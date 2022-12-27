package com.mrgoropeza.portfoliobackend.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.Getter;

@Data
@Configuration
@ConfigurationProperties(prefix = "firebase")
@Getter
public class Properties {

    private String bucketName;

    private String imageUrl;
}