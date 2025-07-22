package com.example.bloggerBacked.bloggerBacked.blog.repository;
import com.example.bloggerBacked.bloggerBacked.blog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BlogRepository extends JpaRepository<Blog, UUID> {
}
