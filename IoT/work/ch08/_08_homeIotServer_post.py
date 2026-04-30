#flask, GPIO 라이브러리
from flask import Flask, request, render_template
import RPi.GPIO as GPIO
#Flask 클래스를 이용하여 객체 생성
app = Flask(__name__)
#LED 핀모드 설정
redLed = 24
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)
GPIO.setup(redLed, GPIO.OUT, initial=GPIO.LOW)

#"/"의 매개변수로 접근(wwwroot)하는 http 요청 처리 -> hello()호출
@app.route("/")
def home() :
    return render_template("index_post.html")

#"/led"으로 접근
#-> led()호출 -> 전달된 led 값에 따라 Red LED를 켜고 끔
@app.route("/led", methods=['POST'])
def led() :
    status = request.form["status"]

    if status == "on" :
        redled.on()
        return home()

    if status == "off" :
        redled.off()
        return home()