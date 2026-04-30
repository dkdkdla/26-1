# _07_pushbutton_monitor_challenge.py
#3개의 pushbutton으로 LED에 대한 토글동작을 수행하고, 이를 원격 모니터링
from flask import Flask, render_template, url_for, redirect
#푸시버튼 3개를 LED의 토글 스위치로 동작
#푸시버튼 동작으로는 [실습5-2]를 이용
from gpiozero import LEDBoard, Button
from time import sleep
#이름을 갖는 LEDBoard 선언, 알파벳 순서로 값이 할당됨
leds = LEDBoard(blue=25, green=24, red=23)
#3개의 푸시버튼 정의, 내부 풀업저항 이용
btnred = Button(16, pull_up=True, bounce_time=1)
btngreen = Button(20, pull_up=True, bounce_time=1)
btnblue = Button(21, pull_up=True, bounce_time=1)
#각 LED의 상태를 저장할 딕셔너리(키와 값의 쌍으로 저장)변수 선언
led_states ={'blue':0, 'green':0, 'red':0}

#각 버튼을 눌렀을 때 처리할 동작을 구현한 함수
def on_btnred_pressed() :
    leds.red.toggle()
    led_states['red'] = leds.red.value
def on_btngreen_pressed() :
    leds.green.toggle()
    led_states['green'] = leds.green.value
def on_btnblue_pressed() :
    leds.blue.toggle()
    led_states['blue'] = leds.blue.value

# 각 버튼에 대한 이벤트 핸들러 지정
btnred.when_pressed = on_btnred_pressed
btngreen.when_pressed = on_btngreen_pressed
btnblue.when_pressed = on_btnblue_pressed

#Flask 클래스를 이용하여 객체 생성
app = Flask(__name__)
#"/"의 매개변수로 접근(wwwroot)하는 http 요청 처리 --> home()호출
@app.route("/")
def home() :
    #전역변수 사용
    global led_states
    #print(led_states)
    #sleep(1)
    return render_template('buttonapp_c.html', led_states=led_states)

#"/emergency_stop"으로 접근하였을 때, 모든 LED를 Off 시키는 예제
@app.route("/emergency_stop")
def emergency_stop() :
    global led_states
    #모든 LED 상태를 0으로 변경
    led_states = {'blue':0, 'green':0, 'red':0}
    #모든 LED Off
    leds.value = (0, 0, 0)
    return redirect(url_for('home'))

if __name__ == "__main__" : #직접 실행시 객체 구동
    app.run(port=80, host="0.0.0.0")