<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class='media'>
	<div class='media-middle'>
		<div class='media-body' style='padding: 50px 0 0 150px; width: 50%;'>
			<div class='form-group form-inline'>
				<a href='/'> <img src='/image/logo.png'
					style='width: 202 px; height: 90px;' />
				</a>
				<div class='input-group'>
					<input id='search-keyword' type='text' class='form-control'
						style='width: 500px;' />
					<div class='input-group-btn'>
						<button id='search-button' class='btn btn-default form-control'>
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class='media-right'
			style='width: 30%; height: 250px; padding-right: 300px;'>
			<c:choose>
				<c:when test="${requestScope.AuthInformation.authorized == true }">
					<jsp:include page="../auth/logined.jsp" />
				</c:when>
				<c:otherwise>
					<jsp:include page="../auth/loginRequired.jsp" />
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>