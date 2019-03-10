package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import pojo.Station;
import service.StationService;

/**
 * Servlet implementation class StationServlet
 */
@WebServlet("/StationServlet")
public class StationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取站点信息
		//创建一个StationDao对象
		StationService service=new StationService();
		List<Station> list=service.getStation();
		//将list对象转别为JSON字符串
		String jsonStr = JSON.toJSONString(list);
		//将list返回给前台
		response.getWriter().write(jsonStr);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
