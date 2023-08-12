package domain;
/*
 * 用于封装表ADMIN数据的JavaBean
 *  账号、密码
 */
public class Admin {

	private String ano;
	private String apwd;
	private String aname;
	
	public Admin (){};
	public Admin (String ano,String apwd,String aname){
		this.ano = ano;
		this.apwd = apwd;
		this.aname = aname;
	}
	
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getApwd() {
		return apwd;
	}
	public void setApwd(String apwd) {
		this.apwd = apwd;
	};
	
}
