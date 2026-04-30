/* 온습도센서(DHT22) 활용 예제
 * 작성일 : 2023.02.20
 */
#include <DHT.h>        //DHT 라이브러리 포함
#define DHTPIN 2        //Data 핀 설정 --> D4(GPIO2)
#define DHTTYPE DHT22   //센서종류, DHT22

DHT dht(DHTPIN, DHTTYPE); //DHT 객체 생성

void setup() {
  //시리얼모니터 초기화
  Serial.begin(115200);
  Serial.println("DHT22 test!");

  dht.begin(); //dht 객체 동작 활성화
}

void loop() {
  // DHT 센서의 측정 간격은 최소 2초 --> delay로 인한 지연문제 발생
  delay(3000);
  // 센서로부터 온도와 습도값을 읽음
  float t = dht.readTemperature();
  float h = dht.readHumidity();

  //리턴값을 체크하여 NaN(not a number) 체크
  //--> 문제가 없으면 측정값을 시리얼 모니터로 출력
  if (isnan(t) || isnan(h)) {
    Serial.println("Failed to read from DHT");
  }
  else {
    Serial.print("Temperature : ");
    Serial.print(t);
    Serial.print("^C");
    Serial.print("\t"); //Tab
    Serial.print("Humidity : ");
    Serial.print(h);
    Serial.println("%");
  }
}