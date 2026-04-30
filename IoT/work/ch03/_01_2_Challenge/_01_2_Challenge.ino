#include "SSD1306.h"

#define POT_PIN A0      // 가변저항 아날로그 입력핀 (A0)
#define SDAPIN 4        // SDA 핀 --> D2(GPIO4)
#define SCLPIN 5        // SCL 핀 --> D1(GPIO5)

int i2cAddress = 0x3C;
SSD1306 display(i2cAddress, SDAPIN, SCLPIN);

unsigned long prevTime = 0;
unsigned long interval = 500; // 0.5초 갱신

void setup() {
  Serial.begin(115200);
  display.init();
  display.flipScreenVertically();
  display.setFont(ArialMT_Plain_16);
}

void loop() {
  unsigned long currTime = millis();

  if (currTime - prevTime >= interval) {
    prevTime = currTime;

    Serial.println("in readPotentiometer"); 

    int rawValue = analogRead(POT_PIN);
    
    // map() 함수 이용 (0~3300mV 범위로 정수 매핑 후 1000.0으로 나누어 실수 변환)
    long mappedValue = map(rawValue, 0, 1023, 0, 3300);
    float voltage = mappedValue / 1000.0;

    // 시리얼 모니터 출력
    Serial.print("Voltage : ");
    Serial.print(voltage);
    Serial.println(" V");

    // OLED 출력
    String dispStr = "Voltage : " + String(voltage, 2) + " V";
    display.clear();
    display.drawString(0, 20, dispStr);
    display.display();
  }
}