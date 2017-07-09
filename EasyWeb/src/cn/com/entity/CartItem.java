package cn.com.entity;

import java.io.Serializable;

public class CartItem implements Serializable{
	private Product product;
	private int number;
	
	public CartItem(Product product, int number) {
		this.product=product;
		this.number=number;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
}
