package com.company.hello.model;
public class UserDTO { /* 사용자 정보를 담아 계층 간 전달하는 DTO 클래스 */
	/* 필드 선언 */
	private String userId; /* 사용자 아이디를 저장하는 변수 */
	private String name; /* 사용자 이름을 저장하는 변수 */
	private int point; /* 가입 후 저장되는 포인트 저장용 변수 선언 */
	/* 생성자 선언 */
	
	/* 메소드 선언 */
	
	public String getUserId() { /* userId 값을 반환하는 getter 메서드 */
		return userId;
	}
	public void setUserId(String userId) { /* userId 값을 설정하는 setter 메서드 */
		this.userId = userId;
	}
	public String getName() { /* name 값을 반환하는 getter 메서드 */
		return name;
	}
	public void setName(String name) { /* name 값을 설정하는 setter 메서드 */
		this.name = name;
	}
	public int getPoint() { /* point 값을 반환하는 getter 메서드 */
		return point;
	}
	public void setPoint(int point) { /* point 값을 설정하는 setter 메서드 */
		this.point = point;
	}
	
	
}