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
//입력값 저장 변수 --> 누르지 않으면 -1, 누르면 0,1,2
int inputKey = -1;

void setup() {
  //LED 핀모드 초기화
  LedInit();
  //Pushbutton 핀모드 설정
  PbtInit();
  //시리얼 모니터 초기화
  Serial.begin(115200);
}

void loop() {
  //푸시버튼의 입력값을 추출하고, 대응하는 LED를 켬
  inputKey = PbtGetKey();
  //입력값이 0보다 크면 버튼을 누른 상태
  if(inputKey >= 0) {
    //시리얼 모니터 출력
    Serial.print("Input Key is ");
    Serial.println(inputKey);
  }
  //LED 동작 처리
  LedTurnOn(inputKey);
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

//푸시버튼 값 입력 --> 단일 입력 동작
int PbtGetKey() {
  int inputKey = -1; //입력값이 0 이상이면 누른 상태
  //for 반복문을 이용, 누른 버튼 찾기
  for(int i=0; i<PBTS; i++) {
    if(digitalRead(pbtpins[i])) {
      inputKey = i;
      break; //누른 버튼을 찾았으므로 더 이상 확인할 필요 없음
    }
    delay(10);
  }
  return inputKey;
}