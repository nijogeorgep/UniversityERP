/**
 * 
 */
package com.university.college.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;

/**
 * @author 553243
 *
 */
@Api(basePath = "/", value = "/", description = "Home Controller")
@Controller
public class HomeController {

  @RequestMapping("/")
  public String home() {
    return "redirect:swagger-ui.html";
  }
}
