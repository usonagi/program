package domain;

/*
 * 自定义一个返回json数据结果的JavaBean
 */

public class JsonResult {

	// ture--成功   false--失败
	private boolean status;
	
	// json数据的错误信息
	private String ErrorJson;
	
	// 封装对象数据
	private Object content;

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getErrorJson() {
		return ErrorJson;
	}

	public void setErrorJson(String errorJson) {
		ErrorJson = errorJson;
	}
	
	
}
