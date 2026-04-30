/* OLED에 글자 출력하기. OLED를 글자 출력용으로 사용
 * 작성일 : 2025.02.11, 수정일 : 2026.03.04
 */
#include "SSD1306.h"    //OLED 라이브러리 추가
#include <DHT.h>        //DHT 라이브러리
#define DHTPIN 2        //Data 핀 설정 --> D4(GPIO2)
#define DHTTYPE DHT22 //센서종류, DHT22
#define SDAPIN 4        //SDA 핀 --> D2(GPIO4)
#define SCLPIN 5        //SDA 핀 --> D1(GPIO5)

DHT dht(DHTPIN, DHTTYPE); //DHT 객체 생성
float t=0.0, h=0.0; //온습도 값을 저장할 전역변수 선언
String temp, humid; //OLED 출력을 위해 t와 h를 글자로 변환
//주기적으로 값을 측정하기 위한 시간변수 선언
long prevTime=0, currTime=0, interval=2000;
//OLED 객체 생성,I2C주소(0x3C) 필요
int i2cAddress = 0x3C;
SSD1306 display(i2cAddress, SDAPIN, SCLPIN);

void setup() {
  //시리얼모니터 초기화
  Serial.begin(115200);
  Serial.println("DHT22 test!");
  //OLED 객체 설정 초기화
  display.init();
  display.flipScreenVertically(); //문자의 표현방향 --> 핀을 향함
  display.setTextAlignment(TEXT_ALIGN_LEFT); //문자를 좌측 정렬로 함
  display.setFont(ArialMT_Plain_16); //글꼴
  display.drawString(0, 10, "DHT22/SSD1306"); //(0,10) 위치에 문자 출력
  display.display();
  delay(3000); //3초간 위의 글자가 출력됨

  dht.begin(); //dht 객체 동작 활성화
  //ms 단위로 아두이노 보드 실행후 경과시간 측정, 현재/이전 시간 초기화
  currTime = prevTime = millis();
}

void loop() {
  currTime = millis(); //현재까지 경과시간 측정
  //지정 interval(현재 2초)을 초과하는 경우, 센서 데이터를 읽음
  if(currTime - prevTime > interval){
    Serial.println("Measure t & h !!!");
    t = dht.readTemperature();
    h = dht.readHumidity();
    //t와 h를 글자로 변환
    temp = String(t); //float to String
    humid = String(h);
    prevTime = currTime; //이전시간 업데이트
  }

  //측정값이 NaN(not a number)인 지 체크
  if (isnan(t) || isnan(h)) {
    Serial.println("Failed to read from DHT");
    //OLED 출력
    display.clear();
    display.drawString(0, 2, "Fail to read!");
    display.display();
  }
  else {
    Serial.print("Temperature : ");
    Serial.print(t);
    Serial.print("^C");
    Serial.print("\t"); //Tab
    Serial.print("Humidity : ");
    Serial.print(h);
    Serial.println("%");
    //OLED 출력
    display.clear();
    display.setFont(ArialMT_Plain_10); //글꼴을 축소
    display.drawString(100, 0, "o"); //degree 출력
    display.setFont(ArialMT_Plain_16); //글꼴을 원래로 환원
    //아래 출력에서 : 이후 빈칸 14칸 추가 --> 추후 온도/습도 값이 들어갈 위치
    display.drawString(0, 2, "Temp :              C"); //온도 표시위치 표현
    display.drawString(56, 2, temp); //온도 값 출력
    display.drawString(0, 18, "Humid:              %"); //습도 표시위치 표현
    display.drawString(56, 18, humid); //습도 값 출력
    display.display();
  }
  delay(500);
}