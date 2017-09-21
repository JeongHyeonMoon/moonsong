<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src='/js/auth/logined.js'>
	
</script>

<div class='form-group'>
	<div class='media'>
		<div class='media-top'></div>
		<div class='media-body'>
			<c:choose>
				<c:when test="${requestScope.AuthInformation.admin == true }">
					<span class='label label-danger'>ADMIN</span>
				</c:when>
				<c:when test="${requestScope.AuthInformation.authorized == true }">
					<span class='label label-success'>NORMAL</span>
				</c:when>
			</c:choose>
			<div id='login-member-nickname'></div>
		</div>
		<div class='media-bottom'>
			<button id='log-out' class='btn btn-sm btn-danger'>로그아웃</button>
		</div>
	</div>
</div>
