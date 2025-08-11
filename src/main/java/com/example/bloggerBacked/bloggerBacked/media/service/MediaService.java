package com.example.bloggerBacked.bloggerBacked.media.service;

import com.example.bloggerBacked.bloggerBacked.media.model.MediaFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MediaService {
    String saveMedia(MultipartFile file) throws IOException;
    MediaFile getMediaById(String id);
}
