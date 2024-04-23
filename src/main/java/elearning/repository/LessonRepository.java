package elearning.repository;

import elearning.dto.LessonDto;
import elearning.model.Lesson;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
	@Query("select new elearning.dto.LessonDto(e, true) from Lesson e  ")
	List<LessonDto> getAll();
	
	@Query("select new elearning.dto.LessonDto(e, true) from Lesson e"
			  + " Where  ( :title is null or  e.title like concat('%',:title,'%'))")
	Page<LessonDto> getLessonPage(Pageable pageable, String title);
	
	boolean existsByChapterId(Long chapterId);
	
	@Transactional
	void deleteAllByChapterCourseId(Long courseId);
}
