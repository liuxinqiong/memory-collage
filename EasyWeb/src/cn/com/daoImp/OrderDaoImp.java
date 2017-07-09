package cn.com.daoImp;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

import javax.servlet.jsp.jstl.sql.Result;

import cn.com.daoInf.OrderDaoInf;
import cn.com.db.DBUtil;
import cn.com.db.PreSql;
import cn.com.db.SqlCommand;
import cn.com.entity.CartItem;
import cn.com.entity.User;

public class OrderDaoImp implements OrderDaoInf {

	@Override
	public int saveOrder(Map<Integer, CartItem> cart, User user) {
		// TODO Auto-generated method stub
		// 表：订单（一个购物车对应一张订单（行）） 订单详细表
		double totalMoney = this.getTotalMoney(cart);
		// 手动事务：打存分层
		Connection conn = DBUtil.getConn();
		SqlCommand comm = null;
		int result=0;
		try {
			conn.setAutoCommit(false);
			// 插入订单表中
			Object[] args = new Object[] { user.getId(), user.getUname(),
					user.getUname(),totalMoney };
			comm = new SqlCommand(PreSql.SqlOrder.INSERT_SQL, args);
			result=comm.execute(conn);
			
			int orderId = this.getOrderId()+1;
			// 订单详细表,多条插入语句
			Collection<CartItem> list=cart.values();
			for (CartItem cartItem : list) {
				String sql = "insert into easybuy_order_detail (eod_id, eo_id, ep_id, eod_quantity, eod_cost) "
						+ "values  (SEQ_order_detail_eod_id.nextval," + orderId + ",?,?,?)";
				comm.setSql(sql);
				int pid=cartItem.getProduct().getPid();
				int number=cartItem.getNumber();
				Double cost=number*cartItem.getProduct().getPprice();
				args=new Object[]{pid,number,cost};
				comm.setArgs(args);
				comm.execute(conn);
			}
			conn.commit();		
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
		return result;
	}

	private double getTotalMoney(Map<Integer, CartItem> cart) {
		// TODO Auto-generated method stub
		double count = 0;
		Collection<CartItem> values = cart.values();
		for (CartItem item : values) {
			count += item.getProduct().getPprice() * item.getNumber();
		}
		return count;
	}

	@Override
	public int getOrderId() {
		// TODO Auto-generated method stub
		Result result=new SqlCommand(PreSql.SqlOrder.MAX_SQL, null).getResult(null);
		int max= Integer.parseInt(result.getRows()[0].get("MAX").toString()); 
		return max;
	}
}
