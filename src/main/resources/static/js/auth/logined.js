/**
 * 
 */

var memberNicknameElement;

$(document).ready(function(){
	memberNicknameElement = $('#login-member-nickname');
	
	sendGet('/api/member/my-info', {}, function(success){
		memberNicknameElement.text(success.nickname + ' 님 환영합니다.');
	});
	
	$('#log-out').on('click', function(){
		sendDeleteHandleError('/auth/logout', {}, function(success, statusCode){
			location.reload();
		}, function(){
			location.reload();
		});
	});
});