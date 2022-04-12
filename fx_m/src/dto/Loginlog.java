package dto;

import java.util.ArrayList;

public class Loginlog {

	private String id;
	private String logindate;
	public static ArrayList<Loginlog> loginlog = new ArrayList<>();
	
	public Loginlog() {
	}
	
	public Loginlog(String id, String logindate) {
		super();
		this.id = id;
		this.logindate = logindate;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLogindate() {
		return logindate;
	}
	public void setLogindate(String logindate) {
		this.logindate = logindate;
	}
	
	
}
