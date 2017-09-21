<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src='/js/admin/admin-request.js'></script>

<div id="pageRequestForm" class='form-group' style='padding-top: 30px;'>
	<table class='table table-hover'>
		<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">페이지 제목</th>
				<th scope="col">요청자</th>
				<th scope="col">승인</th>
			</tr>
		</thead>
		<tbody id="author-request">
		</tbody>
	</table>

	<table style='display: none;'>
		<tbody id="author-request-format">
			<tr>
				<td>%index%</td>
				<td>%pageTitle%</td>
				<td>%requester%</td>
				<td>%pageId%</td>
				<td><input type="button" class="authorBtn" requester-id="%requesterId%" page-id="%pageId%" id="%pageId%_%requesterId%"
					value="승인" /></td>
			</tr>
		</tbody>
	</table>
</div>