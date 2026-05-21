package com.company.hello;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page. 애플리케이션 홈페이지 요청 처리.
 */
@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	/**
	 * Simply selects the home view to render by returning its name. 이름을 반환하여 렌더링할 홈 뷰를 선택하기만 하면 됩니다.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		return "home";
		//return calculator; 
	}
	/* Spring MVC 계산기  */
	//계산기 입력 화면 
	@RequestMapping(value="/calc", method=RequestMethod.GET)
	public String calcMain() {
		return "calculator"; 
	}
	//계산기 실행 (주소: /calc, method=POST 방식)
	@RequestMapping(value="/calc", method=RequestMethod.POST)
	public String calculator(
			//@RequestParam: 사용자가 브라우저에 입력값(num1, num2, operator) 
			//를 Java 변수로 직접 매핑에 온다. 
			@RequestParam("num1") double num1, 
			@RequestParam("num2") double num2, 
			@RequestParam("operator") String operator, Model model	) {
		double result = 0; 
		if(operator.equals("+")) result = num1 + num2; 
		else if(operator.equals("-")) result = num1 - num2; 
		else if(operator.equals("*")) result = num1 * num2; 
		else if(operator.equals("/")) result = (num2 != 0) ? num1 / num2 : 0;
		model.addAttribute("num1", num1); 
		model.addAttribute("num2", num2); 
		model.addAttribute("operator", operator); 
		model.addAttribute("result", result); 
		return "calculator"; 
	}
}
