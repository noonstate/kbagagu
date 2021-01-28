package shop.kbgagu.www.app.home.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.kbgagu.www.vo.ItemVo;

import static shop.kbgagu.www.common.JdbcUtil.close;

public class HomeDao {
	private Connection con;
	
	private HomeDao() {}
	
	private static class LazyHolder{
		private static final HomeDao INSTANCE = new HomeDao();
	}
	
	public static HomeDao getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public ArrayList<ItemVo> getNewItemList(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ItemVo> newItemList = new ArrayList<>();
		String SQL = "SELECT * FROM inf_item_tb WHERE new_fl = true order by rand() limit 3";
		
		try {
			pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ItemVo vo = new ItemVo();
				vo.setImg_n0(rs.getString("img_n0"));
				vo.setPrice(rs.getInt("price"));
				vo.setSale_cnt(rs.getInt("sale_rate"));
				vo.setNew_fl(rs.getBoolean("new_fl"));
				vo.setUpvote_fl(rs.getBoolean("upvote_fl"));
				vo.setItem_nm(rs.getString("item_nm"));
				vo.setItem_sq(rs.getInt("item_sq"));
				newItemList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return newItemList;
	}
	
	public ArrayList<ItemVo> getUpvoteItemList(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ItemVo> newItemList = new ArrayList<>();
		String SQL = "SELECT * FROM inf_item_tb WHERE upvote_fl = true order by rand() limit 3";
		
		try {
			pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ItemVo vo = new ItemVo();
				vo.setImg_n0(rs.getString("img_n0"));
				vo.setPrice(rs.getInt("price"));
				vo.setSale_cnt(rs.getInt("sale_rate"));
				vo.setNew_fl(rs.getBoolean("new_fl"));
				vo.setUpvote_fl(rs.getBoolean("upvote_fl"));
				vo.setItem_nm(rs.getString("item_nm"));
				vo.setItem_sq(rs.getInt("item_sq"));
				newItemList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return newItemList;
	}
	
	public ArrayList<ItemVo> getSaleItemList(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ItemVo> newItemList = new ArrayList<>();
		String SQL = "SELECT * FROM inf_item_tb WHERE sale_fl = true order by rand() limit 3";
		
		try {
			pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ItemVo vo = new ItemVo();
				vo.setImg_n0(rs.getString("img_n0"));
				vo.setPrice(rs.getInt("price"));
				vo.setSale_cnt(rs.getInt("sale_rate"));
				vo.setNew_fl(rs.getBoolean("new_fl"));
				vo.setUpvote_fl(rs.getBoolean("upvote_fl"));
				vo.setItem_nm(rs.getString("item_nm"));
				vo.setItem_sq(rs.getInt("item_sq"));
				newItemList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return newItemList;
	}
}
