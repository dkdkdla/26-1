#flask 라이브러리 import
from flask import Flask, request, render_template
from gpiozero import LEDBoard
#LED 관련 핀번호
leds = LEDBoard(23, 24, 25)
#LED의 On/Off 동작 처리를 위한 딕셔너리 --> {키:값}
led_states = {'red' : 0, 'green' : 0, 'blue' : 0}
#Flask 클래스를 이용하여 객체 생성
app = Flask(__name__)
#"/"의 매개변수로 접근(wwwroot)하는 http 요청 처리 --> home()호출
@app.route("/")
def home() :
    return render_template('index.html', led_states=led_states)
#"/<color>/<int:state>"의 산형괄호 http 요청 처리
@app.route("/<color>/<int:state>")
def ledControl(color, state) :
    #키에 해당하는 딕셔너리에 값을 할당
    led_states[color] = state
    #딕셔너리 값에 따라 LED의 On/Off 동작을 시행
    leds.value = tuple(led_states.values())
    #LED 상태값을 포함하여 index.html 호출결과를 리턴
    return render_template('index.html', led_states=led_states)

if __name__ == "__main__" : #직접 실행시 객체 구동
    app.run(port=80, host="0.0.0.0")