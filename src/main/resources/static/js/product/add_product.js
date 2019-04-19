$(function(){
	var addProductURL = "/shopadmin/addProduct";
	var categoryListURL = "/shopadmin/getproductcategorylist";
	var shopId = getQueryString("shopId");
	addProduct();
	$.getJSON(categoryListURL,function(data){
		if(data.success){
			var tempHtml = "";
			data.productCategoryList.map(function(item,index){
				tempHtml+='<option data-id="'+item.productCategoryId + '">' + item.productCategoryName
				+ '</option>'
			});
			$("#product-category").html(tempHtml);
		}
	});
	function addProduct(){
		$("#submit").click(function(){
			//定义商品对象
			var product = {};
			var shop = {};
			//添加商品对象属性
			product.productName = $("#product-name").val();
			product.priority = $("#product-priority").val();
			product.normalPrice = $("#normal-price").val();
			product.promotionPrice = $("#promotion-price").val();
			product.productDesc = $("#product-desc").val();
			shop.shopId = shopId;
			product.shop = shop;
			product.productCategory = {
				productCategoryId:$("#product-category").find("option").not(function(){
					return !this.selected;
				}).data("id")
			}
			var formData = new FormData();
			//将product对象添加到表单流
			formData.append("productStr",JSON.stringify(product));
			//点击的时候传入图片
			var verifyCodeActual = $("#j_captcha").val();
			if(!verifyCodeActual){
				$.toast("请输入验证码");
				return;
			}
			formData.append("verifyCodeActual",verifyCodeActual);
			$.ajax({
				url:addProductURL,
				type:'POST',
				data:formData,
				contentType:false,
				cache:false,
				processData:false,
				success:function(data){
					if(data.success){
						$.toast("添加商品成功!");
						$("#captcha_img").click();
					}else{
						$.toast(data.errMsg);
						$("#captcha_img").click();
					}
				}
			});
		});
	}
});