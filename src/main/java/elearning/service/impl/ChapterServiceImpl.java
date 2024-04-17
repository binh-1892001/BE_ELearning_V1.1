package elearning.service.impl;

import elearning.dto.ChapterDto;
import elearning.exception.CustomException;
import elearning.model.Chapter;
import elearning.model.Course;
import elearning.repository.ChapterRepository;
import elearning.repository.CourseRepository;
import elearning.repository.LessonRepository;
import elearning.service.ChapterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChapterServiceImpl implements ChapterService {
	
	@Autowired
	ChapterRepository chapterRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	private LessonRepository lessonRepository;
	
	public ChapterDto save(Chapter entity, ChapterDto dto) throws CustomException {
		
		entity.setTitle(dto.getTitle());
		entity.setDescription(dto.getDescription());
		
		if (dto.getCourse() == null || dto.getCourse().getId() == null) {
			throw new CustomException("Course is not null");
		}
		Course course = courseRepository.findById(dto.getCourse().getId()).orElse(null);
		if (course == null) {
			throw new CustomException("Course is not null");
		}
		entity.setCourse(course);
		entity = chapterRepository.save(entity);
		return new ChapterDto(entity);
	}
	
	@Override
	public ChapterDto saveChapter(ChapterDto dto) throws CustomException {
		Chapter entity = new Chapter();
		return this.save(entity, dto);
	}
	
	@Override
	public ChapterDto upDateChapter(ChapterDto dto, Long id) throws CustomException {
		Chapter chapter = chapterRepository.findById(id).orElseThrow(() -> new CustomException("Chapter not found"));
		return this.save(chapter, dto);
	}
	
	@Override
	public void deleteChapter(Long id) throws CustomException {
		Chapter chapter = chapterRepository.findById(id).orElseThrow(() -> new CustomException("Chapter not found"));
		if (lessonRepository.existsByChapterId(id)) {
			chapter.setVoided(!chapter.getVoided());
			chapterRepository.save(chapter);
		} else {
			chapterRepository.deleteById(id);
		}
	}
	
	@Override
	public List<ChapterDto> getAllChapter() {
		return chapterRepository.getAll();
	}
	
	@Override
	public ChapterDto getChapterDtoById(Long id) throws CustomException {
		return new ChapterDto(this.getChapterById(id));
	}
	
	
	private Chapter getChapterById(Long id) throws CustomException {
		Optional<Chapter> optional = chapterRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		throw new CustomException("Chapter not found");
	}
	
	@Override
	public Page<ChapterDto> pagingChapterDto(Pageable pageable, String title) {
		Page<ChapterDto> page = chapterRepository.getChapterPage(pageable, title);
		return page;
	}
	
	@Override
	public List<ChapterDto> getChaptersByCourseId(Long courseId) {
		return chapterRepository.getChaptersByCourseId(courseId);
	}
	
}
