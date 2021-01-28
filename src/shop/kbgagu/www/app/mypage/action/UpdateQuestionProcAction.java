package shop.kbgagu.www.app.mypage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.app.mypage.service.MyPageService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.LoginManager;
import shop.kbgagu.www.common.Paser;
import shop.kbgagu.www.vo.QuestionVo;


public class UpdateQuestionProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sessionId = lm.getMemberId(session);
		
		if(sessionId == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 해주세요');location.href='/member/loginform'</script>");
			out.close();
			return null;
		}
		String sq = request.getParameter("sq");
		String ques_title = request.getParameter("ques_title");
		String ques_cntnt = request.getParameter("ques_cntnt");
		
		QuestionVo vo = new QuestionVo();
		vo.setQues_sq(Integer.parseInt(sq));
		vo.setQues_title(Paser.chgToStr(ques_title));
		vo.setQues_cntnt(Paser.chgToStr(ques_cntnt));
		
		MyPageService svc = new MyPageService();
		
		if (!svc.updateQuestion(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('문의 등록에 실패했습니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/mypage/questionlist");
		return forward;

	}
}

