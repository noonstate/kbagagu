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

public class ReviewDetailAction implements Action { // 리뷰 목록 글 클릭하면 상품리뷰상세페이지 나오는 액션
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 로그인 여부 확인 알고리즘

		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sessionSq = lm.getMemberId(session);

		// 로그인 여부 검사
		if (sessionSq == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요한 페이지입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		String sq = request.getParameter("sq");
		

		// 유효성 검사
		if (!RegExp.isValidExp(sq, REGEXP_NUMBER)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/'</script>");
			out.close();
			return null;
		}

		ReviewVo vo = new ReviewVo();
		
		// vo에서 받는 리뷰글번호 set하기
		vo.setReview_sq(Integer.parseInt(sq));
		
		// 서비스 객체 생성
		MyPageService svc = new MyPageService();
		
		vo = svc.getReviewDetail(sq);
		svc.addReviewHit(sq);
		
		
		request.setAttribute("vo", vo);

		ActionForward forward = new ActionForward();
		forward.setPath("/views/mypage/reviewdetail.jsp");
		return forward;
	}
}
