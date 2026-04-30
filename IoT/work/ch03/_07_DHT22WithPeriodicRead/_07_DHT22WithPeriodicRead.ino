/* 시간 interval을 이용한 온습도센서(DHT22) 활용 예제
 * 작성일 : 2025.02.11
 */
#include <DHT.h>        //DHT 라이브러리
#define DHTPIN 2        //Data 핀 설정 --> D4(GPIO2)
#define DHTTYPE DHT22 //센서종류, DHT22

DHT dht(DHTPIN, DHTTYPE); //DHT 객체 생성
float t=0.0, h=0.0; //온습도 값을 저장할 전역변수 선언
//주기적으로 값을 측정하기 위한 시간변수 선언
long prevTime=0, currTime=0, interval=2000;

void setup() {
  //시리얼모니터 초기화
  Serial.begin(115200);
  Serial.println("DHT22 test!");

  dht.begin(); //dht 객체 동작 활성화
  //ms 단위로 아두이노 보드 실행후 경과시간 측정, 현재/이전 시간 초기화
  currTime = prevTime = millis();
}

void loop() {
  currTime = millis(); //현재까지 경과시간 측정
  //이전/현재 경과시간 차이가 지정시간을 초과하는 경우, 센서 데이터를 읽음
  if(currTime - prevTime > interval){
    Serial.println("Measure t & h !!!");
    t = dht.readTemperature();
    h = dht.readHumidity();
    prevTime = currTime; //이전시간 업데이트
  }

  //측정값이 NaN(not a number)인 지 체크
  //측정간격이 2초, delay가 0.5초이므로, 4번의 출력에는 동일한 값
  if (isnan(t) || isnan(h)) {
    Serial.println("Failed to read from DHT");
  }
  else {
    Serial.print("Temperature : ");
    Serial.print(t);
    Serial.print("^C");
    Serial.print("\t"); //Tab
    Serial.print("Humidity : ");
    Serial.print(h);
    Serial.println("%");
  }
  delay(500);
}