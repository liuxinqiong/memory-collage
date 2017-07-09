package cn.com.daoInf;

import java.util.Map;

import cn.com.entity.CartItem;
import cn.com.entity.Product;
import cn.com.entity.User;

public interface OrderDaoInf {
	public int saveOrder(Map<Integer,CartItem> cart,User user);
	public int getOrderId();
}
