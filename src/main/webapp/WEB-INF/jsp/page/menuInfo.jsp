<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="addMenuForm" class='form-group' style='padding-top: 30px;'>
	<div class='form-inline' style='width: 100%; text-align: center;'>
		메뉴 이름 <input class='form-control' type="text" id="menuName" />
		가격 <input class='form-control' type="text" id="price" />
		<button id="add-menu-btn" class='form-control'>메뉴 추가</button>
	</div>
	
	<br>
	<br>
	<table class='form-group table table-hover'
		style='width: 100%;'>
		<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">메뉴</th>
				<th scope="col">가격</th>
				
			</tr>
		</thead>
		<tbody id = 'menu'>
		</tbody>
	</table>
</div>

<table style='display: none;'>
	<tbody id='menu-format'>
		<tr>
			<td>%index%</td>
			<td>%menuName%</td>
			<td>%price%</td>			
			<td><input type='button' name='menu-delete' id='%MenuName%' value='메뉴 삭제' /></td>
		</tr>
	</tbody>
</table>
