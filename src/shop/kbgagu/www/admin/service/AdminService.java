package shop.kbgagu.www.admin.service;

import java.sql.Connection;
import java.util.ArrayList;

import shop.kbgagu.www.admin.dao.AdminDao;
import shop.kbgagu.www.admin.statistics.VisitVo;
import shop.kbgagu.www.admin.vo.AdminVo;
import shop.kbgagu.www.common.Pagenation;
import shop.kbgagu.www.vo.ItemVo;
import shop.kbgagu.www.vo.MemberVo;
import shop.kbgagu.www.vo.NoticeVo;
import shop.kbgagu.www.vo.OrderVo;
import shop.kbgagu.www.vo.QuestionVo;
import shop.kbgagu.www.vo.ReviewVo;

import static shop.kbgagu.www.common.JdbcUtil.*;

public class AdminService {
	
	public AdminVo loginInfo(String id) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		AdminVo vo = dao.loginInfo(id);
		
		close(con);
		return vo;
		
	}
	
	public int getMemberCount(String query) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.getMemberCount(query);
		close(con);
		return count;
	}

	public MemberVo getMemberInfo(String mberSq) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
	
		MemberVo vo = dao.getMemberInfo(mberSq);
		
		close(con);
		return vo;
	}

	public ArrayList<MemberVo> getMemberList(Pagenation pagenation,String query) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<MemberVo> mar = dao.getMemberList(pagenation,query);
		
		close(con);
		return mar;
	}

	public boolean insertItem(ItemVo vo) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.insertItem(vo);
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

	public ArrayList<ItemVo> getItemList(Pagenation pagenation,String query) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<ItemVo> iar = dao.getItemList(pagenation,query);
		
		close(con);
		return iar;
	}
	public int getItemCount(String query) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.getItemCount(query);
		close(con);
		return count;
	}
	
	public ItemVo getImgPath(int itemsq) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ItemVo vo = dao.getImgPath(itemsq);
		close(con);
		return vo;
	}
	
	public ArrayList<NoticeVo> getNoticeList(Pagenation pagenation,String query) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<NoticeVo> nar = dao.getNoticeList(pagenation,query);
		
		close(con);
		return nar;
	}
	
	public int getNoticeCount(String query) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.getNoticeCount(query);
		close(con);
		return count;
	}
	
	public ItemVo getItemInfo(String itemSq) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
	
		ItemVo vo = dao.getItemInfo(itemSq);
		
		close(con);
		return vo;
	}
	
	public boolean updateItem(ItemVo vo) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.updateItem(vo);
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
	
	public boolean deleteItem(int sq) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.deleteItem(sq);
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
	
	public boolean deleteNotices(int[] sqList) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.deleteNotices(sqList);
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
	
	public int getOrderCount() {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.getOrderCount();
		
		close(con);
		return count;
	}
	
	public int getOrderChangeCount() {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.getOrderChangeCount();
		
		close(con);
		return count;
	}
	
	public ArrayList<OrderVo> getChangeOrderList(Pagenation pagenation) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<OrderVo> orderList = dao.getChangeOrderList(pagenation);
		
		close(con);
		return orderList;
	}
	
	public ArrayList<OrderVo> getChangeOrderList(Pagenation pagenation, int category) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<OrderVo> orderList = dao.getChangeOrderList(pagenation, category);
		
		close(con);
		return orderList;
	}
	
	public ArrayList<OrderVo> getOrderList(Pagenation pagenation) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<OrderVo> orderList = dao.getOrderList(pagenation);
		
		close(con);
		return orderList;
	}
	//overloding
	public ArrayList<OrderVo> getOrderList(Pagenation pagenation, String orderCategory) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<OrderVo> orderList = dao.getOrderList(pagenation, orderCategory);
		
		close(con);
		return orderList;
	}
	
	public ArrayList<OrderVo> getOrderList(Pagenation pagenation, String orderCategory, String payment) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<OrderVo> orderList = dao.getOrderList(pagenation, orderCategory, payment);
		
		close(con);
		return orderList;
	}
	
	public ArrayList<OrderVo> getOrderPaymentList(Pagenation pagenation, String payment) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<OrderVo> orderList = dao.getOrderPaymentList(pagenation, payment);
		
		close(con);
		return orderList;
	}
	
	public boolean updateOrderSttus(OrderVo vo) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.updateOrderSttus(vo);
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
	
	public NoticeVo getNoticeInfo(int sq) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		NoticeVo vo = dao.getNoticeInfo(sq);
		
		close(con);
		return vo;
	}

	public boolean insertNotice(NoticeVo vo) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.insertNotice(vo);
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

	public boolean updateNotice(NoticeVo vo) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.updateNotice(vo);
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

	public int getQuestionCount(String query) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.getQuestionCount(query);
		close(con);
		return count;
	}

	public ArrayList<QuestionVo> getQuestionList(Pagenation pagenation,String query) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<QuestionVo> qar = dao.getQuestionList(pagenation,query);
		
		close(con);
		return qar;
	}

	public QuestionVo getQuestionInfo(int sq) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		QuestionVo vo = dao.getQuestionInfo(sq);
		
		close(con);
		return vo;
		
	}

	public boolean updateAnswer(QuestionVo vo) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		boolean isSuccess = false;
		int count = dao.updateAnswer(vo);
		
		if (count > 0) {
			isSuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
		
	}

	public int getReviewCount(String query) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.getReviewCount(query);
		close(con);
		return count;
	}

	public ArrayList<ReviewVo> getReviewList(Pagenation pagenation,String query) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<ReviewVo> rar = dao.getReviewList(pagenation,query);
		
		close(con);
		return rar;
	}
	
	public boolean deleteReivew(int sq) {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSuccess = true;
		
		int count = dao.deleteReivew(sq);
		if (count > 0) {
			commit(con);
	
		} else {
			rollback(con);
			isSuccess = false;
		}
		close(con);
		return isSuccess;
	}

	public ArrayList<VisitVo> getVisitStatics() {
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<VisitVo> avs = dao.getVisitStatics();
		
		close(con);
		return avs;
	}
	
}
