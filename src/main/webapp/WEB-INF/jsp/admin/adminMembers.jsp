<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script src='/js/admin/admin-members.js'></script>

<div id="membersForm" class='form-group' style='padding-top: 30px;'>
<table class='table table-hover'>
	<thead>
		<tr>
			<th scope="col">번호</th>
			<th scope="col">회원 닉네임</th>
			<th scope="col">관심권역</th>
			<th scope="col">이메일</th>
			<th scope="col">등록일</th>
			<th scope="col">회원 강퇴</th>
		</tr>
	</thead>
	<tbody id = 'my-member'>
	</tbody>
</table>


<table style='display: none;'>
	<tbody id = 'my-member-format'>
		<tr>
			<td>%index%</td>
			<td>%nickname%</td>
			<td>%section%</td>
			<td>%email%</td>
			<td>%reg_date%</td>
			<td><input class='btn btn-danger fire-btn' type="button" value="강퇴" id="%memberId%"></td>
		</tr>
	</tbody>
</table>
</div>