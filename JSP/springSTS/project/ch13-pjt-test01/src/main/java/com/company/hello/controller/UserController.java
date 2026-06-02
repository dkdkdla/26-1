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
    private UserService userService; /* Spring이 UserService 객체를 자동으로 주입 */

    /* 가입 페이지 이동 */
    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String joinPage() {
        return "join"; /* join.jsp 뷰를 반환 */
    }

    /* 가입 처리 */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPage(UserDTO user, Model model) {
        int result = userService.signUp(user); /* 1:신규성공 / 0:중복 / -1:DB오류 */
        model.addAttribute("user",   user);
        model.addAttribute("result", result);
        return "result"; /* result.jsp 뷰를 반환 */
    }
}
