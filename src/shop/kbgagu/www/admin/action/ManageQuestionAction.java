package shop.kbgagu.www.admin.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.admin.common.LoginManager;
import shop.kbgagu.www.admin.service.AdminService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.Pagenation;
import shop.kbgagu.www.vo.QuestionVo;
import shop.kbgagu.www.common.RegExp;

import static shop.kbgagu.www.common.RegExp.REGEXP_NUMBER;

public class ManageQuestionAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 로그인 알고리즘
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sessionId = lm.getMemberId(session);

		// 로그인 확인
		if (sessionId == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 하십시오.');location.href='/views/admin/loginform.jsp';</script>");
			out.close();
			return null;
		}

		// 페이지 번호를 받아 정규식 검사후 정수형변환
		String pagenum = request.getParameter("pn");
		if (!RegExp.isValidExp(pagenum, REGEXP_NUMBER)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/admin/managequestion?pn=1';</script>");
			out.close();
			return null;
		}
		int page = Integer.parseInt(pagenum);
		// 첫페이지에서 -1되거나 사용자가 pn을 0이하로 입력해서 접근한 경우
		if (page < 1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/admin/managequestion?pn=1';</script>");
			out.close();
			return null;
		}

		// 검색기능 위해 남겨둠
		String query = "";

		AdminService svc = new AdminService();
		Pagenation pagenation = new Pagenation(page, svc.getQuestionCount(query));

		// 최대 페이지보다 큰 페이지 입력한 경우 최대 페이지로
		if (page > pagenation.getTotalPageCount()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/admin/managequestion?pn=" + pagenation.getTotalPageCount() + "';</script>");
			out.close();
			return null;
		}

		ArrayList<QuestionVo> qar = svc.getQuestionList(pagenation, query);

		request.setAttribute("pagenation", pagenation);
		request.setAttribute("qar", qar);
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/managequestion.jsp");
		return forward;
	}
}
