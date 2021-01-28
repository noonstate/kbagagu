package shop.kbgagu.www.vo;

public class ItemVo {
	private int item_sq; // 상품번호
	private int ctgry; // 카테고리
	private int admin_sq; // 등록관리자구분
	private boolean del_fl; // 삭제여부
	private boolean new_fl; // 신제품여부
	private boolean upvote_fl; // 추천여부
	private boolean sale_fl; // 할인여부
	private boolean show_fl; // 노출여부
	private int invntry; // 재고
	private int price; // 가격
	private int sale_cnt; // 판매량
	private int sale_rate; // 할인율
	private String item_nm; // 상품명
	private String reg_date; // 등록일
	private String img_n0; // 썸네일이미지
	private String img_n1; // 상세이미지1
	private String img_n2; // 상세이미지2
	private String img_n3; // 상세이미지3
	private String img_n4; // 상세이미지4
	private String detail; // 상품상세정보
	
	public int getItem_sq() {
		return item_sq;
	}
	public void setItem_sq(int item_sq) {
		this.item_sq = item_sq;
	}
	public int getCtgry() {
		return ctgry;
	}
	public void setCtgry(int ctgry) {
		this.ctgry = ctgry;
	}
	public int getAdmin_sq() {
		return admin_sq;
	}
	public void setAdmin_sq(int admin_sq) {
		this.admin_sq = admin_sq;
	}
	public boolean isDel_fl() {
		return del_fl;
	}
	public void setDel_fl(boolean del_fl) {
		this.del_fl = del_fl;
	}
	public boolean isNew_fl() {
		return new_fl;
	}
	public void setNew_fl(boolean new_fl) {
		this.new_fl = new_fl;
	}
	public boolean isUpvote_fl() {
		return upvote_fl;
	}
	public void setUpvote_fl(boolean upvote_fl) {
		this.upvote_fl = upvote_fl;
	}
	public boolean isSale_fl() {
		return sale_fl;
	}
	public void setSale_fl(boolean sale_fl) {
		this.sale_fl = sale_fl;
	}
	public boolean isShow_fl() {
		return show_fl;
	}
	public void setShow_fl(boolean show_fl) {
		this.show_fl = show_fl;
	}
	public int getInvntry() {
		return invntry;
	}
	public void setInvntry(int invntry) {
		this.invntry = invntry;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSale_cnt() {
		return sale_cnt;
	}
	public void setSale_cnt(int sale_cnt) {
		this.sale_cnt = sale_cnt;
	}
	public int getSale_rate() {
		return sale_rate;
	}
	public void setSale_rate(int sale_rate) {
		this.sale_rate = sale_rate;
	}
	public String getItem_nm() {
		return item_nm;
	}
	public void setItem_nm(String item_nm) {
		this.item_nm = item_nm;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getImg_n0() {
		return img_n0;
	}
	public void setImg_n0(String img_n0) {
		this.img_n0 = img_n0;
	}
	public String getImg_n1() {
		return img_n1;
	}
	public void setImg_n1(String img_n1) {
		this.img_n1 = img_n1;
	}
	public String getImg_n2() {
		return img_n2;
	}
	public void setImg_n2(String img_n2) {
		this.img_n2 = img_n2;
	}
	public String getImg_n3() {
		return img_n3;
	}
	public void setImg_n3(String img_n3) {
		this.img_n3 = img_n3;
	}
	public String getImg_n4() {
		return img_n4;
	}
	public void setImg_n4(String img_n4) {
		this.img_n4 = img_n4;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
