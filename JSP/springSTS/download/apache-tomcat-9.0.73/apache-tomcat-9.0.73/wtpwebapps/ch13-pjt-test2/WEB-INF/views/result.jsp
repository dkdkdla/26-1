<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입 결과 페이지</title>
<style>
    body { background-color: #f0f0f0; margin: 30px; }
    h2 { margin-bottom: 10px; }
    .box { background-color: #ffffff; border: 1px solid #cccccc; padding: 16px 20px; display: inline-block; }
    p { margin: 6px 0; }
    a { display: inline-block; margin-top: 14px; }
</style>
</head>
<body>

<h2>가입 결과</h2>

<c:choose>
    <%-- 1. 신규 가입 성공 (result == 1) --%>
    <c:when test="${result == 1}">
        <div class="box">
            <p>신규가입을 축하합니다. <b>${user.name}</b>님</p>
            <p>아이디 : ${user.userId}</p>
            <p>지급 포인트 : <b>${user.point} P</b></p>
        </div>
    </c:when>

    <%-- 2. 중복 회원 (result == 0) --%>
    <c:when test="${result == 0}">
        <div class="box">
            <p>이미 가입된 회원입니다.</p>
            <p>포인트가 지급되지 않습니다.</p>
            <p>아이디 : ${user.userId}</p>
        </div>
    </c:when>

    <%-- 3. DB 오류 (result == -1) --%>
    <c:otherwise>
        <div class="box">
            <p>오류가 발생했습니다. 다시 시도해 주세요.</p>
        </div>
    </c:otherwise>
</c:choose>

<br>
<a href="join">입력페이지로</a>

</body>
</html>
