#flask, gpiozero 라이브러리
from gpiozero import LED
from flask import Flask, request
from gpiozero.pins.native import NativeFactory

#Flask 클래스를 이용하여 객체 생성
app = Flask(__name__)
#LED 핀모드 설정
redLed = 24
blueLed = 23
redled = LED(redLed, pin_factory=NativeFactory())
blueled = LED(blueLed, pin_factory=NativeFactory())

#"/"의 매개변수로 접근(wwwroot)하는 http 요청 처리 -> hello()호출
@app.route("/")
def hello() :
    return "Hello World!"

#"/ledcontrol?led=<led>&state=<state>"로 접근
#쿼리스트링으로 전달된 값은 request에서 찾을 수 있음
@app.route("/ledcontrol")
def ledControl() :
    led = request.args.get("led")
    state = request.args.get("state")
    
    # 따른 동작
    if led == "red" :
        if state == "on" :
            # /ledcontrol?led=red&state=on
            redled.on()
        elif state == "off" :
            # /ledcontrol?led=red&state=off
            redled.off()
    elif led == "blue" :
        if state == "on" :
            # /ledcontrol?led=blue&state=on
            blueled.on()
        elif state == "off" :
            # /ledcontrol?led=blue&state=off
            blueled.off()

    return "LED " + led + " " + state

if __name__ == "__main__" : #직접 실행시 객체 구동
    app.run(debug=True, port=80, host="0.0.0.0")