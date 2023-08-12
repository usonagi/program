package com.film.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.film.utils.DbUtil;

/*
 * 操作电影表tb_film
 */
public class FilmDAO {

	/*
	 * 添加电影信息
	 * fyear 制片时间 
	 * data  上映时间
	 * imagepath 图片路径
	 */
	public int addFilm(String fname,String ftype,String fyear,String flength,
			String date,String director,String actors,String fids,String farea,
			String imagepath) throws Exception{
		//获取连接
		Connection conn = DbUtil.getConn();
		int result =0;
		String sql="insert into tb_film values(null,?,?,?,?,?,?,?,?,?,?)";
		//获取预编译对象
		PreparedStatement pstmt = conn.prepareStatement(sql);
		//设置参数
		pstmt.setObject(1, fname);
		pstmt.setObject(2, ftype);
		pstmt.setObject(3, fyear);
		pstmt.setObject(4, flength);
		pstmt.setObject(5, date);
		pstmt.setObject(6, director);
		pstmt.setObject(7, actors);
		pstmt.setObject(8, fids);
		pstmt.setObject(9, farea);
		pstmt.setObject(10, imagepath);
		//执行更新操作
		result = pstmt.executeUpdate();
		//关闭资源
		DbUtil.closeAll(conn, pstmt, null);
		
		return result;
	}
	
	//查看所有电影信息
	public List<Map<String, Object>> findAll() throws Exception{
		String sql = "select fid,fname,ftype,fyear,flength,fdate,director,actors,fdis,farea,fimage from tb_film order by fid desc";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map =null;
		
		Connection conn = DbUtil.getConn();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		//返回多条记录
		while(rs.next()){
			map = new HashMap<String,Object>();
			map.put("fid", rs.getObject("fid"));
			map.put("fname", rs.getObject("fname"));
			map.put("ftype", rs.getObject("ftype"));
			map.put("fyear", rs.getObject("fyear"));
			map.put("flength", rs.getObject("flength"));
			map.put("fdate", rs.getObject("fdate"));
			map.put("director", rs.getObject("director"));
			map.put("actors", rs.getObject("actors"));
			map.put("fdis", rs.getObject("fdis"));
			map.put("area", rs.getObject("farea"));
			map.put("fimage", rs.getObject("fimage"));
			//将map添加到集合中 
			list.add(map);
		}
		DbUtil.closeAll(conn, pstmt, rs);
		return list;
		
	}
	
	//根据编号查看电影信息
	public Map<String, Object> findByFid(String fid) throws Exception{
		String sql="select fid,fname,ftype,fyear,flength,fdate,director,actors,fdis,farea,fimage from tb_film where fid =?";
		Map<String, Object> map =null;
		Connection conn = DbUtil.getConn();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		//设置参数
		pstmt.setObject(1, fid);
		//执行查询操作
		ResultSet rs = pstmt.executeQuery();
		//对结果集进行处理
		if(rs.next()){
			//创建map
			map = new HashMap<String,Object>();
			map.put("fid", rs.getObject("fid"));
			map.put("fname", rs.getObject("fname"));
			map.put("ftype", rs.getObject("ftype"));
			map.put("fyear", rs.getObject("fyear"));
			map.put("flength", rs.getObject("flength"));
			map.put("fdate", rs.getObject("fdate"));
			map.put("director", rs.getObject("director"));
			map.put("actors", rs.getObject("actors"));
			map.put("fdis", rs.getObject("fdis"));
			map.put("area", rs.getObject("farea"));
			map.put("fimage", rs.getObject("fimage"));
		}
		DbUtil.closeAll(conn, pstmt, rs);
		return map;
	}
	
	public static void main(String[] arge) throws Exception{
		FilmDAO dao = new FilmDAO();
		Map<String, Object> map = dao.findByFid("1");
		System.out.println(map);
	}
	
}
