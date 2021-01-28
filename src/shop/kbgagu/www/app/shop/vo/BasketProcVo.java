package shop.kbgagu.www.app.shop.vo;

public class BasketProcVo {
	
	// 장바구니 요청시 사용할 vo
	private int item_sq;		// 상품번호
	private String item_nm;		// 상품이름
	private String img_n0;		// 대표이미지
	private int sale_rate;		// 할인률
	private int price;			// 상품가격
	private int basket_sq;		// 장바구니번호
	private int qy;				// 장바구니 상품수량
	private String dttm;		// 장바구니 등록일
	
	
	public int getItem_sq() {
		return item_sq;
	}
	public void setItem_sq(int item_sq) {
		this.item_sq = item_sq;
	}
	public String getItem_nm() {
		return item_nm;
	}
	public void setItem_nm(String item_nm) {
		this.item_nm = item_nm;
	}
	public String getImg_n0() {
		return img_n0;
	}
	public void setImg_n0(String img_n0) {
		this.img_n0 = img_n0;
	}
	public int getSale_rate() {
		return sale_rate;
	}
	public void setSale_rate(int sale_rate) {
		this.sale_rate = sale_rate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getBasket_sq() {
		return basket_sq;
	}
	public void setBasket_sq(int basket_sq) {
		this.basket_sq = basket_sq;
	}
	public int getQy() {
		return qy;
	}
	public void setQy(int qy) {
		this.qy = qy;
	}
	public String getDttm() {
		return dttm;
	}
	public void setDttm(String dttm) {
		this.dttm = dttm;
	}
	
	
}
