package shop.kbgagu.www.app.shop.service;

import java.sql.Connection;
import java.util.ArrayList;

import shop.kbgagu.www.app.shop.dao.ShopDao;
import shop.kbgagu.www.app.shop.vo.BasketProcVo;
import shop.kbgagu.www.common.Pagenation;
import shop.kbgagu.www.vo.BasketVo;
import shop.kbgagu.www.vo.ItemVo;
import shop.kbgagu.www.vo.MemberVo;
import shop.kbgagu.www.vo.OrderVo;

import static shop.kbgagu.www.common.JdbcUtil.*;

public class ShopService {
	
	public ArrayList<ItemVo> getItemList(Pagenation pagenation , String query, int ctgry) {
		ShopDao dao = ShopDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<ItemVo> iar = dao.getItemList(pagenation,query,ctgry);
		
		close(con);
		return iar;
	}
	
	public ItemVo getItemInfo(String itemSq) {
		ShopDao dao = ShopDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
	
		ItemVo vo = dao.getItemInfo(itemSq);
		
		close(con);
		return vo;
	}
	
	public int getItemCount(String query,int ctgry) {
		ShopDao dao = ShopDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.getItemCount(query,ctgry);
		close(con);
		return count;
	}

	public MemberVo getMemberInfo(String memberSq) {
		ShopDao dao = ShopDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
	
		MemberVo vo = dao.getMemberInfo(memberSq);
		
		close(con);
		return vo;
	}
	
	public OrderVo getOrderInfo(String memberSq) {
		ShopDao dao = ShopDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
	
		OrderVo vo = dao.getOrderInfo(memberSq);
		
		close(con);
		return vo;
	}
	
	public boolean makeOrder(OrderVo vo) {
		ShopDao dao = ShopDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		int count = dao.makeOrder(vo);
		boolean isSuccess = true;
		if (count > 0) {
			commit(con);

		} else {
			rollback(con);
			isSuccess = false;
		}
		close(con);
		return isSuccess;
	}
	
	public boolean addBasket(BasketVo vo) {
		ShopDao dao = ShopDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		int count = dao.addBasket(vo);
		boolean isSuccess = true;
		if (count > 0) {
			commit(con);

		} else {
			rollback(con);
			isSuccess = false;
		}
		close(con);
		return isSuccess;
	}
	public ArrayList<BasketProcVo> getBasketInfo(String session) {
		ShopDao dao = ShopDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
	
		ArrayList<BasketProcVo> basket = dao.getBasketInfo(session);
		
		close(con);
		return basket;
	}
	 
	public boolean deleteBasket(int basket_sq) {
		ShopDao dao = ShopDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.deleteBasket(basket_sq);
		boolean isSuccess = true;
		if (count > 0) {
			commit(con);

		} else {
			rollback(con);
			isSuccess = false;
		}
		close(con);
		return isSuccess;
		
	}
	
	public boolean updateQy(BasketVo vo) {
		ShopDao dao = ShopDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.updateQy(vo);
		boolean isSuccess = true;
		if (count > 0) {
			commit(con);

		} else {
			rollback(con);
			isSuccess = false;
		}
		close(con);
		return isSuccess;
	}
	
	public int getInvntry(OrderVo vo) {
		ShopDao dao = ShopDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int invntry = dao.getInvntry(vo);

		return invntry;
	}
	
	public BasketProcVo getBasket(String session) {
		ShopDao dao = ShopDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
	
		BasketProcVo vo = dao.getBasket(session);
		
		close(con);
		return vo;
	}
	
	public String getItemNm(int item_sq) {
		ShopDao dao = ShopDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
	
		String itemNm = dao.getItemNm(item_sq);
		
		close(con);
		return itemNm;
	}
	
	public boolean updateQy(OrderVo vo) {
		ShopDao dao = ShopDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.updateQy(vo);
		boolean isSuccess = true;
		if (count > 0) {
			commit(con);

		} else {
			rollback(con);
			isSuccess = false;
		}
		close(con);
		return isSuccess;
	}
}
