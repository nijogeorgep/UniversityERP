/**
 * 
 */
package com.university.college.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.university.college.domain.Hostel;

/**
 * @author 553243
 *
 */
public interface HostelRepository extends MongoRepository<Hostel, String> {

}
