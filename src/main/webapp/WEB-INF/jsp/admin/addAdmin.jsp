<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script src='/js/admin/add-admin.js'></script>

<div id="addAdminForm" class='form-group' style='padding-top: 30px;'>
	<div class='form-inline' style='width: 100%; text-align: center;'>
		<input class='form-control' type="text" id="searchNickname" />
		<button id="searchNicknameBtn" class='form-control'>검색</button>
	</div>
	
	<br>
	<br>
	<table id="addAdmin" class='form-group table table-hover'
		style='width: 100%;'>
		<thead>
			<tr>
				<th scope="col">멤버 ID</th>
				<th scope="col">닉네임</th>
				<th scope="col">이메일</th>
				<th scope="col">가입일</th>
				<th scope="col">승인</th>
			</tr>
		</thead>
		<tbody>
			<tr>
			</tr>
		</tbody>
	</table>
</div>

<table style='display: none;'>
	<tbody id='member-format'>
		<tr>
			<td>%member-id%</td>
			<td>%nickname%</td>
			<td>%email%</td>
			<td>%regDate%</td>
			<td><input type='button' name='addAdminBtn' id='%member-id%' value='승인' /></td>
		</tr>
		<tr>
			<td><span id = "admincheck"></span></td>
		</tr>
	</tbody>
</table>
