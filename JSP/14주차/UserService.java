package com.company.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.hello.dao.UserDAO;
import com.company.hello.model.UserDTO;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO; /* UserDAO 자동 주입 */

    /* 필드 선언 */

    /* 생성자 선언 */

    /* 메소드 선언 */
    public int signUp(UserDTO user) {
        /* 1. 중복 확인: DB에 이미 같은 userId 가 있으면 0 반환 */
        if (userDAO.isExist(user.getUserId())) {
            return 0; /* 기존 이미 가입된 회원 */
        }
        /* 2. 신규 회원 -> DB에 저장 */
        return userDAO.insertUser(user);
    }
}
