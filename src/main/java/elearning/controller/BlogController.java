package elearning.controller;

import elearning.dto.BlogDto;
import elearning.exception.CustomException;
import elearning.model.Blog;
import elearning.repository.BlogRepository;
import elearning.service.BlogService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blogs")
public class BlogController {
    @Autowired
    BlogService blogService;

    @Autowired
    BlogRepository blogRepository;

    @GetMapping("/")
    public ResponseEntity<List<Blog>> getAll() {
        List<Blog> blogs = blogService.getAll();
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Blog>> getAllBlogsByUserLogin(Authentication authentication) {
        String phoneNumber = authentication.getName();
        List<Blog> blogs = blogRepository.findAllBlogsByUserLogin(phoneNumber);
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogDto> getBlogById(@PathVariable Long id) {
        try {
            BlogDto blogDto = blogService.findOne(id);
            return new ResponseEntity<>(blogDto, HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<BlogDto> save(@ModelAttribute BlogDto blogDto) {
        try {
            BlogDto createdBlog = blogService.save(blogDto);
            return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
        } catch (CustomException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BlogDto> update(@ModelAttribute BlogDto blogDto, @PathVariable Long id) {
        try {
            BlogDto updatedBlog = blogService.upDateBlogs(blogDto, id);
            return new ResponseEntity<>(updatedBlog, HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            blogService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
