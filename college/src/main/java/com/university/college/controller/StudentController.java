/**
 * 
 */
package com.university.college.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.university.college.domain.Student;
import com.university.college.exceptions.StudentNotFoundException;
import com.university.college.repository.StudentRepository;

/**
 * @author 553243
 *
 */
@RestController
public class StudentController {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private MongoOperations mongoOperations;

  @GetMapping("/students")
  public ResponseEntity<List<Student>> getStudents() {
    List<Student> students = studentRepository.findAll();
    if (students.isEmpty()) {
      return new ResponseEntity<List<Student>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Student>>(HttpStatus.OK);
  }

  @GetMapping("/students/{id}")
  public ResponseEntity<Student> getStudent(@PathVariable String studentId)
      throws StudentNotFoundException {
    Optional<Student> student = studentRepository.findById(studentId);

    if (!student.isPresent()) {
      return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<Student>(student.get(), HttpStatus.OK);
  }

  @PostMapping("/student")
  public ResponseEntity<Void> saveStudent(@RequestBody Student student) {
    Student studentsExists = mongoOperations
        .findOne(Query.query(Criteria.where("rollNo").is(student.getRollNo())), Student.class);

    if (studentsExists != null) {
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    studentRepository.save(student);

    HttpHeaders headers = new HttpHeaders();
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(student.getId()).toUri();
    headers.setLocation(location);

    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @PutMapping("/students/{id}")
  public ResponseEntity<Student> updateStudent(@PathVariable String studentId,
      @RequestBody Student student) {
    Optional<Student> studentOptional = studentRepository.findById(studentId);
    if (!studentOptional.isPresent()) {
      return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
    }
    student.setId(studentId);

    studentRepository.save(student);

    return new ResponseEntity<Student>(studentOptional.get(), HttpStatus.OK);
  }

  @DeleteMapping("/students/{id}")
  public ResponseEntity<Student> deleteStudent(@PathVariable String studentId) {
    Optional<Student> studentOptional = studentRepository.findById(studentId);
    if (!studentOptional.isPresent()) {
      return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
    }
    studentRepository.deleteById(studentId);
    return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
  }
}
