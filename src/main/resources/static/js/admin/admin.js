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

	// 신고내역 보기
	sendGet('/api/report', {}, function(report){
		for(var i = 0; i<report['reviewReports'].length; i++){
			appendReviewReport(report['reviewReports'][i],i);
		}
		for(var i = 0; i<report['pageReports'].length; i++){
			appendPageReport(report['pageReports'][i],i);
		}
		for(var i = 0; i<report['reviewCommentReports'].length; i++){
			appendCommentReport(report['reviewCommentReports'][i],i);
		}
		for(var i = 0; i<report['pageCommentReports'].length; i++){
			appendCommentReport(report['pageCommentReports'][i],i);
		}
	})
	
	// 페이지 인증 요청 보기
	sendGet('/api/page/request-page',{},function(request){
		for(var i = 0; i< request.length; i++){
			
			appendRequest(request[i],i);
		}
	})
	
	
	
	
	memberFormat = $('#member-format').html();
	
	// Admin 검색
	$('#searchNicknameBtn').on('click', function(){
		var nickname = $('#searchNickname').val().trim();
		$.ajax({
			url : '/admin/search',
			contentType : 'application/json',
			method : 'POST',
			dataType : 'json',
			data : JSON.stringify({nickname : nickname}),
			success : function(success){
				var renderedHtml = memberFormat.replace(/%member-id%/gi, success['member-id'])
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

		var memberId = {
				'member-id' : $(this).attr('id')
		}
		
		$.ajax({
			type:'POST',
			contentType : 'application/json',
			url:'/admin/adminCheck',
			data:JSON.stringify(memberId),
			success:function(data){
				if(data){
					$('#admincheck').html('<b style="font-size:13px;color:green">일반유저</b>');
					addAdmin(true);
				}else{
					$('#admincheck').html('<b style="font-size:13px;color:red">이미 Admin입니다</b>');
				}
			}
		})
	});
	 

	 // 회원 강퇴 버튼
	 $(".fire-btn").on("click",function(){
		 console.log($(this).attr('id'));
		var r = confirm('정말 회원을 강퇴시키겠습니까?');
		
		if(r){
			 var memberId = {
					 'member-id' : $(this).attr('id')
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
	 
	 // 신고 요청 삭제
	 $('#reviewDeleteBtn').on("click", function(event){
		 deleteRequest('deleteReview','/review/delete')
	 });
	 
	 $('#pageDeleteBtn').on("click", function(event){
		 deleteRequest('deletePage','/page/delete')
	 });
	 
	 $('#commentDeleteBtn').on("click", function(event){
		 deleteRequest('deleteComment','/comment/delete')
	 });
	 
	 // 페이지 승인 
	 $("input[name = 'AuthorBtn']").on("click",function(){
		var r = confirm('인증 페이지로 승인하시겠습니까?');
			
		if(r){
			var pageId = {
					'page-id' : $(this).attr('id')
			}
			 $.ajax({
				 url : '/admin/author'
				 ,contentType : 'application/json'
				 ,method : 'POST'
				 ,data : JSON.stringify(pageId)
				 ,success : function(success){
					alert('승인 성공');
					location.reload();
				}
				,error : function(error){
					alert('승인 실패');
				}
			 });
		}
	 });
	 
	 

	 
});

// 신고된 내용을 삭제하는 함수
function deleteRequest(reqKind,reqUrl){
	var r = confirm('신고된 요청을 삭제하시겠습니까? \n복구 불가능합니다.');
	
	if(r){
		 var checkArr = [];
		 $("input[name="+reqKind+"]:checked").each(function(){
			 checkArr.push($(this).attr('id'));
		 })
		 $.ajax({
			 url:reqUrl
			,type : 'POST'
			,dataType : 'text'
			,data :{
				reviewArr :checkArr
			}
		 	,success : function(success){
		 		alert('삭제 성공');
		 		location.reload();
		 	}
		 })
	}
}


// admin으로 추가하는 메소드
function addAdmin(checkAdmin){
	if(checkAdmin){
		
		var r = confirm('Admin으로 추가하시겠습니까?');
	
		if(r){
			 $.ajax({
				 url : '/admin/addAdmin'
				 ,contentType : 'application/json'
				 ,method : 'POST'
				 ,data : JSON.stringify({
						'member-id' : $("input[name = 'addAdminBtn']").attr('id')
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


// 테이블에 추가하는 메소드
function appendMember(member,index){
	var myMemberFormat = $('#my-member-format').html();
	
	var renderedHtml = myMemberFormat.replace(/%index%/,index)
	.replace(/%nickname%/,member.nickname)
	.replace(/%section%/,member.sectionName)
	.replace(/%email%/,member.email)
	.replace(/%reg_date%/,member.regDate)
	.replace(/%memberId%/,member.memberId);
	
	$('#my-member').append(renderedHtml);
}

// report 를 추가하는 메소드
function appendReviewReport(report,index){
	var myReviewReportFormat = $('#review-report-format').html();
	var renderedHtml = myReviewReportFormat.replace(/%index%/,index+1)
	.replace(/%reviewId%/,report.reviewId)
	.replace(/%reviewTitle%/,report.reviewTitle)
	.replace(/%writer%/,report.writer)
	.replace(/%reporter%/,report.reporter)
	.replace(/%regDate%/,report.regDate);
	
	$('#review-report').append(renderedHtml);
}

function appendPageReport(report,index){
	var myPageReportFormat = $('#page-report-format').html();
	var renderedHtml = myPageReportFormat.replace(/%index%/,index+1)
	.replace(/%pageId%/,report.pageId)
	.replace(/%pageTitle%/,report.pageTitle)
	.replace(/%writer%/,report.writer)
	.replace(/%reporter%/,report.reporter)
	.replace(/%regDate%/,report.regDate);
	
	$('#page-report').append(renderedHtml);
}

function appendCommentReport(report,index){
	var myCommentReportFormat = $('#comment-report-format').html();
	var renderedHtml = myCommentReportFormat.replace(/%index%/,index+1)
	.replace(/%commentId%/,report.commentId)
	.replace(/%content%/,report.content)
	.replace(/%writer%/,report.writer)
	.replace(/%reporter%/,report.reporter)
	.replace(/%regDate%/,report.regDate);
	
	$('#comment-report').append(renderedHtml);
}

function appendRequest(request,index){
	var requestPageFormat = $('#author-request-format').html();
	var renderedHtml = requestPageFormat.replace(/%index%/,index+1)
	.replace(/%pageTitle%/,request.pageTitle)
	.replace(/%requester%/,request.requester)
	.replace(/%pageId%/,request.pageId)
	
	$('#author-request').append(renderedHtml);
}