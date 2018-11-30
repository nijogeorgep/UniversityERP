/**
 * 
 */
package com.university.authserver.controller;

import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 553243
 *
 */
@RestController
public class AuthController {

  private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

  @RequestMapping("/user/me")
  public Principal user(Principal principal) {
    logger.info(principal.getName());
    return principal;
  }
}
