package com.example.bloggerBacked.bloggerBacked.blog.model;
import com.example.bloggerBacked.bloggerBacked.user.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name="blogs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    @Id
    @GeneratedValue
    @GenericGenerator(name="uuid",strategy = "uuid2")
    @Column(name = "id",updatable = false,nullable = false)
    private UUID id;

    @Column(name = "created_at", nullable = false)
    @JsonProperty("created_at")
    private String createdAt;

    @Column(name = "title",nullable = false)
    private String title;

    @Lob
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "likes")
    private int likes;
//
//    @Column(name="created_by_id",nullable = false)
//    private UUID createdById;

    @Column(name = "created_by_id", nullable = false)
    private UUID createdById;

    @Column(name = "created_by_name", nullable = false )
    @JsonProperty("created_by")
    private String createdByName;

    @Lob
    @Column(name = "thumbnail", columnDefinition = "LONGBLOB")
    private byte[] thumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

}
