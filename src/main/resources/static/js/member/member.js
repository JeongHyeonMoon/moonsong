/**
 * @author Naver 문정현
 */


var nickCheckIcon;
var passwordReCheckIcon;
var passwordElement;
var sectionFormat;
var sectionList;

$(document).ready(function(){
	nickCheckIcon = $('#nickname-check');
	passwordElement = $('#password');
	passwordReCheckIcon = $('#password-check');
	sectionFormat = $('#section-format').html();
	sectionList = $('#section-select');
	
	var tnf = [false, false, false];
	var check = false;
	

	sendGet('/api/section', {}, function(success){
		for(var i = 0;i < success.length;i++){
			var renderedHtml = renderSectionFormat(sectionFormat, success[i]);
			sectionList.append(renderedHtml);
		}
	});
	
	$('#nickname').on('keyup', function(){
		var value = $(this).val();
		if(value){
			nickCheckIcon.attr({
				'class' : 'glyphicon glyphicon-remove'
			});
			nickCheckIcon.css({
				color : '#e60000'
			});
		}
		sendPost('/api/member/exist', {
			nickname : value
		}, function(data){
			if(!data){
				nickCheckIcon.attr({
					'class' : 'glyphicon glyphicon-ok'
				});
				nickCheckIcon.css({
					color : '#00cc00'
				});
				tnf[0] = true;
			}else{
				nickCheckIcon.attr({
					'class' : 'glyphicon glyphicon-remove'
				});
				nickCheckIcon.css({
					color : '#e60000'
				});
				tnf[0] = false;
			}				
		});
	});
	$('#password_re').on('keyup', function(){
		if(passwordElement.val() == $(this).val()){
			passwordReCheckIcon.attr({
				'class' : 'glyphicon glyphicon-ok'
			});
			passwordReCheckIcon.css({
				color : '#00cc00'
			});
			return;
		}
		passwordReCheckIcon.attr({
			'class' : 'glyphicon glyphicon-remove'
		});
		passwordReCheckIcon.css({
			color : '#e60000'
		});		
	});
	
	// 회원가입
	$('#join-btn').on('click', function(){
		var input = {
				'sectionId' : $('#section-select').val(),
				'nickname' : $('#nickname').val(),
				'password' : passwordElement.val(),
				'email' : $('#email-front-value').val() + '@' + $('select#email-back-value option:selected').val()
			};
		sendPost('/api/member', input, function(success){
			window.close();
		});
	});
});
function renderSectionFormat(htmlString, data){
	return $(htmlString.replace(/%section_id%/gi, data.sectionId).replace(
			/%section_name%/gi, data.sectionName));
}
function isEmpty(val){
	if(!val){
		return true;
	}
	if(val.trim() == ''){
		return true;
	}
	return false;
}
function isSame(val1, val2){
	return val1 === val2
}