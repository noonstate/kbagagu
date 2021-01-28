package shop.kbgagu.www.vo;

public class NoticeVo {
	private int notice_sq;
	private int admin_sq;
	private int notice_ctgry;
	private int notice_hit;
	private boolean del_fl;
	private String notice_dttm;
	private String notice_title;
	private String notice_cntnt;
	
	public int getNotice_sq() {
		return notice_sq;
	}
	public void setNotice_sq(int notice_sq) {
		this.notice_sq = notice_sq;
	}
	public int getAdmin_sq() {
		return admin_sq;
	}
	public void setAdmin_sq(int admin_sq) {
		this.admin_sq = admin_sq;
	}
	public int getNotice_ctgry() {
		return notice_ctgry;
	}
	public void setNotice_ctgry(int notice_ctgry) {
		this.notice_ctgry = notice_ctgry;
	}
	public int getNotice_hit() {
		return notice_hit;
	}
	public void setNotice_hit(int notice_hit) {
		this.notice_hit = notice_hit;
	}
	public boolean isDel_fl() {
		return del_fl;
	}
	public void setDel_fl(boolean del_fl) {
		this.del_fl = del_fl;
	}
	public String getNotice_dttm() {
		return notice_dttm;
	}
	public void setNotice_dttm(String notice_dttm) {
		this.notice_dttm = notice_dttm;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_cntnt() {
		return notice_cntnt;
	}
	public void setNotice_cntnt(String notice_cntnt) {
		this.notice_cntnt = notice_cntnt;
	}
}
