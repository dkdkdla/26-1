#LED의 On/Off 상황을 Tuple로 전달하여 LED를 동시에 제어
from gpiozero import LEDBoard
from time import sleep

#LEDBoard 객체 생성
leds = LEDBoard(23, 24, 25)

try :
    while True :
        leds.value = (1,1,1)
        sleep(1)
        leds.value = (0,0,0)
        sleep(1)
except KeyboardInterrupt :
    #종료시에는 모든 LED를 Off
    leds.value = (0,0,0)