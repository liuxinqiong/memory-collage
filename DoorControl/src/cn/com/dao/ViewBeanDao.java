package cn.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.db.DBUtil;
import cn.com.entity.Grade;
import cn.com.entity.Record;
import cn.com.entity.User;
import cn.com.entity.ViewBean;

/**
 * @function ���ڴ����η������ݿ⣬������Ŀ�����õ����⣬һ�ΰ���Ҫ������ȫ��ȡ��
 * @author sky
 *
 */
public class ViewBeanDao {
	public List<ViewBean> getAllViewBeans(String keyWord,String startTime,String endTime){
		List<ViewBean> list=new ArrayList<ViewBean>();
		ViewBean v=null;
		User u=null;
		Record r=null;
		Grade g=null;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs=null;
		//select * from
		//(select door_user.*,recordId,openTime,gradeName,gradeCount from door_user,door_grade,door_record  where door_user.userId=door_record.userId and door_user.userGradeId=door_grade.gradeId and openTime between to_date('2015-05-17','yyyy-mm-dd') and to_date('2015-05-19','yyyy-mm-dd') order by openTime desc)
		//where userid like '%%' or userName like '%��%' 
		//and door_user.userName like '%��%' and openTime between to_date('2015-05-17','yyyy-mm-dd hh24:mi:ss') and to_date('2015-05-19','yyyy-mm-dd hh24:mi:ss')
		String sql = "select door_user.*,recordId,openTime,gradeName,gradeCount from door_user,door_grade,door_record where door_user.userId=door_record.userId and door_user.userGradeId=door_grade.gradeId order by openTime desc";
		if(keyWord!=null&&startTime!=null&&endTime!=null){
			sql="select * from (select door_user.*,recordId,openTime,gradeName,gradeCount from door_user,door_grade,door_record  where door_user.userId=door_record.userId and door_user.userGradeId=door_grade.gradeId and openTime between to_date( ? ,'yyyy-mm-dd hh24:mi:ss') and to_date( ? ,'yyyy-mm-dd hh24:mi:ss') order by openTime desc) where userid like ? or userName like ? ";
		}
		try {
			pstm=conn.prepareStatement(sql);
			if(keyWord!=null&&startTime!=null&&endTime!=null){
				pstm.setString(1, startTime);
				pstm.setString(2,endTime);
				pstm.setString(3, "%"+keyWord+"%");
				pstm.setString(4, "%"+keyWord+"%");
			}
			rs = pstm.executeQuery();
			while (rs.next()) {
				v=new ViewBean();
				u = new User();
				r=new Record();
				g=new Grade();
				//ע�⹲����Ϣ�Ļ�ȡ �α��Զ�ת������
				String userId=rs.getString("userId");
				int gradeId=rs.getInt("userGradeId");
				u.setUserId(userId);
				u.setUserName(rs.getString("userName"));
				u.setUserGradeId(gradeId);
				
				r.setRecordId(rs.getInt("recordId"));
				r.setOpenTime(rs.getString("openTime"));
				r.setUserId(userId);
				
				g.setGradeCount(rs.getInt("gradeCount"));
				g.setGradeId(gradeId);
				g.setGradeName(rs.getString("gradeName"));
				
				v.setUser(u);
				v.setRecord(r);
				v.setGrade(g);
				list.add(v);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.free( rs,pstm, conn);
		}
		return list;
	}
	
	public List<ViewBean> getUGBeans(){
		List<ViewBean> list=new ArrayList<ViewBean>();
		ViewBean v=null;
		User u=null;
		Grade g=null;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs=null;
		String sql = "select door_user.*,gradeName,gradeCount from door_user,door_grade where door_user.userGradeId=door_grade.gradeId";
		try {
			pstm=conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				v=new ViewBean();
				u = new User();
				g=new Grade();
				//ע�⹲����Ϣ�Ļ�ȡ �α��Զ�ת������
				String userId=rs.getString("userId");
				int gradeId=rs.getInt("userGradeId");
				u.setUserId(userId);
				u.setUserName(rs.getString("userName"));
				u.setUserGradeId(gradeId);
		
				g.setGradeCount(rs.getInt("gradeCount"));
				g.setGradeId(gradeId);
				g.setGradeName(rs.getString("gradeName"));
				
				v.setUser(u);
				v.setGrade(g);
				list.add(v);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.free( rs,pstm, conn);
		}
		return list;
	}
}
