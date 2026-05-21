package com.company.hello.service;

import java.util.HashSet; /* HashSet 컬렉션 클래스 */
import java.util.Set; /* Set 인터페이스 */

import org.springframework.stereotype.Service; /* 서비스 계층 어노테이션 */
import com.company.hello.model.Money; /* 포인트 금액 클래스 */
import com.company.hello.model.UserDTO; /* 사용자 데이터 전송 객체 */

@Service /* 이 클래스가 Spring의 서비스 계층 빈(Bean)임을 선언 */
public class UserService {
	/* 가입된 회원 목록 (DB 대신 메모리 저장) */
	private Set<String> registeredUsers = new HashSet<>(); /* 중복 확인용 회원 ID 저장소 */

	/**
	 * 회원가입 처리
	 * - 신규 회원: 1000 포인트 지급, true 반환
	 * - 기존 회원: 포인트 지급 안 함, false 반환
	 */
	public boolean register(UserDTO dto) { /* 회원가입 처리 메서드, 신규 여부를 boolean으로 반환 */
		/* 기존 가입 회원인지 확인 */
		if (registeredUsers.contains(dto.getUserId())) { /* Set에 해당 userId가 있는지 검색 */
			/* 기존 회원이면 포인트 지급하지 않음 */
			dto.setPoint(0); /* 포인트를 0으로 설정 */
			System.out.println("기존 회원:" + dto.getUserId()); /* 콘솔에 기존 회원 메시지 출력 */
			return false; /* 기존 회원이므로 false 반환 */
		}

		/* 신규 회원이면 포인트 지급 */
		Money welcomePoint = new Money(1000); /* 신규가입 환영 포인트 1000원 생성 */
	/* DTO(Data Transfer Object)
	 * --> 계층간(Controller, Service, Repository)데이터 전송을 위한 객체
	 * --> 가변적일 수 있으며, getter(), setter()를 가진다.
	 * --> 비즈니스 로직을 포함하지 않고 오직 데이터 전달만을 위해 존재
	 *  */
		dto.setPoint(welcomePoint.getAmount()); /* Money 객체에서 금액(1000)을 꺼내 DTO의 포인트에 설정 */
		registeredUsers.add(dto.getUserId()); /* 신규 회원 ID를 Set에 추가 */
		System.out.println("DB 저장 완료:" + dto.getUserId()); /* 콘솔에 저장 완료 메시지 출력 */
		return true; /* 신규 회원이므로 true 반환 */
	}
}
