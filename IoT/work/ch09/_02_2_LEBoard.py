#gpiozero의 LEDBoard 클래스를 이용한 LED 제어
from gpiozero import LEDBoard
from time import sleep

#3개의 LED가 포함된 LEDBoard 객체 생성
leds = LEDBoard(23, 24, 25)

try :
    while True :
        #반복문을 이용하여 LED를 제어
        for led in leds :
            led.on()
            sleep(1)
            led.off()
            sleep(1)
except KeyboardInterrupt :
    pass