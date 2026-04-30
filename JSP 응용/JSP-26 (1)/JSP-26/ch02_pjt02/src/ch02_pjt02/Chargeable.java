package ch02_pjt02;

public interface Chargeable {
	void charge(); /* 충전 동작 기능(추상메서드) */

}
/* 추상 클래스 : 모든 '탈것'의 공통 속성 정의 (미완성)*/
abstract class Vehicle {
	
	/* 필드 선언 */
	String model;
	/* 생성자 선언 */
	public Vehicle(String model){
		this.model = model;
	}
	
	/* 메서드 선언 */
	public void start(){
		System.out.println(model + "의 시동을 겁니다.");
	}
	/* 추상 메서드 */
	public abstract void drive();
	
}
