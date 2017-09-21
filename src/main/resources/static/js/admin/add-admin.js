/**
 * @author Naver 문정현
 */



$(document).ready(function(){
	
	var memberFormat = $('#member-format').html();
	
	// Admin 검색
	$("#addAdminForm").on("click","#searchNicknameBtn",function(){
		$.ajax({
			url : '/api/member/search',
			contentType : 'application/json',
			method : 'POST',
			dataType : 'json',
			data : JSON.stringify({
				nickname : $('#searchNickname').val().trim()
				}),
			success : function(success){
				var renderedHtml = memberFormat.replace(/%member-id%/gi, success['memberId'])
											.replace(/%nickname%/gi, success['nickname'])
											.replace(/%email%/gi, success['email'])
											.replace(/%regDate%/gi, success['regDate']);
				$('#addAdmin > tbody').empty();
				$('#addAdmin > tbody').append(renderedHtml);
			}
		});
	});
	
	
	// Admin으로 등록
	$("#addAdminForm").on("click","input[name = 'addAdminBtn']",function(){
		
		$.ajax({
			type:'POST',
			contentType : 'application/json',
			url:'/api/admin/exist',
			data:JSON.stringify({
				'memberId' : $(this).attr('id')
			}),
			success:function(data){
				if(data == false){
					$('#admincheck').html('<b style="font-size:13px;color:green">일반유저</b>');
					addAdmin(true);
				}else{
					$('#admincheck').html('<b style="font-size:13px;color:red">이미 Admin입니다</b>');
				}
			}
		})
	});
	 

});

// admin으로 추가하는 메소드
function addAdmin(checkAdmin){
	if(checkAdmin){
		
		var r = confirm('Admin으로 추가하시겠습니까?');
	
		if(r){
			 $.ajax({
				 url : '/api/admin'
				 ,contentType : 'application/json'
				 ,method : 'POST'
				 ,data : JSON.stringify({
						'memberId' : $("input[name = 'addAdminBtn']").attr('id')
					})
				 ,success : function(success){
					alert('승인 성공');
					location.reload();
				}
				,error : function(error){
					alert('승인 실패');
				}
			 });
		}
	}
}
