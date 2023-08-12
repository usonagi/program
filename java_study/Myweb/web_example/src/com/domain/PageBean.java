package com.domain;

import java.util.List;

/* ���ڷ�װ��ҳ�������Ϣ
 * �ڷ������˶���PageBean����������װ��ҳ��Ҫ�����ݣ��������Ͳ���Ҫ
 * 	  ����List<Product>,���Ƿ���һ��PageBean��json
 */
public class PageBean<T> {
	private int pageNo;		//ҳ��
	private int pageSize;	//ÿҳ��ʾ����
	private int totalPage;	//��ҳ��
	private int totalCount;	//������
	private List<T> content;	//��ǰҳ��ʾ������
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> content) {
		this.content = content;
	}
}
