package shop.kbgagu.www.vo;

public class ReviewVo extends MemberVo{
	private int review_sq; // 리뷰게시판 글 고유번호 
	private int item_sq; // 상품 번호
	private int mber_sq; // 회원 번호
	private int order_sq; // 주문서 번호
	private boolean del_fl; // 리뷰 삭제 여부
	private int review_hit; // 리뷰 조회수
	private int star_rating; // 리뷰 별점
	private String review_dttm; // 리뷰 등록일시
	private String review_title; // 리뷰 제목
	private String review_cntnt; // 리뷰 내용
	
	
	public int getReview_sq() {
		return review_sq;
	}
	public void setReview_sq(int review_sq) {
		this.review_sq = review_sq;
	}
	public int getItem_sq() {
		return item_sq;
	}
	public void setItem_sq(int item_sq) {
		this.item_sq = item_sq;
	}
	public int getMber_sq() {
		return mber_sq;
	}
	public void setMber_sq(int mber_sq) {
		this.mber_sq = mber_sq;
	}
	public int getOrder_sq() {
		return order_sq;
	}
	public void setOrder_sq(int order_sq) {
		this.order_sq = order_sq;
	}
	public boolean isDel_fl() {
		return del_fl;
	}
	public void setDel_fl(boolean del_fl) {
		this.del_fl = del_fl;
	}
	public int getReview_hit() {
		return review_hit;
	}
	public void setReview_hit(int review_hit) {
		this.review_hit = review_hit;
	}
	public int getStar_rating() {
		return star_rating;
	}
	public void setStar_rating(int star_rating) {
		this.star_rating = star_rating;
	}
	public String getReview_dttm() {
		return review_dttm;
	}
	public void setReview_dttm(String review_dttm) {
		this.review_dttm = review_dttm;
	}
	public String getReview_title() {
		return review_title;
	}
	public void setReview_title(String review_title) {
		this.review_title = review_title;
	}
	public String getReview_cntnt() {
		return review_cntnt;
	}
	public void setReview_cntnt(String review_cntnt) {
		this.review_cntnt = review_cntnt;
	}
	
	
}
