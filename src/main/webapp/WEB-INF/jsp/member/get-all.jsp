<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src='/js/review/review-read-all.js?v=1'></script>

<div class='panel panel-default'>
	<div id='review-data-list' class='panel-body list-group'
		style='width: 100%;'></div>
</div>
<c:if test="${requestScope.AuthInformation.authorized == true}">
	<input id='hidden-member-id' type='hidden'
		value='${requestScope.AuthInformation.authorization.memberId }' />
</c:if>

<c:if test="${requestScope.AuthInformation.authorized == true}">
	<a href='javascript:openNewWindow("/reviews/add/");'
		style='position: fixed; right: 300px; bottom: 100px; width: 100px;'>
		<input type="image" src="/image/plus-button.png" style='width: 100%;' />
	</a>
</c:if>

<div id='review-data-format' style='display: none;'>
	<div class='list-group-item media' >
		<div class='media-left' id='review-prev-image-list-%review_id%'
			onclick='openNewWindow("/reviews/get/%review_id%");'
			style='width: 100px;'></div>
		<div class='media-body form-group'
			onclick='openNewWindow("/reviews/get/%review_id%");'
			style='width: 200px;'>
			<h4 class='media-heading'>%review_title%</h4>
			<ul id='tag-data-list-%review_id%' class='form-control breadcrumb'></ul>
			<div>%reg_date%</div>
			<div style='text-overflow: ellipsis; width: 600px;'>%content%</div>
		</div>
		<div class='media-right' id='review-controller'></div>
	</div>
</div>
<div id='review-controller-format' style='display: none;'>
	<button review-id='%review_id%'
		class='btn btn-primary review-update-button'
		onclick='javascript:openNewWindow("/reviews/modify/%review_id%");'>수정</button>
	<button class='btn btn-danger review-delete'
		onclick='javascript:sendDelete("/api/reviews/%review_id%", {}, function(){location.reload();});'>삭제</button>
</div>
<ul id='tag-format' class='breadcrumb' style='display: none;'>
	<li>%tag_name%</li>
</ul>