<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>페이지 리스트</title>
<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<!-- bootstrap CDN -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">

<script src='/js/common.js'>
	
</script>
<script src='/js/search/search-list.js'>
	
</script>
</head>
<body>
	<input id='keyword' type='hidden' value='${requestScope.keyword }' />
	<div>
		<strong>키워드 : <c:out value="${requestScope.keyword }"></c:out></strong>
	</div>
	<div id='search-page-list' class='list-group'></div>
</body>

<div id='search-page-format' style='display: none;'>
	<div class='list-group-item media'>
		<div class='media-left' id='search-page-prev-image-list-%page_id%'></div>
		<div class='media-body'>
			<div class='media-top'>
				<div class='form-control'
					onclick='openNewPopUp("/page/main/%page_id%");'>
					<span class='label label-success'>%authorized%</span> %page_title%
				</div>
				<c:if test="${requestScope.AuthInformation.authorized == true }">
					<button class='btn btn-default' onclick='reportPage("%page_id%");'>신고하기</button>
				</c:if>
			</div>
			<div class='media-middle'>
				<div class=''>%section_name%</div>
				<div class=''>%category_name%</div>
			</div>
		</div>

	</div>
</div>
</html>