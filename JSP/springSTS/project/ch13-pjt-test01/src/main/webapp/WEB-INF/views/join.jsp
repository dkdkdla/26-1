<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 페이지</title>
</head>
<body>
<h3>로그인 페이지 입니다.</h3>

<%-- POST 방식으로 /register 에 데이터 전송 --%>
<form action="register" method="post">
    <table border="1" cellpadding="6" cellspacing="0">
        <tr>
            <th>ID</th>
            <td><input type="text"     name="userId"   value=""></td>
        </tr>
        <tr>
            <th>PWD</th>
            <td><input type="password" name="password" value=""></td>
        </tr>
        <tr>
            <th>NAME</th>
            <td><input type="text"     name="name"     value=""></td>
        </tr>
        <tr>
            <th>MAIL</th>
            <td><input type="text"     name="email"    value=""></td>
        </tr>
        <tr>
            <th>PHONE</th>
            <td><input type="text"     name="phone"    value=""></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="SIGN UP">
            </td>
        </tr>
    </table>
</form>

</body>
</html>
