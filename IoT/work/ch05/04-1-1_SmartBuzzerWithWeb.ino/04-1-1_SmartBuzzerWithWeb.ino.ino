/* 웹을 이용한 스마트 부저
 * NodeMCU를 서버로 구성하고, 웹브라우저로 부저/LED를 On/Off
 * 작성일 : 2023.02.15, 수정 : 김형래
 */

#include <ESP8266WiFi.h> //라이브러리 추가
#define BZ_PIN D1 //부저 핀번호 지정 --> D1 (gpio5)
#define LED_PIN D2 //LED 핀번호 --> D2 (gpio4)

//연결할 네트워크 정보
const char* ssid = "khr";
const char* password = "12345678";

WiFiServer server(80); //서버 모듈(객체) 생성

//부저의 상태값
int buzzerState = LOW;

void setup() {
  Serial.begin(115200); //시리얼모니터 초기화
  delay(10);

  pinMode(BZ_PIN, OUTPUT); //부저 핀모드 설정
  pinMode(LED_PIN, OUTPUT); //LED 핀모드 설정
  digitalWrite(BZ_PIN, LOW);

  // 연결할 네트워크 정보 출력
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

  // 서버 동작 시작
  server.begin();
  Serial.println("Server started");

  // 서버의 IP 주소 출력 --> 클라이언트에서 접속할 때 사용
  Serial.print("Use this URL to connect: ");
  Serial.print("http://");
  Serial.print(WiFi.localIP());
  Serial.println("/");
}

void loop() {
  //부저/LED 동작
  if(buzzerState) {
    //0.5초 동안 삐("솔" 음계) 소리를 내고 LED를 켬
    tone(BZ_PIN, 392, 500);
    digitalWrite(LED_PIN, HIGH);
    delay(300);
    //0.5초 동안 부저를 멈추고 LED를 끔
    noTone(BZ_PIN);
    digitalWrite(LED_PIN, LOW);
    delay(300);
  } else {
    //부저 멈춤, LED 끔
    noTone(BZ_PIN);
    digitalWrite(LED_PIN, LOW);
  }

  // 클라이언트의 접속 여부 확인
  WiFiClient client = server.available();
  if (!client) {
    delay(20);
    return;
  }

  // 클라이언트가 데이터를 보낼 때까지 기다림
  Serial.println("new client");
  while(!client.available()){
    delay(1);
  }

  // 클라이언트가 보낸 응답의 첫번째 줄을 읽고, 시리얼 모니터로 출력
  String request = client.readStringUntil('\r');
  Serial.println(request);
  client.flush();

  // 입력값 인식
  if (request.indexOf("/BuzzerOn") != -1) {
    //Serial.println("ringing");
    buzzerState = HIGH; //부저가 울리고 있음
  }
  if (request.indexOf("/BuzzerOff") != -1) {
    //Serial.println("stop");
    buzzerState = LOW; //부저가 멈춤
  }

  // HTML 헤더 --> 클라이언트로 보내는 웹 페이지 정보
  // --> 이를 이용하여 클라이언트에서 응답을 줄 수 있음
  client.println("HTTP/1.1 200 OK");
  client.println("Content-Type: text/html");
  client.println(""); // 이 줄이 꼭 필요함을 잊지 말 것
  client.println("<!DOCTYPE HTML>");
  client.println("<html>");

  //클라이언트가 선택할 수 있는 버튼 배치
  client.println("<br><br>");
  //토글버튼이므로 현재의 릴레이 상태에 따라 표시값이 다름
  client.print("Buzzer is ");
  if(buzzerState) {
    client.println("ringing!");
    client.println("<a href=\"/BuzzerOff\"><button>Stop </button></a>");
  } else {
    client.println("stop!");
    client.println("<a href=\"/BuzzerOn\"><button>Start </button></a>");
  }
  client.println("</html>");

  delay(1);
  Serial.println("Client disconnected");
  Serial.println("");
}