/**
 * 
 */
package com.university.college.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.university.college.domain.State;

/**
 * @author 553243
 *
 */
public interface StateRepository extends MongoRepository<State, String> {

  List<State> findByActiveStatus(boolean active);

}
