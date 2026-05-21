<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입완료 페이지</title>
</head>
<body>
<% boolean isNew = (Boolean) request.getAttribute("isNew"); %>
<% if (isNew) { %>
    <div>가입 완료 되었습니다.</div>
    <div>축하합니다. ${user.name}님!</div>
    <div>신규가입으로 <strong>${user.point} 포인트</strong>가 지급되었습니다.</div>
<% } else { %>
    <div>이미 가입된 회원입니다.</div>
    <div>${user.name}님은 기존 회원이므로 포인트가 지급되지 않습니다.</div>
<% } %>
<a href="join">돌아가기</a>
</body>
</html>