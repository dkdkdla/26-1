#LED의 On/Off 상황을 Tuple로 전달하여 LED를 동시에 제어
from gpiozero import LEDBoard
from time import sleep

#LEDBoard 객체 생성
leds = LEDBoard(23, 24, 25)

#LED 제어를 위한 Dictionary 자료 정의
led_states = {'red':0, 'green':0, 'blue':0}

try :
    while True :
        #키를 이용하여 Dictionary의 값을 원하는 형태로 변경
        #led_states['red'] = 1
        #led_states['blue'] = 1

        #사용자로부터 입력을 받아 LED 상태를 반전
        color = input(">>>상태를 바꿀 LED 입력 : ")
        #if color == "red" or color == "green" or color == "blue" :
        if color in led_states.keys() :
            led_states[color] = not led_states[color]

        #Dictionary의 값을 Tuple로 변환하여 LED 제어
        leds.value = tuple(led_states.values())
        sleep(1)
except KeyboardInterrupt :
    #종료시에는 모든 LED를 Off
    leds.value = (0,0,0)