package shop.kbgagu.www.app.board.service;

import static shop.kbgagu.www.common.JdbcUtil.getConnection;
import static shop.kbgagu.www.common.JdbcUtil.rollback;
import static shop.kbgagu.www.common.JdbcUtil.close;
import static shop.kbgagu.www.common.JdbcUtil.commit;

import java.sql.Connection;
import java.util.ArrayList;

import shop.kbgagu.www.app.board.dao.BoardDao;
import shop.kbgagu.www.common.Pagenation;
import shop.kbgagu.www.vo.NoticeVo;

public class BoardService {
	
	public ArrayList<NoticeVo> getNoticeList(Pagenation pagenation){
		
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<NoticeVo> nar = dao.getNoticeList(pagenation);
		
		close(con);
		return nar;
		
	}
	
	public int getNoticeCount(String query) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.getNoticeCount(query);
		close(con);
		return count;
	}
	
	public NoticeVo getNoticeDetail(int sq) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		NoticeVo vo = dao.getNoticeDetail(sq);
		close(con);
		return vo;
	}
	
	public boolean isDuplicatedNoticeHist(String ip) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		boolean duplicated = false;
		if(!dao.isDuplicatedNoticeHist(ip)) {
			duplicated = true;
		}
		close(con);
		return duplicated;
	}
	
	public boolean updateNoticeHit(int sq) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		if(dao.updateNoticeHit(sq)==0) {
			rollback(con);
			close(con);
			return false;
		}else{
			commit(con);
			close(con);
			return true;
		}
	}
}
