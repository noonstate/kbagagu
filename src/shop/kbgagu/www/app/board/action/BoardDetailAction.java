package shop.kbgagu.www.app.board.action;

import static shop.kbgagu.www.common.RegExp.REGEXP_NUMBER;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.kbgagu.www.app.board.service.BoardService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.RegExp;
import shop.kbgagu.www.vo.NoticeVo;

public class BoardDetailAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String sq = request.getParameter("sq");
		
		if (!RegExp.isValidExp(sq, REGEXP_NUMBER)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근');location.href='/';</script>");
			out.close();
			return null;
		}
		
		BoardService svc = new BoardService();
		
		NoticeVo vo  = svc.getNoticeDetail(Integer.parseInt(sq));
		
		if(vo==null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('게시물정보를 불러오는데 실패했습니다.');history.back();</script>");
			out.close();
			return null;
		}
		
		
		
		// 조회수구문
		
		//나중에 중복 조회 안되게 할때 추가
		//String ip = request.getRemoteAddr();
		
		if(!svc.updateNoticeHit(Integer.parseInt(sq))) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("history.back();</script>");
			out.close();
			return null;
		}
		
		request.setAttribute("vo", vo);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/noticedetail.jsp");
		return forward;
	}
}
