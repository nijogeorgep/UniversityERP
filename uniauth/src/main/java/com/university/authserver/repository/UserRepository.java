/**
 * 
 */
package com.university.authserver.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.university.authserver.data.UserDetails;

/**
 * @author 553243
 *
 */
@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {

  Optional<UserDetails> findByUsername(String username);

  Optional<UserDetails> findByUsernameIgnoreCaseAndEnabledTrue(String username);
}
