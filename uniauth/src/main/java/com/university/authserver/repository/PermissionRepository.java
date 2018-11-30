/**
 * 
 */
package com.university.authserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.university.authserver.data.Permission;

/**
 * @author 553243
 *
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

  Permission findByName(String name);

}
