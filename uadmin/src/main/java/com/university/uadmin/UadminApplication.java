package com.university.uadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import de.codecentric.boot.admin.server.config.EnableAdminServer;

@Configuration
@SpringBootApplication
@EnableAdminServer
public class UadminApplication {

  public static void main(String[] args) {
    SpringApplication.run(UadminApplication.class, args);
  }
}
