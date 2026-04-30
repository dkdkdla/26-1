package ch02_pjt02;
/* 클래스는 사물(사람, 객체)의 틀을 설계 하는 것이다. */
//import java.io.*;
public class Car {
	/* 자동차의 몸체, 자동차 모델, 컬러, 엔진배기량, 속도*/
	/* 필드(변수의 값)의 선언 */
	String model;
	String color;
	int speed;
	/* 생성자 -> 필드의 초기화 
	 * 클래스 Car가 생성되어질 때 호출되며 초기 상태를 설정 
	 */
	public Car(String model, String color){
		this.model = model;
		this.color = color;
		this.speed = 0;
		
	}
	/* 메서드와 생성자의 차이점 */
	/* 메서드 --> 동작(기능) : 가속,중지,정보전달 */
	public void acceleration(int amount){
		//this.speed = amount + this.speed;
		/* this : 클래스의 필드와 생성자 매개변수의 이름이 같을 때,
		 * 클래스의 필드 임을 명확히 지정하기 위해 사용 */
		this.speed += amount;
		System.out.println(model + "이(가)" + "km/h 가속하여 현재" +
		speed + "km/h"); //sysout-> Ctrl+Spacebar
		
	}
	/* Car 정보를 출력하는 메서드 선언 */
	public void displayInfo(){ /* 접근제어자 : public, private, protected */
		System.out.println("모델:" + model + ", 색상:" + color + ", 현재속도:" +
		speed + "km/h");
		
	}
	
}
