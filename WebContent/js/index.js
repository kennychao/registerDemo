var resourceUrl = 'http://localhost:8080/registerDemo/rest/api/user';
var userInfo = {};

$( document ).ready(function() {
	$("#userName").on("blur",function(event){
		localStorage.firstkeyname = $('#userName').val();
	});
});



//註冊
function signUp(){
	
	//檢核是否有輸入資料
	if(!$('#userName').val()){
		alert('請輸入使用者名稱');
		return;
	}else if(!$('#userPwd').val()){
		alert('請輸入使用者密碼');
		return;
	}
	
	//取值
	userInfo.username = $('#userName').val();
	userInfo.pwd = $('#userPwd').val();
	userInfo.firstkeyname = localStorage.firstkeyname;
	
	//成功時的callback
	var successFn = function(jsobObj) {
		bind(jsobObj);
    }
	
	getAjaxInfo(resourceUrl,userInfo,successFn);
}

// 這邊就印出回傳的JSON以供觀看
function bind(jsobObj){
	
	if(jsobObj.returnCode == '0000'){
		
	}else{
		$('#userName').val(jsobObj.suggestName);
		$('#userPwd').val('');
	}
	alert(JSON.stringify(jsobObj));
	
}

//發送ajax
function getAjaxInfo(resourceUrl, jsobObj, successFn, errorFn) {
	
	var resourceUrl = resourceUrl || "";
	var jsobObjStr = JSON.stringify(jsobObj) || "";
	var successFn = successFn || function(){};
	var errorFn = errorFn || function(){alert('fail')};
	var setTimeout = 180 * 1000;
	
	jQuery.support.cors = true;
	$.ajax({
		crossDomain : true,
		timeout : setTimeout,
		url: resourceUrl,
		headers: {
			'Content-Type' : 'application/json; charset=utf-8'
		},
		data: jsobObjStr,
		dataType: "json",
		type: "POST",
		success: successFn,
		error: errorFn
	});
}