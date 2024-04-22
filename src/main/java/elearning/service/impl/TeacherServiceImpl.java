package elearning.service.impl;

import elearning.dto.TeacherDto;
import elearning.exception.CustomException;
import elearning.model.Teacher;
import elearning.repository.CourseRepository;
import elearning.repository.TeacherRepository;
import elearning.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService {
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private FileService fileService;
	
	@Override
	public List<TeacherDto> findAll() {
		return teacherRepository.findAll().stream().map(TeacherDto::new).toList();
	}
	
	@Override
	public TeacherDto findById(Long idTeacher) throws CustomException {
		return teacherRepository.findById(idTeacher).map(TeacherDto::new).orElseThrow(() -> new CustomException("teacher not found"));
	}
	
	@Override
	public TeacherDto save(TeacherDto dto) {
		Teacher teacher = Teacher.builder()
				  .name(dto.getName())
				  .image(fileService.uploadFile(dto.getImageFile()))
				  .specialize(dto.getSpecialize())
				  .status(true)
				  .build();
		return new TeacherDto(teacherRepository.save(teacher));
	}
	
	@Override
	public TeacherDto update(TeacherDto dto, Long idTeacher) throws CustomException {
		Teacher teacher = teacherRepository.findById(idTeacher).orElseThrow(() -> new CustomException("teacher not found"));
		teacher.setName(dto.getName());
		if (dto.getImageFile() != null) {
			teacher.setImage(fileService.uploadFile(dto.getImageFile()));
		}
		teacher.setSpecialize(dto.getSpecialize());
		return new TeacherDto(teacherRepository.save(teacher));
	}
	
	@Override
	public String deleteById(Long idTeacher) throws CustomException {
		if (teacherRepository.findById(idTeacher).isPresent()) {
			if (courseRepository.existsByTeacherId(idTeacher)) {
				Teacher teacher = teacherRepository.findById(idTeacher).orElseThrow(() -> new CustomException("teacher not found"));
				teacher.setStatus(!teacher.getStatus());
				teacherRepository.save(teacher);
			} else {
				teacherRepository.deleteById(idTeacher);
			}
			return "delete teacher successfully";
		}
		throw new CustomException("teacher not found");
	}
}
