package shop.kbgagu.www.app.mypage.action;

import static shop.kbgagu.www.common.RegExp.REGEXP_NUMBER;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.app.mypage.service.MyPageService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.LoginManager;
import shop.kbgagu.www.common.RegExp;
import shop.kbgagu.www.vo.ReviewVo;

public class WriteReviewProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 로그인 여부 확인 알고리즘

		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sessionId = lm.getMemberId(session);

		if (sessionId == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요한 서비스입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		String sq = request.getParameter("sq");
		String review_title = request.getParameter("title");
		String review_cntnt = request.getParameter("cntnt");
		String star = request.getParameter("star-input");
		
		
		if(!RegExp.isValidExp(sq, REGEXP_NUMBER)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.11');location.href='/'</script>");
		}
		
		if(!RegExp.isValidExp(star, REGEXP_NUMBER)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.22');history.back();</script>");
		}
		
		int star_rating = Integer.parseInt(star);
		int item_sq = Integer.parseInt(sq);
		int mber_sq = Integer.parseInt(sessionId);
		
		ReviewVo vo = new ReviewVo();
		
		vo.setItem_sq(item_sq);
		vo.setMber_sq(mber_sq);
		vo.setStar_rating(star_rating);
		vo.setReview_title(review_title);
		vo.setReview_cntnt(review_cntnt);
		
		MyPageService svc = new MyPageService();
		
		if(!svc.writeReview(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('변경실패');history.back();</script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/mypage/reviewlist");
		forward.setRedirect(true);
		return forward;
	}
}
