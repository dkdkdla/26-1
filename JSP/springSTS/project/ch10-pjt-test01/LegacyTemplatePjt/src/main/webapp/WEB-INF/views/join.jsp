<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 페이지</title>
</head>
<body>
<div>회원 가입</div>
<form method="post" action="register"> <%-- POST 방식으로 /register에 데이터 전송 --%>
	<table border="1" cellspacing="0" cellpadding="6">
		<tr><th>아이디</th>
			<td> <input type="text" name="userId" value=""> </td><%-- UserDTO의 userId에 바인딩 --%>
		</tr>
		<tr><th>이름</th>
			<td> <input type="text" name="name" value=""> </td> <%-- UserDTO의 name에 바인딩 --%>
		</tr>
		<tr> <td colspan="2"> <button type="submit">가입하기</button> </td>
	</table>
</form>
</body>
</html>
