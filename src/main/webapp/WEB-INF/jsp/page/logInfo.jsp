<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<div id = "pageLog" class='form-group' style='padding-top: 30px;'>
	<table class='table table-hover'>
		<thead>
			<tr>
				<th scope = "col">번호</th>
				<th scope = "col">페이지 번호</th>
				<th scope = "col">내용</th>
				<th scope = "col">카테고리 번호</th>
				<th scope = "col">주소</th>
				<th scope = "col">전화번호</th>
				<th scope = "col">권역</th>
				<th scope = "col">시작 시간</th>
				<th scope = "col">끝 시간</th>
				<th scope = "col">변경 시간</th>
				<th scope = "col">변경한 사람</th>
			</tr>
		</thead>
		<tbody id ="page-log">
		</tbody>
	</table>
	
	<table style='display: none;'>
	<tbody id = 'page-log-format'>
		<tr>
			<td>%index%</td>
			<td>%pageId%</td>
			<td>%content%</td>
			<td>%categoryId%</td>
			<td>%address%</td>
			<td>%phoneNumber%</td>
			<td>%sectionId%</td>
			<td>%startTime%</td>
			<td>%endTime%</td>
			<td>%changeDate%</td>
			<td>%loginId%</td>
		</tr>
	</tbody>
</table>
	
	
</div>
</html>