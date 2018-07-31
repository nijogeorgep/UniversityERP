package com.university.college;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CollegeApplication {

  public static void main(String[] args) {
    SpringApplication.run(CollegeApplication.class, args);
  }
}
