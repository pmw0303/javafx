package dto;

public class Logincheck {
	private int mnum;
	private String logdate;
	
	public Logincheck() {
	}

	public Logincheck(int mnum, String logdate) {
		super();
		this.mnum = mnum;
		this.logdate = logdate;
	}

	public int getMnum() {
		return mnum;
	}

	public void setMnum(int mnum) {
		this.mnum = mnum;
	}

	public String getLogdate() {
		return logdate;
	}

	public void setLogdate(String logdate) {
		this.logdate = logdate;
	}
	


}
