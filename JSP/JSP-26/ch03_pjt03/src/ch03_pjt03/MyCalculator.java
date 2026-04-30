package ch03_pjt03;

public class MyCalculator {
	/* 필드(Field) 선언 */
	
	/* 생성자(Constructor) 선언 */
	
	/* 메서드(Method) 선언 : 더하기, 빼기, 곱하기, 나누기*/
	public void calAdd(int fNum, int sNum){ 
		/*연산에 필요한 정수형 데이터 2개 */
		ICalculator calculator = new CalAdd();
		int value = calculator.doOperation(fNum, sNum);
		System.out.println("result(+) = " + value);
		
	}
	
	public void calSub(int fNum, int sNum) {
		ICalculator calculator = new CalSub();
		int value = calculator.doOperation(fNum, sNum);
		System.out.println("result(-) = " + value);
		
	}
	
	public void calMul(int fNum, int sNum) {
		ICalculator calculator = new CalMul();
		int value = calculator.doOperation(fNum, sNum);
		System.out.println("result(*) = " + value);
	}
	
	public void calDiv(int fNum, int sNum) {
	    ICalculator calculator = new CalDiv();
	    
	    try {
	        // 정상적으로 실행을 시도할 연산 코드
	        int value = calculator.doOperation(fNum, sNum);
	        System.out.println("result(/) = " + value);
	        
	    } catch (ArithmeticException e) {
	        // 0으로 나누어 에러가 발생했을 때 프로그램 종료 대신 실행될 코드
	        System.out.println("오류 발생: 0으로 나눌 수 없습니다. 예외 처리를 완료했습니다.");
	    }
	}
	
}
