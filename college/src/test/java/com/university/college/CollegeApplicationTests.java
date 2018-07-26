package com.university.college;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import com.university.college.config.MongoConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MongoConfig.class)
@TestPropertySource(locations = "classpath:application.yml")
public class CollegeApplicationTests {

  @Test
  public void contextLoads() {}

}
