package shop.kbgagu.www.app.home.service;

import static shop.kbgagu.www.common.JdbcUtil.getConnection;
import static shop.kbgagu.www.common.JdbcUtil.close;

import java.sql.Connection;
import java.util.ArrayList;

import shop.kbgagu.www.app.home.dao.HomeDao;
import shop.kbgagu.www.vo.ItemVo;

public class HomeService {
	
	public ArrayList<ItemVo> getNewItemList(){
		HomeDao dao = HomeDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<ItemVo> newItemList = dao.getNewItemList();
		
		close(con);
		return newItemList;
	}
	
	public ArrayList<ItemVo> getUpvoteItemList(){
		HomeDao dao = HomeDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<ItemVo> upvoteItemList = dao.getUpvoteItemList();
		
		close(con);
		return upvoteItemList;
	}
	
	public ArrayList<ItemVo> getSaleItemList(){
		HomeDao dao = HomeDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<ItemVo> saleItemList = dao.getSaleItemList();
		
		close(con);
		return saleItemList;
	}
	
}
