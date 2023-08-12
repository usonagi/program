package com.film.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.film.dao.FilmDAO;
import com.google.gson.Gson;


//注解
@WebServlet("/film.action")
public class FilmServlet extends HttpServlet {

	//实例化FilmDAO
	FilmDAO dao = new FilmDAO();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String op = request.getParameter("op");
		if("add".equals(op)){
			doAdd(request,response);
		}else if("findByFid".equals(op)){
			doFindByFid(request,response);
		}else if("findAll".equals(op)){
			doFindAll(request,response);
		}
	}
	/*添加电影信息
	 * 
	 */
	protected void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从页面取值
		String fname = request.getParameter("fname");
		String ftype = request.getParameter("ftype");
		String fyear = request.getParameter("fyear");
		String flength = request.getParameter("flength");
		String fdate = request.getParameter("fdate");
		String director = request.getParameter("director");
		String actors = request.getParameter("actors");
		String fdis = request.getParameter("fdis");
		String farea = request.getParameter("farea");
		String imagepath = request.getParameter("imagepath");
		imagepath="film_images/"+imagepath;
		try {
			int result =dao.addFilm(fname, ftype, fyear, flength, fdate, director, actors, fdis, farea, imagepath);
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("code", result);
			Gson gson = new Gson();
			String info = gson.toJson(jsonMap);
			response.getWriter().print(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/*
	 * 根据编号查询
	 */
	protected void doFindByFid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fid = request.getParameter("fid");
		try {
			Map<String, Object> map = dao.findByFid(fid);
			Gson gson = new Gson();
			String info = gson.toJson(map);
			response.getWriter().print(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/*
	 * 查看所有
	 */
	protected void doFindAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Map<String,Object>> list =dao.findAll();
			Gson gson = new Gson();
			String info = gson.toJson(list);
			response.getWriter().print(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	

}
