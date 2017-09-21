<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div id="profileImageInfo"
	style="float: left; width: 30%; padding: 5px; margin: 2px; height: 500px; overflow-y: scroll;">
	<div id="page-image-title" style="padding: 5px; margin: 2px;"></div>
</div>
<div id="basicInfo"
	style="float: left; width: 60%; padding: 5px; margin: 2px;">
	<table class='table table-hover'>
		<tr>
			<th>페이지 제목</th>
			<td id="page-title"></td>
		</tr>
		<tr>
			<th>페이지 번호</th>
			<td id="page-id"></td>
		</tr>
		<tr>
			<th>카테고리</th>
			<td id="category-name">
				<select id='category-select' name="category">
					<option value="" disabled>::선택::</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td id="writer-nickname"></td>
		</tr>
		<tr>
			<th>등록일</th>
			<td id="reg-date"></td>
		</tr>
		<tr>
			<th>인증 여부</th>
			<td><span id="is-authorized"></span> <input type="button"
				id="author-page-btn" class='btn btn-default' value="주인 인증 요청"></td>
		</tr>

		<tr id="content-tr">
			<th>설명</th>
			<td><textarea id="content"></textarea> 
			</td>
		</tr>
		
		<!-- 인증된 페이지 내용 -->
		<tr>
			<td>소유자</td>
			<td id = "owner-id"></td>
		</tr>
		
		<tr>
			<td>주소</td>
			<td ><input type="text" id = "address"></td>
		</tr>
		
		<tr>
			<td>전화번호</td>
			<td ><input type="text" id = "phone-number"></td>
		</tr>

		<tr id = "section-name">
			<td>권역</td>
			<td > 
				<select id='section-select' name="section">
					<option value="" disabled>::선택::</option>
				</select>
			</td>
		</tr>
		
		<tr>
			<td>영업 시간</td>
			<td><span><input type="time"  id = "start-time" required ></span> ~ <span ><input type="time" id = "end-time" required ></span></td>

		</tr>
		
	</table>
	<input type="button" id="page-modify" class='btn btn-default' value="기본 정보 수정">
</div>

<div id="imageInfo" style="padding: 5px; margin: 2px; clear: both;">
	<h3>사진보기</h3>
	<div id="page-image-list" style="padding: 5px; margin: 2px;"></div>
	<input class='btn btn-default' id='page-new-image' type='file' />
	<button id='page-image-add' class='btn btn-default'>사진 추가</button>
</div>
<div id="comment-form">
	<input type="text" id="comment-content" />
	<button class='btn btn-success' id="comment-btn">댓글 입력</button>
	<div id='comment-list' class='list-group'></div>
</div>
<div id='comment-format' style='display: none;'>
	<div class='media'>
		<div class='media-body'>
			<h3 class='media-heading'>
				<b>%writerNickName%</b>
			</h3>
			%regDate%
			<div>%content%</div>
		</div>
		<input type="button" value="삭제" class="comment-delete"
			writer-id="%writerId%" id="%commentId%"> <input type="button"
			value="신고" class="comment-report" comment-id="%commentId%">
	</div>
</div>

</html>
