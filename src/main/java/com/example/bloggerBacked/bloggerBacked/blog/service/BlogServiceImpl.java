package com.example.bloggerBacked.bloggerBacked.blog.service;
import com.example.bloggerBacked.bloggerBacked.blog.service.BlogService;
import com.example.bloggerBacked.bloggerBacked.blog.model.Blog;
import com.example.bloggerBacked.bloggerBacked.blog.repository.BlogRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import com.example.bloggerBacked.bloggerBacked.user.model.User;
import com.example.bloggerBacked.bloggerBacked.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private UserRepository userRepository;

//    @Override
//    public Blog createBlog(Blog blog) {
//        return blogRepository.save(blog);
//    }

    @Override
    public Blog createBlog(Blog blog) {
        UUID userId = blog.getCreatedById();

        if (userId == null) {
            throw new RuntimeException("Missing createdById");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        blog.setUser(user); // this will set user_id foreign key
        blog.setCreatedById(user.getId());
        blog.setCreatedByName(user.getFirstName());
        blog.setCreatedAt(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        Blog savedBlog = blogRepository.save(blog);
        return savedBlog;
    }

    @Override
    public Blog getSingleBlog(UUID id) {
//        if (!blogRepository.existsById(id)) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog not found");
//        }
//      return blogRepository.findById(id);

        Blog blog = blogRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
        return blog;
    }




    @Override
    public void deleteBlogById(UUID id) {
        if (!blogRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog not found");
        }
        blogRepository.deleteById(id);
    }


    @Override
    public List<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }

}
