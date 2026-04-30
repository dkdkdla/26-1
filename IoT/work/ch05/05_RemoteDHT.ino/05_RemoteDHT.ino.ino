/* 웹을 이용한 원격 측정, 동일한 WiFi 망에 NodeMCU와 PC를 접속
 * NodeMCU --> 웹서버로 구축, 온습도센서 측정정보를 공유
 * PC 혹은 Smart Phone --> 웹브라우저로 접속하여 측정정보 확인
 * 작성일 : 2023.02.21, 수정 : 김형래
 */
#include <ESP8266WiFi.h> //WiFi 라이브러리
#include "SSD1306.h"     //OLED 라이브러리
#include <DHT.h>         //DHT 라이브러리
#define DHTPIN 2         //Data 핀 설정 --> D4(GPIO02)
#define DHTTYPE DHT22    //센서종류, DHT22
#define SDAPIN 4 //SDA 핀 --> D2(GPIO04)
#define SCLPIN 5 //SCL 핀 --> DA(GPIO05)

//연결할 네트워크 정보
const char* ssid = "khr";
const char* password = "12345678";

DHT dht(DHTPIN, DHTTYPE); //DHT 객체 생성
WiFiServer server(80); //웹서버 모듈(객체) 생성
//OLED 객체 생성, I2C 주소는 0x3C
int i2caddress = 0x3C;
SSD1306 display(i2caddress, SDAPIN, SCLPIN);

float t, h; // 온습도값 저장 변수 선언
String temp, humid; //OLED에 출력하기 위해 온습도값을 글자로 변환
//주기(2초)적으로 온습도값을 측정하기 위한 시간변수
long prevTime=0, currTime=0, interval=2000;
//클라이언트 접속 여부
bool isClientConnected = false;

void setup() {
  //시리얼모니터 초기화
  Serial.begin(115200);
  Serial.println("Remote Measurement using web server.");

  //OLED 객체 설정 초기화
  display.init();
  display.flipScreenVertically(); //문자의 표현방향 --> 핀을 향함
  display.setTextAlignment(TEXT_ALIGN_LEFT); //문자를 좌측 정렬로 함
  display.setFont(ArialMT_Plain_16); //글꼴
  display.drawString(0, 10, "DHT22/SSD1306"); //(0,10) 위치에 문자 출력
  display.display();
  delay(3000); //3초간 위의 글자가 출력됨
  //dht 객체 동작 활성화
  dht.begin();
  delay(10);

  //WiFi 네트워크에 연결
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  WiFi.begin(ssid, password);

  //WiFi 네트워크 연결 상태 확인
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  //여기에 도달하면 WiFi 망에 연결 완료
  Serial.println("");
  Serial.println("WiFi connected");
  // 서버 동작 시작
  server.begin();
  Serial.println("Server started");
  // 서버의 IP 주소 출력 --> 클라이언트에서 접속할 때 사용
  Serial.print("Use this URL to connect: ");
  Serial.print("http://");
  Serial.print(WiFi.localIP());
  Serial.println("/");
}

//시리얼모니터 출력
void displaySerial(){
  Serial.print("Temperature : ");
  Serial.print(t);
  Serial.print("^C");
  Serial.print("\t"); //Tab
  Serial.print("Humidity : ");
  Serial.print(h);
  Serial.println("%");
}

//OLED 출력
void displayOLED(){
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
//WebServer 기능을 이용하여 온습도 정보 공유
void displayWebServer(){
  // 클라이언트의 접속 여부 확인
  WiFiClient client = server.available();
  if (client) { //클라이언트 접속됨
    Serial.println("new client");
    isClientConnected = true;
    delay(10);
  }

  // HTML 헤더 --> 클라이언트로 보내는 웹 페이지 정보
  // --> 이를 이용하여 클라이언트에서 응답을 줄 수 있음
  client.println("HTTP/1.1 200 OK");
  client.println("Content-Type: text/html");
  client.println(""); // 이 빈 줄이 꼭 필요함을 잊지 말 것
  client.println("<!DOCTYPE HTML>");
  client.println("<html>");
  //<meta> 태그를 이용하여 새로고침을 정의함, content="5"는 새로고침 시간
  client.println("<meta http-equiv=\"refresh\" content=\"5\">");

  //클라이언트가 접속된 경우 정보 전달
  if(isClientConnected){
    client.print("DHT22 Test : ");
    client.println("<br />");
    client.print("==========");
    client.println("<br />");
    //온도 전달
    client.print("Temp : ");
    client.print(t);
    client.print("^C");
    client.println("<br />");
    //습도 전달
    client.print("Humid : ");
    client.print(h);
    client.print("%");
    client.println("<br /><br />");

    client.println("</html>");
  }
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
  //온습도를 제대로 읽으면 정보출력
  if(isnan(t) || isnan(h)) {
    Serial.println("Failed to read from DHT");
  }
  else {
    displaySerial();    //시리얼 모니터 출력
    displayOLED();      //OLED 출력
    displayWebServer(); //WebServer 공유
  }
  delay(100);
}