#flask, gpiozero 라이브러리
from gpiozero import LED
from flask import Flask, request
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

#"/led?state=<state>"로 접근
#쿼리스트링으로 전달된 값은 request 객체로부터 구할 수 있음
@app.route("/led")
def ledControl() :
    state = request.args.get("state")
    #print(state)
    if state=="on" :
        led.on()
    elif state=="off" :
        led.off()
    return "LED " + state

if __name__ == "__main__" : #직접 실행시 객체 구동
    app.run(debug=True, port=80, host="0.0.0.0")