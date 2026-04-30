package ch04_pjt_03;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	/* 필드 선언 */
	
	/* 생성자 선언 */
	
	/* 메소드(Method) 선언 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* 새로운 객체 생성 --> 인스턴스(instance) */
		//TransportationWalk transportationWalk = new TransportationWalk();
      	//transportationWalk.move();

        GenericXmlApplicationContext ctx = 
        		new GenericXmlApplicationContext("classpath:applicationContext.xml");
        TransportWalk transportWalk = ctx.getBean("tWalk", TransportWalk.class);
        transportWalk.move();

        ctx.close();
    }
}