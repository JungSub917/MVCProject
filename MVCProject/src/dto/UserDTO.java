package dto;

public class UserDTO {
	private int idx;
	private String email;
	private String name;
	private String nickName;
	private String password;
	private String agree1;
	private String agree2;
	private String agree3;
	private String status;
	private String token;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAgree1() {
		return agree1;
	}
	public void setAgree1(String agree1) {
		this.agree1 = agree1;
	}
	public String getAgree2() {
		return agree2;
	}
	public void setAgree2(String agree2) {
		this.agree2 = agree2;
	}
	public String getAgree3() {
		return agree3;
	}
	public void setAgree3(String agree3) {
		this.agree3 = agree3;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTokken() {
		return token;
	}
	public void setTokken(String token) {
		this.token = token;
	}
	
}