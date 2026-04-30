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

try :
    while True :
        #각 버튼을 누르면 이벤트 핸들러 동작에 의해 움직임
        print(led_states)
        sleep(1.0)
except KeyboardInterrupt :
    leds.value = (0, 0, 0)