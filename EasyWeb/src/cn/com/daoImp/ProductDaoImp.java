package cn.com.daoImp;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import javax.servlet.jsp.jstl.sql.Result;

import cn.com.daoInf.ProductDaoInf;
import cn.com.db.PreSql;
import cn.com.db.SqlCommand;
import cn.com.entity.Product;

public class ProductDaoImp implements ProductDaoInf{

	@Override
	public List<Product> getAllProduct(Product product_, int pageSize,
			int pageNum) {
		List<Product> allProducts = new ArrayList<Product>();
		String sql = PreSql.SqlProduct.SELECTSQL;
		Object[] args=null;
		
		if(null!=product_){
			if(product_.getPcChildId()!=0){
				sql += " and epc_child_id=?";
				args = new Object[] { product_.getPcChildId() };
			}
		}
		
		int passRecord=(pageNum-1)*pageSize;
		int totalRecord=pageNum*pageSize;
		if(pageSize!=0){
			sql+=" and rownum<="+totalRecord;
			sql="select * from ("+sql+") where rn >"+passRecord;
		}
		// 本地的结果集
		Result rs;
		try {
			rs = new SqlCommand(sql, args).getResult(null);
			SortedMap<String, Object>[] rows = rs.getRows();
			// 扫描
			for (int i = 0; i < rows.length; i++) {
				SortedMap<String, Object> row = rows[i];
				Product product = new Product();
				warp(product,row);
				allProducts.add(product);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allProducts;
	}


	private void warp(Product product, SortedMap<String, Object> row) {
		// TODO Auto-generated method stub
		product.setPid(Integer.parseInt(String.valueOf(row.get("ep_id"))));
		product.setPname(String.valueOf(row.get("ep_name")));
		product.setPdescription(String.valueOf(row.get("ep_description")));
		product.setPprice(Integer.parseInt(String.valueOf(row.get("ep_price"))));
		product.setPstock(Integer.parseInt(String.valueOf(row.get("ep_stock"))));
		product.setPcId(Integer.parseInt(String.valueOf(row.get("epc_id"))));
//		product.setPcChildId(Integer.parseInt(String.valueOf(row.get("epc_child_id"))));
		product.setPfileName(String.valueOf(row.get("ep_file_name")));
	}

	@Override
	public Product getProductByInfo(Product p) {
		// TODO Auto-generated method stub
		Product product=null;
		String sql=PreSql.SqlProduct.SELECTSQL;
		Object[] args=null;
		if(p.getPid()!=0){
			sql+="and ep_id = ?";
			args=new Object[]{p.getPid()};
		}else if (p.getPname() != null) {
			sql += " and ep_name=?";
			args = new Object[] { p.getPname() };
		}
		SqlCommand command=new SqlCommand(sql, args);
		Result rs=command.getResult(null);
		int count=rs!=null?rs.getRowCount():0;
		if(count!=0){
			product=new Product();
			SortedMap<String, Object> row=rs.getRows()[0];
			this.warp(product, row);
		}
		return product;
	}


	@Override
	public int getCount(Product product) {
		// TODO Auto-generated method stub
		Result result=new SqlCommand(PreSql.SqlProduct.COUNTSQL, null).getResult(null);
		int count= Integer.parseInt(result.getRows()[0].get("count").toString()); 
		return count;
	}
}
