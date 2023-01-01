package com.mrgoropeza.portfoliobackend.service.interfaces;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface IStorageService {

    String getImageUrl(String name);

    void save(MultipartFile file, String fileName, String path) throws IOException;

    void delete(String name) throws IOException;

    default String getExtension(String originalFileName) {
        return StringUtils.getFilenameExtension(originalFileName);
    }

    default String generateFileName(String originalFileName) {
        return UUID.randomUUID().toString() + getExtension(originalFileName);
    }
}
