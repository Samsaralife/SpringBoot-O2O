$(function(){
	//从uRL里获取productId参数的值
	var productId = getQueryString("productId");
	//通过productId获取商品的URL
	var infoURL = "/shopadmin/getproductbyid?productId="+productId;
	//获取当前店铺设定的商品类别列表的URL
	var categoryURL = "/shopadmin/getproductgorylist";
	//更新商品信息的URL
	var productPostURL = "/shopadmin/modifyproduct";
	//由于商品添加和编辑使用的是同一个页面
	//该标识符用来表明本次是添加还是编辑操作
	var isEdit = false;
	if(productId){
		//若有productId则为编辑操作
		getInfo(productId);
		isEdit = true;
	}else{
		getCategory();
		productPostURL = "/shopadmin/addproduct";
	}
	//获取需要编辑的商品的商品信息，并赋值为表单
	function getInfo(id){
		$.getJSON(infoURL,function(data){
			if(data.success){
				//从返回的JSON当中获取product对象的信息，并赋值给表单
				var product = data.product;
				$("#product-name").val(product.productName);
				$("#product-desc").val(product.productDesc);
				$("priority").val(product.priority);
				$("#normal-price").val(product.normalPrice);
				$("#prototion-price").val(product.promotionPrice);
				//获取原本的商品类别以及该店铺的所有商品类别列表
				var optionHtml = "";
				var optionArr = data.productCategoryList;
				var optionSelected = product.productCategory.productCayegoryId;
				//生成的前端的html商品类别列表，并默认选择编辑钱的商品类别
				optionArr.map(function(item,index){
					var isSelect = optionSelected === item.productCategoryId ? 'selected':'';
					optionHtml += '<option data-value="' + item.productCategoryId + '"'
					+isSelect +'>'
					+item.productCategoryName
					+ '</option>';
			}
				$("#category").html(optionHtml);
		});
	}
		
		}
	function getCategory()
	{
		$.getJSON(categoryURL,function(data){
			if(data.success){
				var productCategory  = data.data;
				var optionHtml  = '';
				productCategoryList.map(function(item,index){
					optionHtml = '<option data-value="'
						+ item.productCategoryId +'"'>
						+ item.productCategoryName + '</option>';
				});
				$("#category").html(optionHtml);
			}
			
			});
		}
	$("#submit").click(function(){
		//提交按钮的事件响应，分别对商品添加和编辑操作做不同的响应
		function(){
			//创建商品json对象，并从表单里面获取相对应的属性值
			var product = {};
			product.productName = $('#product-name').val();
			product.productDesc= $('#product-desc').val();
			product.priority = $('priority').val();
			product.normalPrice = $('#normal-price').val();
			product.promotionPrice = $('#promotion-price').val();
			//获取选定的商品类别值
			product.productCategory= {
					productCategoryId : $('#category').find('option').not(
							function(){
								return !this.selected;
							}).data('value');
			}
			product.productId = productId;
		}
	})
});