package shop.kbgagu.www.app.board.dao;

import static shop.kbgagu.www.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.kbgagu.www.common.Pagenation;
import shop.kbgagu.www.vo.NoticeVo;

public class BoardDao {
	private Connection con;
	
	private BoardDao() {}
	
	private static class LazyHolder{
		private static final BoardDao INSTANCE = new BoardDao();
	}
	
	public static BoardDao getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	
	public ArrayList<NoticeVo> getNoticeList(Pagenation pagenation){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<NoticeVo> nar = new ArrayList<>();
		
		String SQL = "select * from inf_notice_tb where del_fl = false LIMIT ?,10";
		
		try {
			
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, pagenation.getStartArticleNumber());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NoticeVo vo = new NoticeVo();
				vo.setAdmin_sq(rs.getInt("admin_sq"));
				vo.setDel_fl(rs.getBoolean("del_fl"));
				vo.setNotice_cntnt(rs.getString("notice_cntnt"));
				vo.setNotice_ctgry(rs.getInt("notice_ctgry"));
				vo.setNotice_dttm(rs.getString("notice_dttm"));
				vo.setNotice_hit(rs.getInt("notice_hit"));
				vo.setNotice_sq(rs.getInt("notice_sq"));
				vo.setNotice_title(rs.getString("notice_title"));
				nar.add(vo);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
	
		return nar;
	}
	
	public int getNoticeCount(String query) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "select count(*) from inf_notice_tb where del_fl = false";
		int count = 0;
		try {
			pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return count;
	}
	
	public NoticeVo getNoticeDetail(int sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NoticeVo vo = null;
		
		String SQL = "select * from inf_notice_tb where notice_sq = ?";
		
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, sq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new NoticeVo();
				vo.setAdmin_sq(sq);
				vo.setDel_fl(rs.getBoolean("del_fl"));
				vo.setNotice_cntnt(rs.getString("notice_cntnt"));
				vo.setNotice_ctgry(rs.getInt("notice_ctgry"));
				vo.setNotice_dttm(rs.getString("notice_dttm"));
				vo.setNotice_hit(rs.getInt("notice_hit"));
				vo.setNotice_sq(rs.getInt("notice_sq"));
				vo.setNotice_title(rs.getString("notice_title"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return vo;
	}
	
	public boolean isDuplicatedNoticeHist(String ip) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "select count(*) from hist_notice_tb where ip = ? and dttm = now()";
		boolean duplicated = false;
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, ip);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1)>0) {
					duplicated = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return duplicated;
	}
	
	public int updateNoticeHit(int sq) {
		PreparedStatement pstmt = null;
		String SQL = "UPDATE inf_notice_tb SET notice_hit = (notice_hit+1) where notice_sq = ?";
		int count =0;
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, sq);
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

}
