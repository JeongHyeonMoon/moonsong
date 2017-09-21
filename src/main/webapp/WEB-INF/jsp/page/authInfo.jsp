<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div id = "page-image-title" style="float: left; width: 30%; padding:5px; margin:2px;"></div>

<div id = "basicInfo" style="float: left; width: 60%; padding:5px; margin:2px;">
	<table class='table table-hover'>
		<tr><th>페이지 제목</th><td id = "page-title"></td></tr>
		<tr><th>페이지 번호</th><td id = "page-id"></td></tr>
		<tr><th>관련 태그</th><td id = "init-tagName"></td></tr>
		<tr><th>카테고리</th><td id = "category-id"></td></tr>
		<tr><th>작성자</th><td id = "writer-id"></td></tr>
		<tr><th>등록일</th><td id = "reg-date"></td></tr>
		<tr><th>인증 여부</th><td id = "is-authorized"></td></tr>
	</table>
</div>

<div id = "page-image-list" style = "padding:5px; margin:2px; clear : both;">

<input id='page-new-image' type='file' value = "사진 추가">
<input type="button" id = "page-image-add" value = "사진 등록">

<h3>사진보기 </h3>
</div>

</html>