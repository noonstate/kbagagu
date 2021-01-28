package shop.kbgagu.www.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.admin.common.LoginManager;
import shop.kbgagu.www.admin.service.AdminService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.vo.QuestionVo;
import shop.kbgagu.www.common.RegExp;

import static shop.kbgagu.www.common.RegExp.REGEXP_NUMBER;

public class UpdateAnswerAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로그인 알고리즘
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
		
		String sq = request.getParameter("sq");
		if (!RegExp.isValidExp(sq, REGEXP_NUMBER)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/admin/managequestion?sq=1';</script>");
			out.close();
			return null;
		}
		
		AdminService svc = new AdminService();
		
		QuestionVo vo= svc.getQuestionInfo(Integer.parseInt(sq));
		

		request.setAttribute("vo", vo);
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/updateanswerform.jsp");
		return forward;
	}
}
