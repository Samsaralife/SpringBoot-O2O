$(document).ready(function(){
	var shopId=getQueryString("shopId");
	var shopInfoURL = '/shopadmin/shopoperation?shopId=' + shopId;
	toOperation();
	function toOperation(){
	$("#shopinfo").click(function(){
		window.location.href= shopInfoURL;
		});
	}
	/*
	$.getJSON(shopInfoURL,function(data){
		if(data.redirect){
			window.location.href=data.url;
		}else{
			if(data.shopId !=undefined && data.shopId !=null){
				shopId = data.shopId;
			}
			$("#shopInfo").attr('href','/shopadmin/shopoperation?shopId='+shopId);
		}
	});*/
});