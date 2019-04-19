$(function(){
	 var registerURL = "/shopadmin/registeruser";
	 var loginURL="/shopadmin/userlogin"
	 $("#reset").click(function(){
		 $("#username").html("");
		 $("#password").html("");
	 })
	 $("#submit").click(function(){
		 var user ={};
		 user.username = $("#username").val();
		 user.password = $("#password").val();
		 $.ajax({
		 url:registerURL,
		 type:'POST',
		 data:JSON.stringify(user),
		 contentType:'application/json;charset=utf-8',
		 processData:false,
		 success:function(data){
			 if(data.success){
				 $.toast("注册成功");
				 window.location.href=loginURL;
			 }else{
				 $.toast("注册失败"+data.errMsg);
			 }
		 }
		 
	 });
	 })
});