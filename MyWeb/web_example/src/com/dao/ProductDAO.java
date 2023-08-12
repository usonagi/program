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
 * ���� ��ҳ��ʾ��Ϣ �ĳ־û���  ���������ݿ⽻��
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
			//��дsql
			//String sql = "select * from products";
			
			//ʹ��ROW_NUMBER��������id��ΪRNO���±�temp ָ��RNO�Ĵ�С
			String sql = "select * from (select *,ROW_NUMBER() over (order by id ) as RNO "
						+ "from products) temp where RNO >= ? and RNO <= ?";
			//Ԥ����sql
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, (pageNO - 1)*pageSize+1);	//����1--��������¼��ʼ
			pstmt.setInt(2, (pageNO - 1)*pageSize+pageSize); //����2--ÿҳ��ʾ��������¼  ����������¼Ϊֹ
			
			//ִ��sql
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
	
	//��ѯ������
	public int findTotalCount() {
		
		try {
			conn = JdbcUtils.getConnection();
			
			//��дsql  ͨ��count����ͳ������id�ĸ���  total��Ϊ����
			String sql = "select COUNT(id) total from products";

			//Ԥ����sql
			pstmt = conn.prepareStatement(sql);
			
			//ִ��sql
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
