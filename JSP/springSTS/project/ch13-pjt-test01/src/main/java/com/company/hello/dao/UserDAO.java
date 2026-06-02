package com.company.hello.dao;

import java.sql.*;
import org.springframework.stereotype.Repository;
import com.company.hello.model.UserDTO;

@Repository
public class UserDAO {
    /* 필드 선언 */
    private String url  = "jdbc:mysql://localhost:3306/my_app_db?useUnicode=true&characterEncoding=utf8";
    private String dbId = "pen";  /* DB 관리 계정 */
    private String dbPw = "1234"; /* 비밀번호 */

    /* 생성자 선언 */
    public UserDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); /* JDBC 드라이버 로드 */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* 메소드 선언 */

    /* 회원 중복 확인: userId 가 DB에 있으면 true */
    public boolean isExist(String userId) {
        String sql = "select count(*) from users where userId = ?";
        try (Connection conn = DriverManager.getConnection(url, dbId, dbPw);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* 신규 회원 DB 저장: 성공 시 1 반환 */
    public int insertUser(UserDTO user) {
        String sql = "insert into users (userId, password, name, email, phone)"
                   + " values (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, dbId, dbPw);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getPhone());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
