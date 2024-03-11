<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>수정</title>
</head>
<body>
<H1>수정하기</H1>
<form action="/member/modMember.do" method="post">
<input type="hidden" name="id" placeholder="아이디값을 가져오기 위해서!" value="${dto.id}">
<br><input type="text" name="pw" placeholder="비밀번호를 입력해주세요" value="${dto.pw}"><br><input type="text" name="name" placeholder="이름을 입력해주세요" value="${dto.name}"><br><input type="text" name="email" placeholder="이메일을 입력해주세요" value="${dto.email}">
<br><button type="submit">수정하기</button>
<button type="reset">다시입력</button>
</form>
</body>
</html>
