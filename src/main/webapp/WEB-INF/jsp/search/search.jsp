<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script src='/js/search/search.js'>
	
</script>

<div id='search-page-list' class='list-group'></div>
<a href='javascript:openPageList();'>페이지 더보기</a>
<div id='search-review-list' class='list-group'></div>

<div id="search-new-page-format" style='display: none;'>
	<div id='' class='list-group-item panel panel-default'>
		<div class='panel-heading'>
			<h4>새로운 페이지 등록</h4>
		</div>
		<div class='panel-body'>
			<div id="insertPageTable" class='form-group'>
				<label for="pageTitle">페이지 제목</label> <input class='form-control'
					type="text" id="page-title" value='%page_title%' disabled /> <label
					for="category-id">카테고리</label> <select class='form-control'
					id="%page_title%-category-id">
					<option disabled>::선택::</option>
				</select> <label for="content">페이지 설명</label>
				<textarea class='form-control' id="%page_title%-content" rows="4"
					cols="50" style="resize: none"
					placeholder="page에 대한 설명을 자유롭게 써주세요 (장소,시간,특징 등)"></textarea>
				<label for="page-main-image">대표 이미지</label> <input
					class='form-control' type="file" id="%page_title%-page-main-image" />
			</div>
		</div>
		<div class='panel-footer'>
			<button onclick='javascript:addPage("%page_title%");'
				class='btn btn-default'>등록</button>
		</div>
	</div>
</div>
<select id='category-format' style='display: none;'>
	<option value='%category_id%'>%category_name%</option>
</select>

<div id='search-review-format' style='display: none;'>
	<div id='search-review-data-%review_id%' class='list-group-item media'>
		<div class='media-left' id='search-review-prev-image-list-%review_id%'
			style='width: 100px;'></div>
		<div class='media-body form-group' style='width: 200px;'>
			<div class='media-top'>
				<h4 class='media-heading'
					onclick='openNewPopUp("/reviews/get/%review_id%");'>%review_title%</h4>
				<c:if test="${requestScope.AuthInformation.authorized == true }">
					<button class='btn btn-default'
						onclick='reportReview("%review_id%");'>신고하기</button>
				</c:if>
			</div>
			<ul id='search-tag-review-list-%review_id%'
				class='form-control breadcrumb'></ul>
			<div>%reg_date%</div>
			<div style='text-overflow: ellipsis; width: 600px;'>%content%</div>
		</div>
		<div class='media-right' id='search-review-controller-%review_id%'></div>
	</div>
</div>

<div id='search-review-controller-format' style='display: none;'>
	<button review-id='%review_id%'
		class='btn btn-primary review-update-button'
		onclick='javascript:openNewWindow("/reviews/%review_id%?type=update");'>수정</button>
	<button class='btn btn-danger review-delete'
		onclick='javascript:sendDelete("/reviews/%review_id%", {}, function(){location.reload();});'>삭제</button>
</div>

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