package com.company.hello.dao;
import java.sql.*;
import org.springframework.stereotype.Repository;
import com.company.hello.model.UserDTO;

@Repository
public class UserDAO {
  /* 필드 선언 */
  private String url = "jdbc:mysql://localhost:3306/my_app_db?useUnicode=true&characterEncoding=utf8";
  private String dbId = "pen"; /* DB 관리 계정 */
  private String dbPw = "1234"; /* 비밀번호 */
  
  /* 생성자 선언 */
  public UserDAO(){
	/* 예외처리 문 */
	try {
	  Class.forName("com.mysql.jdbc.Driver");
		
	} catch(Exception e) {
	   e.printStackTrace();
	}
	
  }

  /* 메소드 선언 */
  public boolean isExist(String userId) {
	String sql = "select count(*) from userswhere userId = ?";
	try(Connection conn = DriverManager.getConnection(url, dbId, dbPw); 
	  PreparedStatement pstmt = conn.prepareStatement(sql))
	{
	  pstmt.setString(1, userId);
	  ResultSet rs = pstmt.executeQuery();
	  if(rs.next())return rs.getInt(1)>0;
	}catch(Exception e) {
	  e.printStackTrace();
	}
	return false;
  }

  public int insertUser(UserDTO user) {
	String sql = "insert into users (userId, name, point) values (?,?,?)";
	try(Connection conn = DriverManager.getConnection(url, dbId, dbPw);
		 PreparedStatement pstmt = conn.prepareStatement(sql)) {
	  pstmt.setString(1, user.getUserId());
	  pstmt.setString(2, user.getName());
	  pstmt.setInt(3, user.getPoint());
	  return pstmt.executeUpdate();
		
	} catch(Exception e) {
		e.printStackTrace();
	}
	return -1;
	
  }


}
