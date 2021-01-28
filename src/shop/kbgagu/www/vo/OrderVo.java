package shop.kbgagu.www.vo;

public class OrderVo extends ItemVo{
	private int order_sq; 			// 주문서번호   
	private int mber_sq; 			// 회원번호
	private int item_sq; 			// 상품번호
	private int sttus;     			// 주문상태 default = 1 (결제중) 
	private int pymnt;    			// 결제방법
	private int qy;					// 주문 수량
	private String order_nm;   		// 주문서 성명
	private String order_tel;		// 주문서 전화번호
	private String zip;				// 우편번호
	private String adres;   		// 배송지 주소
	private String detail_adres;    // 배송지 상세주소
	private String extra_adres;		// 배송지 추가주소
	private String requst;			// 추가요청사항
	private String reg_date;		// 주문일자
	
	
	public int getOrder_sq() {
		return order_sq;
	}
	public void setOrder_sq(int order_sq) {
		this.order_sq = order_sq;
	}
	public int getMber_sq() {
		return mber_sq;
	}
	public void setMber_sq(int mber_sq) {
		this.mber_sq = mber_sq;
	}
	public int getItem_sq() {
		return item_sq;
	}
	public void setItem_sq(int item_sq) {
		this.item_sq = item_sq;
	}
	public int getSttus() {
		return sttus;
	}
	public void setSttus(int sttus) {
		this.sttus = sttus;
	}
	public int getPymnt() {
		return pymnt;
	}
	public void setPymnt(int pymnt) {
		this.pymnt = pymnt;
	}
	public int getQy() {
		return qy;
	}
	public void setQy(int qy) {
		this.qy = qy;
	}
	public String getOrder_nm() {
		return order_nm;
	}
	public void setOrder_nm(String order_nm) {
		this.order_nm = order_nm;
	}
	public String getOrder_tel() {
		return order_tel;
	}
	public void setOrder_tel(String order_tel) {
		this.order_tel = order_tel;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	public String getDetail_adres() {
		return detail_adres;
	}
	public void setDetail_adres(String detail_adres) {
		this.detail_adres = detail_adres;
	}
	public String getExtra_adres() {
		return extra_adres;
	}
	public void setExtra_adres(String extra_adres) {
		this.extra_adres = extra_adres;
	}
	public String getRequst() {
		return requst;
	}
	public void setRequst(String requst) {
		this.requst = requst;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	
	
}
