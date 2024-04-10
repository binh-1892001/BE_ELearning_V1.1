package elearning.service.impl;

import elearning.dto.CategoryDto;
import elearning.exception.CustomException;
import elearning.model.Category;
import elearning.model.Course;
import elearning.repository.CategoryRepository;
import elearning.repository.CourseRepository;
import elearning.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CourseRepository courseRepository;

    public CategoryDto save(Category entity, CategoryDto dto) throws CustomException {

        entity.setName(dto.getName());
        entity.setStatus(dto.isStatus());

        if (dto.getCourseDto() == null || dto.getCourseDto().getId() == null) {
            throw new CustomException("Course is not null");
        }
        Course course = courseRepository.findById(dto.getCourseDto().getId()).orElse(null);
        if (course == null) {
            throw new CustomException("Course is not null");
        }
        entity.setCourse(course);
        entity = categoryRepository.save(entity);
        return new CategoryDto(entity);
    }
    @Override
    public CategoryDto save(CategoryDto categoryDto) throws CustomException {
        Category entity = new Category();
        return this.save(entity, categoryDto);
    }

    @Override
    public CategoryDto findOne(Long id) throws CustomException{
        return new CategoryDto(this.getCategoryById(id));
    }

    private Category getCategoryById(Long id) throws CustomException {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new CustomException("Category not found");
    }

    @Override
    public void delete(Long id) throws CustomException {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CustomException("category not found"));
        category.setVoided(!category.isStatus());
        categoryRepository.save(category);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryDto upDateCategory(CategoryDto dto, Long id) throws CustomException {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CustomException("Category not found"));
        return this.save(category, dto);
    }
}
