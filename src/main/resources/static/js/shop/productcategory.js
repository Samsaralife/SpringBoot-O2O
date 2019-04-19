$(function(){
	var shopId = getQueryString("shopId");    //获取店铺id
	var listURL="/shopadmin/getproductcategorylist?shopId="+shopId;  //productcategory全部列表
	var addURL="/shopadmin/addproductcategory";	  // productcategory添加url
	var deleteURL = "/shopadmin/removeproductcategory";  //删除productcategory url
	getList();
	function getList(){
		$.getJSON(listURL,function(data){
			if(data.success){
				var dataList  = data.productCategoryList;
				var tempHtml = "";
				dataList.map(function(item,index){
					tempHtml += '<div class="row row-product-category now ">'
							 + '<div class="col-33 product-category-name">'
							 + item.productCategoryName
							 +'</div>'
							 + '<div class="col-33">'
							 + item.priority
							 + '</div>'
							 + '<div class="col-33"> <a href="#" class="button delete" data-id=" '
							 +item.productCategoryId +'">删除</a></div>'
							 +'</div>';
				});
				$('.category-wrap').append(tempHtml);
			}
		});
	}
	$('#new').click(function(){
		var tempHtml = '<div class= "row row-product-category temp">'
			+ '<div class="col-33"><input class="category-input category shop-category" type="text" placeholder="店铺类别"></div>'
			+ '<div class="col-33"><input class="category-input priority shop-priority" type="number" placeholder="优先级" ></div>'
			+ '<div class="col-33"><a href="#" class="button delete" id="delete" >删除</a></div>'
			+'</div>';
		$(".category-wrap").append(tempHtml);
	});
		var productCategoryList = [];
		$('#submit').click(function(){
			var tempArr = $(".temp");  //获取的新增的行
			$.toast(shopId);
			//遍历tempArr
			tempArr.map(function(item,index){
				var tempObj = {};
				tempObj.productCategoryName = $(index).find('.shop-category').val();
				tempObj.priority=$(index).find('.shop-priority').val();
				if(tempObj.productCategoryName && tempObj.priority){
					productCategoryList.push(tempObj);
				}
			});
			$.ajax({
				url: addURL,
				type: "POST",
				data: JSON.stringify(productCategoryList),
				contentType:'application/json',
				success:function(data){
					if(data.success){
						$.toast("提交成功");
						window.location.href="/shopadmin/shopmanagent?shopId="+shopId;
					}else{
						$.toast("提交失败");
					}
				}
				
			});
			//删除店铺
				$(".temp").on("click",function(){
					console.log($(this).parent().parent());
					$(this).parent().parent().remove();
				});
			
			$(".category-wrap").on('click','.row-product-category.now.delete',function(e){
				var target = e.currentTarget;
				$.confirm('确定吗？',function(){
					$.ajax({
						url:deleteURL,
						type:'POST',
						date: {
							productCategoryId:target.dataset.id
						},
						dataType:'json',
						success:function(data){
							if(data.success){
								$.toast("删除成功");
								getList();
							}else{
								$.toast("删除失败");
							}
						}
					});
				});
			});
		});
});