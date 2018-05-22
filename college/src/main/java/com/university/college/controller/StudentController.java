/**
 * 
 */
package com.university.college.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.university.college.domain.Student;
import com.university.college.repository.StudentRepository;

/**
 * @author 553243
 *
 */
@RestController
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	@GetMapping("/students")
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	@GetMapping("/students/:id")
	public Optional<Student> getStudent(@RequestParam String studentId) {
		return studentRepository.findById(studentId);
	}
}
