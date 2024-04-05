package elearning.service.impl;

import elearning.dto.BlogDto;
import elearning.exception.CustomException;
import elearning.model.*;
import elearning.repository.BlogRepository;
import elearning.repository.UserRepository;
import elearning.service.BlogService;
import elearning.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
	@Autowired
	BlogRepository blogRepository;
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	private FileService fileService;
	
	@Autowired
	IUserService iUserService;
	
	public BlogDto save(Blog entity, BlogDto dto) throws CustomException {
		
		Users users = iUserService.getCurrentUser();
		if (users == null || users.getId() == null) {
			throw new CustomException("User not found");
		}
		entity.setUsers(users);
		entity.setTitle(dto.getTitle());
		entity.setLikes(dto.getLikes());
		entity.setImage(fileService.uploadFile(dto.getFileUpload()));
		entity.setTags(dto.getTags());
		entity.setContent(dto.getContent());
		blogRepository.save(entity);
		return new BlogDto(entity);
	}
	
	@Override
	public BlogDto save(BlogDto blogDto) throws CustomException {
		Blog entity = new Blog();
		return this.save(entity, blogDto);
	}
	
	@Override
	public BlogDto findOne(Long id) throws CustomException {
		return new BlogDto(this.getBlogById(id));
	}
	
	private Blog getBlogById(Long id) throws CustomException {
		Optional<Blog> optional = blogRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		throw new CustomException("Blog not found");
	}
	
	@Override
	public void delete(Long id) throws CustomException {
		Blog blog = blogRepository.findById(id).orElseThrow(() -> new CustomException("Blog not found"));
		blogRepository.save(blog);
	}
	
	@Override
	public List<Blog> getAll() {
		return blogRepository.findAll();
	}
	
	@Override
	public BlogDto upDateBlogs(BlogDto dto, Long id) throws CustomException {
		Blog blog = blogRepository.findById(id).orElseThrow(() -> new CustomException("Blog not found"));
		return this.save(blog, dto);
	}
}
