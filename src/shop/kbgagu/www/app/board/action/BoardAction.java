package shop.kbgagu.www.app.board.action;

import static shop.kbgagu.www.common.RegExp.REGEXP_NUMBER;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.kbgagu.www.app.board.service.BoardService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.Pagenation;
import shop.kbgagu.www.common.RegExp;
import shop.kbgagu.www.vo.NoticeVo;

public class BoardAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 페이지 번호를 받아 정규식 검사후 정수형변환
		String pagenum = request.getParameter("pn");
		if (!RegExp.isValidExp(pagenum, REGEXP_NUMBER)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/board/list?pn=1';</script>");
			out.close();
			return null;
		}
		int page = Integer.parseInt(pagenum);
		// 첫페이지에서 -1되거나 사용자가 pn을 0이하로 입력해서 접근한 경우
		if (page < 1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/board/list?pn=1';</script>");
			out.close();
			return null;
		}

		// 검색 위해 남겨둠
		String query = "";

		BoardService svc = new BoardService();
		Pagenation pagenation = new Pagenation(page, svc.getNoticeCount(query));

		// 최대 페이지보다 큰 페이지 입력한 경우 최대 페이지로
		if (page > pagenation.getTotalPageCount()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/board/list?pn= "+ pagenation.getTotalPageCount() +"';</script>");
			out.close();
			return null;
		}

		ArrayList<NoticeVo> nar = svc.getNoticeList(pagenation);
		
		request.setAttribute("pagenation", pagenation);
		request.setAttribute("nar", nar);
		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/noticelist.jsp");
		return forward;
	}
}
