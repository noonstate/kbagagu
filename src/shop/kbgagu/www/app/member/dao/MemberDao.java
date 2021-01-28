package shop.kbgagu.www.app.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.kbgagu.www.vo.MemberVo;

import static shop.kbgagu.www.common.JdbcUtil.close;

public class MemberDao {
	private Connection con;

	private MemberDao() {
	}

	private static class LazyHolder {
		private static final MemberDao INSTANCE = new MemberDao();
	}

	public static MemberDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public int joinMember(MemberVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		String SQL = "insert into inf_mber_tb(id, pwd, nm, tel) values(?,?,?,?)";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getNm());
			pstmt.setString(4, vo.getTel());
			count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public MemberVo loginInfo(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo vo = null;
		String SQL = "select mber_sq, del_fl, pwd from inf_mber_tb where id =?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new MemberVo();
				vo.setMber_sq(rs.getInt("mber_sq"));
				vo.setDel_fl(rs.getBoolean("del_fl"));
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

	public ArrayList<String> findId(MemberVo vo) {
		ArrayList<String> arid = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "select id from inf_mber_tb where tel=? and nm = ?";

		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, vo.getTel());
			pstmt.setString(2, vo.getNm());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String temp = rs.getString("id");
				System.out.println(temp);
				arid.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return arid;
	}

	public MemberVo userInfo(int mber_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo vo = null;

		try {
			pstmt = con.prepareStatement("SELECT nm,id,tel FROM inf_mber_tb where mber_sq = ?");
			pstmt.setInt(1, mber_sq);
			rs = pstmt.executeQuery(); //rs에 쿼리실행결과 담기  
			while (rs.next()) {
				vo = new MemberVo();  //vo에 rs결과를 담기
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

	public int checkMailId(MemberVo vo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String SQL = "select count(*) from inf_mber_tb where id = ?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, vo.getId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return count;
	}

	public int findPwd(MemberVo vo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String SQL = "select count(*) from inf_mber_tb where id = ? and nm = ? and tel = ?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getNm());
			pstmt.setString(3, vo.getTel());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count(*)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public MemberVo getPwd(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo vo = null;
		
		String SQL = "select count(*), pwd from inf_mber_tb where id = ?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("count(*)") != 0) {
					vo = new MemberVo();
					vo.setPwd(rs.getString("pwd"));
				} else {
					vo = new MemberVo();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return vo;
	}

	public int updatePwd(MemberVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		String SQL = "update inf_mber_tb set pwd = ? where id=? and nm =? and tel = ?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getNm());
			pstmt.setString(4, vo.getTel());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public int setLoginHistory(String id) {
		PreparedStatement pstmt = null;
		int count = 0;
		String SQL = "insert into hist_lgn_tb (mber_sq)"
				+ " VALUES((select mber_sq from inf_mber_tb where id = ?))";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, id);
			count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public int setLogoutHistory(String sessionId) {
		PreparedStatement pstmt = null;
		int count = 0;
		String SQL = "insert into hist_lgn_tb (mber_sq, sttus) VALUES(?, ?)";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, sessionId);
			pstmt.setBoolean(2, true);
			count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

}
