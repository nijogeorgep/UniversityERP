package com.university.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableJpaAuditing
@EnableAsync(proxyTargetClass = true)
@SpringBootApplication
public class AuthenticationServerApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(AuthenticationServerApplication.class, args);
  }
}
