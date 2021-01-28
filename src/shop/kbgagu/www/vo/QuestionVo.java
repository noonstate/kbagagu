package shop.kbgagu.www.vo;

public class QuestionVo extends MemberVo{
	private int ques_sq;        //문의 번호
	private int mber_sq;        //회원 번호
	private int admin_sq;       //관리자 번호
	private boolean del_fl;     //문의 삭제 여부
	private String ques_dttm;   //문의 등록일시
	private String ques_title;  //문의 제목
	private String ques_cntnt;  //문의 내용
	private String answer;      //문의 답글
	/**
	 * @return the ques_sq
	 */
	public int getQues_sq() {
		return ques_sq;
	}
	/**
	 * @param ques_sq the ques_sq to set
	 */
	public void setQues_sq(int ques_sq) {
		this.ques_sq = ques_sq;
	}
	/**
	 * @return the mber_sq
	 */
	public int getMber_sq() {
		return mber_sq;
	}
	/**
	 * @param mber_sq the mber_sq to set
	 */
	public void setMber_sq(int mber_sq) {
		this.mber_sq = mber_sq;
	}
	/**
	 * @return the admin_sq
	 */
	public int getAdmin_sq() {
		return admin_sq;
	}
	/**
	 * @param admin_sq the admin_sq to set
	 */
	public void setAdmin_sq(int admin_sq) {
		this.admin_sq = admin_sq;
	}
	/**
	 * @return the del_fl
	 */
	public boolean isDel_fl() {
		return del_fl;
	}
	/**
	 * @param del_fl the del_fl to set
	 */
	public void setDel_fl(boolean del_fl) {
		this.del_fl = del_fl;
	}
	/**
	 * @return the ques_dttm
	 */
	public String getQues_dttm() {
		return ques_dttm;
	}
	/**
	 * @param ques_dttm the ques_dttm to set
	 */
	public void setQues_dttm(String ques_dttm) {
		this.ques_dttm = ques_dttm;
	}
	/**
	 * @return the ques_title
	 */
	public String getQues_title() {
		return ques_title;
	}
	/**
	 * @param ques_title the ques_title to set
	 */
	public void setQues_title(String ques_title) {
		this.ques_title = ques_title;
	}
	/**
	 * @return the ques_cntnt
	 */
	public String getQues_cntnt() {
		return ques_cntnt;
	}
	/**
	 * @param ques_cntnt the ques_cntnt to set
	 */
	public void setQues_cntnt(String ques_cntnt) {
		this.ques_cntnt = ques_cntnt;
	}
	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
	

}
