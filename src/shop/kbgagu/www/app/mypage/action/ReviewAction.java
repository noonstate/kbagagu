package shop.kbgagu.www.app.mypage.action;

import static shop.kbgagu.www.common.RegExp.REGEXP_NUMBER;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.app.mypage.service.MyPageService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.LoginManager;
import shop.kbgagu.www.common.Pagenation;
import shop.kbgagu.www.common.RegExp;
import shop.kbgagu.www.vo.ReviewVo;

public class ReviewAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 로그인 상태 확인 알고리즘
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sessionId = lm.getMemberId(session);
		if (sessionId == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 되어있지 않습니다.');location.href='/';</script>");
			out.close();
			return null;
			// 비로그인상태. -> 홈으로
		}
		
		//페이지 번호를 받아 정규식 검사후 정수형변환
		String pagenum = request.getParameter("pn");
		if (!RegExp.isValidExp(pagenum, REGEXP_NUMBER)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/mypage/reviewlist?pn=1';</script>");
			out.close();
			return null;
		}
				
		int page = Integer.parseInt(pagenum);
		
		//첫페이지에서 -1되거나 사용자가 pn을 0이하로 입력해서 접근한 경우
		if (page < 1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/mypage/reviewlist?pn=1';</script>");
			out.close();
			return null;
		}

		String query = "";
		
		MyPageService svc = new MyPageService();
		Pagenation pagenation = new Pagenation(page, svc.getReviewCount(query));
		
		// 최대 페이지보다 큰 페이지 입력한 경우 최대 페이지로
		if (page > pagenation.getTotalPageCount()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/mypage/reviewlist??pn=" + pagenation.getTotalPageCount() + "';</script>");
			out.close();
			return null;
		}
		
		ArrayList<ReviewVo> rar = svc.getReviewList(pagenation, query, sessionId);
		
		// 리뷰폼으로 보내는 액션

		request.setAttribute("pagenation", pagenation);
		request.setAttribute("rar", rar);
		ActionForward forward = new ActionForward();
		forward.setPath("/views/mypage/reviewlist.jsp");
		return forward;
	}
}
