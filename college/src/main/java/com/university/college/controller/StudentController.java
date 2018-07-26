/**
 * 
 */
package com.university.college.controller;

import java.net.URI;
import java.time.LocalDate;
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
import com.university.college.dto.StudentDto;
import com.university.college.exceptions.StudentNotFoundException;
import com.university.college.repository.CollegeRepository;
import com.university.college.repository.HostelRepository;
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
  private HostelRepository hostelRepository;

  @Autowired
  private CollegeRepository collegeRepository;

  @Autowired
  private MongoOperations mongoOperations;

  @GetMapping("/students")
  public ResponseEntity<List<Student>> getStudents() {
    List<Student> students = studentRepository.findAll();
    if (students.isEmpty()) {
      return new ResponseEntity<List<Student>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
  }

  @GetMapping("/students/active")
  public ResponseEntity<List<Student>> getActiveStudents() {
    List<Student> activeStudents = studentRepository.findByActiveStatus(true);
    if (activeStudents.isEmpty()) {
      return new ResponseEntity<List<Student>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Student>>(activeStudents, HttpStatus.OK);
  }

  @GetMapping("/students/inactive")
  public ResponseEntity<List<Student>> getInactiveStudents() {
    List<Student> inactiveStudents = studentRepository.findByActiveStatus(false);
    if (inactiveStudents.isEmpty()) {
      return new ResponseEntity<List<Student>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Student>>(inactiveStudents, HttpStatus.OK);
  }

  @GetMapping("/students/{studentId}")
  public ResponseEntity<Student> getStudent(@PathVariable String studentId)
      throws StudentNotFoundException {
    Optional<Student> student = studentRepository.findById(studentId);

    if (!student.isPresent()) {
      return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<Student>(student.get(), HttpStatus.OK);
  }

  @PostMapping("/student")
  public ResponseEntity<Void> saveStudent(@RequestBody StudentDto studentDto) {
    Student studentsExists = mongoOperations
        .findOne(Query.query(Criteria.where("rollNo").is(studentDto.getRollNo())), Student.class);

    if (studentsExists != null) {
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }
    studentsExists = new Student();
    studentsExists.setName(studentDto.getName());
    studentsExists.setEmail(studentDto.getEmail());
    studentsExists.setMobileNumber(studentDto.getMobileNumber());
    studentsExists.setRollNo(studentDto.getRollNo());
    studentsExists.setDob(studentDto.getDob());
    studentsExists.setYear(studentDto.getYear());
    studentsExists.setYearOfJoining(studentDto.getYearOfJoining());
    studentsExists.setActiveStatus(studentDto.isActiveStatus());
    studentsExists.setHostel(hostelRepository.findById(studentDto.getHostel()).get());
    studentsExists.setCollege(collegeRepository.findById(studentDto.getCollege()).get());
    studentsExists.setCreatedOn(LocalDate.now());
    studentRepository.save(studentsExists);

    HttpHeaders headers = new HttpHeaders();
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(studentsExists.getId()).toUri();
    headers.setLocation(location);

    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @PutMapping("/students/{studentId}")
  public ResponseEntity<Student> updateStudent(@PathVariable String studentId,
      @RequestBody StudentDto studentDto) {
    Optional<Student> studentOptional = studentRepository.findById(studentId);
    if (!studentOptional.isPresent()) {
      return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
    }
    Student student = studentOptional.get();
    student.setId(studentId);
    student.setName(studentDto.getName());
    student.setEmail(studentDto.getEmail());
    student.setMobileNumber(studentDto.getMobileNumber());
    student.setRollNo(studentDto.getRollNo());
    student.setDob(studentDto.getDob());
    student.setYear(studentDto.getYear());
    student.setYearOfJoining(studentDto.getYearOfJoining());
    student.setActiveStatus(studentDto.isActiveStatus());
    student.setHostel(hostelRepository.findById(studentDto.getHostel()).get());
    student.setCollege(collegeRepository.findById(studentDto.getCollege()).get());
    student.setUpdatedOn(LocalDate.now());
    studentRepository.save(student);

    return new ResponseEntity<Student>(student, HttpStatus.OK);
  }

  @DeleteMapping("/students/{studentId}")
  public ResponseEntity<Student> deleteStudent(@PathVariable String studentId) {
    Optional<Student> studentOptional = studentRepository.findById(studentId);
    if (!studentOptional.isPresent()) {
      return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
    }
    studentRepository.deleteById(studentId);
    return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
  }
}
