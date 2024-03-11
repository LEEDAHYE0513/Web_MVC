<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
<H1>회원 가입</H1>
<form action="/member/addMember.do" method="post">
    <input type="text" name="id" placeholder="아이디를 입력해주세요">
    <br>
    <input type="text" name="pw" placeholder="비밀번호를 입력해주세요">
    <br>
    <input type="text" name="name" placeholder="이름을 입력해주세요">
    <br>
    <input type="text" name="email" placeholder="이메일을 입력해주세요">
    <br>
    <button type="submit">가입하기</button>
    <button type="reset">다시입력</button>
</form>
</body>
</html>
