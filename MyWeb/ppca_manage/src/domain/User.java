package domain;

/*
 * ���ڷ�װ��TUSER���ݵ�JavaBean
 *  �˺š����롢�û������Ա��ֻ��� | ����
 *  ע��ʱ���û��� ���� �ֻ���
 *  ������Ϣ���Ա� רҵ  ���� 
 */
public class User {

	// ��������Ҫ��html�ϵ�name����һ��
	private String uno;
	private String username;
	private String password;
	private String sex;
	private String phone;
	private String email;

	public User() {
	};

	//���ڹ���Ա�鿴������
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
