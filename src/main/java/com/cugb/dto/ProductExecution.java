package com.cugb.dto;

import java.util.List;

import com.cugb.entity.Product;
import com.cugb.enumration.ProductEnum;

public class ProductExecution {

	//商品状态码
	private String state;
	//商品状态信息
	private String stateInfo;
	//操作的product(增删改商品的时候用)
	private Product product;
	//商品数量
	private int count;
	//获取的product列表（查询商品列表的时候用）
	private List<Product> products;
	
	public ProductExecution() {
		
	}
	//失败时的构造器
	public ProductExecution(ProductEnum productEnum) {
		this.state = productEnum.getState();
		this.stateInfo =productEnum.getStateInfo();
	}
	
	//单个商品操作成功的构造器
	public ProductExecution(ProductEnum productEnum,Product product) {
		this.state = productEnum.getState();
		this.stateInfo = productEnum.getStateInfo();
		this.product = product;
	}
	//多个商品操作成功的构造器
	public ProductExecution(ProductEnum productEnum,List<Product> products) {
		this.state = productEnum.getState();
		this.stateInfo = productEnum.getStateInfo();
		this.products = products;
	}
	public Product getProduct() {
		return product;
	}
	public List<Product> getProducts() {
		return products;
	}
	public String getState() {
		return state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	
}
