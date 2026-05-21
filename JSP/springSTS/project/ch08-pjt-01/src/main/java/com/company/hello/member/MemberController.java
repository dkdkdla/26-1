package com.company.hello.member;

import java.text.DateFormat;
import java.util.Date; 
import java.util.Locale; 

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/* 의존객체 */	
@Controller
 public class MemberController {
 /* 의존 객체 생성  */
	/* 필드 생성  */
	//의존 객체 자동 주입 
	@Autowired
	 MemberService memberService;
	@RequestMapping("/signUp")
	public String singUp() {
		return "sign_up"; 
	}
	/*@RequestParam 이용   */
	/* 아이디, 비밀번호, 이메일, 전화번호  */ 
	//@RequestMapping("/signUpConfrim")
	/* 
	 *  public String signUpConfrim(@RequestParam String m_id,
	 *    @RequestParam String m_pw,
	 *    @RequestParam String m_mail, 
	 *    @RequestParam String m_phone)  
	 *  { 
	 *     System.out.println("[MemberController] signUpConfrim()"); 
	 *  
	 *  }
	 */
	/* 생성자 선언  */
	/* 메소드 선언  */ 
}