package com.example.bloggerBacked.bloggerBacked.media.repository;

import com.example.bloggerBacked.bloggerBacked.media.model.MediaFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<MediaFile, String> {
}
