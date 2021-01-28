package shop.kbgagu.www.app.member.service;

import java.sql.Connection;
import java.util.ArrayList;

import shop.kbgagu.www.app.member.dao.MemberDao;
import shop.kbgagu.www.vo.MemberVo;

import static shop.kbgagu.www.common.JdbcUtil.*;

public class MemberService {

	public boolean joinMember(MemberVo vo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.joinMember(vo);
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
	
	public MemberVo loginInfo(String id) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		MemberVo vo = dao.loginInfo(id);

		close(con);
		return vo;
	}
	
	public ArrayList<String> findId(MemberVo vo){
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<String> arid = dao.findId(vo);
		return arid;
	}
	
	public MemberVo userInfo(int mber_sq) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		MemberVo vo = dao.userInfo(mber_sq);
		close(con);
		return vo;
	}
		public boolean checkMailId(MemberVo vo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.checkMailId(vo);
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
	
	public MemberVo getPwd(String id) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		MemberVo vo = dao.getPwd(id);

		close(con);
		return vo;
	}
	
		public boolean findpwd(MemberVo vo){
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.findPwd(vo);
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
	
	public boolean updatePwd(MemberVo vo){
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.updatePwd(vo);
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

	public boolean setLoginHistory(String id) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.setLoginHistory(id);
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

	public boolean setLogoutHistory(String sessionId) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.setLogoutHistory(sessionId);
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
