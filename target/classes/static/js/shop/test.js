$(document).ready(function(){
	var initURL='/shopadmin/getshopinitinfo';
	var registerShopURL='/shopadmin/registershop';
	//页面加载的时候执行函数
	getShopInfo（);
	$.getJSON(initURL,function(data){
		if(data.success){
			var tempHtml = '';
			var tempAreaHtml='';
			//html元素拼接
			data.shopCategoryList.map(function(item,index){  
				tempHtml +='<option data-id="' + item.shopCategoryId +'">'
				+ item.shopCategoryName +'</option>';
			});
			data.areaList.map(function(item,index){
				registerShopURL +='<option data-id="' + item.areaId + '">'
				+ item.areaName + '</option>';
			});
			//将拼接好的html元素塞入html中
			$("#shop-category").html(tempHtml);
			$("#shop-area").html(registerShopURL); 
		}
	});
	function getShopInfo(){
		$("submit").click(function(){
			var shop ={};
			shop.shopName = $("#shop-name").val();
			shop.shopAddr = $("#shop-addr").val();
			shop.phone = $("#shop-phone").val();
			shop.desc = $("#shop-desc").val();
			shop.shopCategory = {
					shopCategoryId:$("#shop-category").find("option").not(function(){
						return !this.selected;
					}).data("id")
			};
			shop.area ={
					areaId:$("#shop-area").find("option").not(function(){
						return !this.selected;
					}).data("id")
			};
		});
		 //上传文件
		var shopImg = $("shop-img").[0].files[0];
		var formData = new formData();
		//将对象转换为json对象
		formData.append("shopStr",shop);
		$.ajax(function(data){
			url:registerShopURL,
			type:'POST',
			data:formData,
			cache:false,
			Content-Type:'application/json',
			success:function(data){
				if(data.success){
					$.toast("提交成功");
				}else{
					$.toast("提交失败"+data.errMsg);
				}
			}
		});
	}
		
});