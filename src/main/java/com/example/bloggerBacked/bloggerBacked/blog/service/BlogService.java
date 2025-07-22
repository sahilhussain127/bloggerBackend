package com.example.bloggerBacked.bloggerBacked.blog.service;
import com.example.bloggerBacked.bloggerBacked.blog.model.Blog;
import java.util.List;
import java.util.UUID;

public interface BlogService {
    Blog createBlog(Blog user);
    List<Blog> getAllBlogs();
}
