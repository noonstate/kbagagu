package shop.kbgagu.www.admin.dao;

import static shop.kbgagu.www.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import shop.kbgagu.www.common.Paser;
import shop.kbgagu.www.admin.statistics.VisitVo;
import shop.kbgagu.www.admin.vo.AdminVo;
import shop.kbgagu.www.common.Pagenation;
import shop.kbgagu.www.vo.ItemVo;
import shop.kbgagu.www.vo.MemberVo;
import shop.kbgagu.www.vo.NoticeVo;
import shop.kbgagu.www.vo.OrderVo;
import shop.kbgagu.www.vo.QuestionVo;
import shop.kbgagu.www.vo.ReviewVo;

public class AdminDao {
	private Connection con;
	
	private AdminDao() {}
	
	private static class LazyHolder {
		private static final AdminDao INSTANCE = new AdminDao();
	}
	
	public static AdminDao getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	// 어드민 로그인을 위한 조회
	public AdminVo loginInfo(String id) {  
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AdminVo vo = null;
		
		String SQL = "select admin_sq, admin_id, admin_pwd from inf_admin_tb where admin_id =?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new AdminVo();
				vo.setAdmin_sq(rs.getInt("admin_sq"));
				vo.setAdmin_id(rs.getString("admin_id"));
				vo.setAdmin_pwd(rs.getString("admin_pwd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return vo;
	}
	
	// 멤버페이지 페이징을 위해서 멤버수 조회
	public int getMemberCount(String query) {  
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_mber_tb where 1=1" + query); // 테이블의 row카운트
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
	
	// 특정 멤버 정보 전체 조회
	public MemberVo getMemberInfo(String mberSq) {  
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo vo = null;
		
		String SQL = "select * from inf_mber_tb where mber_sq =?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, mberSq);
			rs = pstmt.executeQuery();
			vo = new MemberVo();
			while (rs.next()) {
				vo = new MemberVo();
				vo.setDel_fl(rs.getBoolean("del_fl"));
				vo.setDttm(rs.getString("dttm"));
				vo.setId(rs.getString("id"));
				vo.setMber_sq(rs.getInt("mber_sq"));
				vo.setNm(rs.getString("nm"));
				vo.setTel(rs.getString("tel"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return vo;
	}
	
	// 탈퇴처리된 멤버를 제외한 멤버전체 조회
	public ArrayList<MemberVo> getMemberList(Pagenation pagenation,String query){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo vo = null;
		ArrayList<MemberVo> mar = new ArrayList<MemberVo>();
		
		String SQL = "SELECT * FROM inf_mber_tb where del_fl=false ORDER BY mber_sq LIMIT ?,10";
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, pagenation.getStartArticleNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new MemberVo();
				vo.setNm(rs.getString("nm"));
				vo.setMber_sq(rs.getInt("mber_sq"));
				vo.setDel_fl(rs.getBoolean("del_fl"));
				vo.setDttm(rs.getString("dttm"));
				vo.setId(rs.getString("id"));
				vo.setTel(rs.getString("tel"));
				mar.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return mar;
	}
	
	// 상품추가
	public int insertItem(ItemVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		String SQL = "insert into inf_item_tb(item_nm, price, detail, ctgry, new_fl, upvote_fl, "
				+ "sale_fl, sale_rate, img_n0, img_n1, img_n2, img_n3, img_n4) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, vo.getItem_nm());
			pstmt.setInt(2, vo.getPrice());
			pstmt.setString(3, vo.getDetail());
			pstmt.setInt(4, vo.getCtgry());
			pstmt.setBoolean(5, vo.isNew_fl());
			pstmt.setBoolean(6, vo.isUpvote_fl());
			pstmt.setBoolean(7, vo.isSale_fl());
			pstmt.setInt(8, vo.getSale_rate());
			pstmt.setString(9, vo.getImg_n0());
			pstmt.setString(10, vo.getImg_n1());
			pstmt.setString(11, vo.getImg_n2());
			pstmt.setString(12, vo.getImg_n3());
			pstmt.setString(13, vo.getImg_n4());
			
			count = pstmt.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	// 상품리스트에 페이징을 위한 상품수 조회
	public int getItemCount(String query) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_item_tb where 1=1 and del_fl=false" + query); // 테이블의 row카운트
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
	
	// 상품전체정보 조회
	public ArrayList<ItemVo> getItemList(Pagenation pagenation,String query){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVo vo = null;
		ArrayList<ItemVo> iar = new ArrayList<ItemVo>();
		
		String SQL = "SELECT * FROM inf_item_tb where del_fl=false ORDER BY item_sq DESC LIMIT ?,10";
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, pagenation.getStartArticleNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new ItemVo();
				vo.setItem_sq(rs.getInt("item_sq"));
				vo.setCtgry(rs.getInt("ctgry"));
				vo.setAdmin_sq(rs.getInt("admin_sq"));
				vo.setDel_fl(rs.getBoolean("del_fl"));
				vo.setNew_fl(rs.getBoolean("new_fl"));
				vo.setUpvote_fl(rs.getBoolean("upvote_fl"));
				vo.setSale_fl(rs.getBoolean("sale_fl"));
				vo.setShow_fl(rs.getBoolean("show_fl"));
				vo.setInvntry(rs.getInt("invntry"));
				vo.setPrice(rs.getInt("price"));
				vo.setSale_cnt(rs.getInt("sale_cnt"));
				vo.setSale_rate(rs.getInt("sale_rate"));
				vo.setItem_nm(rs.getString("item_nm"));
				vo.setReg_date(rs.getString("reg_date"));
				vo.setImg_n0(rs.getString("img_n0"));
				vo.setImg_n1(rs.getString("img_n1"));
				vo.setImg_n2(rs.getString("img_n2"));
				vo.setImg_n3(rs.getString("img_n3"));
				vo.setImg_n4(rs.getString("img_n4"));
				vo.setDetail(rs.getString("detail"));
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
	
	// 특정 상품정보 조회
	public ItemVo getItemInfo(String itemSq) {
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
	
	// 상품이미지 저장경로 조회
	public ItemVo getImgPath(int itemsq){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVo vo = null;
		
		String SQL = "select img_n0, img_n1, img_n2, img_n3, img_n4 from inf_item_tb"
				+ " where del_fl=false and item_sq=?";
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, itemsq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new ItemVo();
				vo.setImg_n0(rs.getString("img_n0"));
				vo.setImg_n1(rs.getString("img_n1"));
				vo.setImg_n2(rs.getString("img_n2"));
				vo.setImg_n3(rs.getString("img_n3"));
				vo.setImg_n4(rs.getString("img_n4"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return vo;
	}
	
	// 상품정보 수정
	public int updateItem(ItemVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		String SQL = "update inf_item_tb set item_nm=?, ctgry=?, new_fl=?, upvote_fl=?, "
				+ "sale_fl=?, show_fl=?, invntry=?, price=?, sale_rate=?, "
				+ "img_n0=?, img_n1=?, img_n2=?, img_n3=?, img_n4=?, detail=? "
				+ "where item_sq=?";
		try {
			pstmt = con.prepareStatement(SQL);
			
			pstmt.setString(1, vo.getItem_nm());
			pstmt.setInt(2, vo.getCtgry());
			pstmt.setBoolean(3, vo.isNew_fl());
			pstmt.setBoolean(4, vo.isUpvote_fl());
			pstmt.setBoolean(5, vo.isSale_fl());
			pstmt.setBoolean(6, vo.isShow_fl());
			pstmt.setInt(7, vo.getInvntry());
			pstmt.setInt(8, vo.getPrice());
			pstmt.setInt(9, vo.getSale_rate());
			pstmt.setString(10, vo.getImg_n0());
			pstmt.setString(11, vo.getImg_n1());
			pstmt.setString(12, vo.getImg_n2());
			pstmt.setString(13, vo.getImg_n3());
			pstmt.setString(14, vo.getImg_n4());
			pstmt.setString(15, vo.getDetail());
			pstmt.setInt(16, vo.getItem_sq());
			
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	// 특정 상품 삭제
	public int deleteItem(int sq) {
		PreparedStatement pstmt = null;
		int count = 0;
		
		String SQL = "update inf_item_tb set del_fl = true where item_sq = ?";
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
	
	//주문페이지 페이징을 위한 갯수 조회
	public int getOrderCount() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_order_tb"); // 테이블의 row카운트
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
	
	// 교환/환불페이지 갯수 조회
	public int getOrderChangeCount() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_order_tb where sttus not between 1 and 5"); // 테이블의 row카운트
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
	
	// 상품상태(예 : 결제완료 -> 배송대기중) 변경
	public int updateOrderSttus(OrderVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		String SQL = "update inf_order_tb set sttus = ? where order_sq = ?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, vo.getSttus());
			pstmt.setInt(2, vo.getOrder_sq());
			
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 주문리스트 조회
	public ArrayList<OrderVo> getOrderList(Pagenation pagenation) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVo vo = null;
		ArrayList<OrderVo> list = new ArrayList<>();
		
		String SQL = "select o.*, i.img_n0, i.item_nm, i.price, i.sale_rate from inf_order_tb o "
				+ "inner join inf_item_tb i on i.item_sq=o.item_sq "
				+ "where i.del_fl = false and o.sttus BETWEEN 1 and 5 order by o.reg_date desc limit ?,20";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, pagenation.getStartArticleNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new OrderVo();
				vo.setImg_n0(rs.getString("img_n0"));
				vo.setPrice(rs.getInt("price"));
				vo.setItem_nm(rs.getString("item_nm"));
				vo.setSale_rate(rs.getInt("sale_rate"));
				vo.setOrder_sq(rs.getInt("order_sq"));
				vo.setSttus(rs.getInt("sttus"));
				vo.setPymnt(rs.getInt("pymnt"));
				vo.setQy(rs.getInt("qy"));
				vo.setOrder_nm(rs.getString("order_nm"));
				vo.setOrder_tel(rs.getString("order_tel"));
				vo.setZip(rs.getString("zip"));
				vo.setAdres(rs.getString("adres"));
				vo.setDetail_adres(rs.getString("detail_adres"));
				vo.setExtra_adres(rs.getString("extra_adres"));
				vo.setRequst(rs.getString("requst"));
				vo.setReg_date(rs.getString("reg_date"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return list;
	}

	// 주문상태 카테고리 필터링
	public ArrayList<OrderVo> getOrderList(Pagenation pagenation, String orderCategory) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVo vo = null;
		ArrayList<OrderVo> list = new ArrayList<>();
	
		String SQL = "select o.*, i.img_n0, i.item_nm, i.price, i.sale_rate from inf_order_tb o "
				+ "inner join inf_item_tb i on i.item_sq=o.item_sq "
				+ "where i.del_fl = false and o.sttus = ? and o.sttus BETWEEN 1 and 5 order by o.reg_date desc limit ?,20";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, orderCategory);
			pstmt.setInt(2, pagenation.getStartArticleNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new OrderVo();
				vo.setImg_n0(rs.getString("img_n0"));
				vo.setPrice(rs.getInt("price"));
				vo.setItem_nm(rs.getString("item_nm"));
				vo.setSale_rate(rs.getInt("sale_rate"));
				vo.setOrder_sq(rs.getInt("order_sq"));
				vo.setSttus(rs.getInt("sttus"));
				vo.setPymnt(rs.getInt("pymnt"));
				vo.setQy(rs.getInt("qy"));
				vo.setOrder_nm(rs.getString("order_nm"));
				vo.setOrder_tel(rs.getString("order_tel"));
				vo.setZip(rs.getString("zip"));
				vo.setAdres(rs.getString("adres"));
				vo.setDetail_adres(rs.getString("detail_adres"));
				vo.setExtra_adres(rs.getString("extra_adres"));
				vo.setRequst(rs.getString("requst"));
				vo.setReg_date(rs.getString("reg_date"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return list;
	}
	
	// 주문리스트 결제방법 필터링
	public ArrayList<OrderVo> getOrderPaymentList(Pagenation pagenation, String payment) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVo vo = null;
		ArrayList<OrderVo> list = new ArrayList<>();
	
		String SQL = "select o.*, i.img_n0, i.item_nm, i.price, i.sale_rate from inf_order_tb o "
				+ "inner join inf_item_tb i on i.item_sq=o.item_sq "
				+ "where i.del_fl = false and o.pymnt = ? and o.sttus BETWEEN 1 and 5 order by o.reg_date desc limit ?,20";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, payment);
			pstmt.setInt(2, pagenation.getStartArticleNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new OrderVo();
				vo.setImg_n0(rs.getString("img_n0"));
				vo.setPrice(rs.getInt("price"));
				vo.setItem_nm(rs.getString("item_nm"));
				vo.setSale_rate(rs.getInt("sale_rate"));
				vo.setOrder_sq(rs.getInt("order_sq"));
				vo.setSttus(rs.getInt("sttus"));
				vo.setPymnt(rs.getInt("pymnt"));
				vo.setQy(rs.getInt("qy"));
				vo.setOrder_nm(rs.getString("order_nm"));
				vo.setOrder_tel(rs.getString("order_tel"));
				vo.setZip(rs.getString("zip"));
				vo.setAdres(rs.getString("adres"));
				vo.setDetail_adres(rs.getString("detail_adres"));
				vo.setExtra_adres(rs.getString("extra_adres"));
				vo.setRequst(rs.getString("requst"));
				vo.setReg_date(rs.getString("reg_date"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return list;
	}

	// 카테고리랑 결제방법 두개 조회
	public ArrayList<OrderVo> getOrderList(Pagenation pagenation, String orderCategory, String payment) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVo vo = null;
		ArrayList<OrderVo> list = new ArrayList<>();
	
		String SQL = "select o.*, i.img_n0, i.item_nm, i.price, i.sale_rate from inf_order_tb o "
				+ "inner join inf_item_tb i on i.item_sq=o.item_sq "
				+ "where i.del_fl = false and o.pymnt = ? and o.sttus = ? and o.sttus BETWEEN 1 and 5 order by o.reg_date desc limit ?,20";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, payment);
			pstmt.setString(2, orderCategory);
			pstmt.setInt(3, pagenation.getStartArticleNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new OrderVo();
				vo.setImg_n0(rs.getString("img_n0"));
				vo.setPrice(rs.getInt("price"));
				vo.setItem_nm(rs.getString("item_nm"));
				vo.setSale_rate(rs.getInt("sale_rate"));
				vo.setOrder_sq(rs.getInt("order_sq"));
				vo.setSttus(rs.getInt("sttus"));
				vo.setPymnt(rs.getInt("pymnt"));
				vo.setQy(rs.getInt("qy"));
				vo.setOrder_nm(rs.getString("order_nm"));
				vo.setOrder_tel(rs.getString("order_tel"));
				vo.setZip(rs.getString("zip"));
				vo.setAdres(rs.getString("adres"));
				vo.setDetail_adres(rs.getString("detail_adres"));
				vo.setExtra_adres(rs.getString("extra_adres"));
				vo.setRequst(rs.getString("requst"));
				vo.setReg_date(rs.getString("reg_date"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return list;
	}
	
	// 교환/환불페이지 리스트 조회
	public ArrayList<OrderVo> getChangeOrderList(Pagenation pagenation) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVo vo = null;
		ArrayList<OrderVo> list = new ArrayList<>();
		
		String SQL = "select o.*, i.img_n0, i.item_nm, i.price, i.sale_rate from inf_order_tb o "
				+ "inner join inf_item_tb i on i.item_sq=o.item_sq "
				+ "where i.del_fl = false and o.sttus not BETWEEN 1 and 5 order by o.reg_date desc limit ?,20";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, pagenation.getStartArticleNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new OrderVo();
				vo.setImg_n0(rs.getString("img_n0"));
				vo.setPrice(rs.getInt("price"));
				vo.setItem_nm(rs.getString("item_nm"));
				vo.setSale_rate(rs.getInt("sale_rate"));
				vo.setOrder_sq(rs.getInt("order_sq"));
				vo.setSttus(rs.getInt("sttus"));
				vo.setPymnt(rs.getInt("pymnt"));
				vo.setQy(rs.getInt("qy"));
				vo.setOrder_nm(rs.getString("order_nm"));
				vo.setOrder_tel(rs.getString("order_tel"));
				vo.setZip(rs.getString("zip"));
				vo.setAdres(rs.getString("adres"));
				vo.setDetail_adres(rs.getString("detail_adres"));
				vo.setExtra_adres(rs.getString("extra_adres"));
				vo.setRequst(rs.getString("requst"));
				vo.setReg_date(rs.getString("reg_date"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return list;
	}
	
	// 교환/환불리스트 카테고리 필터링
	public ArrayList<OrderVo> getChangeOrderList(Pagenation pagenation, int category) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVo vo = null;
		ArrayList<OrderVo> list = new ArrayList<>();
		
		String SQL = "select o.*, i.img_n0, i.item_nm, i.price, i.sale_rate from inf_order_tb o "
				+ "inner join inf_item_tb i on i.item_sq=o.item_sq "
				+ "where i.del_fl = false and o.sttus not BETWEEN 1 and 5 and o.sttus = ? "
				+ "order by o.reg_date desc limit ?,20";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, category);
			pstmt.setInt(2, pagenation.getStartArticleNumber());
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new OrderVo();
				vo.setImg_n0(rs.getString("img_n0"));
				vo.setPrice(rs.getInt("price"));
				vo.setItem_nm(rs.getString("item_nm"));
				vo.setSale_rate(rs.getInt("sale_rate"));
				vo.setOrder_sq(rs.getInt("order_sq"));
				vo.setSttus(rs.getInt("sttus"));
				vo.setPymnt(rs.getInt("pymnt"));
				vo.setQy(rs.getInt("qy"));
				vo.setOrder_nm(rs.getString("order_nm"));
				vo.setOrder_tel(rs.getString("order_tel"));
				vo.setZip(rs.getString("zip"));
				vo.setAdres(rs.getString("adres"));
				vo.setDetail_adres(rs.getString("detail_adres"));
				vo.setExtra_adres(rs.getString("extra_adres"));
				vo.setRequst(rs.getString("requst"));
				vo.setReg_date(rs.getString("reg_date"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return list;
	}
	
	// 공지추가 
	public int insertNotice(NoticeVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		String SQL = "INSERT INTO inf_notice_tb (admin_sq,notice_ctgry,notice_title,notice_cntnt)VALUES(?,?,?,?)";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, vo.getAdmin_sq());
			pstmt.setInt(2, vo.getNotice_ctgry());
			pstmt.setString(3, vo.getNotice_title());
			pstmt.setString(4, vo.getNotice_cntnt());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	// 특정 공지 세부내용 조회
	public NoticeVo getNoticeInfo(int sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NoticeVo vo = null;
		
		String SQL = "select * from inf_notice_tb where notice_sq =?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, sq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new NoticeVo();
				vo.setAdmin_sq(rs.getInt("admin_sq"));
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
			close(pstmt);
			close(rs);
		}
		return vo;
	}
	
	// 공지페이지 페이징을 위해 갯수 조회
	public int getNoticeCount(String query) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_notice_tb where 1=1" + query); // 테이블의 row카운트
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
	
	// 공지페이지 전체 조회
	public ArrayList<NoticeVo> getNoticeList(Pagenation pagenation,String query){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NoticeVo vo = null;
		ArrayList<NoticeVo> nar = new ArrayList<NoticeVo>();
		
		String SQL = "SELECT * FROM inf_notice_tb ORDER BY notice_sq DESC LIMIT ?,10";
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, pagenation.getStartArticleNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new NoticeVo();
				vo.setAdmin_sq(rs.getInt("admin_sq"));
				vo.setDel_fl(rs.getBoolean("del_fl"));
				vo.setNotice_cntnt(Paser.chgToHTML(rs.getString("notice_cntnt")));
				vo.setNotice_ctgry(rs.getInt("notice_ctgry"));
				vo.setNotice_dttm(rs.getString("notice_dttm"));
				vo.setNotice_hit(rs.getInt("notice_hit"));
				vo.setNotice_sq(rs.getInt("notice_sq"));
				vo.setNotice_title(rs.getString("notice_title"));
				nar.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return nar;
	}
	
	// 공지 수정
	public int updateNotice(NoticeVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		String SQL = "UPDATE inf_notice_tb set admin_sq = ?,notice_ctgry = ?,notice_title =?,notice_cntnt = ? where notice_sq = ?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, vo.getAdmin_sq());
			pstmt.setInt(2, vo.getNotice_ctgry());
			pstmt.setString(3, vo.getNotice_title());
			pstmt.setString(4, vo.getNotice_cntnt());
			pstmt.setInt(5, vo.getNotice_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	// 질문글 답변추가
	public int updateAnswer(QuestionVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		
		String SQL = "UPDATE inf_ques_tb SET admin_sq = ? , answer = ? where ques_sq = ?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, vo.getAdmin_sq());
			pstmt.setString(2, vo.getAnswer());
			pstmt.setInt(3, vo.getQues_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	// 공지삭제
	public int deleteNotices(int[] sqList) {
		PreparedStatement pstmt = null;
		int count = 0;
		String SQL = "update inf_notice_tb set del_fl = true where notice_sq = ?";
		try {
			for(int i=0;i<sqList.length;i++) {
				pstmt = con.prepareStatement(SQL);
				pstmt.setInt(1, sqList[i]);
				count = count + pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	// 특정 질문글 조회
	public QuestionVo getQuestionInfo(int sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QuestionVo vo = null;
		
		String SQL = "select * from inf_ques_tb where ques_sq =?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, sq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new QuestionVo();
				vo.setQues_sq(rs.getInt("ques_sq"));
				vo.setMber_sq(rs.getInt("mber_sq"));
				vo.setQues_dttm(rs.getString("ques_dttm"));
				vo.setQues_title(rs.getString("ques_title"));
				vo.setQues_cntnt(rs.getString("ques_cntnt"));
				vo.setAnswer(rs.getString("answer"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return vo;
	}
	
	// 질문글 전체 조회
	public ArrayList<QuestionVo> getQuestionList(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QuestionVo vo = null;
		ArrayList<QuestionVo> list = new ArrayList<>();
		
		String SQL = "select i.*, m.nm from inf_ques_tb i INNER JOIN inf_mber_tb m on i.mber_sq=m.mber_sq";
		
		try {
			pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new QuestionVo();
				vo.setQues_sq(rs.getInt("ques_sq"));
				vo.setAdmin_sq(rs.getInt("admin_sq"));
				vo.setQues_title(Paser.chgToHTML(rs.getString("ques_title")));
				vo.setQues_cntnt(Paser.chgToHTML(rs.getString("ques_cntnt")));
				vo.setQues_dttm(rs.getString("ques_dttm"));
				vo.setAnswer(Paser.chgToHTML(rs.getString("answer")));
				vo.setNm(rs.getString("nm"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	// 페이징을 위해서 질문 갯수 조회
	public int getQuestionCount(String query) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_ques_tb where 1=1" + query); // 테이블의 row카운트
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
	
	// 질문 페이징 
	public ArrayList<QuestionVo> getQuestionList(Pagenation pagenation,String query){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QuestionVo vo = null;
		ArrayList<QuestionVo> qar = new ArrayList<QuestionVo>();
		
		String SQL = "SELECT q.* ,m.nm,m.tel,m.id FROM inf_ques_tb q INNER JOIN inf_mber_tb m on m.mber_sq=q.mber_sq ORDER BY ques_sq DESC LIMIT ?,10";
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, pagenation.getStartArticleNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new QuestionVo();
				vo.setQues_sq(rs.getInt("ques_sq"));
				vo.setMber_sq(rs.getInt("mber_sq"));
				vo.setAdmin_sq(rs.getInt("admin_sq"));
				vo.setDel_fl(rs.getBoolean("del_fl"));
				vo.setQues_dttm(rs.getString("ques_dttm"));
				vo.setQues_title(rs.getString("ques_title"));
				vo.setQues_cntnt(rs.getString("ques_cntnt"));
				vo.setAnswer(rs.getString("answer"));
				vo.setNm(rs.getString("nm"));
				vo.setTel(rs.getString("tel"));
				vo.setId(rs.getString("id"));
				qar.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return qar;
	}
	
	// 리뷰 페이징을 위한 갯수 조회
	public int getReviewCount(String query) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_review_tb where 1=1" + query); // 테이블의 row카운트
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
	
	// 리뷰 리스트 조회
	public ArrayList<ReviewVo> getReviewList(Pagenation pagenation,String query){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReviewVo vo = null;
		ArrayList<ReviewVo> rar = new ArrayList<ReviewVo>();
		
		String SQL = "SELECT * FROM inf_review_tb r INNER JOIN inf_mber_tb m on m.mber_sq=r.mber_sq ORDER BY review_sq DESC LIMIT ?,10";
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, pagenation.getStartArticleNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new ReviewVo();
				vo.setReview_sq(rs.getInt("review_sq"));
				vo.setMber_sq(rs.getInt("mber_sq"));
				vo.setOrder_sq(rs.getInt("order_sq"));
				vo.setStar_rating(rs.getInt("star_rating"));
				vo.setDel_fl(rs.getBoolean("del_fl"));
				vo.setReview_hit(rs.getInt("review_hit"));
				vo.setReview_dttm(rs.getString("review_dttm"));
				vo.setReview_title(rs.getString("review_title"));
				vo.setReview_cntnt(rs.getString("review_cntnt"));
				vo.setNm(rs.getString("nm"));
				vo.setId(rs.getString("id"));
				vo.setItem_sq(rs.getInt("item_sq"));
				rar.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return rar;
	}
	
	public int deleteReivew(int sq) {
		PreparedStatement pstmt = null;
		int count = 0;
		String SQL = "UPDATE inf_review_tb SET del_fl = if(del_fl = true,FALSE ,true) where review_sq = ?";
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
	
	
	// 방문자 통계
	public ArrayList<VisitVo> getVisitStatics(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		VisitVo vo = null;
		ArrayList<VisitVo> avs = new ArrayList<VisitVo>();
		
		String SQL = "SELECT count(*),date(date_add(now(),INTERVAL ? DAY)) as dttm FROM hist_lgn_tb WHERE date(dttm) = date(date_add(now(),INTERVAL ? DAY)) and sttus = FALSE;";
		try {
			for(int i = 0; i<30;i++) {
				pstmt = con.prepareStatement(SQL);
				pstmt.setInt(1, -i);
				pstmt.setInt(2, -i);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					vo = new VisitVo();
					vo.setDate(rs.getString("dttm"));
					vo.setHit(rs.getInt("count(*)"));
					avs.add(vo);
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return avs;
	}
	
}
