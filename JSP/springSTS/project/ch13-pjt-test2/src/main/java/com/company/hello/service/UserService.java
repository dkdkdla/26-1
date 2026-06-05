package com.company.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.hello.dao.UserDAO;
import com.company.hello.model.Money;
import com.company.hello.model.UserDTO;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO; /* UserDAO 자동 주입 */

    /* 필드 선언 */

    /* 생성자 선언 */

    /* 메소드 선언 */
    public int signUp(UserDTO user) {
        System.out.println("[UserService] signUp()");
        System.out.println("userId : "   + user.getUserId());
        System.out.println("name : "     + user.getName());
        System.out.println("email : "    + user.getEmail());
        System.out.println("phone : "    + user.getPhone());

        /* 1. 중복 확인: DB에 이미 같은 userId 가 있으면 0 반환 */
        if (userDAO.isExist(user.getUserId())) {
            System.out.println("[UserService] 이미 가입된 회원 -> 포인트 미지급");
            return 0; /* 기존 이미 가입된 회원 -> 포인트 미지급 */
        }

        /* 2. 신규 회원 -> 포인트 1000 지급 후 DB 저장 */
        Money welcomePoint = new Money(1000);
        user.setPoint(welcomePoint.getAmount());
        System.out.println("[UserService] 신규 회원 -> point : " + user.getPoint());

        return userDAO.insertUser(user);
    }
}