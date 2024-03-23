package elearning.service;

import elearning.dto.CategoryDto;
import elearning.exception.CustomException;
import elearning.model.Category;

import java.util.List;

public interface CategoryService {
    CategoryDto save(CategoryDto categoryDto) throws CustomException;

    CategoryDto findOne(Long id) throws CustomException;

    void delete(Long id) throws CustomException;

    List<Category> getAll();

    CategoryDto upDateCategory(CategoryDto dto, Long id) throws CustomException;
}
