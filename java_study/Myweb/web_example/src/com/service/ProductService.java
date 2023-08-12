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
	
	//使用PageBean封装数据
	public PageBean findAll(int pageNO,int pageSize) throws SQLException
	{
		ProductDAO pd = new ProductDAO();
		List<Product> ps = pd.findAll(pageNO,pageSize);	//查询的产品信息
		
		//将分页数据封装到pagebean
		PageBean<Product> pb = new PageBean<Product>();
		pb.setContent(ps);
		pb.setPageNo(pageNO);
		pb.setPageSize(pageSize);
		
		int totalCount = pd.findTotalCount();	//数据总条数调用表查询得到
		int totalPage = (int) Math.ceil(totalCount*1.0/pageSize);	//总页数根据总条数向上取整
		pb.setTotalPage(totalPage);
		pb.setTotalCount(totalCount);
		
		return pb;
	}
}
