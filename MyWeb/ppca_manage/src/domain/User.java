package domain;

/*
 * 用于封装表TUSER数据的JavaBean
 *  账号、密码、用户名、性别、手机号 | 邮箱
 *  注册时：用户名 密码 手机号
 *  完善信息：性别 专业  邮箱 
 */
public class User {

	// 属性名需要和html上的name属性一致
	private String uno;
	private String username;
	private String password;
	private String sex;
	private String phone;
	private String email;

	public User() {
	};

	//用于管理员查看的数据
	public User(String uno, String username, String password,String sex, String phone, String email) {
		this.uno = uno;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.phone = phone;
		this.email = email;
	};

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUno() {
		return uno;
	}

	public void setUno(String uno) {
		this.uno = uno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
