<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MS</title>
<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<!-- bootstrap CDN -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">

<!-- css -->
<link rel="stylesheet" media="screen" type="text/css"
	href="/css/body-style.css" />
<link rel="stylesheet" media="screen" type="text/css"
	href="/css/tab-style.css" />
<!-- javascript -->

<script src='/js/common.js'>
	
</script>

<script src='/js/index.js'>
	
</script>
</head>
<body style='padding: 0 200px 0 200px;'>
	<div>
		<div class='header'>
			<jsp:include page='./component/header.jsp' />
		</div>
		<div class='body'>
			<div class='body-tab'>
				<c:choose>
					<c:when test="${requestScope.AuthInformation.admin == true}">
						<div class="tab">
							<button class='tablinks' onclick='getTab("/reviews")'>전체
								리뷰 보기</button>
							<button class='tablinks' onclick='getTab("/member/myinfo")'>내
								정보</button>
							<button class='tablinks' onclick='getTab("/member/myreviews")'>내
								리뷰</button>
							<button class='tablinks' onclick='getTab("/member/mybookmarks")'>즐겨찾기</button>

							<button class="tablinks" onclick="getTab('/admin/members')"
								id="defaultOpen">전체 회원 보기</button>
							<button class="tablinks" onclick="getTab('/admin/reports')">신고
								내역</button>
							<button class="tablinks" onclick="getTab('/admin/auth')">인증
								허가</button>
							<button class="tablinks" onclick="getTab('/admin/new')">Admin
								추가</button>
						</div>
					</c:when>
					<c:when test="${requestScope.AuthInformation.authorized == true }">
						<div class="tab">
							<button class='tablinks' onclick='getTab("/reviews")'>전체
								리뷰 보기</button>
							<button class='tablinks' onclick='getTab("/member/myinfo")'>내
								정보</button>
							<button class='tablinks' onclick='getTab("/member/mybookmarks")'>즐겨찾기</button>
							<button class='tablinks' onclick='getTab("/member/myreviews")'>내
								리뷰</button>
						</div>
					</c:when>
					<c:otherwise>
						<div class="tab">
							<button class='tablinks' onclick='getTab("/reviews")'>전체
								리뷰 보기</button>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			<div id='body-content' class='body-content'></div>
		</div>
	</div>
</body>
</html>