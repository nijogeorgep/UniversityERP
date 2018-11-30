/**
 * 
 */
package com.university.authserver.service.impl;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import com.university.authserver.data.UserDetails;
import com.university.authserver.repository.UserRepository;
import com.university.authserver.service.AuthorizationService;

/**
 * @author 553243
 *
 */
@Service("authorizationService")
@Transactional(
    rollbackFor = {
            ConstraintViolationException.class
    }
)
@Validated
public class AutorizationServiceImpl implements AuthorizationService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails getUser() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Long getUserId() {
    return userRepository
        .findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get()
        .getId();
  }

  @Override
  public String getUsername() {
    return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
        .getUsername();
  }

}
