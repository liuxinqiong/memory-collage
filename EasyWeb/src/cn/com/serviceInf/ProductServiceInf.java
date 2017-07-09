package cn.com.serviceInf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.com.entity.CartItem;
import cn.com.entity.Product;
import cn.com.entity.ProductCategory;
import cn.com.entity.User;
import cn.com.util.PageUtil;

public interface ProductServiceInf {
	public List<Product> getAllProducts(Product product,int pageSize,int pageNum);
	public Product getProductByInfo(Product p);
	public boolean addCart(Map<Integer, CartItem> cart, Product product,int num);
	public PageUtil<Product> getPageUtil(Product product,int pageSize,int pageNum,String url);
	public List<ProductCategory> getAllProductCategory();
	public boolean order(Map<Integer, CartItem> cart, User user);
	public String historyProduct(HttpServletRequest request,Product p);
}
