package shop.kbgagu.www.app.mypage.service;

import static shop.kbgagu.www.common.JdbcUtil.close;
import static shop.kbgagu.www.common.JdbcUtil.commit;
import static shop.kbgagu.www.common.JdbcUtil.getConnection;
import static shop.kbgagu.www.common.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import shop.kbgagu.www.app.mypage.dao.MyPageDao;
import shop.kbgagu.www.common.Pagenation;
import shop.kbgagu.www.vo.MemberVo;
import shop.kbgagu.www.vo.OrderVo;
import shop.kbgagu.www.vo.QuestionVo;
import shop.kbgagu.www.vo.ReviewVo;

public class MyPageService {

	public boolean leaveMember(int sq) {
		MyPageDao dao = MyPageDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		int count = dao.leaveMember(sq);
		boolean isSuccess = false;
		if (count > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}

		close(con);
		return isSuccess;
	}

	public MemberVo getPwd(int sq) {
		MyPageDao dao = MyPageDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		MemberVo vo = dao.getPwd(sq);

		close(con);
		return vo;
	}
	
	public MemberVo getId(int sq) {
		MyPageDao dao = MyPageDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		MemberVo vo = dao.getId(sq);

		close(con);
		return vo;
	}

	public boolean modifyMember(MemberVo vo) {
		MyPageDao dao = MyPageDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		int count = dao.modifyMember(vo);
		boolean isSuccess = false;
		if (count > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}

		close(con);
		return isSuccess;
	}
	
	public boolean modifyPwd(MemberVo vo){
		MyPageDao dao = MyPageDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.modifyPwd(vo);
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
	
	public int getOrderCount(String query) {
		MyPageDao dao = MyPageDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int orderCount = dao.getOrderCount(query);
		
		close(con);
		return orderCount;
	}
	
	public ArrayList<ReviewVo> getReviewList() {
		MyPageDao dao = MyPageDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<ReviewVo> rar = dao.getReviewList();
		
		close(con);
		return rar;
	}
	
	public ArrayList<QuestionVo> getQuestionList(String sq) {
		MyPageDao dao = MyPageDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<QuestionVo> que = dao.getQuestionList(sq);
		
		close(con);
		return que;
	}
	
	
	public boolean insertQuestion(QuestionVo vo) {
		MyPageDao dao = MyPageDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.insertQuestion(vo);
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
	
	public boolean updateQuestion(QuestionVo vo) {
		MyPageDao dao = MyPageDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.updateQuestion(vo);
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
	
	public ArrayList<QuestionVo> getQuestion(String sq) {
		MyPageDao dao = MyPageDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<QuestionVo> que = dao.getQuestion(sq);
		
		close(con);
		return que;
	}
	
	public ArrayList<ReviewVo> getReview(String sq) {
		MyPageDao dao = MyPageDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<ReviewVo> review = dao.getReview(sq);
		
		close(con);
		return review;
	}
	
	public boolean updateSttus(OrderVo vo) {
		MyPageDao dao = MyPageDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSuccess = false;
		int count = dao.updateSttus(vo);
		if (count > 0) {
			isSuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		return isSuccess;
	}

	
	public ArrayList<OrderVo> getOrderList(Pagenation pagenation, String sq) {
		MyPageDao dao = MyPageDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<OrderVo> orderList = dao.getOrderList(pagenation, sq);
		
		close(con);
		return orderList;
	}
	public ReviewVo getReviewDetail(String sq) {
		MyPageDao dao = MyPageDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ReviewVo vo = dao.getReviewDetail(sq);
		
		close(con);
		return vo;
	}

	public boolean deleteReviewDetail(ReviewVo vo) {
		MyPageDao dao = MyPageDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		int count = dao.deleteReviewDetail(vo);
		boolean isSuccess = false;
		if (count > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	
	public void addReviewHit(String sq) {
		MyPageDao dao = MyPageDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.addReviewHit(sq);
		if(count > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return;
	}
	
	public boolean writeReview(ReviewVo vo) {
		MyPageDao dao = MyPageDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		int count = dao.writeReview(vo);
		boolean isSuccess = false;
		if (count > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}

		close(con);
		return isSuccess;
	}
	
	public ArrayList<ReviewVo> getReviewList(Pagenation pagenation, String query, String id) {
		MyPageDao dao = MyPageDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		ArrayList<ReviewVo> rar = dao.getReviewList(pagenation, query, id);

		close(con);
		return rar;
	}

	public int getReviewCount(String query) {
		MyPageDao dao = MyPageDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		int count = dao.getReviewCount(query);
		close(con);
		return count;
	}
}
