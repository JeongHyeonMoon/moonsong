<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src='/js/member/mybookmarks.js'></script>

<div id='search-page-list' class='list-group'></div>

<select id='category-format' style='display: none;'>
	<option value='%category_id%'>%category_name%</option>
</select>

<div id='search-page-format' style='display: none;'>
	<div class='list-group-item media'>
		<div class='media-left' id='search-page-prev-image-list-%page_id%'></div>
		<div class='media-body'>
			<div class='media-top'>
				<div class='form-control'
					onclick='openNewPopUp("/page/main/%page_id%");'>
					<span class='label label-success'>%authorized%</span> %page_title%
				</div>
			</div>
			<div class='media-middle'>
				<div class=''>%section_name%</div>
				<div class=''>%category_name%</div>
			</div>
		</div>
	</div>
</div>