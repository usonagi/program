package domain;

/*
 * �Զ���һ������json���ݽ����JavaBean
 */

public class JsonResult {

	// ture--�ɹ�   false--ʧ��
	private boolean status;
	
	// json���ݵĴ�����Ϣ
	private String ErrorJson;
	
	// ��װ��������
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
