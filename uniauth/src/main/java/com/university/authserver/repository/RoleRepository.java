/**
 * 
 */
package com.university.authserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.university.authserver.data.Role;

/**
 * @author 553243
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  Role findByName(String name);

}
