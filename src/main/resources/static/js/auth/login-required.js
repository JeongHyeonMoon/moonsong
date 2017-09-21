/**
 * 
 */
$(document).ready(function(){
	$('#log-in').on('click', function(){
		sendPostHandleError('/auth/login',{
			id : $('#id').val(),
			password : $('#password').val()
		}, function(success){
			location.reload();
		}, function(errorStatus){
			switch(errorStatus){
				case 404:
					alert('일치하는 회원 정보가 없습니다.');
					break;
				default : 
					alert(errorStatus + ' 에러가 발생했습니다.');
					break;
			}
		});
	});
});

