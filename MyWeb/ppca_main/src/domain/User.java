package domain;

/*
 * ���ڷ�װ��TUSER���ݵ�JavaBean
 *  �˺š����롢�û������Ա��ֻ���
 *  ע��ʱ���û��� ���� �ֻ���
 *  ��¼ʱ���û���|�ֻ��� ����  
 *  ������Ϣ��ѧ�� ��ʵ���� רҵ ���� 
 */
public class User {
	// ��������Ҫ��html�ϵ�name����һ��
	private String uno;
	private String username;
	private String password;
	private String sex;
	private String phone;
	private String email;

	private String realname;
	private String stuid;
	private String major;
	private int age;

	
	public User(){}
	
	//������Ϣ��user
	public User(String username,String phone,String sex,String email ){
		this.username = username;
		this.phone = phone;
		this.email = email;
		this.sex = sex;
	}
	
	//��ϸ��Ϣ��user
	public User(String username,String phone,String sex,String email,String 
			realname,String stuid,String major,int age){
		this.username = username;
		this.phone = phone;
		this.email = email;
		this.sex = sex;
		this.realname = realname;
		this.stuid = stuid;
		this.major = major;
		this.age = age;
	}
	
	//ʵ����Ϣ��user
	public User(String phone,String realname,String stuid,String major,int age){
		this.phone = phone;
		this.realname = realname;
		this.stuid = stuid;
		this.major = major;
		this.age = age;
	}
	
	public String getUno() {
		return uno;
	}

	public void setUno(String uno) {
		this.uno = uno;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getStuid() {
		return stuid;
	}

	public void setStuid(String stuid) {
		this.stuid = stuid;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
