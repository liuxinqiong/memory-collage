package cn.com.daos.keepMoneyInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.keepMoneyInfo.KeeyMoneyInfoBean;
import cn.com.beans.keepMoneyInfo.KeeyMoneyInfoViewBean;
import cn.com.beans.readerInfo.ReaderInfoBean;
import cn.com.beans.readerInfo.ReaderInfoViewBean;
import cn.com.beans.readerInfo.ReaderTypeInfoBean;
import cn.com.beans.userInfo.UserInfoBean;
import cn.com.db.DBUtil;

public class KeepMoneyInfoDAOImp implements KeepMoneyInfoDAOInf{

	@Override
	public List<KeeyMoneyInfoViewBean> getAllKeepMoneyInfoView(String content) {
		// TODO Auto-generated method stub
		List<KeeyMoneyInfoViewBean> list = new ArrayList<KeeyMoneyInfoViewBean>();
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		KeeyMoneyInfoViewBean view = null;
		KeeyMoneyInfoBean keeyMoney = null;
		UserInfoBean user = null;
		ReaderInfoViewBean readerView = null;
		ReaderInfoBean reader=null;
		ReaderTypeInfoBean readerType=null;
		String sql = "select readerInfo.*, userInfo.*,recordId,operateTime,money,useWay from readerInfo,userInfo,keeyMoneyInfo where keeyMoneyInfo.userId=userInfo.userId and keeyMoneyInfo.readerId=readerInfo.readerId";
		if (!content.equals("")) {
			sql += " and readerName like ? or readerInfo.readerId like ?";
		}
		try {
			pstm = conn.prepareStatement(sql);
			if (!content.equals("")) {
				pstm.setString(1, "%" + content + "%");
				pstm.setString(2, "%" + content + "%");
			}
			rs = pstm.executeQuery();
			while (rs.next()) {
				view = new KeeyMoneyInfoViewBean();
				keeyMoney = new KeeyMoneyInfoBean();
				readerView = new ReaderInfoViewBean();;
				user = new UserInfoBean();
				reader=new ReaderInfoBean();
				readerType=new ReaderTypeInfoBean();
				
				keeyMoney.setRecordId(rs.getInt("recordId"));
				int userId=rs.getInt("userId");
				keeyMoney.setUserId(userId);
				user.setUserId(userId);			
				int readerId=rs.getInt("readerId");
				keeyMoney.setReaderId(readerId);
				reader.setReaderId(readerId);
				keeyMoney.setMoney(rs.getDouble("money"));
				keeyMoney.setOperateTime(rs.getString("OperateTime").substring(0, 10));
				keeyMoney.setUseWay(rs.getInt("useWay"));

				
				user.setUserName(rs.getString("userName"));
				user.setUserWorkDate(rs.getString("userWorkDate").substring(0,10));
				user.setUserTel(rs.getString("userTel"));
				user.setUserPwd(rs.getString("userPwd"));
				user.setUserType(rs.getInt("userType"));
				user.setUserState(rs.getInt("userState"));
				user.setUserIsDel(rs.getInt("userIsDel"));
				
				
				
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
		
				readerView.setReader(reader);
				readerView.setReaderType(readerType);
				
				
				view.setReaderView(readerView);
				view.setUser(user);
				view.setKeeyMoney(keeyMoney);
				
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
	public boolean getKeepMoney(ReaderInfoBean reader, UserInfoBean user,String money,int type) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		int rs = 0;
		String sql = "insert into keeyMoneyInfo values(seq_keeyMoneyInfo_recordId.nextVal,?,?,sysdate,?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, user.getUserId());
			pstm.setInt(2, reader.getReaderId());
			pstm.setDouble(3, new Double(money));
			pstm.setInt(4, type);
			rs = pstm.executeUpdate();
			if (rs == 0) {
				bool = false;
			} else {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free(pstm, conn);
		}
		return bool;
	}
}
