#flask 라이브러리 import
from flask import Flask
#Flask 클래스를 이용하여 객체 생성
app = Flask(__name__)

#"/"의 매개변수로 접근(wwwroot)하는 http 요청 처리 -> hello()호출
@app.route("/")
def hello() :
    return "Hello World!"

#"/led/on"의 매개변수로 접근하는 http 요청 처리 -> ledOn()호출
@app.route("/led/on")
def ledOn() :
    return "LED On!"

#"/led/off"의 매개변수로 접근하는 http 요청 처리 -> ledOff()호출
@app.route("/led/off")
def ledOff() :
    return "LED Off!"

if __name__ == "__main__" : #직접 실행시 객체 구동
    app.run(debug=True, port=80, host="0.0.0.0")