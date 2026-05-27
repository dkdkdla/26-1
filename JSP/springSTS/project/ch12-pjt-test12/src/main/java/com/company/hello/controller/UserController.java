package com.company.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.hello.model.UserDTO;
import com.company.hello.service.UserService;

@Controller
public class UserController {
  @Autowired
  private UserService userService;
  
  /* 가입페이지 이동 */
  @RequestMapping(value="/join", method = RequestMethod.GET)
  public String joinPage() {
    return "join";
  }
  
  /* 가입 처리 */
  @RequestMapping(value="/register", method = RequestMethod.POST)
  public String registerPage(UserDTO user, Model model) {
	int result = userService.signUp(user);
	model.addAttribute("user", user);
	model.addAttribute("result",result);
	return "register";
	
  }
  
	
}
