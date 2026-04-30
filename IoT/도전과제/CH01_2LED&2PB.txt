# 2개의 토글 버튼 제어
import RPi.GPIO as GPIO
# pushbutton, LED 핀 지정 (슬라이드 기준)
btnPin1 = 23
ledPin1 = 27
btnPin2 = 24
ledPin2 = 22
# 불필요한 경고 제거
GPIO.setwarnings(False)
# 핀모드 설정
GPIO.setmode(GPIO.BCM)
    GPIO.setup(btnPin1, GPIO.IN, pull_up_down=GPIO.PUD_UP)
    GPIO.setup(btnPin2, GPIO.IN, pull_up_down=GPIO.PUD_UP)
    GPIO.setup(ledPin1, GPIO.OUT)
    GPIO.setup(ledPin2, GPIO.OUT)
# 이전 버튼 상태 및 LED 현재 상태 변수 선언
preBtn1Input = True
    led1On = False
    preBtn2Input = True
    led2On = False
# 외부 단자로부터의 입력 처리
try :
    while True :
        # 버튼 1 관련 동작
       curBtn1Input = GPIO.input(btnPin1)
        if not curBtn1Input and preBtn1Input :
            led1On = not led1On
            time.sleep(0.1)
        # 버튼 2 관련 동작
        curBtn2Input = GPIO.input(btnPin2)
        if not curBtn2Input and preBtn2Input :
            led2On = not led2On
            time.sleep(0.1)
        # LedOn 상태값에 따라 LED 동작
        GPIO.output(ledPin1, led1On)
        GPIO.output(ledPin2, led2On)        
        # 동일 입력에 대응하지 않도록 이전 입력값을 변경
        preBtn1Input = curBtn1Input
        preBtn2Input = curBtn2Input
except KeyboardInterrupt :
    pass
# GPIO 초기화
GPIO.cleanup()