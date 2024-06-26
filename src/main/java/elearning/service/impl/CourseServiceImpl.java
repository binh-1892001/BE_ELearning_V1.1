package elearning.service.impl;

import elearning.dto.CourseDto;
import elearning.exception.CustomException;
import elearning.model.Course;
import elearning.repository.CourseRepository;
import elearning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    FileService fileService;

    public CourseDto save(Course entity, CourseDto dto) throws IOException {
        entity.setVoided(dto.isVoided());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setSubDescription(dto.getSubDescription());
        entity = this.uploadFileImg(dto, entity);
        entity = courseRepository.save(entity);
        return new CourseDto(entity);
    }
    // upload file img
    private Course uploadFileImg(CourseDto dto, Course entity) throws IOException {
        if (dto.getImageFile() != null && !dto.getImageFile().isEmpty()) {
            String img = fileService.uploadFile(dto.getImageFile());
            entity.setImage(img);
        }
        return entity;
    }

    private String removeAccentsAndSpaces(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String result = pattern.matcher(temp).replaceAll("");
        result = result.replaceAll("đ", "d").replaceAll("Đ", "D");
        result = result.replaceAll("\\s+", "");
        return result;
    }
    @Override
    public CourseDto saveCourse(CourseDto dto) throws IOException {
        Course course = new Course();
        return this.save(course, dto);
    }

    @Override
    public CourseDto upDateCourse(CourseDto dto, Long id) throws CustomException, IOException {
        Course course = courseRepository.findById(id).orElseThrow(() -> new CustomException("Course not found"));
        return this.save(course, dto);
    }


    @Override
    public void deleteCourse(Long id) throws CustomException {
        Course course = courseRepository.findById(id).orElseThrow(() -> new CustomException("Course not found") );
        if(course.getVoided() == null || course.getVoided() == false){
            course.setVoided(true);
        }else {
            course.setVoided(false);
        }
        courseRepository.save(course);
    }

    @Override
    public List<CourseDto> getAllCourse() {
        return courseRepository.getAll();
    }

    @Override
    public CourseDto getCourseDtoById(Long id) throws CustomException {
        return new CourseDto(this.getCourseById(id));
    }

    private Course getCourseById(Long id) throws CustomException {
        Optional<Course> optional = courseRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new CustomException("Course not found");
    }

    @Override
    public Page<CourseDto> pagingCourseDto(Pageable pageable, String title,String home) {
        Page<CourseDto> page = courseRepository.getCoursePage(pageable, title, home);
        return page;
    }

}
