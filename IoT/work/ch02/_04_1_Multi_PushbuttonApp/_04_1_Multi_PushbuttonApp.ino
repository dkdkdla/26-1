/*
 * 다양한 LED 패턴
 * 작성자 : 박민재, 작성일 : 2026.03.12
 */

#define LEDS 3 //LED의 갯수
#define PBTS 3 //pushbutton의 갯수

//LED 핀배열 선언 --> 반복문을 활용 용이
//이진수 동작 고려 우측 LED를 배열 index 0번에 위치
const byte ledpins[LEDS] = {D5, D6, D7};
//Pushbutton 핀배열(이진수동작을 위해 우측버튼을 0번에 위치)
const byte pbtpins[PBTS] = {D1, D2, D4};
//입력값 저장 변수 --> 누르지 않으면 0, 누르면 1~7의 값
int inputKey = 0;

void setup() {
  //LED 핀모드 초기화
  LedInit();
  //Pushbutton 핀모드 설정
  PbtInit();
  //시리얼 모니터 초기화
  Serial.begin(115200);
}

void loop() {
  //푸시버튼의 입력값을 읽음
  inputKey = PbtGetByte();
  //switch~case문을 이용 다중선택 기능 처리
  switch(inputKey) {
    case 1 : //0번 스위치 --> 점멸동작
      Serial.println("---점멸동작---");
      for(int i=0; i<5; i++) {
        LedOnOff();
      }
      break;
    case 2 : //1번 스위치 --> 시퀀스 동작
      Serial.println("---순차 동작---");
      for(int i=0; i<5; i++) {
        LedSequence();
      }
      break;
    case 4 : //2번 스위치 --> 이진수 표시기 동작
      for(int j=0; j<8; j++){
        Serial.println("---이진수 표시기---");
        LedBinary(j);
      }
      break;
    default : //그 외 키 입력 --> 모든 LED를 끔
      LedOff();
      break;
  }
  delay(1000);
}

//LED 핀모드 초기화
void LedInit() {
  for(int i=0; i<LEDS ; i++){
    pinMode(ledpins[i], OUTPUT);
  }
}

//동시점멸 동작
void LedOnOff() {
  LedOn();
  LedOff();
}

//모든 LED 켜기
void LedOn() {
  for(int i=0; i<LEDS; i++) { //모든 LED 켜기
    digitalWrite(ledpins[i], HIGH);
  }
  delay(500);
}

//모든 LED 끄기
void LedOff() {
  for(int i=0; i<LEDS; i++) { //모든 LED 끄기
    digitalWrite(ledpins[i], LOW);
  }
  delay(500);
}

//특정 digit의 LED 켜기
void LedTurnOn(int digit){
  //각 디지트를 순회하며, 해당 디지트의 LED만 켬
  for(int i=0; i<LEDS; i++){
    if(i==digit) digitalWrite(ledpins[i], HIGH);
    else digitalWrite(ledpins[i], LOW);
  }
  delay(500);
}

//푸시버튼 초기화
void PbtInit() {
  for(int i=0; i<PBTS ; i++){
    pinMode(pbtpins[i], INPUT);
  }
}

//푸시버튼 값 입력 --> 다중 입력 동작
int PbtGetKey() {
  int inputKey = 0; //입력값이 0이면 unpressed
  //for 반복문을 이용, 누른 버튼 찾기
  for(int i=0; i<PBTS; i++) {
    if(digitalRead(pbtpins[i])) {
      //해당 디지트 위치에 '1'을 추가
      inputKey |= (1 << i);
    }
    delay(1);
  }
  return inputKey;
}