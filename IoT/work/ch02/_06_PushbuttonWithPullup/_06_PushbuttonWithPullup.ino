/* 내부풀업회로(internal pullup) 회로를 이용한 푸시버튼 예제
 * 작성자 : 박민재, 작성일 : 2026.03.12
 */
//핀번호 지정
int ledpin = D7;
int pbtpin = D4;

void setup() {
  //핀모드 설정
  pinMode(ledpin, OUTPUT);
  pinMode(pbtpin, INPUT);
  digitalWrite(pbtpin, HIGH); //내부 풀업저항 활성화
  //시리얼 모니터 초기화
  Serial.begin(115200);
}

void loop() {
  //동작 : PB를 누르면 LED 켜지고, 누르지 않으면 꺼짐
  if(!digitalRead(pbtpin)) {
    Serial.println("Pbt is pressed!");
    digitalWrite(ledpin, HIGH);
  }
  else {
    //Serial.println("Pbt is unpressed!");
    digitalWrite(ledpin, LOW);
  }
  delay(500);
}