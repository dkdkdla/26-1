package ch02_pjt02;

public class ElectricCar extends Vehicle implements Chargeable{
	/* 필드 선언 */
	int batteryLevel;
	/* 생성자 선언 */
	public ElectricCar(String model){
		super(model); /* 부모(Vehicle) 생성자 호출 */
		this.batteryLevel = 100;
	}
	/* 메서드 선언*/
	/* 추상 클래스의 메서드 오버라이딩 */
	

	@Override
	public void drive() {
		// TODO Auto-generated method stub
		if(batteryLevel > 0) {
			System.out.println(model + "이 전기 모터로 조용히 주행합니다.");
			batteryLevel -= 10;
		} else {
			System.out.println("배터리 충전이 부족합니다.");
		}
	}
	
	@Override
	public void charge() {
		// TODO Auto-generated method stub
		batteryLevel = 100;
		System.out.println(model + "을 완충하였습니다.");
		
	}
	
	
}
