package com.company.hello.controller;
import org.springframework.beans.factory.annotation.Autowired; /* 의존성 자동 주입 어노테이션 */
import org.springframework.stereotype.Controller; /* 컨트롤러 어노테이션 */
import org.springframework.ui.Model; /* 뷰에 데이터를 전달하기 위한 Model 객체 */
import org.springframework.web.bind.annotation.RequestMapping; /* URL 매핑 어노테이션 */
import org.springframework.web.bind.annotation.RequestMethod; /* HTTP 메서드 지정용 */

import com.company.hello.model.UserDTO; /* 사용자 데이터 전송 객체 */
import com.company.hello.service.UserService; /* 회원가입 서비스 클래스 */

@Controller /* 이 클래스가 Spring MVC 컨트롤러임을 선언 */
public class UserController {
	@Autowired /* Spring이 UserService 객체를 자동으로 주입 */
	private UserService userService; /* 회원가입 비즈니스 로직을 처리하는 서비스 객체 */
	
	/* 가입 페이지 이동 */
	@RequestMapping(value = "/join", method = RequestMethod.GET) /* GET 방식으로 /join 요청 시 실행 */
	public String joinPage() {
		return "join"; /* join.jsp 뷰를 반환 */
	}
	
	/* 가입 처리 */
	@RequestMapping(value = "/register", method = RequestMethod.POST) /* POST 방식으로 /register 요청 시 실행 */
	public String register(UserDTO userDTO, Model model) { /* 폼 데이터가 UserDTO 객체에 자동 바인딩 */
		/* 신규 회원인지 기존 회원인지 확인 */
		boolean isNew = userService.register(userDTO); /* 서비스 계층에서 회원가입 처리, 신규 여부 반환 */
		model.addAttribute("user", userDTO); /* 뷰에서 사용할 수 있도록 user 객체를 Model에 저장 */
		model.addAttribute("isNew", isNew); /* 신규/기존 회원 여부를 Model에 저장 */
		return "result"; /* result.jsp 뷰를 반환 */
	}
}
