package elearning.repository;

import elearning.dto.CourseDto;
import elearning.model.Course;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	@Query("select new elearning.dto.CourseDto(e, true) from Course e  ")
	List<CourseDto> getAll();
	
	@Query("select new elearning.dto.CourseDto(e, true) from Course e "
			  + " Where ((:home is null OR (e.voided is null or e.voided = false)) AND (:title is null or  e.title like concat('%',:title,'%'))) ")
	Page<CourseDto> getCoursePage(Pageable pageable, String title, String home);
	
	boolean existsByTeacherId(Long teacherId);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM wish_list WHERE course_id = :idDelete", nativeQuery = true)
	void deleteAllWishListByCourseId(@Param("idDelete") Long idDelete);
	
	
}
