package ch02_pjt02;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* 객체 생성 : new를 이용해서 실체(myCar)를 만든다. */
		Car myCar = new Car("소렌토", "블랙");
		Car friendCar = new Car("제네시스", "흰색");
		myCar.acceleration(50);
		myCar.displayInfo();
		friendCar.displayInfo();
	}

}
