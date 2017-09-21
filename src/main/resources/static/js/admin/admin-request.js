/**
 * @author Naver 문정현
 */


$(document).ready(function(){
	
	// 페이지 인증 요청 보기
	sendGet('/api/page/request-page',{},function(request){
		for(var i = 0; i< request.length; i++){
			appendRequest(request[i],i);
		}
	})

	$("#pageRequestForm").on("click",".authorBtn",function(){
	 // 페이지 승인 
		var r = confirm('인증 페이지로 승인하시겠습니까?');
		if(r){
			 $.ajax({
				 url : '/api/page/request-page/authorize-page'
				 ,contentType : 'application/json'
				 ,method : 'PUT'
				 ,data : JSON.stringify({
						'pageId' : $(this).attr('page-id'),
						'requesterId' : $(this).attr('requester-id')
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
	 });

});

function appendRequest(request,index){
	var requestPageFormat = $('#author-request-format').html();
	var renderedHtml = requestPageFormat.replace(/%index%/,index+1)
	.replace(/%pageTitle%/gi,request.pageTitle)
	.replace(/%requester%/gi,request.requester)
	.replace(/%pageId%/gi,request.pageId)
	.replace(/%requesterId%/gi,request.requesterId);
	
	$('#author-request').append(renderedHtml);
}