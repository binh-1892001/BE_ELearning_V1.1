package elearning.service;

import elearning.dto.TeacherDto;
import elearning.exception.CustomException;

import java.util.List;

public interface ITeacherService {

    List<TeacherDto> findAll();

    TeacherDto findById(Long idTeacher) throws CustomException;

    TeacherDto save(TeacherDto dto);

    TeacherDto update(TeacherDto dto, Long idTeacher) throws CustomException;

    String deleteById(Long idTeacher) throws CustomException;

}
