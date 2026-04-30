/* 푸시버튼 입력에 대응한 부저(Buzzer) 동작
 * Toggle Button을 이용하여 누를 때 마다 상태 변화가 일어나며,
 * 상태값에 따라 Buzzer의 On/Off 제어
 * 작성자 : 김형래, 작성일 : 2023.02.15
 */
//핀번호 지정
#define BZ_PIN D1 //D1 (gpio5)
#define LED_PIN D2 //D2 (gpio4)
#define PB_PIN D4 //D4 (gpio2)
//상태값 지정 변수 선언
int buzzerState = LOW; //Buzzer의 현재 상태 값
int currentButtonState = false; //푸시버튼의 현재상태
int previousButtonState = false; //푸시버튼의 이전 상태

void setup() {
  //핀모드 설정
  pinMode(BZ_PIN, OUTPUT);
  pinMode(LED_PIN, OUTPUT);
  pinMode(PB_PIN, INPUT_PULLUP);
  //시리얼모니터 초기화
  Serial.begin(115200);
}

void loop() {
  //현재 푸시버튼 상태값을 읽음
  currentButtonState = digitalRead(PB_PIN);
  //pullup회로 이용, 토글버튼 구현 --> falling edge
  if(previousButtonState && !currentButtonState) {
    //Serial.println("The pushbutton is pressed!");
    buzzerState = !buzzerState; //Buzzer 상태값 반전
  }
  //부저 제어 동작, "솔" 음을 0.3초씩 간격으로 발생
  if(buzzerState) {
    tone(BZ_PIN, 392, 500);
    digitalWrite(LED_PIN, HIGH);
    delay(300);
    noTone(BZ_PIN);
    digitalWrite(LED_PIN, LOW);
    delay(300);
  }
  else {
    noTone(BZ_PIN);
    digitalWrite(LED_PIN, LOW);
  }
  //현재 푸시버튼 상태값을 이전 상태값에 할당
  previousButtonState = currentButtonState;
  delay(100); //0.1초 지연
}