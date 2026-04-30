/* [도전과제] 웹서버를 이용한 원격 모터 제어
 * NodeMCU를 웹 서버로 구성하여 브라우저의 버튼으로 모터 제어
 * 회로 연결: D1 -> BIA, D2 -> BIB
 */

#include <ESP8266WiFi.h>

// 연결할 네트워크 정보 (사용자 환경에 맞게 수정)
#ifndef STASSID
#define STASSID "khr"
#define STAPSK  "12345678"
#endif

const char* ssid = STASSID;
const char* password = STAPSK;

// 모터 드라이버 연결 핀 설정
const int BIA = D1; 
const int BIB = D2;

WiFiServer server(80);

void setup() {
  Serial.begin(115200);
  delay(10);

  // 모터 제어 핀을 출력으로 설정하고 초기 상태는 정지(LOW, LOW)
  pinMode(BIA, OUTPUT);
  pinMode(BIB, OUTPUT);
  digitalWrite(BIA, LOW);
  digitalWrite(BIB, LOW);

  // WiFi 네트워크 접속
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  // 서버 시작 및 IP 주소 출력
  Serial.println("");
  Serial.println("WiFi connected");
  server.begin();
  Serial.println("Server started");
  Serial.print("Use this URL to connect: ");
  Serial.print("http://");
  Serial.print(WiFi.localIP());
  Serial.println("/");
}

void loop() {
  // 클라이언트 접속 확인
  WiFiClient client = server.available();
  if (!client) {
    return;
  }

  // 클라이언트로부터 데이터가 올 때까지 대기
  while(!client.available()){
    delay(1);
  }

  // 요청 읽기 및 시리얼 모니터 출력 (이미지 실행결과와 일치)
  String request = client.readStringUntil('\r');
  Serial.println("new client");
  Serial.println(request);
  client.flush();

  // 명령 해석 및 모터 제어 (URL 파싱)
  if (request.indexOf("/MOTOR=FORWARD") != -1) {
    digitalWrite(BIA, HIGH);
    digitalWrite(BIB, LOW);
  } 
  else if (request.indexOf("/MOTOR=BACKWARD") != -1) {
    digitalWrite(BIA, LOW);
    digitalWrite(BIB, HIGH);
  } 
  else if (request.indexOf("/MOTOR=STOP") != -1) {
    digitalWrite(BIA, LOW);
    digitalWrite(BIB, LOW);
  }

  // 클라이언트로 웹 페이지(HTML) 전송
  client.println("HTTP/1.1 200 OK");
  client.println("Content-Type: text/html");
  client.println(""); 
  client.println("<!DOCTYPE HTML>");
  client.println("<html>");
  client.println("<head><meta name='viewport' content='width=device-width, initial-scale=1'></head>");
  client.println("<body>");
  client.println("<h2>Motor Control</h2>");
  
  // 이미지의 버튼 레이아웃 구현
  client.println("<a href=\"/MOTOR=FORWARD\"><button>Forward</button></a>");
  client.println("<a href=\"/MOTOR=BACKWARD\"><button>Backward</button></a>");
  client.println("<a href=\"/MOTOR=STOP\"><button>Stop</button></a>");
  
  client.println("</body>");
  client.println("</html>");

  delay(1);
  Serial.println("Client disconnected");
  Serial.println("");
}