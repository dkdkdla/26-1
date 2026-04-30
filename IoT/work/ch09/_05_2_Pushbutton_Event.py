#gpiozero 라이브러리를 이용하여 푸시버튼의 입력을 인식하는 예제
from gpiozero import Button
from time import sleep
#3개의 푸시버튼 정의, 내부 풀업저항 이용
btnred = Button(16, pull_up=True, bounce_time=1)
btngreen = Button(20, pull_up=True, bounce_time=1)
btnblue = Button(21, pull_up=True, bounce_time=1)
#각 버튼을 눌렀을 때 처리할 동작을 구현한 함수
def on_btnred_pressed() :
    print("btnred is pressed!")
def on_btngreen_pressed() :
    print("btngreen is pressed!")
def on_btnblue_pressed() :
    print("btnblue is pressed!")

# 각 버튼에 대한 이벤트 핸들러 지정
btnred.when_pressed = on_btnred_pressed
btngreen.when_pressed = on_btngreen_pressed
btnblue.when_pressed = on_btnblue_pressed

try :
    while True :
        #각 버튼을 누를 때마다 그 결과를 Shell에 출력
        sleep(0.5)
except KeyboardInterrupt :
    pass