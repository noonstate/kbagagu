package shop.kbgagu.www.app.member.action;

import static shop.kbgagu.www.common.RegExp.REGEXP_NAME;
import static shop.kbgagu.www.common.RegExp.REGEXP_TELNUMBER;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.kbgagu.www.app.member.service.MemberService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.LoginManager;
import shop.kbgagu.www.common.RegExp;
import shop.kbgagu.www.vo.MemberVo;

public class FindIdProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 로그인 여부 체크
		LoginManager lm = LoginManager.getInstance();
		HttpSession session = request.getSession();
		String sessionId = lm.getMemberId(session);
		if (sessionId != null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		// 데이터 로드
		String nm = request.getParameter("nm");
		String tel = request.getParameter("tel");

		// 데이터 체크
		if (!RegExp.isValidExp(nm, REGEXP_NAME) || !RegExp.isValidExp(tel, REGEXP_TELNUMBER)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		// 맞으면 보에 담아서
		MemberVo vo = new MemberVo();
		vo.setNm(nm);
		vo.setTel(tel);

		// 서비스에서 처리 결과 리스트로 받아옴
		MemberService svc = new MemberService();
		// 디비에서 아이디 리스트로 받아서 저장
		ArrayList<String> arSource = svc.findId(vo);
		// 암호화 후 보낼 리스트
		ArrayList<String> arid = new ArrayList<String>();

		// 아이디 중간에 *삽입하는 알고리즘
		for (int i = 0; i < arSource.size(); i++) {
			String source = arSource.get(i);

			// 먼저 @ 의 인덱스를 찾는다
			int idx = source.indexOf("@");

			// substring은 첫번째 지정한 인덱스는 포함하지 않는다.
			// @ 앞부분을 추출
			// 왼쪽은 처리할거라 처리전을 before로 설정
			String leftbefore = source.substring(0, idx);

			// 뒷부분을 추출
			String right = source.substring(idx + 1);

			// 왼쪽(일부 가리기처리 하는 부분)
			String left = "";
			// 1~2번째 글자는 그대로
			for (int j = 0; j < 2; j++) {
				left = left + leftbefore.charAt(j);
			}
			// 3~5번째 별삽입
			for (int j = 2; j < 5; j++) {
				left = left + "*";
			}
			// 6~마지막 글자는 다시 그대로
			for (int j = 5; j < leftbefore.length(); j++) {
				left = left + leftbefore.charAt(j);
			}
			// 어레이리스트에 숨긴 문자열 + @ + 그대로 붙일 오른쪽 문자열담음
			arid.add(left + "@" + right);
		}

		request.setAttribute("arid", arid);

		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/findedId.jsp");
		return forward;
	}
}
