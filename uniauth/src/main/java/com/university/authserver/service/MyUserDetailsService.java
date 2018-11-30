/**
 * 
 */
package com.university.authserver.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.university.authserver.data.Permission;
import com.university.authserver.data.Role;
import com.university.authserver.repository.UserRepository;

/**
 * @author 553243
 *
 */
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

  private static final Logger LOGGER = LoggerFactory.getLogger(MyUserDetailsService.class);

  @Autowired
  private UserRepository userRepository;

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.
   * String)
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    LOGGER.info("Called with username {}", username);

    Optional<com.university.authserver.data.UserDetails> userDetail =
        userRepository.findByUsernameIgnoreCaseAndEnabledTrue(username);

    userDetail.orElseThrow(
        () -> new UsernameNotFoundException("No User found with username " + username));

    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    for (Role role : userDetail.get().getRoles()) {
      for (Permission permission : role.getPermissions()) {
        grantedAuthorities.add(new SimpleGrantedAuthority(permission.getAuthority()));
      }
    }

    return new User(userDetail.get().getUsername(), userDetail.get().getPassword(),
        grantedAuthorities);

  }

}
