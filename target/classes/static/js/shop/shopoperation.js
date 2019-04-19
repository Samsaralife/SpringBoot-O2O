/**
* 获取后台信息填充至前端页面
* 以ajax异步处理方式将表单信息提交到后台
*/

$(document).ready(function(){
	var shopId = getQueryString("shopId");    //获取店铺id
	var isEdit =shopId?true:false;    //店铺信息是否可编辑
	var initURL = '/shopadmin/getshopinitinfo';  //初始化加载页面url
	var registerShopURL = '/shopadmin/registershop';  //注册页面url
	var getshopURL="/shopadmin/getshopbyid?shopId=" +shopId;  // 根据id查找相应的商店
	var editShopURL = "/shopadmin/modifyshop?shopId="+shopId; //编辑店铺url
	if(!isEdit){
		getShopInitInfo();  //如果是不可编辑的话加载部分信息
	}else
		{
			getShopInfo();  //如果可编辑
			getShopInitInfo();
		}
	function getShopInfo(){   //根据ShopId查询商店
		$.getJSON(getshopURL,function(data){
			if(data.success){
			var shop  = data.shop;
			$("#shop-name").val(shop.shopName);
			$("#shop-addr").val(shop.shopAddr);
			$("#shop-phone").val(shop.phone);
			$("#shop-desc").val(shop.shopDesc);
			var shopCategory = '<option data-id="'+shop.shopCategory.shopCategoryId+'"selected>'
			+shop.shopCategory.shopCategoryName+ '</option>';
			var tempAreaHtml = "";
			data.areaList.map(function(item,index){
				tempAreaHtml +='<option data-id="'+item.areaId + '">'+item.areaName + '</option>';
			});
			$("#shop-category").html(shopCategory);
			$("#shop-category").attr('disabled','disabled');
			$("#area").html(tempAreaHtml);
			$("area option[data-id='"+shop.area.areaId+"']").attr("selected","selected");
		}
		});
	}
	$.getJSON(initURL,function(data){   //获取后台信息，对option标签进行拼接
		if(data.success){
			var tempHtml = "";
			var tempAreaHtml= "";
			data.shopCategoryList.map(function(item,index){
				tempHtml +='<option data-id="'+item.shopCategoryId+'">'+
				item.shopCategoryName + '</option>';
			});
			data.areaList.map(function(item,index){
				tempAreaHtml +='<option data-id="'+item.areaId +'">'+
				item.areaName + '</option>';
			});
			$("#shop-category").html(tempHtml);  //将拼接后的信息塞到html里
			$("#shop-area").html(tempAreaHtml);
		}
	});
	function getShopInitInfo(){
		$("#submit").click(function(){   //提交
			alert("hello");
			var shop = {};
			if(isEdit){
				shop.shopId = shopId;
			}
			shop.shopName = $("#shop-name").val();
			shop.shopAddr = $("#shop-addr").val();
			shop.phone = $("#shop-phone").val();
			shop.shopDesc = $("#shop-desc").val();
			shop.shopCategory =  {
					shopCategoryId:$("#shop-category").find("option").not(function(){
						return !this.selected;
					}).data("id")
			};
			shop.area = {
				areaId :$("#shop-area").find("option").not(function(){
					return !this.selected;
				}).data("id")
			};
			/**
			 * 上传文件流
			 */
		//	var shopImg = $("#shop-img")[0].files[0]; 
			var formData = new FormData(); //数据流，可以将对象添加进里面
		//	formData.append("shopImg",shopImg);
			formData.append("shopStr",JSON.stringify(shop));  //添加对象
			//点击的时候传入图片
			var verifyCodeActual = $("#j_captcha").val();
			if(!verifyCodeActual){
				$.toast("请输入验证码");
				return;
			}
			formData.append("verifyCodeActual",verifyCodeActual);
			$.ajax({
				url:(isEdit?editShopURL:registerShopURL),
				type:"POST",
				data:formData,
				contentType:false,
				processData:false,
				cache:false,
				success:function(data){
					if(data.success){
						$.toast("提交成功");
						window.location.href="/shopadmin/shoplist";
					}else{
						$.toast("提交失败"+data.errMsg);
					}
					$("#captcha_img").click();
				}
			});
		});
	}
});
