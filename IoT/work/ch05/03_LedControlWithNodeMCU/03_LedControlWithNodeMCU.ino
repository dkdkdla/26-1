/* 이번 실습은 NodeMCU를 서버로 구성하고, 클라이언트에서 접속하여
 * LED를 On/Off 시키는 예제
 * 원본 소스 : https://www.instructables.com/
 * 작성일 : 2025.02.24, 수정 : 김형래
 */
#include <ESP8266WiFi.h> //라이브러리 추가

//연결할 네트워크 정보
const char* ssid = "khr";
const char* password = "12345678";

int ledPin = D4; //LED 연결핀 번호 GPIO2 --> D4
WiFiServer server(80); //서버 모듈(객체) 생성

void setup() {
  Serial.begin(115200); //시리얼모니터 초기화
  delay(10);

  pinMode(ledPin, OUTPUT); //LED 핀 모드 설정
  digitalWrite(ledPin, LOW);

  Serial.println();     // 네트워크 연결 정보 출력
  Serial.print("Connecting to ");
  Serial.println(ssid);

  WiFi.begin(ssid, password); // WiFi 네트워크에 연결

  //WiFi 네트워크에 연결되면 "연결되었다"는 정보 출력
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi connected");

  server.begin();       // 서버 동작 시작
  Serial.println("Server started");

  // 서버의 IP 주소 출력 --> 클라이언트에서 접속할 때 사용
  Serial.print("Use this URL to connect: ");
  Serial.print("http://");
  Serial.print(WiFi.localIP());
  Serial.println("/");
}

void loop() {
  // 클라이언트의 접속 확인 --> 연결되면 클라이언트 정보를 갖고 옴
  WiFiClient client = server.available();
  if (!client) {
    return;
  }

  // 클라이언트가 데이터를 보낼 때까지 기다림
  Serial.println("new client");
  while(!client.available()){
    delay(1);
  }
  // 클라이언트가 보낸 신호의 첫번째 줄('\r\n'은 Enter 키)을 읽고, 시리얼 모니터로 출력
  String request = client.readStringUntil('\r');
  Serial.println(request);
  client.flush();

  // 응답에 따른 동작
  int value = LOW;
  if (request.indexOf("/LED=ON") != -1)  {
    digitalWrite(ledPin, HIGH);
    value = HIGH;
  }
  if (request.indexOf("/LED=OFF") != -1) {
    digitalWrite(ledPin, LOW);
    value = LOW;
  }

  // 클라이언트로 보내는 웹 페이지 정보 --> HTML로 작성
  // 클라이언트에서 웹페이지를 열고, 동작신호를 줄 수 있음
  client.println("HTTP/1.1 200 OK");
  client.println("Content-Type: text/html");
  client.println(""); // 이 줄이 꼭 필요함을 잊지 말 것
  client.println("<!DOCTYPE HTML>");
  client.println("<html>");

  //현재의 LED 상태를 표시함
  client.print("Led is now: ");
  if(value == HIGH) {
    client.print("On");
  } else {
    client.print("Off");
  }

  //클라이언트가 선택할 수 있는 버튼 배치
  client.println("<br><br>");
  client.println("<a href=\"/LED=ON\"><button>On </button></a>");
  client.println("<a href=\"/LED=OFF\"><button>Off </button></a><br />");
  client.println("</html>");

  delay(1);
  Serial.println("Client disconnected");
  Serial.println("");
}