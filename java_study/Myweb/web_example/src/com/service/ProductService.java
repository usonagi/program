package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.ProductDAO;
import com.domain.PageBean;
import com.domain.Product;

public class ProductService {
	public List<Product> findAllL(int pageNO,int pageSize) throws SQLException
	{
		ProductDAO pd = new ProductDAO();
		return pd.findAll(pageNO,pageSize);
	}
	
	//ʹ��PageBean��װ����
	public PageBean findAll(int pageNO,int pageSize) throws SQLException
	{
		ProductDAO pd = new ProductDAO();
		List<Product> ps = pd.findAll(pageNO,pageSize);	//��ѯ�Ĳ�Ʒ��Ϣ
		
		//����ҳ���ݷ�װ��pagebean
		PageBean<Product> pb = new PageBean<Product>();
		pb.setContent(ps);
		pb.setPageNo(pageNO);
		pb.setPageSize(pageSize);
		
		int totalCount = pd.findTotalCount();	//�������������ñ��ѯ�õ�
		int totalPage = (int) Math.ceil(totalCount*1.0/pageSize);	//��ҳ����������������ȡ��
		pb.setTotalPage(totalPage);
		pb.setTotalCount(totalCount);
		
		return pb;
	}
}
