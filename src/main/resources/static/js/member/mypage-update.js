/**
 * Naver 문정현
 */
//var myInfoView;
var myInfoFormat;
var sectionId;
var memberId;
$(document).ready(function(){

	myInfoFormat = $('#my-info-format').html();
	// 내 정보 보기
	sendGet('/api/member/my-info', {}, function(member){
		var renderedHtml = myInfoFormat.replace(/%member_id%/gi, member.memberId)
		.replace(/%member_nickname%/gi, member.nickname)
		.replace(/%member_email%/gi, member.email)
		.replace(/%member_regdate%/gi, member.regDate);
		
		 $('#my-info').append(renderedHtml);
		 sectionId = member.sectionId;
		 memberId = member.memberId;
		 
		 sendGet('/api/section',{},function(section){
				var sectionStr = "";
				
				for (var i = 0; i < section.length; i++) {
					sectionStr += "<option value=" + section[i].sectionId + ">";
					sectionStr += section[i].sectionName + "</option>";
				}
				 $("#"+memberId+"-section").append(sectionStr);
				 $("#"+memberId+"-section").val(sectionId).attr("selected", "selected");
			});
	});

	
	// 정보 수정 버튼
	$("#modify-btn").on("click",function(){
		
		sendPut('/api/member',
		{
			"email": $("#member_email").val(),
			"sectionId" : $("#section").val(),
			"memberId" : memberId
		},
		function(){
			location.reload();
		})
	})
	
	// 회원 탈퇴
	$("#remove-btn").on("click",function(){
		var r = confirm('정말 회원을 탈퇴하시겠습니까?');
		if(r){
			sendDelete('/api/member',
				{
					"memberId" : memberId
				},
				function(){
					alert("회원 탈퇴되었습니다.");
					location.reload();
				})
		}
	})
	
	// 비밀번호 수정
	$("#pw-modify-btn").on("click",function(){
		sendPut('/api/member/password',
			{
				"memberId" : memberId,
				"password" : $("#pw-modify-btn").val()
			},
			function(){
				location.reload();
			})
	})
	
	// bookmark 리스트 보이기
	itemForm = $('#page-data-format').html();
	listBody = $('#review-data-list');
});

