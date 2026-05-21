#flask 웹서버로 NodeMCU에 연결된 LED를 제어하는 예제
from flask import Flask, render_template, request
from urllib.request import import urlopen # python3

#WiFi에서 할당받은 NodeMCU의 HTTP 서버 주소
deviceIp = "192.168.137.14"
portNo = "80"
base_url = "http://" + deviceIp + ":" + portNo
# "/toggleled" url로 NodeMCU에 접속
led_url = base_url + "/ledtoggle"
#현재 LED의 상태 --> 여러 LED의 경우 딕셔너리 이용
#led_states = {"red":0, "green":0, "blue":0}
led_state = 0
#Flask 클래스를 이용하여 객체 생성
app = Flask(__name__)

#클라이언트로부터 LED 토글 동작을 위한 신호 받음
@app.route("/ledtoggle", methods=["POST"])
def controlled():
    global led_state
    #print(request)
    # "/ledtoggle" url로 NodeMCU 웹서버에 접속
    u = urlopen(led_url)
    #print(u.read())
    # 리턴값(b'1' 혹은 b'0')에 따라 led_state 값 할당
    if(u.read() == b'1'):
        #print("Led on!")
        led_state = 1
    else:
        #print("Led off!")
        led_state = 0
    return index()

#"/"의 매개변수로 접근(wwwroot)하는 http 요청 처리
@app.route("/")
def index():
    return render_template("index_led_toggle.html", led_state=led_state)

if __name__ == "__main__": #직접 실행시 객체 구동
    app.run(debug=True, port=80, host="0.0.0.0")
