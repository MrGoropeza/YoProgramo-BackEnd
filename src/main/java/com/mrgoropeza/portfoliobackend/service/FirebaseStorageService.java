package com.mrgoropeza.portfoliobackend.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import com.mrgoropeza.portfoliobackend.service.interfaces.IStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FirebaseStorageService implements IStorageService {

    @Autowired
    private StorageClient firebaseStorage;

    @Autowired
    private String imageUrl;

    @Override
    public String getImageUrl(String name) {
        return String.format(imageUrl, name);
    }

    @Override
    public void save(MultipartFile file, String fileName, String path) throws IOException {
        Bucket bucket = firebaseStorage.bucket();

        bucket.create(path + "/" + fileName, file.getBytes(), file.getContentType());
    }

    @Override
    public void delete(String name) throws IOException {

        Bucket bucket = firebaseStorage.bucket();

        if (StringUtils.hasText(name)) {
            throw new IOException("invalid file name");
        }

        Blob blob = bucket.get(name);

        if (blob == null) {
            throw new IOException("file not found");
        }

        blob.delete();
    }

}
