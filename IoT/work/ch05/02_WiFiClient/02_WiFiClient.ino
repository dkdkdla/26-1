/*
    This sketch establishes a TCP connection to a "quote of the day" service.
    It sends a "hello" message, and then prints received data.
*/

#include <ESP8266WiFi.h>

//인터넷 공유기의 ID와 비밀번호 설정
#ifndef STASSID
#define STASSID "khr"
#define STAPSK "12345678"
#endif

const char* ssid = STASSID;
const char* password = STAPSK;

//접속할 명언 서버
const char* host = "djxmmx.net";
const uint16_t port = 17;

void setup() {
  Serial.begin(115200);
  // We start by connecting to a WiFi network
  Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);

  /* Explicitly set the ESP8266 to be a WiFi-client, otherwise, it by default,
     would try to act as both a client and an access-point and could cause
     network-issues with your other WiFi-devices on your WiFi-network. */
  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  //WiFi에 연결되면, 할당받은 IP주소를 시리얼모니터로 출력
  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
}

void loop() {
  static bool wait = false;
  //접속할 서버의 정보 출력
  Serial.print("connecting to ");
  Serial.print(host);
  Serial.print(':');
  Serial.println(port);

  // 서버에 접속, 실패하면 "connection failed" 정보 출력
  WiFiClient client;
  if (!client.connect(host, port)) {
    Serial.println("connection failed");
    delay(5000);
    return;
  }

  // 서버에 "hello from ESP8266"이란 메시지를 보냄
  Serial.println("sending data to server");
  if (client.connected()) { client.println("hello from ESP8266"); }

  // 서버로부터 전달된 데이터가 도착할 때까지 기다림
  unsigned long timeout = millis();
  while (client.available() == 0) {
    if (millis() - timeout > 5000) {
      Serial.println(">>> Client Timeout !");
      client.stop();
      delay(60000);
      return;
    }
  }

  // Read all the lines of the reply from server and print them to Serial
  Serial.println("receiving from remote server");
  // 서버로부터 전달된 데이터가 존재하면, 그 정보를 시리얼모니터로 출력
  //client.available()의 반환값은 전달된 바이트수, 1보다 큰 경우 참(true)으로 취급
  while (client.available()) {
    char ch = static_cast<char>(client.read());
    Serial.print(ch);
  }

  // Close the connection
  Serial.println();
  Serial.println("closing connection");
  client.stop();

  if (wait) {
    delay(300000); // execute once every 5 minutes, don't flood remote service
  }
  wait = true;
}