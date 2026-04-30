#flask 라이브러리 import
from flask import Flask
#Flask 클래스를 이용하여 객체 생성
#객체명은 app, __name__은 현재 사용중인 파일명 혹은 모듈명
#직접 실행 : __main__ 할당, 다른 파일에서 import : _01_flask_basic.py
app = Flask(__name__)

#"/"의 매개변수로 접근(wwwroot)하는 http 요청 처리 -> hello()호출
@app.route("/")
def hello() :
    return "Hello World!"

if __name__ == "__main__" : #직접 실행시 객체 구동
    app.run()