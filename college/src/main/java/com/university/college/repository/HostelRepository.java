/**
 * 
 */
package com.university.college.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.university.college.domain.Hostel;

/**
 * @author 553243
 *
 */
public interface HostelRepository extends MongoRepository<Hostel, String> {

  Optional<List<Hostel>> findAllByCollege(String id);

  List<Hostel> findByActiveStatus(boolean active);

}
