#define LED_PIN D0  //아날로그 출력핀 (D1)

void setup() {
  // LED 핀을 출력으로 설정--> PWM 출력에서는 생략 가능
  //pinMode(LED_PIN, OUTPUT);
}

void loop() {
  // PWM 신호의 듀티 사이클을 변화시켜 LED 밝기 조절
  for (int i = 0; i <= 255; i++) {
    analogWrite(LED_PIN, i);  // 채널 0에 듀티 사이클 설정
    delay(10);              // 시간 지연-
  }

  // delay가 중첩되면, 여러 개의 장치를 연결하는 경우 속도 저하...
  // timer 개념 이용
  delay(500);  // 밝기 변화 후 잠시 기다리기

  for (int i = 255; i >= 0; i--) {
    analogWrite(LED_PIN, i);  // 채널 0에 듀티 사이클 설정
    delay(10);              // 시간지연
  }

  delay(500); // 밝기 변화 후 잠시 기다리기
}