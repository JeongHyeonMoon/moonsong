<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>NEW PAGE</title>

<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-3.2.1.js"
	integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
	crossorigin="anonymous"></script>
	
<!-- javascript -->
<script src='/js/common.js'></script>
<script src='/js/page/page-create.js'></script>

</head>
<body>
	<div id = "main">
		<h3>새로운 페이지 등록</h3>
		<form>
		<table id = "insertPageTable" align="center">
			<tr>
				<th><label for = "init-tagName">태그명</label></th>
				<td><b>#</b>
				
				<c:choose>
					<c:when test='${initTagName eq null}'>
						<input type = "text" id = "init-tagName" placeholder= "tag명을 입력해주세요.">
					</c:when>
					<c:otherwise>
						<input type = "text" id = "init-tagName" value = "${initTagName}">
					</c:otherwise>
				</c:choose>
				&nbsp;<span id = "tagcheck"></td>
			</tr>
			<tr>
				<th><label for = "pageTitle">페이지 제목</label></th>
				<td><input type = "text" id = "page-title"></td>
			</tr>
			<tr>
				<th><label for = "category-id">카테고리</label></th>
				<td>
					<select id = "category-id">
						<option>::선택::</option>
						<c:forEach var="category" items="${categorys}">
		                	 <option value="${category.categoryId}">${category.categoryName}</option>
		                </c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th><label for = "content">페이지 설명</label></th>
				<td><textarea id = "content" rows="4" cols="50"  style="resize:none" placeholder= "page에 대한 설명을 자유롭게 써주세요 (장소,시간,특징 등)"></textarea></td>
			</tr>
			<tr>
				<th><label for = "writer-id">작성자</label></th>
				<td><label for = "writer-id">${writer.nickname}</label>
					<input type = "hidden" id = "writer-id" value="${writer.memberId}">
				</td>
			</tr>
			<tr>
				<th><label for = "page-main-image">대표 이미지</label></th>
				<td><input type = "file" id = "page-main-image" ></td>
			</tr>
			<tr>
				<td><input type = "button" id = "page-add" value = "등록"></td>
				<td><input type = "reset" value = "다시쓰기"></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>