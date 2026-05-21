/*
    This sketch establishes a TCP connection to a "quote of the day" service.
    It sends a "hello" message, and then prints received data.
*/

#include <ESP8266WiFi.h>

//WiFi 설정
#ifndef STASSID
#define STASSID "pmj"
#define STAPSK "12345678"
#endif

const char* ssid = STASSID;
const char* password = STAPSK;

//라즈베리파이 서버 정보 지정
const char* host = "192.168.137.207";
const uint16_t port = 8080;

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

    Serial.println("");
    Serial.println("WiFi connected");
    Serial.println("IP address: ");
    Serial.println(WiFi.localIP());
}

void loop() {
    static bool wait = false;

    Serial.print("connecting to ");
    Serial.print(host);
    Serial.print(':');
    Serial.println(port);

    // Use WiFiClient class to create TCP connections
    WiFiClient client;
    if (!client.connect(host, port)) {
        Serial.println("connection failed");
        delay(5000);
        return;
    }

    // This will send a string to the server
    Serial.println("sending data to server");
    if (client.connected()) {
        char* message = "HELLO";
        client.print(message);
    }
    delay(1000);
}
