/*
* 店铺编辑javascript
*/
$(document).ready(function(){
	getlist();
	function getlist(){
		$.ajax({
			url:"/shopadmin/getshoplist",
			type:"get",
			dataType:"json",
			success:function(data){
				if(data.success){
					handleList(data.shopList);  //渲染店铺
					handleUser(data.personInfo);  //获取用户信息，然后展示出来
				}
			}
		});
	}
	function handleUser(data){
		$("#user-name").text(data.name);
	}
	function handleList(data){
		var Html = "";
		data.map(function(item,index){
			Html +='<div class="row row-shop"><div class="col-40">'
				+ item.shopName + '</div><div class="col-40">'
				+ shopStatus(item.enableStatus)
				+ '</div><div class="col-20">'
				+goShop(item.enableStatus,item.shopId) + '</div></div>';
		});
		$('.shop-wrap').html(Html);
		$(".row-shop:even").css("backgroun-color","#B2E0FF");
	}
	function shopStatus(status){  //显示店铺状态
		if(status == 0){
			return "审核中";
		}else if(status == -1){
			return "店铺非法";
		}else if(status == 1){
			return "审核通过";
		}
	}
	function goShop(status,id){  //根据id状态进入店铺管理
		if(status == 1){
			return '<a href="/shopadmin/shopmanagent?shopId=' + id + '">进入</a>';
		}else {
			return "";
		}
	}
});