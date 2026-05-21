import socket

#host 설정 및 Socket 생성
HOST, PORT = "192.168.137.207", 8080
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# 대기하다가 클라이언트의 접속요청이 있으면 연결을 설정
sock.bind((HOST, PORT))
sock.listen(1)

while True :
    #클라이언트로부터 전송된 데이터를 출력함
    conn, addr = sock.accept()
    data = conn.recv(1024)
    print("Received : ", data)
    conn.close()
