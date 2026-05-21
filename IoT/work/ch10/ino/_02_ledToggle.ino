// 원격으로 LED 제어를 위한 NodeMCU 웹서버 프로그램
#include <ESP8266WiFi.h>
#include <ESP8266WebServer.h>

#ifndef STASSID
#define STASSID "pmj"
#define STAPSK "12345678"
#define LEDPIN D4
#endif

const char* ssid = STASSID;
const char* password = STAPSK;

//웹서버 객체, 인수는 포트번호
ESP8266WebServer server(80);
//LED 상태값 저장 전역변수
int ledstate = 0;

//LED Toggle 동작을 위한 이벤트 처리
void toggleled() {
    ledstate = !ledstate; //토글기능
    digitalWrite(LEDPIN, ledstate);
    Serial.print("led : ");
    Serial.println(ledstate);
    //LED의 On/Off 상태를 확인하기 위해 상태값을 리턴
    server.send(200, "text/plain", String(ledstate));
}

void setup() {
    Serial.begin(115200);
    delay(10);
    pinMode(LEDPIN, OUTPUT);

    // Connect to WiFi network
    Serial.println();
    Serial.println();
    Serial.print(F("Connecting to "));
    Serial.println(ssid);
    WiFi.mode(WIFI_STA);
    WiFi.begin(ssid, password);
    while (WiFi.status() != WL_CONNECTED) {
        delay(500);
        Serial.print(F("."));
    }

    Serial.println();
    Serial.println(F("WiFi connected"));
    // Start the server
    server.begin();
    Serial.println(F("Server started"));
    // Print the IP address
    Serial.println(WiFi.localIP());

    //이벤트 설정 및 이벤트 핸들러 등록
    // "/toggleled" url로 접속할 경우 toggleled() 메서드 호출
    server.on("/ledtoggle", toggleled);
    //Start the server
    server.begin();
}

void loop() {
    //WebServer.handleClient() 메서드는 loop() 내에서 호출되며,
    //WebServer.on() 메서드 내부에 설정된 이벤트핸들러를 호출
    server.handleClient();
}
