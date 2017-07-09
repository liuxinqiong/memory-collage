package cn.com.db;

public final class PreSql {
	public static class SqlUser {
		public static final String SQLQUERY = "select eu_user_id, eu_user_name, eu_password from easybuy_user where eu_user_name=?";
		public static final String INSERTSQL = "insert into EASYBUY_USER (EU_USER_ID,EU_USER_NAME,EU_PASSWORD,EU_STATUS) values (SEQ_EASYBUY_USER_EU_USER_ID.NEXTVAL,?,?,1)";
		public static final String UPDATESQL = "update easybuy_user set eu_user_name=?, eu_password=?,eu_status=1  where eu_user_id=?";
	}

	public static class SqlProduct {
		public static final String SELECTSQL = "select rownum rn,ep_id, ep_name, ep_description, ep_price, ep_stock, epc_id, epc_child_id, ep_file_name from easybuy_product where 1=1";
		public static final String COUNTSQL = "select count(*) as count from easybuy_product";
	}

	public static class SqlProductCategory {
		public static final String SELECTSQL = "select epc_id, epc_name, epc_parent_id from easybuy_product_category where 1=1";
	}

	public static class SqlOrder {
		public static final String INSERT_SQL = "insert into easybuy_order  (eo_id, eo_user_id, eo_user_name, eo_user_address,eo_cost) values  (SEQ_easybuy_order_eo_id.nextval,?,?,?,?)";
		public static final String MAX_SQL="select max(eo_id) as max from easybuy_order";
	}

}
