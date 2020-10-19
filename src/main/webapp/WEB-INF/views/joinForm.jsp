<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>joinForm 페이지 입니다</h1>
<hr/>
<form action="/joinProc" method="post">
<input type="text" name="username" placeholder="유저네임"/>
<input type="password" name="password" placeholder="패스워드"/>
<input type="email" name="email" placeholder="이메일"/>
<button>회원가입</button>
</form>
</body>
</html>