/**
 * 
 */
package com.university.college.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.university.college.domain.Student;

/**
 * @author 553243
 *
 */
public interface StudentRepository extends MongoRepository<Student, String> {

  Optional<List<Student>> findAllByCollege(String id);

  List<Student> findByActiveStatus(boolean active);

}
