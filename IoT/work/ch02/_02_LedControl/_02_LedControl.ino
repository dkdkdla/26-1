/*
 * 다양한 LED 패턴
 * 작성자 : 박민재, 작성일 : 2026.03.13
 */

#define LEDS 3 //LED의 갯수

//LED 핀배열 선언 --> 반복문을 활용 용이
//이진수 동작 고려 우측 LED를 배열 index 0번에 위치
const byte ledpins[LEDS] = {D5, D6, D7};

//Pushbutton 핀번호 지정
int btnpin = D4l

void setup() {
  //LED 핀모드 초기화
  LedInit();
  //Pushbutton 핀모드 설정
  pinMode(btnpin, INPUT);
  //시리얼 모니터 초기화
  Serial.begin(115200);
}

void loop() {
  //시리얼 모니터 출력 --> print() 함수는 줄바꿈 없음
  Serial.print("Pushbutton is ");
  //푸시버튼의 값을 입력으로 읽고, 값에 따라 처리
  if(digitalRead(btnpin)) { //버튼을 누른 상태
    Serial.println("pressed!");
    LedOn();
  }
  else { //버튼을 누르지 않은 상태
    Serial.println("unpressed!");
    LedOff();
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