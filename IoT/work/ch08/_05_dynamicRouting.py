#flask, gpiozero 라이브러리
from gpiozero import LED
from flask import Flask
from gpiozero.pins.native import NativeFactory

#Flask 클래스를 이용하여 객체 생성
app = Flask(__name__)

#LED 핀모드 설정
redLed = 24
led = LED(redLed, pin_factory=NativeFactory())

#"/"의 매개변수로 접근(wwwroot)하는 http 요청 처리 -> hello()호출
@app.route("/")
def hello() :
    return "Hello World!"

#"/led/<state>"로 접근 -> 주소로 전달된 <state>는 변수로 동작
@app.route("/led/<state>")
def ledControl(state) :
    #print(state)
    if state=="on" : #/led/on
        led.on()
    elif state=="off" : #/led/off
        led.off()
    return "LED " + state

if __name__ == "__main__" : #직접 실행시 객체 구동
    app.run(debug=True, port=80, host="0.0.0.0")