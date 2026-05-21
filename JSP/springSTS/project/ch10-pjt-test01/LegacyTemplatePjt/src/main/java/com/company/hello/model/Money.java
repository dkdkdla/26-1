package com.company.hello.model;
public final class Money { /* 포인트 금액을 표현하는 불변(Immutable) 클래스, final로 상속 불가 */
	/* 필드 선언 */
	private final int amount; /* 금액을 저장하는 변수, final로 한번 설정 후 변경 불가 */
	/* 생성자 선언 */
	public Money(int amount){ /* 생성자: 객체 생성 시 금액을 초기화 */
		this.amount = amount; /* 전달받은 금액을 필드에 저장 */
	}
	
	/* 메소드 선언 */
	public int getAmount() { /* 금액을 반환하는 getter 메서드 */
		return amount;
	}
	
}