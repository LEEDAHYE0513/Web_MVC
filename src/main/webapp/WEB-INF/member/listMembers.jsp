<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>회원정보</h1>
<ul>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Password</th>
            <th>Name</th>
            <th>Email</th>
            <th>Date</th>
            <th>수정</th>
            <th>삭제</th>

        </tr>
    <c:forEach items="${dtoList}" var="dto">
        <tr>
            <td>${dto.id}</td>
            <td>${dto.pw}</td>
            <td>${dto.name}</td>
            <td>${dto.email}</td>
            <td>${dto.date}</td>
            <td>
                <form action="/member/modMember.do">
                    <input type="hidden" name="id" value="${dto.id}">
                    <button type="submit">수정</button>
                </form>
            </td>
            <td>
                <form action="/member/delMember.do" method="post">
                    <input type="hidden" name="id" value="${dto.id}">
                    <button type="submit">삭제</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </table>
</ul>

<form action="/member/addMember.do">
    <button type="submit">회원가입하기</button>
</form>
</body>
</html>
