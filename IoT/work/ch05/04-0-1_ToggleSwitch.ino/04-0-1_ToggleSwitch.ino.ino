/* 푸시버튼을 이용한 토글스위치 구현
 * internal pullup저항을 채용, 누르지 않으면 1, 누르면 0
 * 작성자 : 김형래, 작성일 : 2023.02.15
 */
//핀번호 지정
#define LED_PIN D4 //gpio2
#define PB_PIN D2 //gpio4
//상태값 지정 변수 선언
int ledState = LOW; //LED의 현재 상태(On/Off) 값
int currentButtonState = false; //푸시버튼의 현재상태
int previousButtonState = false; //푸시버튼의 이전 상태

void setup() {
  //핀모드 설정
  pinMode(LED_PIN, OUTPUT);
  pinMode(PB_PIN, INPUT_PULLUP); //내부풀업회로
  //시리얼모니터 초기화
  Serial.begin(115200);
}

void loop() {
  //현재 푸시버튼 상태값을 읽음
  currentButtonState = digitalRead(PB_PIN);
  //토글버튼 --> 풀업에서는 falling edge에서 상태값 변화
  if(previousButtonState && !currentButtonState) {
    //Serial.println("The pushbutton is pressed!");
    ledState = !ledState; //LED 상태값 반전
  }
  digitalWrite(LED_PIN, ledState); //LED 제어동작
  //현재 푸시버튼 상태값을 이전 상태값에 할당
  previousButtonState = currentButtonState;
  delay(100); //0.1초 지연
}