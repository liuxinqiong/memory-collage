package cn.com.daoImp;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import javax.servlet.jsp.jstl.sql.Result;

import cn.com.daoInf.ProductCategoryDaoInf;
import cn.com.db.PreSql;
import cn.com.db.SqlCommand;
import cn.com.entity.ProductCategory;

public class ProductCategoryDaoImp implements ProductCategoryDaoInf{

	@Override
	public List<ProductCategory> getAllProductCategory() {
		// TODO Auto-generated method stub
		List<ProductCategory> allProductCategorys = new ArrayList<ProductCategory>();
		SqlCommand command=new SqlCommand(PreSql.SqlProductCategory.SELECTSQL, null);
		Result rs;
		try {
			rs = command.getResult(null);
			SortedMap<String, Object>[] rows = rs.getRows();
			for (int i = 0; i < rows.length; i++) {
				SortedMap<String, Object> row = rows[i];
				ProductCategory productCategory = new ProductCategory();
				wrap(row, productCategory);
				allProductCategorys.add(productCategory);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return allProductCategorys;
	}

	private void wrap(SortedMap<String, Object> row,
			ProductCategory productCategory) {
		productCategory.setEpcId(Integer.parseInt(String.valueOf(row
				.get("epc_id"))));
		productCategory.setEpcName(row.get("epc_name").toString());
		productCategory.setEpcParentId(Integer.parseInt(String.valueOf(row
				.get("epc_parent_id"))));

	}
	
}
