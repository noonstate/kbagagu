package shop.kbgagu.www.admin.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.admin.common.LoginManager;
import shop.kbgagu.www.admin.service.AdminService;
import shop.kbgagu.www.admin.statistics.VisitVo;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;


public class AdminAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로그인 알고리즘
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sessionId = lm.getMemberId(session);
				
		// 로그인 확인
		if (sessionId == null) {
			ActionForward forward = new ActionForward();
			forward.setPath("/views/admin/loginform.jsp");
			forward.setRedirect(true);
			return forward;
		}
		
		AdminService svc = new AdminService();
		
		// 메인화면에 뿌려줄 데이터
		
		ArrayList<VisitVo> avs = svc.getVisitStatics();
		request.setAttribute("avs", avs);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/admin.jsp");
		return forward;
	}
}
