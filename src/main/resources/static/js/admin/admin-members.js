/**
 * @author Naver 문정현
 */


var memberFormat;


$(document).ready(function(){
	
	// 전체 멤버 리스트 보이기
	sendGet('/api/member', {}, function(members){
		for(var i = 0; i<members.length; i++){
			appendMember(members[i],i);
		}
	})

	$("#membersForm").on("click",".fire-btn",function(){
	 // 회원 강퇴 버튼
		var r = confirm('정말 회원을 강퇴시키겠습니까?');
		
		if(r){
			 var memberId = {
					 'memberId' : $(this).attr('id')
			 }
			 
			 $.ajax({
				 url : '/api/member',
				 contentType : 'application/json',
				 method : 'DELETE',
				 data : JSON.stringify(memberId),
				 success : function(success){
					 alert('강퇴 성공');
					location.reload();  
				},
				error : function(error){
					alert('강퇴 실패');
				}
			 });
		}
	 })

});

// 테이블에 추가하는 메소드
function appendMember(member,index){
	var myMemberFormat = $('#my-member-format').html();
	
	var renderedHtml = myMemberFormat.replace(/%index%/,index+1)
	.replace(/%nickname%/gi,member.nickname)
	.replace(/%section%/gi,member.sectionName)
	.replace(/%email%/gi,member.email)
	.replace(/%reg_date%/gi,member.regDate)
	.replace(/%memberId%/gi,member.memberId);
	
	$('#my-member').append(renderedHtml);
}

