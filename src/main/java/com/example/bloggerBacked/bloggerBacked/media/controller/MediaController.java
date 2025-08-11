package com.example.bloggerBacked.bloggerBacked.media.controller;

import com.example.bloggerBacked.bloggerBacked.media.model.MediaFile;
import com.example.bloggerBacked.bloggerBacked.media.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @PostMapping
    public ResponseEntity<?> uploadMedia(@RequestParam("file") MultipartFile file) {
        try {
            String id = mediaService.saveMedia(file);
            return ResponseEntity.ok().body("{\"mediaId\":\"" + id + "\"}");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save file.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMedia(@PathVariable String id) {
        MediaFile media = mediaService.getMediaById(id);
        if (media == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Media not found");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(media.getContentType()));
        headers.setContentDisposition(ContentDisposition.inline().filename(media.getFileName()).build());

        return new ResponseEntity<>(media.getData(), headers, HttpStatus.OK);
    }
}
