/**
 * 
 */
package com.university.college.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.university.college.domain.College;

/**
 * @author 553243
 *
 */
public interface CollegeRepository extends MongoRepository<College, String> {

  List<College> findByActiveStatus(boolean active);

  Optional<List<College>> findAllByCity(String id);

}
