package elearning.service;

import elearning.dto.BlogDto;
import elearning.exception.CustomException;
import elearning.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    BlogDto save(BlogDto blogDto) throws CustomException ;

    BlogDto findOne(Long id) throws CustomException;

    void delete(Long id) throws CustomException;

    List<Blog> getAll();

    BlogDto upDateBlogs(BlogDto dto, Long id) throws CustomException;
}
