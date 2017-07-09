package cn.com.daoInf;

import java.util.List;

import cn.com.entity.Product;


public interface ProductDaoInf {
	public abstract List<Product> getAllProduct(Product product,int pageSize,int pageNum);
	public Product getProductByInfo(Product p);
	public int getCount(Product product);
}
