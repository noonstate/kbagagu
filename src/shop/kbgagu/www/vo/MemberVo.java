package shop.kbgagu.www.vo;

public class MemberVo {
	private int mber_sq;
	private boolean del_fl;
	private String dttm;
	private String tel;
	private String id;
	private String pwd;
	private String nm;

	public int getMber_sq() {
		return mber_sq;
	}

	public void setMber_sq(int mber_sq) {
		this.mber_sq = mber_sq;
	}

	public boolean isDel_fl() {
		return del_fl;
	}

	public void setDel_fl(boolean del_fl) {
		this.del_fl = del_fl;
	}

	public String getDttm() {
		return dttm;
	}

	public void setDttm(String dttm) {
		this.dttm = dttm;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

}
