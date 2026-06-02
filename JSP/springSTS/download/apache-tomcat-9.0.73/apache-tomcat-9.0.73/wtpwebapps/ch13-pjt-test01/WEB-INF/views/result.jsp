<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
 .box{
  border-style:solid;
 }
</style>
</head>
<body>
<h2> 가입 완료 </h2>
<div>
<c:choose>
    <%-- 1. 신규 회원 가입 성공 시 (user 객체가 존재할 때) --%>
    <c:when test="${not empty user && user.userId != null}">
        <div class="box">
            <p>신규가입을 축하합니다. <strong>${user.name}</strong>님</p>
        </div>
    </c:when>

    <%-- 2. 이미 가입된 회원이거나 오류 발생 시 --%>
    <c:otherwise>
        <div class="error-box">
            <p>이미 가입된 회원이거나 신규 회원이 아닙니다.</p>
        </div>
    </c:otherwise>
</c:choose>


</div>
<div>
 <a href="join"> 입력페이지로 </a>
</div>

</body>
</html>