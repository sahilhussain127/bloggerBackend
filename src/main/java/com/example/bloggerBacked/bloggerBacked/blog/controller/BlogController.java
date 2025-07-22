package com.example.bloggerBacked.bloggerBacked.blog.controller;
import com.example.bloggerBacked.bloggerBacked.blog.model.Blog;
import com.example.bloggerBacked.bloggerBacked.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/blogs")
@CrossOrigin // Optional, allows frontend calls
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping
    public Blog createBlog(@RequestBody Blog blog) {
        return blogService.createBlog(blog);
    }

    @GetMapping
    public List<Blog> getAllBlogs()
    {
        return blogService.getAllBlogs();
    }


}
