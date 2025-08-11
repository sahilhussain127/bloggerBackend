package com.example.bloggerBacked.bloggerBacked.media.service;

import com.example.bloggerBacked.bloggerBacked.media.model.MediaFile;
import com.example.bloggerBacked.bloggerBacked.media.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Override
    public String saveMedia(MultipartFile file) throws IOException {
        MediaFile media = new MediaFile();
        media.setFileName(file.getOriginalFilename());
        media.setContentType(file.getContentType());
        media.setData(file.getBytes());
        media = mediaRepository.save(media);
        return media.getId();
    }

    @Override
    public MediaFile getMediaById(String id) {
        return mediaRepository.findById(id).orElse(null);
    }
}
