/*
 * 시리얼 모니터로부터 여러자리 숫자를 입력받는 예제
 * 작성자 : 박민재, 작성일 : 2026.03.06.
 */
//D4 핀을 ledPin으로 설정 --> 내장 LED
int ledPin = D4;
//LED의 점멸 간격(interval) --> 입력값으로 지정
int iInterval = 0;
//입력을 처리할 임시 변수
int iInput = 0;

void setup()
{
  //핀모드 설정
  pinMode(ledPin, OUTPUT);
  //시리얼 모니터 초기화
  Serial.begin(115200);
}

void loop()
{
  iInput=0; //입력 값 초기화
//시리얼 모니터로부터 전달되어온 자료가 있으면 처리
  while(Serial.available())
  {
    //시리얼 모니터로부터 전달된 글자 한 자를 읽음
    char cValue = Serial.read();
    //Serial.println(cValue);
    if(isDigit(cValue)) //isDigit() --> 숫자변환 가능여부, ASCII '0'~'9'이면 true
    {
      //기존 값에 10을 곱하여 자릿수를 올리고, 마지막 읽은 값을 추가
      //읽은숫자 = (cValue ASCII 코드값) - ('0'의 ASCII 코드값)
      iInput = iInput * 10 + cValue - '0';
      //Serial.println(iInput);
    }
    iInterval = iInput;
  }
  Serial.print("The blink interval is ");
  Serial.println(iInterval);
  //LED 점멸 동작
  digitalWrite(ledPin, HIGH);
  delay(iInterval);
  digitalWrite(ledPin, LOW);
  delay(iInterval);
}