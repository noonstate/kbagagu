package shop.kbgagu.www.app.mypage.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.app.mypage.action.DeleteReviewDetailProcAction;
import shop.kbgagu.www.app.mypage.action.InsertQuestionAction;
import shop.kbgagu.www.app.mypage.action.LeaveAction;
import shop.kbgagu.www.app.mypage.action.LeaveProcAction;
import shop.kbgagu.www.app.mypage.action.ModifyMyInfoAction;
import shop.kbgagu.www.app.mypage.action.ModifyMyInfoProcAction;
import shop.kbgagu.www.app.mypage.action.ModifyProcPwdAction;
import shop.kbgagu.www.app.mypage.action.ModifyPwdAction;
import shop.kbgagu.www.app.mypage.action.MypageAction;
import shop.kbgagu.www.app.mypage.action.OrderAction;
import shop.kbgagu.www.app.mypage.action.OrderSttusChangeAction;
import shop.kbgagu.www.app.mypage.action.QuestionAction;
import shop.kbgagu.www.app.mypage.action.QuestionListAction;
import shop.kbgagu.www.app.mypage.action.QuestionRemoveAction;
import shop.kbgagu.www.app.mypage.action.ReviewAction;
import shop.kbgagu.www.app.mypage.action.ReviewDetailAction;
import shop.kbgagu.www.app.mypage.action.UpdateQuestionAction;
import shop.kbgagu.www.app.mypage.action.UpdateQuestionProcAction;
import shop.kbgagu.www.app.mypage.action.WriteReviewAction;
import shop.kbgagu.www.app.mypage.action.WriteReviewProcAction;
import shop.kbgagu.www.app.mypage.action.UserInfoAction;
import shop.kbgagu.www.common.Action;

@WebServlet("/mypage/*")
public class MypageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 도메인 뒤의 경로를 문자열로 저장하는 내장 매서드들
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length()).replaceAll("/mypage", "");

		ActionForward forward = null;

		if (command.equals("")) {
			Action action = new MypageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/order")) {
			Action action = new OrderAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/ordersttuschange")) {
			Action action = new OrderSttusChangeAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/questionlist")) {
			Action action = new QuestionListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/question")) {
			Action action = new QuestionAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/insertquestion")) {
			Action action = new InsertQuestionAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/questionremove")) {
			Action action = new QuestionRemoveAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/reviewlist")) { // 제품 리뷰 목록 페이지로 이동
			Action action = new ReviewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/writereview")) { // 제품 리뷰 작성 페이지로 이동
			Action action = new WriteReviewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/userinfo")) { // /views/mypage/mypageDetail.jsp로 이동
			Action action = new UserInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/modifymyinfo")) { // /views/mypage/modifyform.jsp으로 이동
			Action action = new ModifyMyInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/leaveform")) { // /views/mypage/leaveform.jsp으로 이동
			Action action = new LeaveAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/leaveproc")) {  // 탈퇴 알고리즘
			Action action = new LeaveProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/modifymyinfoproc")) {
			Action action = new ModifyMyInfoProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/ModifyPwd")) {
			Action action = new ModifyPwdAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/ModifyProcPwd")) {
			Action action = new ModifyProcPwdAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/updatequestion")) {
			Action action = new UpdateQuestionAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/updatequestionproc")) {
			Action action = new UpdateQuestionProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/writereviewproc")) {
			Action action = new WriteReviewProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/writereview")) {
			Action action = new WriteReviewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/reviewdetail")) {
			Action action = new ReviewDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/deletereviewdetailproc")) {
			Action action = new DeleteReviewDetailProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (forward != null) {
			if (forward.isRedirect()) {
				// 리다이렉스
				response.sendRedirect(forward.getPath());
			} else {
				// 디스패치
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
