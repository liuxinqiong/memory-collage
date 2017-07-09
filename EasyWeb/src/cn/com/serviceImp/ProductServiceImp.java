package cn.com.serviceImp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import cn.com.daoImp.OrderDaoImp;
import cn.com.daoImp.ProductCategoryDaoImp;
import cn.com.daoImp.ProductDaoImp;
import cn.com.daoInf.OrderDaoInf;
import cn.com.daoInf.ProductCategoryDaoInf;
import cn.com.daoInf.ProductDaoInf;
import cn.com.entity.CartItem;
import cn.com.entity.Product;
import cn.com.entity.ProductCategory;
import cn.com.entity.User;
import cn.com.serviceInf.ProductServiceInf;
import cn.com.util.PageUtil;
import cn.com.util.Util;

public class ProductServiceImp implements ProductServiceInf {
	private ProductDaoInf productDao;
	private ProductCategoryDaoInf productCategoryDaoInf;
	private OrderDaoInf orderDao;

	public ProductServiceImp() {
		productDao = new ProductDaoImp();
		productCategoryDaoInf = new ProductCategoryDaoImp();
		orderDao = new OrderDaoImp();
	}

	@Override
	public Product getProductByInfo(Product p) {
		// TODO Auto-generated method stub
		return productDao.getProductByInfo(p);
	}

	@Override
	public List<Product> getAllProducts(Product product, int pageSize,
			int pageNum) {
		return this.productDao.getAllProduct(product, pageSize, pageNum);
	}

	@Override
	public boolean addCart(Map<Integer, CartItem> cart, Product product, int num) {
		// TODO Auto-generated method stub
		// map按键取值
		CartItem ci = cart.get(product.getPid());
		if (null == ci) {
			cart.put(product.getPid(), new CartItem(product, 1));
		} else {
			if (num == 0) {
				ci.setNumber(ci.getNumber() + 1);
			} else {
				ci.setNumber(num);
			}

		}
		return true;
	}

	@Override
	public PageUtil<Product> getPageUtil(Product product, int pageSize,
			int pageNum, String url) {
		List<Product> products = this
				.getAllProducts(product, pageSize, pageNum);
		int totalRecords = this.productDao.getCount(product);
		PageUtil<Product> pageUtil = new PageUtil<Product>(products, pageSize,
				pageNum, totalRecords, url);
		return pageUtil;
	}

	@Override
	public List<ProductCategory> getAllProductCategory() {
		// TODO Auto-generated method stub
		return productCategoryDaoInf.getAllProductCategory();
	}

	@Override
	public boolean order(Map<Integer, CartItem> cart, User user) {
		// TODO Auto-generated method stub
		return this.orderDao.saveOrder(cart, user) > 0;
	}

	@Override
	public String historyProduct(HttpServletRequest req, Product p) {
		// TODO Auto-generated method stub
		int pid = p.getPid();
		String historyProduct = null;
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			// 得到存放历史的cookie
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("historyProduct")) {
					historyProduct = cookie.getValue();
					break;
				}
			}
		}

		LinkedList<String> list = null;
		if (historyProduct != null) {
			list = new LinkedList<String>(Arrays.asList(historyProduct
					.split("/")));
		} else {
			return historyProduct = new StringBuffer(pid + "/").toString();
		}
		if (list.contains(pid)) {
			list.remove(pid);
		} else {
			if (list.size() >5) {
				list.removeLast();
			}
		}
		list.addFirst(pid + "");
		StringBuffer sb = new StringBuffer();
		for (String s : list) {
			sb.append(s + "/");
		}
		return sb.deleteCharAt(sb.length() - 1).toString();
	}
}
