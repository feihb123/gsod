package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import pojo.Temp;
import service.TempService;



@WebServlet("/TempServlet")
public class TempServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//获取请求中参数
		String str=req.getParameter("name");	
		
		String stn=str.substring(0,6);
    	String wban=str.substring(6);

    	TempService service=new TempService();
    	List<Temp> list=service.getTemp(stn, wban);
	    //将数据返回前台
    	String jsonStr=JSON.toJSONString(list);
    	resp.getWriter().write(jsonStr);
    	
    } 
      
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
    }

}
