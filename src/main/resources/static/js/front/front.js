$(function(){
	var initURL = "/frontend/listmainpageinfo";
	$.getJSON(initURL,function(data){
		if(data.success){
			var htmlTemp = '';
			data.shopCategoryList.map(function(item,index){
				$.toast(item.shopCategoryName);
				htmlTemp += '<div class="col-50" id="shopCategoryName"><a href="#">' + item.shopCategoryName +'</a><div data-id="'+item.shopCategoryId+'"></div></div>';
			});
			$(".row").html(htmlTemp);
		}
	});
});