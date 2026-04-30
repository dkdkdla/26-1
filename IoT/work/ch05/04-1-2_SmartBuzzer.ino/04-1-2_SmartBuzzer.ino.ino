/* 스마트 버튼을 이용한 스마트 부저, 스마트 버튼 모듈
 * 스마트 부저는 WiFiServer, 스마트 버튼은 WiFiClient
 * 스마트 부저가 동작되는 상황에서 실행시켜야 함
 * 작성일 : 2023.02.15, 수정 : 김형래
 */

#include <ESP8266WiFi.h> //라이브러리 추가
#define LED_PIN 2 //D4 (gpio2)
#define PB_PIN 4 //D2 (gpio4)

//연결할 네트워크 정보
const char* ssid = "khr";
const char* password = "12345678";
//스마트서버의 IP 주소 및 포트번호
//스마트부저 실행시 제시되는 IP 주소와 설정값
const char* host="192.168.137.32";
const int port=80;

//버튼 입력 상태값 관련 변수 선언
int buzzerState = LOW; //현재 입력상태 값
int currentButtonState = false; //푸시버튼의 현재상태
int previousButtonState = false; //푸시버튼의 이전 상태

void setup() {
  Serial.begin(115200); //시리얼모니터 초기화
  delay(10);

  //핀모드 설정
  pinMode(LED_PIN, OUTPUT);
  pinMode(PB_PIN, INPUT_PULLUP);

  // 연결할 네트워크 정보 입력
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  // WiFi 네트워크에 연결
  WiFi.begin(ssid, password);
  //WiFi 네트워크에 연결되면 "연결되었다"는 정보 출력
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.println("WiFi connected");
}

void sendMessage(){
  //WiFi 클라이언트 객체 생성 및 연결
  WiFiClient client;
  Serial.print("Connecting to ");
  Serial.println(host);
  if (!client.connect(host, port)){ //연결 실패 상황
    Serial.println("Connection Failed");
    return;
  }

  //입력상태값에 따른 동작 명령을 전송 처리
  if(buzzerState){ //토글버튼 On 상태
    Serial.println("Buzzer is ringing!");
    client.print(String("/BuzzerOn\r\n"));
  }
  else{ //토글버튼 Off 상태
    Serial.println("Buzzer is stopped!");
    client.print(String("/BuzzerOff\r\n"));
  }

  //클라이언트 연결 해제
  client.stop();
  Serial.println("Connection is closed.");
  Serial.println();
}

void loop() {
  //현재 푸시버튼 상태값을 읽음
  currentButtonState = digitalRead(PB_PIN);
  //internal pullup 회로를 채택한 토글버튼 구현
  if(previousButtonState && !currentButtonState) {
    //Serial.println("The pushbutton is pressed!");
    buzzerState = !buzzerState; //입력상태값 반전
    //WiFiServer에 제어 정보 전송
    sendMessage();
  }
  //현재 푸시버튼 상태값을 이전 상태값에 할당
  previousButtonState = currentButtonState;

  //현재 버튼의 상태를 LED로 표시함
  digitalWrite(LED_PIN, buzzerState);
  delay(10); //0.01초 지연
}