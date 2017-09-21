<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src='/js/auth/login-required.js'>
</script>

<div class='form-group'>
	<label for='id'>ID</label> <input class='form-control' type='text'
		id='id' /> <label for='password'>PASSWORD</label> <input
		class='form-control' type='password' id='password' />
	<hr>
	<button id='log-in' class='btn btn-success'>로그인</button>
	<button id='' class='btn btn-primary'
		onclick='openNewPopUp("/member/form");'>회원 가입</button>
</div>
