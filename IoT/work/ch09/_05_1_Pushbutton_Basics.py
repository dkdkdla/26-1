#gpiozero 라이브러리를 이용하여 푸시버튼의 입력을 인식하는 예제
from gpiozero import Button
from time import sleep

#3개의 푸시버튼 정의, 내부 풀업저항 이용
btnred = Button(16, pull_up=True, bounce_time=1)
btngreen = Button(20, pull_up=True, bounce_time=1)
btnblue = Button(21, pull_up=True, bounce_time=1)

try :
    while True :
        #각 버튼을 누를 때마다 그 결과를 Shell에 출력
        if btnred.is_pressed :
            print("btnred is pressed!")
        if btngreen.is_pressed :
            print("btngreen is pressed!")
        if btnblue.is_pressed :
            print("btnblue is pressed!")
        sleep(0.5)
except KeyboardInterrupt :
    pass