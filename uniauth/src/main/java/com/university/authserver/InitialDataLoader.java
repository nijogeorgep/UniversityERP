/**
 * 
 */
package com.university.authserver;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.university.authserver.data.Permission;
import com.university.authserver.data.Role;
import com.university.authserver.data.UserDetails;
import com.university.authserver.repository.PermissionRepository;
import com.university.authserver.repository.RoleRepository;
import com.university.authserver.repository.UserRepository;

/**
 * @author 553243
 *
 */
@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

  private static final Logger LOGGER = LoggerFactory.getLogger(InitialDataLoader.class);

  boolean alreadySetup = false;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PermissionRepository permissionRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    try {
      if (alreadySetup) {
        return;
      }

      Permission readPermission = createPermissionIfNotFound("READ_PRIVILEGE");
      Permission writePermission = createPermissionIfNotFound("WRITE_PRIVILEGE");

      List<Permission> adminPermissions = Arrays.asList(readPermission, writePermission);

      createRoleIfNotFound("ROLE_ADMIN", adminPermissions);
      createRoleIfNotFound("ROLE_USER", Arrays.asList(readPermission));

      Role adminRole = roleRepository.findByName("ROLE_ADMIN");

      UserDetails user = new UserDetails();
      user.setPassword(passwordEncoder.encode("test"));
      user.setUsername("test@test.com");
      user.setRoles(Arrays.asList(adminRole));
      user.setEnabled(true);
      user.setCreated(new Date());
      userRepository.save(user);
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
    }
    alreadySetup = true;

  }

  @Transactional
  private Role createRoleIfNotFound(String name, List<Permission> permissions) {

    Role role = roleRepository.findByName(name);
    if (role == null) {
      role = new Role(name, new Date());
      role.setPermissions(permissions);
      roleRepository.save(role);
    }
    return role;
  }

  @Transactional
  private Permission createPermissionIfNotFound(String name) {

    Permission permission = permissionRepository.findByName(name);
    if (permission == null) {
      permission = new Permission(name, new Date());
      permissionRepository.save(permission);
    }
    return permission;
  }
}
