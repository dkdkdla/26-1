/* Ajax Polling 방식의 센서모니터링을 구현하기 위한
 * NodeMCU 웹서버 프로그램
 * ESP8266WiFi - WiFiManualWebServer.ino 수정
 */
#include <ESP8266WiFi.h>
#include <ESP8266WebServer.h>
#include <DHT.h>

#ifndef STASSID
#define STASSID "khr"
#define STAPSK "12345678"
#define LEDPIN D4
#define DHTTYPE DHT22
#define DHTPIN D2
#endif

const char* ssid = STASSID;
const char* password = STAPSK;

//웹서버 객체, 인수는 포트번호
ESP8266WebServer server(80);
//LED 상태값 저장 전역변수
int ledstate = 0;
//온습도 측정에 필요한 변수
DHT dht(DHTPIN, DHTTYPE);
float temp, humid; //측정값
String webString = ""; //라즈베리파이로 보낼 응답
unsigned long prevMills = 0, currMills = 0;
const long interval = 2000; //온습도센서 측정 간격

//DHT 센서값을 읽는 메서드
void getDHTSensor() {
    //현재/이전 시간차가 Interval(현재 2초)을 경과하면 온습도 측정
    currMills = millis();
    if(currMills - prevMills >= interval) {
        humid = dht.readHumidity();
        temp = dht.readTemperature(false); //섭씨로 읽음
        prevMills = currMills;
        //값을 제대로 읽지 못한 경우 리턴
        if(isnan(humid) || isnan(temp)) {
            Serial.println("Failed to read dht sensor.");
            return;
        }
    }
}

//센서 모니터링 이벤트 처리를 위한 메서드
void handleevents() {
    //온습도 값을 읽어 옴
    getDHTSensor();
    //라즈베리파이로 보낼 응답 구성 --> \" (escape sequence) 주의할 것
    webString = "{\"temperature\":\"" + String(temp) + "\",\"humidity\":\"" + String(humid) + "\"}";
    Serial.println(webString);
    //응답을 전송
    server.send(200, "text/plain", webString);
    yield(); //다른 작업에 제어권을 전달
}

//LED Toggle 동작을 위한 이벤트 처리
void toggleled() {
    ledstate = !ledstate; //토글기능
    ledaction();
}

//LED On 동작을 위한 이벤트 처리
void ledon() {
    ledstate = 1; //LED On
    ledaction();
}

//LED Off 동작을 위한 이벤트 처리
void ledoff() {
    ledstate = 0; //LED Off
    ledaction();
}

//LED 동작 및 LED 상태값 리턴
void ledaction() {
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
    dht.begin(); //DHT 센서 구동 시작

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
    server.on("/ledtoggle", toggleled);
    server.on("/ledon", ledon);
    server.on("/ledoff", ledoff);
    //DHT 센서 모니터링
    server.on("/events", handleevents);
    //Start the server
    server.begin();
}

void loop() {
    //WebServer.handleClient() 메서드는 loop() 내에서 호출되며,
    //WebServer.on() 메서드 내부에 설정된 이벤트핸들러를 호출
    server.handleClient();
}
