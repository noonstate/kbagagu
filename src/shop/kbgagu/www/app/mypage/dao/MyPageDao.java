package shop.kbgagu.www.app.mypage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.kbgagu.www.common.Pagenation;
import shop.kbgagu.www.vo.MemberVo;
import shop.kbgagu.www.vo.OrderVo;
import shop.kbgagu.www.vo.QuestionVo;
import shop.kbgagu.www.vo.ReviewVo;

import static shop.kbgagu.www.common.JdbcUtil.close;

public class MyPageDao {
	private Connection con;

	private MyPageDao() {

	}

	private static class LazyHolder {
		private static final MyPageDao INSTANCE = new MyPageDao();
	}

	public static MyPageDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public MemberVo getPwd(int sq) {
		MemberVo vo = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String SQL = "select pwd from inf_mber_tb where mber_sq = ?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, sq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new MemberVo();
				vo.setPwd(rs.getString("pwd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return vo;
	}
	
	public MemberVo getId(int sq) {
		MemberVo vo = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String SQL = "select id from inf_mber_tb where mber_sq = ?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, sq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new MemberVo();
				vo.setId(rs.getString("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return vo;
	}

	public MemberVo loginInfo(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo vo = null;
		String SQL = "select mber_sq, del_fl, id, pwd from inf_mber_tb where id =?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new MemberVo();
				vo.setMber_sq(rs.getInt("mber_sq"));
				vo.setDel_fl(rs.getBoolean("del_fl"));
				vo.setId(rs.getString("id"));
				vo.setPwd(rs.getString("pwd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return vo;
	}

	public int leaveMember(int sq) {
		PreparedStatement pstmt = null;
		int count = 0;
		String SQL = "update inf_mber_tb set del_fl = true where mber_sq=?";
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

	public int modifyMember(MemberVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		String SQL = "update inf_mber_tb set tel = ?, nm = ? where mber_sq=?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, vo.getTel());
			pstmt.setString(2, vo.getNm());
			pstmt.setInt(3, vo.getMber_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public int modifyPwd(MemberVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		String SQL = "update inf_mber_tb set pwd = ? where mber_sq = ?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, vo.getPwd());
			pstmt.setInt(2, vo.getMber_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public ArrayList<ReviewVo> getReviewList(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReviewVo vo = null;
		ArrayList<ReviewVo> rar = new ArrayList<ReviewVo>();
		
		String SQL = "select * from inf_review_tb where del_fl = false";
		try {
			pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new ReviewVo();
				vo.setReview_sq(rs.getInt("review_sq"));
				vo.setReview_title(rs.getString("review_title"));
				vo.setReview_cntnt(rs.getString("review_cntnt"));
				vo.setReview_hit(rs.getInt("review_hit"));
				vo.setStar_rating(rs.getInt("star_rating"));
				vo.setReview_dttm(rs.getString("review_dttm"));
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
	
	public ArrayList<QuestionVo> getQuestionList(String sq){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QuestionVo vo = null;
		ArrayList<QuestionVo> que = new ArrayList<QuestionVo>();
		
		String SQL = "select * from inf_ques_tb where del_fl = false and mber_sq=?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, sq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new QuestionVo();
				vo.setQues_sq(rs.getInt("ques_sq"));
				vo.setMber_sq(rs.getInt("mber_sq"));
				vo.setQues_title(rs.getString("ques_title"));
				vo.setQues_cntnt(rs.getString("ques_cntnt"));
				vo.setAnswer(rs.getString("answer"));
				vo.setQues_dttm(rs.getString("ques_dttm"));
				que.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return que;
	}
	
	public int insertQuestion(QuestionVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		String SQL = "insert into inf_ques_tb (ques_title,ques_cntnt,mber_sq) values(?,?,?)";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, vo.getQues_title());
			pstmt.setString(2, vo.getQues_cntnt());
			pstmt.setInt(3, vo.getMber_sq());
			count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public int updateQuestion(QuestionVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		String SQL = "update inf_ques_tb set ques_title = ?, ques_cntnt = ? where ques_sq = ?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, vo.getQues_title());
			pstmt.setString(2, vo.getQues_cntnt());
			pstmt.setInt(3, vo.getQues_sq());
			count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public ArrayList<QuestionVo> getQuestion(String sq){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QuestionVo vo = null;
		ArrayList<QuestionVo> questionList = new ArrayList<>();
		String SQL = "select * from inf_ques_tb where del_fl = false and item_sq =?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1,sq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new QuestionVo();
				vo.setQues_sq(rs.getInt("ques_sq"));
				vo.setMber_sq(rs.getInt("mber_sq"));
				vo.setQues_title(rs.getString("ques_title"));
				vo.setQues_cntnt(rs.getString("ques_cntnt"));
				vo.setAnswer(rs.getString("answer"));
				vo.setQues_dttm(rs.getString("ques_dttm"));
				vo.setNm(rs.getString("nm"));
				questionList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return questionList;
	}
	
	public ArrayList<ReviewVo> getReview(String sq){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReviewVo vo = null;
		ArrayList<ReviewVo> reviewList = new ArrayList<>();
		
		String SQL = "select r.*, m.nm from inf_review_tb r inner join inf_mber_tb m on r.mber_sq=m.mber_sq where r.del_fl=FALSE and r.item_sq = ?";
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, sq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new ReviewVo();
				vo.setReview_sq(rs.getInt("review_sq"));
				vo.setReview_title(rs.getString("review_title"));
				vo.setReview_cntnt(rs.getString("review_cntnt"));
				vo.setReview_hit(rs.getInt("review_hit"));
				vo.setStar_rating(rs.getInt("star_rating"));
				vo.setReview_dttm(rs.getString("review_dttm"));
				vo.setNm(rs.getString("nm"));
				reviewList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return reviewList;
	}
	
	public int getOrderCount(String query) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String SQL = "select count(*) from inf_order_tb where 1=1" + query;
		try {
			pstmt = con.prepareStatement(SQL);
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
	
	public int updateSttus(OrderVo vo) {
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
	
	public ArrayList<OrderVo> getOrderList(Pagenation pagenation, String sq){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVo vo = null;
		ArrayList<OrderVo> orderList = new ArrayList<>();
		
		String SQL = "select o.*,i.item_nm, i.img_n0, i.price, i.sale_rate from inf_order_tb o"
				+ " inner join inf_item_tb i on i.item_sq=o.item_sq"
				+ " where o.mber_sq = ? order by o.reg_date desc limit ?,10";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, sq);
			pstmt.setInt(2, pagenation.getStartArticleNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new OrderVo();
				vo.setOrder_sq(rs.getInt("order_sq"));
				vo.setItem_nm(rs.getString("item_nm"));
				vo.setImg_n0(rs.getString("img_n0"));
				vo.setPrice(rs.getInt("price"));
				vo.setSale_rate(rs.getInt("sale_rate"));
				vo.setItem_sq(rs.getInt("item_sq"));
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
				orderList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return orderList;
	}
	public int writeReview(ReviewVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		String SQL = "insert into inf_review_tb (item_sq, mber_sq, order_sq, star_rating, review_title, review_cntnt)"
				+ " values (?,?,1,?,?,?)";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, vo.getItem_sq());
			pstmt.setInt(2, vo.getMber_sq());
			pstmt.setInt(3, vo.getStar_rating());
			pstmt.setString(4, vo.getReview_title());
			pstmt.setString(5, vo.getReview_cntnt());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	public ReviewVo getReviewDetail(String sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "select * from inf_review_tb where review_sq = ?";
		ReviewVo vo = null;
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, sq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new ReviewVo();
				vo.setReview_sq(rs.getInt("review_sq"));
				vo.setReview_title(rs.getString("review_title"));
				vo.setReview_cntnt(rs.getString("review_cntnt"));
				vo.setStar_rating(rs.getInt("star_rating"));
				vo.setReview_hit(rs.getInt("review_hit"));
				vo.setReview_dttm(rs.getString("review_dttm"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return vo;
	}
	
	public int addReviewHit(String sq) {
		PreparedStatement pstmt = null;
		String SQL = "update inf_review_tb set review_hit = review_hit +1 where review_sq = ?";
		int count = 0;
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, sq);
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	public int deleteReviewDetail(ReviewVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		String SQL = "update inf_review_tb set del_fl = true where review_sq=?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, vo.getReview_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public int getReviewCount(String query) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_review_tb where del_fl = false");
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
	public ArrayList<ReviewVo> getReviewList(Pagenation pagenation, String query, String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReviewVo vo = null;
		ArrayList<ReviewVo> rar = new ArrayList<ReviewVo>();

		String SQL = "select * from inf_review_tb where mber_sq =? and del_fl = false ORDER BY review_sq DESC LIMIT ?,10";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, id);
			pstmt.setInt(2, pagenation.getStartArticleNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new ReviewVo();
				vo.setReview_sq(rs.getInt("review_sq"));
				vo.setReview_title(rs.getString("review_title"));
				vo.setReview_hit(rs.getInt("review_hit"));
				vo.setStar_rating(rs.getInt("star_rating"));
				vo.setReview_dttm(rs.getString("review_dttm"));
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
}   
