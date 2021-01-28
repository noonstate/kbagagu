package shop.kbgagu.www.admin.action;

import java.io.PrintWriter;
import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import shop.kbgagu.www.admin.common.LoginManager;
import shop.kbgagu.www.admin.service.AdminService;
import shop.kbgagu.www.common.Action;
import shop.kbgagu.www.common.ActionForward;
import shop.kbgagu.www.common.ImegeProcess;
import shop.kbgagu.www.common.Paser;
import shop.kbgagu.www.vo.ItemVo;

public class UpdateItemProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String sessionId = lm.getMemberId(session);

		if (sessionId == null || !sessionId.equals("1")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근');location.href='/'</script>");
			out.close();
			return null;
		}
		// 파일 업로드 처리
		String savePath = request.getSession().getServletContext().getRealPath("/upload"); // 업로드 물리적경로
		MultipartRequest multi = null;
		String encoding = "UTF-8";
		int maxSize = 10 * 1024 * 1024; // 파일사이즈는 10MB

		File file = new File(savePath); // 실제주소에 폴더가 없는경우 생성
		if (!file.exists()) {
			file.mkdir();
		}

		// 실질적 파일업로드 처리영역
		try {
			multi = new MultipartRequest(request, savePath, maxSize, encoding, new DefaultFileRenamePolicy());

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 물리적인 경로를 String에 저장
		String img_n0 = multi.getFilesystemName("file[0]");
		String img_n1 = multi.getFilesystemName("file[1]");
		String img_n2 = multi.getFilesystemName("file[2]");
		String img_n3 = multi.getFilesystemName("file[3]");
		String img_n4 = multi.getFilesystemName("file[4]");

		// 이미지가 맞는지 검증 (.jpg, .png, .gif)
		if (img_n0 != null && !ImegeProcess.isImege(multi.getOriginalFileName("file[0]"))) {
			File f = new File("/upload/" + multi.getFilesystemName("file[0]"));
			f.delete();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근');location.href='/'</script>");
			out.close();
			return null;
		}

		String item_sq = multi.getParameter("sq");
		String item_nm = multi.getParameter("item_nm");
		String ctgry = multi.getParameter("ctgry");
		String new_fl = multi.getParameter("new_fl");
		String upvote_fl = multi.getParameter("upvote_fl");
		String show_fl = multi.getParameter("show_fl");
		String price = multi.getParameter("price");
		String invntry = multi.getParameter("invntry");
		String sale_fl = multi.getParameter("sale_fl");
		String sale_rate = multi.getParameter("sale_rate");
		String content = multi.getParameter("content");

		/*
		 * 받은 데이터 검증해야함 if (!RegExp.isValidExp(item_nm, REGEXP_NUMBER) ||
		 * !RegExp.isValidExp(price, REGEXP_NUMBER) || !RegExp.isValidExp(invntry,
		 * REGEXP_NUMBER) || !RegExp.isValidExp(sale_rate, REGEXP_NUMBER) ||
		 * Integer.parseInt(sale_rate) > 100 ) {
		 * 
		 * }
		 */

		ItemVo vo = new ItemVo();

		vo.setItem_sq(Integer.parseInt(item_sq));
		vo.setItem_nm(item_nm);
		vo.setCtgry(Integer.parseInt(ctgry));
		if (new_fl != null && new_fl.equals("true")) { // 신제품 체크박스판정
			vo.setNew_fl(true);
		} else {
			vo.setNew_fl(false);
		}
		if (upvote_fl != null && upvote_fl.equals("true")) { // 추천여부 체크박스판정
			vo.setUpvote_fl(true);
		} else {
			vo.setUpvote_fl(false);
		}
		if (show_fl != null && show_fl.equals("true")) { // 노출여부 체크박스판정
			vo.setShow_fl(true);
		} else {
			vo.setShow_fl(false);
		}
		if (sale_fl != null && sale_fl.equals("true")) { // 할인여부 체크박스판정
			vo.setSale_fl(true);
		} else {
			vo.setSale_fl(false);
		}

		vo.setPrice(Integer.parseInt(price));
		vo.setInvntry(Integer.parseInt(invntry));
		if (sale_rate == null) {
			vo.setSale_rate(0);
		} else {
			vo.setSale_rate(Integer.parseInt(sale_rate));
		}
		AdminService svc = new AdminService();

		ItemVo img = svc.getImgPath(vo.getItem_sq()); //이미 있는 이미지이면 넣을 필요없음

		if (img_n0 != null) {
			vo.setImg_n0("/upload/" + img_n0);
		} else {
			vo.setImg_n0(img.getImg_n0());
		}
		if (img_n1 != null) {
			vo.setImg_n1("/upload/" + img_n1);
		} else {
			vo.setImg_n1(img.getImg_n1());
		}
		if (img_n2 != null) {
			vo.setImg_n2("/upload/" + img_n2);
		} else {
			vo.setImg_n2(img.getImg_n2());
		}
		if (img_n3 != null) {
			vo.setImg_n3("/upload/" + img_n3);
		} else {
			vo.setImg_n3(img.getImg_n3());
		}
		if (img_n4 != null) {
			vo.setImg_n4("/upload/" + img_n4);
		} else {
			vo.setImg_n4(img.getImg_n4());
		}

		vo.setDetail(Paser.chgToStr(content));

		if (!svc.updateItem(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('정보수정에 실패했습니다.');location.href='/'</script>");
			out.close();
			return null;
		}

		ActionForward forward = new ActionForward();
		forward.setPath("/admin/manageitem?pn=1");
		forward.setRedirect(true);
		return forward;
	}
}
