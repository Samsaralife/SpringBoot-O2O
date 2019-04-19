$(function(){
	var shopId = getQueryString("shopId");
	$("#category-management").click(function(){
		window.location.href="/shopadmin/productcategorymanage?shopId="+shopId;
	})
	$("#add-product").click(function(){
		window.location.href = "/shopadmin/addproduct?shopId="+shopId;
	})
})