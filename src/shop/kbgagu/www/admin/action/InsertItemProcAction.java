package shop.kbgagu.www.admin.action;

import java.io.File;
import java.io.PrintWriter;

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

public class InsertItemProcAction implements Action {
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

		String savePath = request.getSession().getServletContext().getRealPath("/upload"); // getRealPath("/upload");
		MultipartRequest multi = null;
		String encoding = "UTF-8";
		int maxSize = 10 * 1024 * 1024;

		File file = new File(savePath);
		if (!file.exists()) {
			file.mkdir();
		}

		try {
			multi = new MultipartRequest(request, savePath, maxSize, encoding, new DefaultFileRenamePolicy());

		} catch (Exception e) {
			e.printStackTrace();
		}

		String img_n0 = multi.getFilesystemName("file[0]");
		String img_n1 = multi.getFilesystemName("file[1]");
		String img_n2 = multi.getFilesystemName("file[2]");
		String img_n3 = multi.getFilesystemName("file[3]");
		String img_n4 = multi.getFilesystemName("file[4]");

		if (img_n0 != null && !ImegeProcess.isImege(multi.getOriginalFileName("file[0]"))) {
			File f = new File("/upload/" + multi.getFilesystemName("file[0]"));
			f.delete();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근');location.href='/'</script>");
			out.close();
			return null;
		}

		String item_nm = multi.getParameter("item_nm"); // 이름
		String ctgry = multi.getParameter("ctgry"); // 카테고리
		String new_fl = multi.getParameter("new_fl"); // 신제품여부
		String upvote_fl = multi.getParameter("upvote_fl"); // 추천여부
		String price = multi.getParameter("price"); // 가격
		String sale_fl = multi.getParameter("sale_fl"); // 할인여부
		String sale_rate = multi.getParameter("sale_rate"); // 할인률
		String content = multi.getParameter("content"); // 상세정보

		ItemVo vo = new ItemVo();

		if (item_nm == null || item_nm.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');history.back();</script>");
			out.close();
			return null;
		}

		if (price == null || price.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');history.back();</script>");
			out.close();
			return null;
		}

		if (content == null || content.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');history.back();</script>");
			out.close();
			return null;
		}

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
		if (sale_fl != null && sale_fl.equals("true")) { // 할인여부 체크박스판정
			vo.setSale_fl(true);
		} else {
			vo.setSale_fl(false);
		}

		vo.setItem_nm(item_nm);
		vo.setCtgry(Integer.parseInt(ctgry));
		vo.setPrice(Integer.parseInt(price));
		if (!sale_rate.equals("")) {
			vo.setSale_rate(Integer.parseInt(sale_rate));
		} else {
			vo.setSale_rate(0);
		}
		vo.setDetail(Paser.chgToStr(content));
		
		if (img_n0 != null) {
			vo.setImg_n0("/upload/" + img_n0);
		} else {
			vo.setImg_n0(img_n0);
		}
		if (img_n1 != null) {
			vo.setImg_n1("/upload/" + img_n1);
		} else {
			vo.setImg_n1(img_n1);
		}
		if (img_n2 != null) {
			vo.setImg_n2("/upload/" + img_n2);
		} else {
			vo.setImg_n2(img_n2);
		}
		if (img_n3 != null) {
			vo.setImg_n3("/upload/" + img_n3);
		} else {
			vo.setImg_n3(img_n3);
		}
		if (img_n4 != null) {
			vo.setImg_n4("/upload/" + img_n4);
		} else {
			vo.setImg_n4(img_n4);
		}
		
		AdminService svc = new AdminService();

		if (!svc.insertItem(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('상품등록에 실패하였습니다.');history.back();</script>");
			out.close();
			return null;
		}

		ActionForward forward = new ActionForward();
		forward.setPath("/admin/manageitem?pn=1");
		forward.setRedirect(true);
		return forward;
	}
}
