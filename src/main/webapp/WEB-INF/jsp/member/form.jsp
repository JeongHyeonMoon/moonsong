<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-3.2.1.js?v=1"></script>
<!-- bootstrap CDN -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script src='/js/member/member.js'>
	
</script>
<script src='/js/common.js'>
	
</script>
</head>
<body>
	<div id="main" class='panel panel-default'>
		<div class='panel-heading'>
			<h2 align="center">회원 가입</h2>
		</div>
		<div class='panel-body'>
			<h5 align="center">* 는 필수입력 항목입니다.</h5>
			<div class='list-group'>
				<div class='list-group-item'>
					<label for='nickname'>* 닉네임</label> <input type='text'
						id='nickname' maxlength='30'>&nbsp;<span
						id='nickname-check'
						class='glyphicon glyphicon-remove glyphicon-danger'
						style='color: #e60000;'></span>
				</div>
				<div class='list-group-item'>
					<label for="password">* 비밀번호</label> <input type="password"
						id="password" maxlength="10">
				</div>
				<div class='list-group-item'>
					<label for="password">* 비밀번호 확인</label> <input type="password"
						id="password_re" maxlength="10">&nbsp; <span
						id='password-check'
						class='glyphicon glyphicon-remove glyphicon-danger'
						style='color: #e60000;'></span>
				</div>
				<div class='list-group-item'>
					<label for="email-front-value">이메일</label> <input
						id='email-front-value' type="text" name="email">@ <select
						id='email-back-value' name="emailaddr">
						<option value="">::선택::</option>
						<option value="daum.net">daum.net</option>
						<option value="empal.com">empal.com</option>
						<option value="gmail.com">gmail.com</option>
						<option value="hanmail.net">hanmail.net</option>
						<option value="msn.com">msn.com</option>
						<option value="naver.com">naver.com</option>
						<option value="nate.com">nate.com</option>
					</select>
				</div>
				<div class='list-group-item'>
					<label for="section">관심권역</label> <select id='section-select'
						name="section">
						<option value="" disabled>::선택::</option>
					</select>
				</div>
			</div>
		</div>
		<div class='panel-footer'>
			<button id='join-btn' class='btn btn-default'>회원 가입</button>
			<button class='btn btn-danger'>닫기</button>
		</div>
	</div>
	<div style='display: none;'>
		<select id='section-format'>
			<option value="%section_id%">%section_name%</option>
		</select>
	</div>
</body>
</html>