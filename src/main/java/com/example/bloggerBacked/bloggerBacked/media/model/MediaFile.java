package com.example.bloggerBacked.bloggerBacked.media.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "media")
public class MediaFile {

    @Id
    private String id = UUID.randomUUID().toString();

    private String fileName;

    private String contentType;

    @Lob
    @Column(length = 52428800) // optional: up to 50MB
    private byte[] data;
}
