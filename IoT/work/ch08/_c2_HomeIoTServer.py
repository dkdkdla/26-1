#LED : LED 구동
from gpiozero import LED
from gpiozero.pins.native import NativeFactory #--> GPIO Busy 오류 방지
#flask, GPIO 라이브러리
from flask import Flask, request, render_template

#Flask 클래스를 이용하여 객체 생성
app = Flask(__name__)
#LED 핀모드 설정
redLed = 24
blueLed = 23
redled = LED(redLed, pin_factory=NativeFactory())
blueled = LED(blueLed, pin_factory=NativeFactory())

#"/"의 매개변수로 접근(wwwroot)하는 http 요청 처리 -> hello()호출
@app.route("/")
def home() :
    return render_template("index_c.html")

#"/ledcontrol"로 접근
#-> ledcontrol()호출 -> 전달된 led, status 값에 따라 LED를 켜고 끔
@app.route("/ledcontrol", methods=['POST'])
def ledcontrol() :
    led = request.form["led"]
    status = request.form["status"]

    #LED와 status 값에 따른 동작 처리
    if led == "red" :
        if status == "on" :
            redled.on()
        elif status == "off" :
            redled.off()
    elif led == "blue" :
        if status == "on" :
            blueled.on()
        elif status == "off" :
            blueled.off()

    # home() 호출 --> 웹페이지 호출
    return home()

if __name__ == "__main__" : #직접 실행시 객체 구동
    app.run(debug=True, port=80, host="0.0.0.0")