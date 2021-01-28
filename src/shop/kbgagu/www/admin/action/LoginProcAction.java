package shop.kbgagu.www.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.admin.common.LoginManager;
import shop.kbgagu.www.admin.service.AdminService;
import shop.kbgagu.www.admin.vo.AdminVo;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;

public class LoginProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로그인 알고리즘
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sessionId = lm.getMemberId(session);
				
		// 로그인 확인
		if (sessionId != null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 된 상태입니다');location.href='/admin/';</script>");
			out.close();
			return null;
		}
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		AdminService svc = new AdminService();
		AdminVo vo = svc.loginInfo(id);
		
		if(pwd == null || !pwd.equals(vo.getAdmin_pwd())) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		// 세션에 sq값을 저장
		lm.setSession(session, String.valueOf(vo.getAdmin_sq()));
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/admin");
		return forward;
	}
}
