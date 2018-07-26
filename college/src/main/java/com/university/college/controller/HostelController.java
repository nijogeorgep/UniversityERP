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
import com.university.college.domain.Hostel;
import com.university.college.domain.Student;
import com.university.college.exceptions.CollegeNotFoundException;
import com.university.college.exceptions.HostelNotFoundException;
import com.university.college.repository.HostelRepository;
import com.university.college.repository.StudentRepository;

/**
 * @author 553243
 *
 */
@RestController
public class HostelController {

  @Autowired
  private HostelRepository hostelRepository;

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private MongoOperations mongoOperations;

  @GetMapping("/hostels")
  public ResponseEntity<List<Hostel>> getHostels() {
    List<Hostel> hostels = hostelRepository.findAll();
    if (hostels.isEmpty()) {
      return new ResponseEntity<List<Hostel>>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<List<Hostel>>(hostels, HttpStatus.OK);
  }

  @GetMapping("/hostels/active")
  public ResponseEntity<List<Hostel>> getActiveHostels() {
    List<Hostel> activeHostels = hostelRepository.findByActiveStatus(true);
    if (activeHostels.isEmpty()) {
      return new ResponseEntity<List<Hostel>>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<List<Hostel>>(activeHostels, HttpStatus.OK);
  }

  @GetMapping("/hostels/inactive")
  public ResponseEntity<List<Hostel>> getInactiveHostels() {
    List<Hostel> inactiveHostels = hostelRepository.findByActiveStatus(false);
    if (inactiveHostels.isEmpty()) {
      return new ResponseEntity<List<Hostel>>(inactiveHostels, HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<List<Hostel>>(HttpStatus.OK);
  }

  @GetMapping("/hostels/{hostelId}")
  public ResponseEntity<Hostel> getHostel(@PathVariable String hostelId)
      throws HostelNotFoundException {
    Optional<Hostel> hostel = hostelRepository.findById(hostelId);

    if (!hostel.isPresent()) {
      return new ResponseEntity<Hostel>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<Hostel>(hostel.get(), HttpStatus.OK);
  }

  @GetMapping("/hostels/{hostelId}/students")
  public ResponseEntity<List<Student>> getStudentsByHostel(@PathVariable String hostelId)
      throws CollegeNotFoundException, HostelNotFoundException {
    Optional<Hostel> hostelOptional = hostelRepository.findById(hostelId);
    if (!hostelOptional.isPresent()) {
      throw new CollegeNotFoundException("College Not Found");
    }

    Optional<List<Student>> studentsOptional =
        studentRepository.findAllByCollege(hostelOptional.get().getId());
    if (!studentsOptional.isPresent()) {
      throw new HostelNotFoundException("Hostels Not Found");
    }

    return new ResponseEntity<List<Student>>(studentsOptional.get(), HttpStatus.OK);
  }

  @PostMapping("/hostel")
  public ResponseEntity<Void> saveHostel(@RequestBody Hostel hostel) {
    Hostel hostelsExists = mongoOperations
        .findOne(Query.query(Criteria.where("_id").is(hostel.getId())), Hostel.class);

    if (hostelsExists != null) {
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    hostelRepository.save(hostel);

    HttpHeaders headers = new HttpHeaders();
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(hostel.getId()).toUri();
    headers.setLocation(location);

    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @PutMapping("/hostels/{hostelId}")
  public ResponseEntity<Hostel> updateHostel(@PathVariable String hostelId,
      @RequestBody Hostel hostel) {
    Optional<Hostel> hostelOptional = hostelRepository.findById(hostelId);
    if (!hostelOptional.isPresent()) {
      return new ResponseEntity<Hostel>(HttpStatus.NOT_FOUND);
    }
    hostel.setId(hostelId);

    hostelRepository.save(hostel);

    return new ResponseEntity<Hostel>(hostelOptional.get(), HttpStatus.OK);
  }

  @DeleteMapping("/hostels/{hostelId}")
  public ResponseEntity<Hostel> deleteHostel(@PathVariable String hostelId) {
    Optional<Hostel> hostelOptional = hostelRepository.findById(hostelId);
    if (!hostelOptional.isPresent()) {
      return new ResponseEntity<Hostel>(HttpStatus.NOT_FOUND);
    }
    hostelRepository.deleteById(hostelId);
    return new ResponseEntity<Hostel>(HttpStatus.NO_CONTENT);
  }
}
