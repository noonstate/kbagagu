package shop.kbgagu.www.app.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.app.member.action.FindIdAction;
import shop.kbgagu.www.app.member.action.FindIdProcAction;
import shop.kbgagu.www.app.member.action.FindPwdAction;
import shop.kbgagu.www.app.member.action.FindPwdProcAction;
import shop.kbgagu.www.app.member.action.JoinAction;
import shop.kbgagu.www.app.member.action.JoinProcAction;
import shop.kbgagu.www.app.member.action.LoginAction;
import shop.kbgagu.www.app.member.action.LoginProcAction;
import shop.kbgagu.www.app.member.action.LogoutProcAction;
import shop.kbgagu.www.app.member.action.PolicyAction;
import shop.kbgagu.www.app.member.action.UpdatePwdProcAction;
import shop.kbgagu.www.common.Action;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//도메인 뒤의 경로를 문자열로 저장하는 내장 매서드들
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length()).replaceAll("/member","");

		ActionForward forward = null;

		if (command.equals("/joinform")) { // 약관동의 클릭시 가입 폼 페이지 이동
			Action action = new JoinAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} else if (command.equals("/joinproc")) { //가입폼에서 양식 채우고 가입하기 클릭시 가입실행하는 액션으로 보냄
			Action action = new JoinProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/loginform")) {  // 로그인폼으로 
			Action action = new LoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/loginproc")) {   // 로그인 검증 알고리즘
			Action action = new LoginProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}  else if (command.equals("/logoutproc")) {
			Action action = new LogoutProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/findid")) { // 아이디 찾기 양식view단 으로 보냄
			Action action = new FindIdAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/findidproc")) { // 아이디 DB에서 조회해서 view단에 뿌려줌
			Action action = new FindIdProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/findpwd")) { //비밀번호 찾기 양식으로 보냄
			Action action = new FindPwdAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/findpwdproc")) { // DB에서 조회후 해당 정보 맞을때만 재설정 폼으로 보내줌
			Action action = new FindPwdProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/findpwdproc")) {
			Action action = new FindPwdProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/updatepwdproc")) {
			Action action = new UpdatePwdProcAction();
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
