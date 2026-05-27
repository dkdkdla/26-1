package com.company.hello.model;

public class UserDTO {
  /* 필드 선언 */
  private String UserId;
  private String name;
  private int point; /* 가입후 부여된 포인트 저장용 */
  /* 생성자 선언 */
  /* 메소드 선언 */
public String getUserId() {
	return UserId;
}
public void setUserId(String userId) {
	UserId = userId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getPoint() {
	return point;
}
public void setPoint(int point) {
	this.point = point;
}	
  
  
  
}
