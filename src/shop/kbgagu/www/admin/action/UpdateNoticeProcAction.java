package shop.kbgagu.www.admin.action;

import static shop.kbgagu.www.common.RegExp.REGEXP_NUMBER;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.admin.common.LoginManager;
import shop.kbgagu.www.admin.service.AdminService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.RegExp;
import shop.kbgagu.www.vo.NoticeVo;
import shop.kbgagu.www.common.Paser;

public class UpdateNoticeProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sessionId = lm.getMemberId(session);

		if (sessionId == null || !sessionId.equals("1")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근');location.href='/'</script>");
			out.close();
			return null;
		}

		String noticeTitle = request.getParameter("notice_title");
		String noticeCtgry = request.getParameter("notice_ctgry");
		String content = request.getParameter("content");
		String noticeSq = request.getParameter("notice_sq");

		// 유효성 검사
		if (noticeTitle == null || noticeTitle.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');history.back();</script>");
			out.close();
			return null;
		}
		if (noticeCtgry == null || noticeCtgry.equals("") || !RegExp.isValidExp(noticeCtgry, REGEXP_NUMBER)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');history.back();</script>");
			out.close();
			return null;
		}
		if (noticeSq == null || noticeSq.equals("") || !RegExp.isValidExp(noticeSq, REGEXP_NUMBER)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');history.back();</script>");
			out.close();
			return null;
		}
		if (content == null || content.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');history.back();</script>");
			out.close();
			return null;
		}

		int sq = Integer.parseInt(sessionId);

		NoticeVo vo = new NoticeVo();
		vo.setNotice_sq(Integer.parseInt(noticeSq));
		vo.setNotice_title(noticeTitle);
		vo.setAdmin_sq(sq);
		vo.setNotice_ctgry(Integer.parseInt(noticeCtgry));
		vo.setNotice_cntnt(Paser.chgToStr(content));

		AdminService svc = new AdminService();

		if (!svc.updateNotice(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('상품정보수정에 실패하였습니다.');history.back();</script>");
			out.close();
			return null;
		}

		ActionForward forward = new ActionForward();
		forward.setPath("/admin/managenotice?pn=1");
		return forward;
	}
}
