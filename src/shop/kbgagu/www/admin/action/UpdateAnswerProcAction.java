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


public class UpdateAnswerProcAction implements Action {
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
		String answer = request.getParameter("answer");
		
		QuestionVo vo = new QuestionVo();
		vo.setAdmin_sq(Integer.parseInt(sessionId));
		vo.setQues_sq(Integer.parseInt(sq));
		vo.setAnswer(answer);
		
		AdminService svc = new AdminService();
		
		if(!svc.updateAnswer(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('답변실패');location.href='/admin/managequestion?sq=1';</script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/admin/updateanswer");
		return forward;
	}
}
