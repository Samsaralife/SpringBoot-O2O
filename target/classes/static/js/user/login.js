$(document).ready(function(){
	var loginURL= "/shopadmin/login";
	var successURL="/shopadmin/shoplist";
	userlogin();
	$("#reset").click(function(){
		$("#username").html("");
		$("#password").html("");
	})
	function userlogin(){
		$("#submit").click(function(){
			var user ={};
			user.username = $("#username").val();
			user.password = $("#password").val();
			var formData =new FormData();
			formData.append("userStr",JSON.stringify(user));
			var verifyCodeActual = $("#j_captcha").val();
			if(!verifyCodeActual)
			{
					$.toast("请输入验证码");
					return ;
			}
			formData.append("verifyCodeActual",JSON.stringify(verifyCodeActual));
			$.ajax({
				url:loginURL,
				type:"POST",
				data:formData,
				contentType:false,
				processData:false,
				success:function(data){
					if(data.success){
						$.toast("登录成功");
						window.location.href=successURL;
					}else
						{
							$.toast("登录失败"+data.errMsg);
						}
				}
			});
		});
	}
});