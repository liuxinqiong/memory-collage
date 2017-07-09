package cn.com.daos.readerInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.readerInfo.ReaderInfoBean;
import cn.com.beans.readerInfo.ReaderInfoViewBean;
import cn.com.beans.readerInfo.ReaderTypeInfoBean;
import cn.com.db.DBUtil;

public class ReaderInfoDAOImp implements ReaderInfoDAOInf{

	/*public boolean addReader(ReaderInfoBean reader)
     * 参数：传入一个读者信息实体
     * 返回值：boolean
     * 修饰符：public
     * 功能：新增读者信息
     * */
	@Override
	public boolean addReader(ReaderInfoBean reader) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		String sql = "insert into readerInfo values(seq_readerInfo_readerId.nextval,?,?,?,?,?,?,sysdate,?,?,?,null,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, reader.getReaderName());
			pstm.setInt(2, reader.getReaderTypeId());
			pstm.setString(3, reader.getReaderSex());
			pstm.setString(4,reader.getReaderCardType());
			pstm.setString(5,reader.getReaderIdentifyCard());
			pstm.setString(6,reader.getReaderTel());
			pstm.setInt(7,reader.getReaderState());
			pstm.setString(8,reader.getReaderPwd());
			pstm.setInt(9,1);
			pstm.setInt(10,0);
			int len = pstm.executeUpdate();
			if(len != 0) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.free(pstm, conn);
		}
		return bool;
	}

	/*public boolean updateReader(ReaderInfoBean reader)
     * 参数：传入一个读者信息实体
     * 返回值：boolean
     * 修饰符：public
     * 功能：修改读者信息
     * */
	@Override
	public boolean updateReader(ReaderInfoBean reader) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		String sql = "update readerInfo set readerName=?,readerSex=?,readerCardType=?,readerIdentifyCard=?,readerTel=?,readerRegistDate=to_date(?,'yyyy-mm-dd HH24:mi:ss'),readerIsDel=?,readerState=?,readerPwd=?,readerVcState=?,readerVcBalance=?,readerTypeId=? where readerId=?";
		try {
			pstm = conn.prepareStatement(sql);					
			pstm.setString(1, reader.getReaderName());		
			pstm.setString(2, reader.getReaderSex());
			pstm.setString(3,reader.getReaderCardType());
			pstm.setString(4,reader.getReaderIdentifyCard());
			pstm.setString(5,reader.getReaderTel());
			pstm.setString(6,reader.getReaderRegistDate());
			pstm.setInt(7,reader.getReaderIsDel());
			pstm.setInt(8,reader.getReaderState());
			pstm.setString(9,reader.getReaderPwd());
			pstm.setInt(10,reader.getReaderVcState());
			pstm.setDouble(11,reader.getReaderVcBalance());
			pstm.setInt(12, reader.getReaderTypeId());
			pstm.setInt(13, reader.getReaderId());		
			int len = pstm.executeUpdate();
			if(len != 0) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	/*public boolean delReader(ReaderInfoBean reader)
     * 参数：传入一个读者信息实体
     * 返回值：boolean
     * 修饰符：public
     * 功能：删除读者信息
     * */
	@Override
	public boolean delReader(ReaderInfoBean reader) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		String sql = "update readerInfo set readerIsDel=1 where readerId= ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, reader.getReaderId());
			int len = pstm.executeUpdate();
			if(len != 0) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}
	
	/*public List<ReaderInfoBean> getAllReaderInfo() 
     * 参数：无
     * 返回值：list数组
     * 修饰符：public
     * 功能：获取所有的读者信息
     * */
	@Override
	public List<ReaderInfoBean> getAllReaderInfo() {
		// TODO Auto-generated method stub
		List<ReaderInfoBean> list = new ArrayList<ReaderInfoBean>();
		ReaderInfoBean r = null;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from ReaderInfo where readerIsDel=0";

		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				r = new ReaderInfoBean();
				r.setReaderId(rs.getInt("readerId"));
				r.setReaderName(rs.getString("readerName"));
				r.setReaderSex(rs.getString("readerSex"));
				r.setReaderCardType(rs.getString("readerCardType"));
				r.setReaderIdentifyCard(rs.getString("readerIdentifyCard"));
				r.setReaderTel(rs.getString("readerTel"));
				r.setReaderRegistDate(rs.getString("readerRegistDate"));
				r.setReaderIsDel(rs.getInt("readerIsDel"));
				r.setReaderState(rs.getInt("readerState"));
				r.setReaderPwd(rs.getString("readerPwd"));
				r.setReaderVcState(rs.getInt("readerVcState"));
				r.setReaderVcBalance(rs.getDouble("readerVcBalance"));
				r.setReaderTypeId(rs.getInt("readerTypeId"));
				list.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free( pstm, conn);
		}
		return list;
	}

	/*public boolean addReaderType(ReaderTypeInfoBean readerType) 
     * 参数：传入一个读者类型信息实体
     * 返回值：boolean
     * 修饰符：public
     * 功能：新增读者类型信息
     * */
	@Override
	public boolean addReaderType(ReaderTypeInfoBean readerType) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		String sql = "insert into readerTypeInfo values(seq_bookTypeInfo_bookTypeId.nextval,?,?,?,?,?,?,?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, readerType.getReaderTypeName());
			pstm.setInt(2, readerType.getMaxNum());
			pstm.setInt(3, readerType.getDays());
			pstm.setDouble(4, readerType.getKeepMoney());
			pstm.setDouble(5, readerType.getNormalRent());
			pstm.setDouble(6, readerType.getExtendedRent());
			pstm.setInt(7, readerType.getLossTimes());
			pstm.setInt(8, readerType.getIsDel());
			int len = pstm.executeUpdate();
			if(len != 0) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.free(pstm, conn);
		}
		return bool;
	}

	/*public boolean updateReaderType(ReaderTypeInfoBean readerType)
     * 参数：传入一个读者类型信息实体
     * 返回值：boolean
     * 修饰符：public
     * 功能：修改读者类型信息
     * */
	@Override
	public boolean updateReaderType(ReaderTypeInfoBean readerType) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		String sql = "update readerTypeInfo set readerTypeName=?,maxNum=?,days=?,keepMoney=?,normalRent=?,extendedRent=?,lossTimes=? where readerTypeId=?";
		try {
			pstm = conn.prepareStatement(sql);					
			pstm.setString(1, readerType.getReaderTypeName());		
			pstm.setInt(2, readerType.getMaxNum());
			pstm.setInt(3,readerType.getDays());
			pstm.setDouble(4,readerType.getKeepMoney());
			pstm.setDouble(5,readerType.getNormalRent());
			pstm.setDouble(6,readerType.getExtendedRent());
			pstm.setInt(7,readerType.getLossTimes());
			pstm.setInt(8, readerType.getReaderTypeId());	
			int len = pstm.executeUpdate();
			if(len != 0) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public boolean delReaderType(ReaderTypeInfoBean readerType) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		String sql = "update readerTypeInfo set readerTypeIsDel=1 where readerTypeId = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, readerType.getReaderTypeId());
			int len = pstm.executeUpdate();
			if(len != 0) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	/*public List<ReaderTypeInfoBean> getAllReaderTypeInfo() 
     * 参数：无
     * 返回值：list集合
     * 修饰符：public
     * 功能：获取所有的读者类型信息
     * */
	@Override
	public List<ReaderTypeInfoBean> getAllReaderTypeInfo() {
		// TODO Auto-generated method stub
		List<ReaderTypeInfoBean> list = new ArrayList<ReaderTypeInfoBean>();
		ReaderTypeInfoBean r = null;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from readerTypeInfo where readerTypeIsDel=0";

		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				r = new ReaderTypeInfoBean();				
				r.setReaderTypeId(rs.getInt("readerTypeId"));
				r.setReaderTypeName(rs.getString("readerTypeName"));
				r.setMaxNum(rs.getInt("maxNum"));
				r.setDays(rs.getInt("days"));
				r.setKeepMoney(rs.getDouble("keepMoney"));
				r.setNormalRent(rs.getDouble("normalRent"));
				r.setExtendedRent(rs.getDouble("extendedRent"));
				r.setLossTimes(rs.getInt("lossTimes"));
				list.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free( pstm, conn);
		}
		return list;
	}

	/*public List<ReaderInfoViewBean> getAllReaderViewInfo()
     * 参数：无
     * 返回值：list集合
     * 修饰符：public
     * 功能：获取所有的读者相关信息
     * */
	@Override
	public List<ReaderInfoViewBean> getAllReaderViewInfo() {
		// TODO Auto-generated method stub
		List<ReaderInfoViewBean> list = new ArrayList<ReaderInfoViewBean>();
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ReaderInfoViewBean view = null;
		ReaderInfoBean reader=null;
		ReaderTypeInfoBean readerType=null;
		String sql = "select readerInfo.*,maxNum,days,keepMoney,normalRent,extendedRent,lossTimes,readerTypeName from readerInfo,readerTypeInfo where readerInfo.readerTypeId =readerTypeInfo.readerTypeId and readerIsDel=0";
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				view = new ReaderInfoViewBean();
				reader=new ReaderInfoBean();
				readerType=new ReaderTypeInfoBean();	
				reader.setReaderId(rs.getInt("readerId"));
				int readerTypeId=rs.getInt("readerTypeId");
				reader.setReaderTypeId(readerTypeId);
				readerType.setReaderTypeId(readerTypeId);
				reader.setReaderName(rs.getString("readerName"));			
				reader.setReaderSex(rs.getString("readerSex"));
				reader.setReaderCardType(rs.getString("readerCardType"));			
				reader.setReaderIdentifyCard(rs.getString("readerIdentifyCard"));
				reader.setReaderTel(rs.getString("readerTel"));
				reader.setReaderRegistDate(rs.getString("readerRegistDate").substring(0,10));
				
				reader.setReaderIsDel(rs.getInt("readerIsDel"));
				reader.setReaderState(rs.getInt("readerState"));
				reader.setReaderPwd(rs.getString("readerPwd"));
				reader.setReaderVcState(rs.getInt("readerVcState"));
				reader.setReaderVcBalance(rs.getDouble("readerVcBalance"));

				readerType.setMaxNum(rs.getInt("maxNum"));
				readerType.setDays(rs.getInt("days"));
				readerType.setKeepMoney(rs.getDouble("keepMoney"));
				readerType.setNormalRent(rs.getDouble("normalRent"));
				readerType.setExtendedRent(rs.getInt("extendedRent"));
				readerType.setLossTimes(rs.getInt("lossTimes"));
				readerType.setReaderTypeName(rs.getString("readerTypeName"));			
				view.setReader(reader);
				view.setReaderType(readerType);				
				list.add(view);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free(rs, pstm, conn);
		}
		return list;
	}

	/*public ReaderInfoViewBean getReaderViewInfoById(ReaderInfoBean re)
     * 参数：传入一个读者信息实体
     * 返回值：ReaderInfoViewBean
     * 修饰符：public
     * 功能：通过读者ID获取所有的读者相关信息
     * */
	@Override
	public ReaderInfoViewBean getReaderViewInfoById(ReaderInfoBean re) {
		// TODO Auto-generated method stub
		List<ReaderInfoViewBean> list =getAllReaderViewInfo();
		ReaderInfoViewBean rr=null;
		for(ReaderInfoViewBean r:list){
			if(r.getReader().getReaderId()==re.getReaderId()){
				rr=r;
				break;
			}
		}
		return rr;
	}
	
	
	
	public boolean checkReaderInfoByReaderId(ReaderInfoBean reader) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from readerInfo where readerId= ? and readerisdel =0";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, reader.getReaderId());
			rs = pstm.executeQuery();
			bool = rs.next();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}
	
	/*public ReaderInfoBean getReaderInfoByRId(ReaderInfoBean reader)
     * 参数：传入一个读者信息实体
     * 返回值：ReaderInfoBean
     * 修饰符：public
     * 功能：通过读者ID获取所有读者信息传入ReaderInfoBean中
     * */
	@Override
	public ReaderInfoBean getReaderInfoByRId(ReaderInfoBean reader) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConn();
		ReaderInfoBean  r = new ReaderInfoBean();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from readerInfo where readerId= ? and readerisdel =0";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, reader.getReaderId());
			rs = pstm.executeQuery();
			
			while(rs.next()){
				r.setReaderId(rs.getInt("readerId"));
				r.setReaderName(rs.getString("readerName"));
				r.setReaderSex(rs.getString("readerSex"));
				r.setReaderCardType(rs.getString("readerCardType"));
				r.setReaderIdentifyCard(rs.getString("readerIdentifyCard"));
				r.setReaderTel(rs.getString("readerTel"));
				r.setReaderRegistDate(rs.getString("readerRegistDate"));
				r.setReaderIsDel(rs.getInt("readerIsDel"));
				r.setReaderState(rs.getInt("readerState"));
				r.setReaderPwd(rs.getString("readerPwd"));
				r.setReaderVcState(rs.getInt("readerVcState"));
				r.setReaderVcBalance(rs.getDouble("readerVcBalance"));
				r.setReaderTypeId(rs.getInt("readerTypeId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r ;
	}
	
	public List<ReaderInfoBean> getCanUseReaderInfo() {
		// TODO Auto-generated method stub
		List<ReaderInfoBean> list = new ArrayList<ReaderInfoBean>();
		ReaderInfoBean r = null;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from ReaderInfo where readerIsDel=0 and readerVcState =0";

		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				r = new ReaderInfoBean();
				r.setReaderId(rs.getInt("readerId"));
				r.setReaderName(rs.getString("readerName"));
				r.setReaderSex(rs.getString("readerSex"));
				r.setReaderCardType(rs.getString("readerCardType"));
				r.setReaderIdentifyCard(rs.getString("readerIdentifyCard"));
				r.setReaderTel(rs.getString("readerTel"));
				r.setReaderRegistDate(rs.getString("readerRegistDate"));
				r.setReaderIsDel(rs.getInt("readerIsDel"));
				r.setReaderState(rs.getInt("readerState"));
				r.setReaderPwd(rs.getString("readerPwd"));
				r.setReaderVcState(rs.getInt("readerVcState"));
				r.setReaderVcBalance(rs.getDouble("readerVcBalance"));
				r.setReaderTypeId(rs.getInt("readerTypeId"));
				list.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free( pstm, conn);
		}
		return list;
	}
	
	@Override
	public boolean checkReaderTypeByName(ReaderTypeInfoBean readerType) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from readerTypeInfo where readerTypeName = ? and readerTypeisdel =0";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, readerType.getReaderTypeName());
			rs = pstm.executeQuery();
			bool = rs.next();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public ReaderTypeInfoBean getReaderTypeByName(ReaderTypeInfoBean readerType) {
		// TODO Auto-generated method stub
		ReaderTypeInfoBean r = new ReaderTypeInfoBean();
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from readerTypeInfo where readerTypeName = ? and readerIsDel = 0";

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, readerType.getReaderTypeName());
			rs = pstm.executeQuery();
			r = new ReaderTypeInfoBean();				
			r.setReaderTypeId(rs.getInt("readerTypeId"));
			r.setReaderTypeName(rs.getString("readerTypeName"));
			r.setMaxNum(rs.getInt("maxNum"));
			r.setDays(rs.getInt("days"));
			r.setKeepMoney(rs.getDouble("keepMoney"));
			r.setNormalRent(rs.getDouble("normalRent"));
			r.setExtendedRent(rs.getDouble("extendedRent"));
			r.setLossTimes(rs.getInt("lossTimes"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free( pstm, conn);
		}
		return r;
	}

	@Override
	public List<ReaderInfoViewBean> getPossibleReadersInfoByName(String trim) {
		// TODO Auto-generated method stub
		List<ReaderInfoViewBean> list = new ArrayList<ReaderInfoViewBean>();
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ReaderInfoViewBean view = null;
		ReaderInfoBean reader=null;
		ReaderTypeInfoBean readerType=null;
		String sql = "select readerInfo.*,maxNum,days,keepMoney,normalRent,extendedRent,lossTimes,readerTypeName from readerInfo,readerTypeInfo where readerInfo.readerTypeId =readerTypeInfo.readerTypeId and readerIsDel=0 and ReaderName like ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%"+trim+"%");
			rs = pstm.executeQuery();
			while (rs.next()) {
				view = new ReaderInfoViewBean();
				reader=new ReaderInfoBean();
				readerType=new ReaderTypeInfoBean();	
				reader.setReaderId(rs.getInt("readerId"));
				int readerTypeId=rs.getInt("readerTypeId");
				reader.setReaderTypeId(readerTypeId);
				readerType.setReaderTypeId(readerTypeId);
				reader.setReaderName(rs.getString("readerName"));			
				reader.setReaderSex(rs.getString("readerSex"));
				reader.setReaderCardType(rs.getString("readerCardType"));			
				reader.setReaderIdentifyCard(rs.getString("readerIdentifyCard"));
				reader.setReaderTel(rs.getString("readerTel"));
				reader.setReaderRegistDate(rs.getString("readerRegistDate").substring(0,10));
				
				reader.setReaderIsDel(rs.getInt("readerIsDel"));
				reader.setReaderState(rs.getInt("readerState"));
				reader.setReaderPwd(rs.getString("readerPwd"));
				reader.setReaderVcState(rs.getInt("readerVcState"));
				reader.setReaderVcBalance(rs.getDouble("readerVcBalance"));

				readerType.setMaxNum(rs.getInt("maxNum"));
				readerType.setDays(rs.getInt("days"));
				readerType.setKeepMoney(rs.getDouble("keepMoney"));
				readerType.setNormalRent(rs.getDouble("normalRent"));
				readerType.setExtendedRent(rs.getInt("extendedRent"));
				readerType.setLossTimes(rs.getInt("lossTimes"));
				readerType.setReaderTypeName(rs.getString("readerTypeName"));			
				view.setReader(reader);
				view.setReaderType(readerType);				
				list.add(view);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public List<ReaderInfoViewBean> getPossibleReadersInfoById(String trim) {
		// TODO Auto-generated method stub
		List<ReaderInfoViewBean> list = new ArrayList<ReaderInfoViewBean>();
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ReaderInfoViewBean view = null;
		ReaderInfoBean reader=null;
		ReaderTypeInfoBean readerType=null;
		String sql = "select readerInfo.*,maxNum,days,keepMoney,normalRent,extendedRent,lossTimes,readerTypeName from readerInfo,readerTypeInfo where readerInfo.readerTypeId =readerTypeInfo.readerTypeId and readerIsDel=0 and readerId like ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%"+trim+"%");
			rs = pstm.executeQuery();
			while (rs.next()) {
				view = new ReaderInfoViewBean();
				reader=new ReaderInfoBean();
				readerType=new ReaderTypeInfoBean();	
				reader.setReaderId(rs.getInt("readerId"));
				int readerTypeId=rs.getInt("readerTypeId");
				reader.setReaderTypeId(readerTypeId);
				readerType.setReaderTypeId(readerTypeId);
				reader.setReaderName(rs.getString("readerName"));			
				reader.setReaderSex(rs.getString("readerSex"));
				reader.setReaderCardType(rs.getString("readerCardType"));			
				reader.setReaderIdentifyCard(rs.getString("readerIdentifyCard"));
				reader.setReaderTel(rs.getString("readerTel"));
				reader.setReaderRegistDate(rs.getString("readerRegistDate").substring(0,10));
				
				reader.setReaderIsDel(rs.getInt("readerIsDel"));
				reader.setReaderState(rs.getInt("readerState"));
				reader.setReaderPwd(rs.getString("readerPwd"));
				reader.setReaderVcState(rs.getInt("readerVcState"));
				reader.setReaderVcBalance(rs.getDouble("readerVcBalance"));

				readerType.setMaxNum(rs.getInt("maxNum"));
				readerType.setDays(rs.getInt("days"));
				readerType.setKeepMoney(rs.getDouble("keepMoney"));
				readerType.setNormalRent(rs.getDouble("normalRent"));
				readerType.setExtendedRent(rs.getInt("extendedRent"));
				readerType.setLossTimes(rs.getInt("lossTimes"));
				readerType.setReaderTypeName(rs.getString("readerTypeName"));			
				view.setReader(reader);
				view.setReaderType(readerType);				
				list.add(view);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free(rs, pstm, conn);
		}
		return list;
	}
}
