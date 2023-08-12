package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.domain.Product;
import com.utils.JdbcUtils;

/*
 * 案例 分页显示信息 的持久化层  用于与数据库交互
 */
public class ProductDAO {

	private List<Product> list = new ArrayList<Product>();
	
	Connection conn = null;
	PreparedStatement  pstmt = null;
	ResultSet rs = null;
	
	public List<Product> findAll(int pageNO,int pageSize) throws SQLException{
		/*QueryRunner  queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		return queryRunner.query("select * form products", new BeanListHandler<Product>(Product.class));*/
		
		try {
			conn = JdbcUtils.getConnection();
			//编写sql
			//String sql = "select * from products";
			
			//使用ROW_NUMBER函数，将id作为RNO的新表temp 指定RNO的大小
			String sql = "select * from (select *,ROW_NUMBER() over (order by id ) as RNO "
						+ "from products) temp where RNO >= ? and RNO <= ?";
			//预编译sql
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, (pageNO - 1)*pageSize+1);	//参数1--从哪条记录开始
			pstmt.setInt(2, (pageNO - 1)*pageSize+pageSize); //参数2--每页显示多少条记录  即到哪条记录为止
			
			//执行sql
			rs = pstmt.executeQuery();
			while(rs.next()){
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setCount(rs.getInt("count"));
				product.setPrice(rs.getFloat("price"));
				list.add(product);
			}
			
			/*System.out.println("productdao:");
			for(int i=0;i<list.size();i++)
			{
				Product p = list.get(i);
				System.out.println(p.getName());
			}*/
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		
		return list;
	}
	
	//查询总条数
	public int findTotalCount() {
		
		try {
			conn = JdbcUtils.getConnection();
			
			//编写sql  通过count函数统计主键id的个数  total作为列名
			String sql = "select COUNT(id) total from products";

			//预编译sql
			pstmt = conn.prepareStatement(sql);
			
			//执行sql
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				//System.out.println(rs.getInt("total"));
				return rs.getInt("total");
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		
		return 0;
	}
		
}
