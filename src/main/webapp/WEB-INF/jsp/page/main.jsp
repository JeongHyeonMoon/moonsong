<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>업체 정보 상세 보기</title>

<!-- bootstrap CDN -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- css -->
<link rel="stylesheet" type="text/css" href="/css/tab-style.css" />

<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-3.2.1.js"
	integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
	crossorigin="anonymous"></script>

<script src='/js/common.js'></script>
<script src='/js/page/page-main.js'></script>

</head>
<body>
	<input type="hidden" id="pageId" value="${pageId}">
	<div class='panel panel-default'>
		<div class='panel-heading'>
			<h2 id="main-page-title"></h2>
			<div class="tab">
				<button class="tablinks" onclick="openPage(event, 'basicInfo')"
					id="defaultOpen">기본 정보</button>
<!-- 				<button class="tablinks" onclick="openPage(event, 'scoreInfo')"
					id="score-info">평점</button> -->
				<button class="tablinks" onclick="openPage(event, 'menuInfo')">메뉴
					정보</button>
				<button class="tablinks" onclick="openPage(event, 'logInfo')"
					id="log-info">변경 이력</button>
			</div>
		</div>
		<div class='panel-body'>
			<div id="basicInfo" class="tabcontent">
				<h3>기본 정보</h3>
					<input type="button" id = "favorite" value = "즐겨찾기">
					<input type="button" id = "unfavorite" value = "즐겨찾기 취소">
				<br>
				<jsp:include page="basicInfo.jsp" flush="false" />
			</div>

<%-- 			<div id="scoreInfo" class="tabcontent">
				<h3>평점</h3>
				<jsp:include page="scoreInfo.jsp" flush="false" />
			</div> --%>

			<div id="menuInfo" class="tabcontent">
				<h3>메뉴 정보</h3>
				<jsp:include page="menuInfo.jsp" flush="false" />
			</div>

			<div id="logInfo" class="tabcontent">
				<h3>변경 이력</h3>
				<jsp:include page="logInfo.jsp" flush="false" />
			</div>

			<div id="default" class="default">
				<h3>기본 정보</h3>
				<jsp:include page="basicInfo.jsp" flush="false" />
			</div>
		</div>
		<div class='panel-footer'>
			<button class='btn btn-danger' onclick='javascript:window.close();'>닫기</button>
		</div>
	</div>
</body>
</html>