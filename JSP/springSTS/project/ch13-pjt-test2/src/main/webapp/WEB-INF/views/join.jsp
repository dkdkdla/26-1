<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 페이지</title>
<style>
    body { background-color: #f0f0f0; margin: 30px; }
    h2 { margin-bottom: 10px; }
    table { border-collapse: collapse; background-color: #ffffff; }
    td { padding: 8px 12px; border: 1px solid #cccccc; }
    input[type="text"], input[type="password"] { padding: 4px 6px; width: 200px; }
    input[type="submit"], input[type="reset"] { margin-top: 10px; padding: 5px 14px; }
</style>
</head>
<body>

<h2>회원 가입</h2>

<form action="register" method="post">
    <table>
        <tr>
            <td>ID</td>
            <td><input type="text"     name="userId"   value=""></td>
        </tr>
        <tr>
            <td>PWD</td>
            <td><input type="password" name="password" value=""></td>
        </tr>
        <tr>
            <td>NAME</td>
            <td><input type="text"     name="name"     value=""></td>
        </tr>
        <tr>
            <td>MAIL</td>
            <td><input type="text"     name="email"    value=""></td>
        </tr>
        <tr>
            <td>PHONE</td>
            <td><input type="text"     name="phone"    value=""></td>
        </tr>
    </table>
    <br>
    <input type="submit" value="SIGN UP">
    <input type="reset"  value="Cancel">
</form>

</body>
</html>
