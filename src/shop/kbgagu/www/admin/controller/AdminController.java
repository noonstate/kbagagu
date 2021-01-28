package shop.kbgagu.www.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.admin.action.AdminAction;
import shop.kbgagu.www.admin.action.InsertItemAction;
import shop.kbgagu.www.admin.action.InsertItemProcAction;
import shop.kbgagu.www.admin.action.InsertNoticeAction;
import shop.kbgagu.www.admin.action.InsertNoticeProcAction;
import shop.kbgagu.www.admin.action.LoginProcAction;
import shop.kbgagu.www.admin.action.LogoutProcAction;
import shop.kbgagu.www.admin.action.ManageChangeAction;
import shop.kbgagu.www.admin.action.ManageItemAction;
import shop.kbgagu.www.admin.action.ManageMemberAction;
import shop.kbgagu.www.admin.action.ManageOrderAction;
import shop.kbgagu.www.admin.action.ManageQuestionAction;
import shop.kbgagu.www.admin.action.ManagenoticeAction;
import shop.kbgagu.www.admin.action.UpdateAnswerAction;
import shop.kbgagu.www.admin.action.UpdateAnswerProcAction;
import shop.kbgagu.www.admin.action.ManageReviewAction;
import shop.kbgagu.www.admin.action.DeleteItemProcAction;
import shop.kbgagu.www.admin.action.DeleteNoticeAction;
import shop.kbgagu.www.admin.action.DeleteNoticeProcAction;
import shop.kbgagu.www.admin.action.UpdateItemAction;
import shop.kbgagu.www.admin.action.UpdateItemProcAction;
import shop.kbgagu.www.admin.action.UpdateMemberAction;
import shop.kbgagu.www.admin.action.UpdateNoticeAction;
import shop.kbgagu.www.admin.action.UpdateNoticeProcAction;
import shop.kbgagu.www.admin.action.UpdateReviewAction;
import shop.kbgagu.www.admin.action.UpdateSttusAction;
import shop.kbgagu.www.common.Action;

@WebServlet("/admin/*")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 도메인 뒤의 경로를 문자열로 저장하는 내장 매서드들
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length()).replaceAll("/admin", "");

		ActionForward forward = null;

		if (command.equals("")) {
			Action action = new AdminAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/loginproc")) {
			Action action = new LoginProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/logoutproc")) {
			Action action = new LogoutProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/managemember")) {
			Action action = new ManageMemberAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/manageitem")) {
			Action action = new ManageItemAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/insertitem")) {
			Action action = new InsertItemAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/insertitemproc")) {
			Action action = new InsertItemProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/deleteitemproc")) { // 목록에서 상품 삭제 검증 알고리즘
			Action action = new DeleteItemProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/updateitem")) {
			Action action = new UpdateItemAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/updateitemproc")) {
			Action action = new UpdateItemProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/updatemember")) {
			Action action = new UpdateMemberAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/managenotice")) {
			Action action = new ManagenoticeAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/insertnotice")) {
			Action action = new InsertNoticeAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/insertnoticeproc")) {
			Action action = new InsertNoticeProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/updatenotice")) {
			Action action = new UpdateNoticeAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/updatenoticeproc")) {
			Action action = new UpdateNoticeProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/deletenotice")) {
			Action action = new DeleteNoticeAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/deletenoticeproc")) {
			Action action = new DeleteNoticeProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/manageorder")) {
			Action action = new ManageOrderAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/managequestion")) {
			Action action = new ManageQuestionAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/managereview")) {
			Action action = new ManageReviewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/updateanswer")) {
			Action action = new UpdateAnswerAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/updateanswerproc")) {
			Action action = new UpdateAnswerProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/managechange")) {
			Action action = new ManageChangeAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/updatesttus")) {
			Action action = new UpdateSttusAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/updatereview")) {
			Action action = new UpdateReviewAction();
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
