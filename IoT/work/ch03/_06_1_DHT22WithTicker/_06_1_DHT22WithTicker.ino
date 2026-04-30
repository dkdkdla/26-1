/* Ticker 라이브러리를 이용한 온습도센서(DHT22) 활용 예제
 * 작성일 : 2025.02.11
 */
#include <Ticker.h>   //Timer 활용 라이브러리, UNO의 MsTimer역할
#include <DHT.h>      //DHT 라이브러리
#define DHTPIN 2      //Data 핀 설정 --> D4(GPIO2)
#define DHTTYPE DHT22 //센서종류, DHT22

DHT dht(DHTPIN, DHTTYPE); //DHT 객체 생성
Ticker mTicker; //Ticker 객체 레퍼런스 변수 선언
float t=0.0, h=0.0; //온습도 값을 저장할 전역변수 선언

void setup() {
  //시리얼모니터 초기화
  Serial.begin(115200);
  Serial.println("DHT22 test!");

  dht.begin(); //dht 객체 동작 활성화
  //2초 간격으로 DHT 센서를 읽도록 Ticker 설정
  //attach_ms()를이용하면 시간간격을 ms 단위로 설정 가능
  mTicker.attach(3.0, readDht);
}

void loop() {

}

// Ticker 발생시 실행할 서비스 루틴
float readDht(){
  Serial.println("in readDht");
  float t = dht.readTemperature();
  float h = dht.readHumidity();
  if (isnan(t) || isnan(h)) {
    Serial.println("Failed to read from DHT");
  }
  else {
    Serial.print("Temperature : ");
    Serial.print(t);
    Serial.print("^C");
    //Serial.print("\t"); //Tab
    //Serial.print("Humidity : ");
    //Serial.print(h);
    //Serial.println("%");
    Serial.println();
  }

  return t;
}