#웹 페이지의 <a> 태크로 LED 상태값을 전달받아 개별 LED를 제어
from flask import Flask, render_template, url_for, redirect
from gpiozero import LEDBoard
#LED 3개가 연결된 LED 객체 생성
leds = LEDBoard(23, 24, 25)
#LED의 On/Off 동작 처리를 위한 딕셔너리 --> {키:값}
led_states = {'red' : 0, 'green' : 0, 'blue' : 0}
#Flask 클래스를 이용하여 객체 생성
app = Flask(__name__)
#"/"의 매개변수로 접근(wwwroot)하는 http 요청 처리 --> home()호출
#index.html을 호출할 때, led_states 값을 전달하여 jinja2 오류 회피
@app.route("/")
def home() :
    return render_template('index_a.html', led_states=led_states)
#"/<color>/<int:state>"로 변수값을 전달받아 개별 LED 제어
@app.route("/<color>/<int:state>")
def ledControl(color, state) :
    #전역변수 사용
    global led_states
    #키에 해당하는 딕셔너리에 값을 할당
    led_states[color] = state
    #딕셔너리 값에 따라 LED의 On/Off 동작을 시행
    leds.value = tuple(led_states.values())
    #LED 상태값 변경 후 기본 페이지 이동 --> 다양한 장비 제어 목적
    return redirect(url_for('home'))

#모든 LED를 동시에 제어
@app.route("/all/<int:state>")
def allOnOff(state) :
    #전역변수 사용
    global led_states
    #state에 따른 값 할당
    if state == 1 :
        led_states = {'red' : 1, 'green' : 1, 'blue' : 1}
    else :
        led_states = {'red' : 0, 'green' : 0, 'blue' : 0}
    #딕셔너리 값에 따라 LED의 On/Off 동작을 시행
    leds.value = tuple(led_states.values())
    #LED 상태값 변경 후 기본 페이지 이동 --> 다양한 장비 제어 목적
    return redirect(url_for('home'))

if __name__ == "__main__" : #직접 실행시 객체 구동
    app.run(port=80, host="0.0.0.0")