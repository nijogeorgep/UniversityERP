/**
 * 
 */
package com.university.college.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.university.college.domain.College;

/**
 * @author 553243
 *
 */
public interface CollegeRepository extends MongoRepository<College, String> {

}
