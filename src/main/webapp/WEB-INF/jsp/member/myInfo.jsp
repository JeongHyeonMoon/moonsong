<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script src='/js/member/mypage-update.js'></script>

<h3>내 정보 보기</h3>

<table class='table table-hover'>
	<tbody id='my-info'>
	</tbody>
</table>

<table style='display: none;'>
	<tbody id='my-info-format'>
		<tr>
			<td>ID</td>
			<td><input type="text" value='%member_id%'></td>
		</tr>
		<tr>
			<td>닉네임</td>
			<td><input type="text" value='%member_nickname%'></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" id="member_email" value='%member_email%'></td>
		</tr>
		<tr>
			<td>관심 권역</td>
			<td><select id="%member_id%-section"></select></td>
		</tr>
		<tr>
			<td>가입일</td>
			<td><input type="text" value='%member_regdate%'></td>
		</tr>
	</tbody>
</table>

<input type="button" class='btn btn-default' id="modify-btn"
	value="정보 수정 ">
<input type="button" class='btn btn-default' id="remove-btn"
	value="회원 탈퇴 ">