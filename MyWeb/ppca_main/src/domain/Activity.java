package domain;

import java.sql.Date;

/*
 * 用于封装表Activity数据的JavaBean
 * 编号、活动名、发布者、活动类型、活动状态  人数、简介、截止时间
 */
public class Activity {

	private String act_id;
	private String act_name;
	private String uname;
	private String act_type;
	private String act_status;
	
	private int number;
	private String introd;
	//private String deadline;
	private Date deadline;
	
	public Activity(){};

	public Activity(String actName, String uName, String actType, String actStatus, int number, String introd,
			Date deadline) {
		this.act_name = actName;
		this.uname = uName;
		this.act_type = actType;
		this.act_status = actStatus;
		this.number = number;
		this.introd = introd;
		this.deadline = deadline;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getIntrod() {
		return introd;
	}

	public void setIntrod(String introd) {
		this.introd = introd;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
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
