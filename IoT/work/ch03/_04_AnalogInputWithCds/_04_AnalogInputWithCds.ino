/* 가변저항(potentiometer)를 이용한 아날로그 입력
 * 가변저항은 A0에, LED는 D0에 연결됨
 * 작성일 : 2025.02.11
 */
#define LED_PIN D0  //아날로그 출력핀 (D0)
#define POT_PIN A0  //아날로그 입력핀 (A0)

void setup() {
  // 아날로그 입출력에서는 핀모드 설정 생략가능
  //pinMode(POT_PIN, INPUT);
  //pinMode(LED_PIN, OUTPUT);
  // 시리얼모니터 설정
  Serial.begin(115200);
}

void loop() {
  // 아날로그 입력핀으로부터 값을 읽음 --> 0~1023 사이의 값
  int input = analogRead(POT_PIN);
  // PWM의 듀티값은 0~255의 범위를 가지므로 map()함수를 이용
  //int duty = map(input, 0, 1023, 0, 255);
  // map() : 300~700 사이의 input 값을 duty 255~0으로 매핑
  // constrain() : duty 값이 0보다 적거나 255보다 큰 경우 값을 제한
  int duty = constrain(map(input, 300, 700, 255, 0), 0, 255);

  // 시리얼 모니터로 출력
  Serial.print("input : ");
  Serial.print(input);
  Serial.print(" --> duty : ");
  Serial.println(duty);

  //PWM 이용, LED의 밝기 조절
  analogWrite(LED_PIN, duty);
  delay(500); // 지연시간
}