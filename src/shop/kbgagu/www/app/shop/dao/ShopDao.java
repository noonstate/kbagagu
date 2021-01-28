package shop.kbgagu.www.app.shop.dao;

import static shop.kbgagu.www.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.kbgagu.www.app.shop.vo.BasketProcVo;
import shop.kbgagu.www.common.Pagenation;
import shop.kbgagu.www.vo.BasketVo;
import shop.kbgagu.www.vo.ItemVo;
import shop.kbgagu.www.vo.MemberVo;
import shop.kbgagu.www.vo.OrderVo;


public class ShopDao {
	private Connection con;
	
	private ShopDao() {}
	
	private static class LazyHolder {
		private static final ShopDao INSTANCE = new ShopDao();
	}
	
	public static ShopDao getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	
	public ArrayList<ItemVo> getItemList(Pagenation pagenation,String query,int ctgry){ // 상품페이지 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVo vo = null;
		ArrayList<ItemVo> iar = new ArrayList<ItemVo>();
		
		String SQL = "select * from inf_item_tb where del_fl = false and show_fl = true and 1=1 "+ query +" order by reg_date desc LIMIT ?,9";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, pagenation.getStartArticleNumber());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new ItemVo();
				vo.setItem_sq(rs.getInt("item_sq"));
				vo.setCtgry(rs.getInt("ctgry"));
				vo.setNew_fl(rs.getBoolean("new_fl"));
				vo.setUpvote_fl(rs.getBoolean("upvote_fl"));
				vo.setSale_fl(rs.getBoolean("sale_fl"));
				vo.setPrice(rs.getInt("price"));
				vo.setSale_rate(rs.getInt("sale_rate"));
				vo.setItem_nm(rs.getString("item_nm"));
				vo.setReg_date(rs.getString("reg_date"));
				vo.setImg_n0(rs.getString("img_n0"));
				iar.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return iar;
	}

	public ItemVo getItemInfo(String itemSq) { // 개별 상품 페이지
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVo vo = null;
		
		String SQL = "select * from inf_item_tb where item_sq =?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, itemSq);
			rs = pstmt.executeQuery();
			vo = new ItemVo();
			while (rs.next()) {
				vo.setItem_sq(rs.getInt("item_sq"));
				vo.setCtgry(rs.getInt("ctgry"));
				vo.setNew_fl(rs.getBoolean("new_fl"));
				vo.setUpvote_fl(rs.getBoolean("upvote_fl"));
				vo.setSale_fl(rs.getBoolean("sale_fl"));
				vo.setShow_fl(rs.getBoolean("show_fl"));
				vo.setInvntry(rs.getInt("invntry"));
				vo.setSale_cnt(rs.getInt("sale_cnt"));
				vo.setPrice(rs.getInt("price"));
				vo.setSale_rate(rs.getInt("sale_rate"));
				vo.setItem_nm(rs.getString("item_nm"));
				vo.setReg_date(rs.getString("reg_date"));
				vo.setImg_n0(rs.getString("img_n0"));
				vo.setImg_n1(rs.getString("img_n1"));
				vo.setImg_n2(rs.getString("img_n2"));
				vo.setImg_n3(rs.getString("img_n3"));
				vo.setImg_n4(rs.getString("img_n4"));
				vo.setDetail(rs.getString("detail"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return vo;
	}
	
	public String getItemNm(int item_sq) { 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String itemNm = "";
		String SQL = "select item_nm from inf_item_tb where item_sq =?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, item_sq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				itemNm = rs.getString("item_nm");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return itemNm;
	}
	
	public int getItemCount(String query,int ctgry) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_item_tb where del_fl=false and show_fl = true and 1=1 " + query); // 테이블의 row카운트
			if(!query.equals("")) {
				pstmt.setInt(1, ctgry);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
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
	
	public MemberVo getMemberInfo(String memberSq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo vo = null;

		try {
			pstmt = con.prepareStatement("SELECT mber_sq, nm, id, tel FROM inf_mber_tb where mber_sq = ?");
			pstmt.setString(1, memberSq);
			rs = pstmt.executeQuery(); //rs에 쿼리실행결과 담기  
			while (rs.next()) {
				vo = new MemberVo();  //vo에 rs결과를 담기
				vo.setMber_sq(rs.getInt("mber_sq"));
				vo.setId(rs.getString("id"));
				vo.setNm(rs.getString("nm"));
				vo.setTel(rs.getString("tel"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return vo;
	}
	
	public OrderVo getOrderInfo(String memberSq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVo vo = null;
		String SQL = "select * from inf_order_tb where mber_sq=?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, memberSq);
			rs = pstmt.executeQuery(); //rs에 쿼리실행결과 담기  
			while (rs.next()) {
				vo = new OrderVo();  //vo에 rs결과를 담기
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return vo;
	}
	
	public int makeOrder(OrderVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		String SQL = "insert into inf_order_tb(mber_sq, item_sq, sttus, pymnt,"
				+ " qy, order_nm, order_tel, zip, adres, detail_adres,"
				+ " extra_adres, requst) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, vo.getMber_sq());
			pstmt.setInt(2, vo.getItem_sq());
			pstmt.setInt(3, vo.getSttus());
			pstmt.setInt(4, vo.getPymnt());
			pstmt.setInt(5, vo.getQy());
			pstmt.setString(6, vo.getOrder_nm());
			pstmt.setString(7, vo.getOrder_tel());
			pstmt.setString(8, vo.getZip());
			pstmt.setString(9, vo.getAdres());
			pstmt.setString(10, vo.getDetail_adres());
			pstmt.setString(11, vo.getExtra_adres());
			pstmt.setString(12, vo.getRequst());
			
			count = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public int getInvntry(OrderVo vo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "select invntry from inf_item_tb where item_sq=?";
		int invntry = 0;
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, vo.getItem_sq());
			rs = pstmt.executeQuery(); 
			while (rs.next()) {
				invntry = rs.getInt("invntry");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return invntry;
	}
	
	public int addBasket(BasketVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		String SQL = "insert into inf_basket_tb(mber_sq, item_sq, qy) values(?,?,?)";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, vo.getMber_sq());
			pstmt.setInt(2, vo.getItem_sq());
			pstmt.setInt(3, vo.getQy());
			count = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public ArrayList<BasketProcVo> getBasketInfo(String session) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BasketProcVo vo = null;
		ArrayList<BasketProcVo> basketList = new ArrayList<>();
		String SQL = "select b.*, i.price, i.item_nm, i.sale_rate, i.img_n0 from inf_basket_tb b inner join inf_item_tb i on i.item_sq=b.item_sq where b.mber_sq=?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, session);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new BasketProcVo();
				vo.setItem_sq(rs.getInt("item_sq"));
				vo.setItem_nm(rs.getString("item_nm"));
				vo.setImg_n0(rs.getString("img_n0"));
				vo.setSale_rate(rs.getInt("sale_rate"));
				vo.setPrice(rs.getInt("price"));
				vo.setBasket_sq(rs.getInt("basket_sq"));
				vo.setQy(rs.getInt("qy"));
				vo.setDttm(rs.getString("dttm"));
				basketList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return basketList;
	}
	
	public int deleteBasket(int basket_sq) {
		PreparedStatement pstmt = null;
		int count = 0;
		String SQL = "delete from inf_basket_tb where basket_sq = ?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, basket_sq);
			count = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public int updateQy(OrderVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		String SQL = "update inf_item_tb set sale_cnt = sale_cnt + ?, invntry = invntry - ? where item_sq=?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, vo.getQy());
			pstmt.setInt(2, vo.getQy());
			pstmt.setInt(3, vo.getItem_sq());
			count = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public BasketProcVo getBasket(String session) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BasketProcVo vo = null;
		String SQL = "select b.*, i.price, i.item_nm, i.sale_rate, i.img_n0 from inf_basket_tb b inner join inf_item_tb i on i.item_sq=b.item_sq where b.basket_sq=?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, session);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new BasketProcVo();
				vo.setItem_sq(rs.getInt("item_sq"));
				vo.setItem_nm(rs.getString("item_nm"));
				vo.setImg_n0(rs.getString("img_n0"));
				vo.setSale_rate(rs.getInt("sale_rate"));
				vo.setPrice(rs.getInt("price"));
				vo.setBasket_sq(rs.getInt("basket_sq"));
				vo.setQy(rs.getInt("qy"));
				vo.setDttm(rs.getString("dttm"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return vo;
	}
	
	public int updateQy(BasketVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		String SQL = "update inf_basket_tb set qy = ? where basket_sq = ?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, vo.getQy());
			pstmt.setInt(2, vo.getBasket_sq());
			count = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
}