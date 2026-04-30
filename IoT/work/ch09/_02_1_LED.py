#gpiozero의 LED 클래스를 이용하여 개별 LED 제어
from gpiozero import LED
from time import sleep

#LED 클래스를 이용한 객체 생성
led1 = LED(23)
led2 = LED(24)
led3 = LED(25)

try :
    while True :
        #LED를 순회하면서 On/Off 실행
        led1.on()
        sleep(1)
        led1.off()
        sleep(1)
        led2.on()
        sleep(1)
        led2.off()
        sleep(1)
        led3.on()
        sleep(1)
        led3.off()
        sleep(1)
except KeyboardInterrupt :
    pass