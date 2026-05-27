package com.company.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.hello.dao.UserDAO;
import com.company.hello.model.UserDTO;

@Service
public class UserService {
  @Autowired
  /* 필드 선언 */
  private UserDAO userDAO;
  
  public int signUp(UserDTO user) {
	/* 1.중복체크 where */
	if (userDAO.isExist(user.getUserId())) {
	  return 0; /* 기존 이미 가입된 회원 */	
	}
	/* 신규회원 -> 포인트 지급 */
	user.setPoint(1000);
	return userDAO.insertUser(user);
	
  }

}
