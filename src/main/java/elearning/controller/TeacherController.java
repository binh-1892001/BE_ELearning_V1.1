package elearning.controller;

import elearning.dto.TeacherDto;
import elearning.exception.CustomException;
import elearning.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherController {
	
	@Autowired
	private ITeacherService teacherService;
	
	@GetMapping
	public ResponseEntity<List<TeacherDto>> findAll() {
		return new ResponseEntity<>(teacherService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{idTeacher}")
	public ResponseEntity<TeacherDto> findById(@PathVariable Long idTeacher) throws CustomException {
		return new ResponseEntity<>(teacherService.findById(idTeacher), HttpStatus.OK);
	}
	
	@PostMapping
//	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','SUB_ADMIN')")
	public ResponseEntity<TeacherDto> handleAddTeacher(@ModelAttribute TeacherDto dto) {
		return new ResponseEntity<>(teacherService.save(dto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{idTeacher}")
//	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','SUB_ADMIN')")
	public ResponseEntity<TeacherDto> handleUpdateTeacher(@ModelAttribute TeacherDto dto, @PathVariable Long idTeacher) throws CustomException {
		return new ResponseEntity<>(teacherService.update(dto, idTeacher), HttpStatus.OK);
	}
	
	@DeleteMapping("/{idTeacher}/delete")
//	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','SUB_ADMIN')")
	public ResponseEntity<String> handleDeleteTeacher(@PathVariable Long idTeacher) throws CustomException {
		return new ResponseEntity<>(teacherService.deleteById(idTeacher), HttpStatus.OK);
	}
	
	
}
