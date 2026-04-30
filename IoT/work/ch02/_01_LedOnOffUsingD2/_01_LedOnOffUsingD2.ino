/*
 * 다양한 LED 패턴
 * 작성자 : 박민재, 작성일 : 2026.03.13
 */

#define LEDS 3 //LED의 갯수

//LED 핀배열 선언 --> 반복문을 활용 용이
//이진수 동작 고려 우측 LED를 배열 index 0번에 위치
const byte ledpins[LEDS] = {D5, D6, D7};

void setup() {
  //LED 핀모드 초기화
  LedInit();
  //시리얼 모니터 초기화
  Serial.begin(115200);
}

void loop() {
  //동시 점멸 동작
  Serial.prinln("---동시 점멸 동작---");
  for(int i=0; i<5; i++){
    LedOnOff();
  }
  delay(1000);
  //순차 점멸 동작
  Serial.println("---순차 점멸 동작---");
  for(int i=0; i<5; i++){
    LedSequence();
  }
  delay(1000);
  //이진수 표시기
  Serial.println("---이진수 표시기---");
  for(int i-0; i<8; i++){
    LedBinary();
  }
  delay(1000);
  //=====도전과제======
  //Serial.println("---FirstOnFirstOff---");
  //LedFirstOnFirstOff();
  //delay(1000);
  //Serial.println("---LastOnFirstOff---");
  //LedLastOnFirstOff();
  //delay(1000);
}

//LED 핀모드 초기화
void LedInit() {
  for(int i=0; i<LEDS ; i++){
    pinMode(ledpins[i], OUTPUT);
  }
}

//동시점멸 동작
void LedOnOff() {
  for(int i=0; i<LEDS; i++) { //모든 LED 켜기
    digitalWrite(ledpins[i], HIGH);
  }
  delay(500);
  for(int i=0; i<LEDS; i++) { //모든 LED 끄기
    digitalWrite(ledpins[i], LOW);
  }
  delay(500);
}

//순차점멸 동작
void LedSequence() {
  for(int i=0; i<LEDS; i++) {
    digitalWrite(ledpins[i], HIGH);
    delay(500);
    digitalWrite(ledpins[i], LOW);
    delay(500);
  }
}

//이진수 표시기
void LedBinary(int value){
  //for문을 이용, 각 디지트의 값을 읽고 이를 LED에 출력
  for(int n=0; n<LEDS; n++){
    digitalWrite(ledpins[n], bitRead(value, n));
  }
  delay(500);
}

//=====도전과제======
//LedFirstOnFirstOff(){
//
//}
//LedLastOnFirstOff(){
//
//}