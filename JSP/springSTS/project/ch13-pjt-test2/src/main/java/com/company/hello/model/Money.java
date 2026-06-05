package com.company.hello.model;

public final class Money {
    /* 필드 선언 */
    private final int amount; /* 금액을 저장하는 변수, final로 한번 설정 후 변경 불가 */

    /* 생성자 선언 */
    public Money(int amount) {
        this.amount = amount;
    }

    /* 메소드 선언 */
    public int getAmount() {
        return amount;
    }
}
