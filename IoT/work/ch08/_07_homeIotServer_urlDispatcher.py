#LED : LED 구동
from gpiozero import LED
from gpiozero.pins.native import NativeFactory #--> GPIO Busy 오류 방지
#Flask:웹서버, request:요청처리, render_template:웹페이지 활용
from flask import Flask, request, render_template

#Flask 클래스를 이용하여 객체 생성
app = Flask(__name__)

#LED 핀모드 설정
redLed = 24
redled = LED(redLed, pin_factory=NativeFactory())

#"/"의 매개변수로 접근(wwwroot) --> hello()호출
# index.html 웹페이지 활용
@app.route("/")
def home() :
    return render_template("index.html")

#"/led/on"으로 접근
#-> ledOn()호출 -> Red LED를 켜고 결과 반환 ok/fail
@app.route("/led/on")
def ledOn() :
    try :
        redled.on()
        return "ok"
    except expression as identifier: # 문법 오류 주의 부분
        return "fail"

#"/led/off"으로 접근
#-> ledOff()호출 -> Red LED를 끄고 결과 반환 ok/fail
@app.route("/led/off")
def ledOff() :
    try :
        redled.off()
        return "ok"
    except expression as identifier: # 문법 오류 주의 부분
        return "fail"

if __name__ == "__main__" : #직접 실행시 객체 구동
    app.run(debug=True, port=80, host="0.0.0.0")