/**
 * 点击按钮生成随机数，更换验证码
 * @param img
 * @returns
 */
function changeVerifyCode(img){
	img.src="/shopadmin/defaultKaptcha?d=" + Math.floor(Math.random()*100);
}
//返回url
function getQueryString(name){
	var reg = new RegExp("(^|&)"+name+"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null){
		return decodeURIComponent(r[2]);
	}
	return '';
}