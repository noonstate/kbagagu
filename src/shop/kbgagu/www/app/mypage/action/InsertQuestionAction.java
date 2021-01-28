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

public class InsertQuestionAction implements Action {
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
			out.println("<script>alert('로그인 하십시오');history.back();</script>");
			out.close();
			return null;
		}
		

		String ques_title = request.getParameter("ques_title");
		String ques_cntnt = request.getParameter("ques_cntnt");
		
		QuestionVo vo = new QuestionVo();
		
		if(ques_title == null || ques_title.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('제목을 입력해주세요.');history.back();</script>");
			out.close();
			return null;
		}
		if(ques_cntnt == null || ques_cntnt.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('내용을 입력해주세요.');history.back();</script>");
			out.close();
			return null;
		}
		
		vo.setMber_sq(Integer.parseInt(sessionId));
		vo.setQues_title(Paser.chgToStr(ques_title));
		vo.setQues_cntnt(Paser.chgToStr(ques_cntnt));
		
		MyPageService svc = new MyPageService();
		
		if (!svc.insertQuestion(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('문의등록에 실패하였습니다.');history.back();</script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/mypage/questionlist");
		return forward;
	}
}
