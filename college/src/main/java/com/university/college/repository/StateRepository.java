/**
 * 
 */
package com.university.college.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.university.college.domain.State;

/**
 * @author 553243
 *
 */
public interface StateRepository extends MongoRepository<State, String> {

}
