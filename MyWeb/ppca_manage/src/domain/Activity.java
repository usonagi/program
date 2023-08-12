package domain;

/*
 * 用于封装表Activity数据的JavaBean
 * 序号、编号、活动名、发布者、活动类型、活动状态
 */
public class Activity {

	private int id;
	private String act_id;
	private String act_name;
	private String uname;
	private String act_type;
	private String act_status;
	
	public Activity(){};
	public Activity(String actId,String actName,String uName,String actType,String actStatus){
		this.act_id = actId;
		this.act_name = actName;
		this.uname = uName;
		this.act_type = actType;
		this.act_status = actStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAct_id() {
		return act_id;
	}

	public void setAct_id(String act_id) {
		this.act_id = act_id;
	}

	public String getAct_name() {
		return act_name;
	}

	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getAct_type() {
		return act_type;
	}

	public void setAct_type(String act_type) {
		this.act_type = act_type;
	}

	public String getAct_status() {
		return act_status;
	}

	public void setAct_status(String act_status) {
		this.act_status = act_status;
	};

}
