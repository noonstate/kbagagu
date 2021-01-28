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


public class DeleteNoticeProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sessionId = lm.getMemberId(session);
		
		if(!sessionId.equals("1")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근');location.href='/'</script>");
			out.close();
			return null;
		}
		
		String[] checkedSq = request.getParameterValues("checkedSq");
		int[] sqList = new int[checkedSq.length];
		for(int i = 0;i<checkedSq.length;i++) { //받아온 sq리스트 데이터 체크
			if (!RegExp.isValidExp(checkedSq[i], REGEXP_NUMBER)) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>location.href='/admin/managenotice?pn=1';</script>");
				out.close();
				return null;
			}
			sqList[i] =  Integer.parseInt(checkedSq[i]);			
		}
		
		
		AdminService svc = new AdminService();
		if(!svc.deleteNotices(sqList)) {
			response.setContentType("text/html;charset=UTF-8"); 
			PrintWriter out = response.getWriter();
			out.println("<script>alert('공지글 삭제에 실패하였습니다.');history.back();</script>");
			out.close(); 
			return null; 
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/admin/managenotice?pn=1");
		return forward;
	}
}
