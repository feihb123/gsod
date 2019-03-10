package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import service.FrshttService;

/**
 * Servlet implementation class FrshttServlet
 */
@WebServlet("/FrshttServlet")
public class FrshttServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FrshttService service=new FrshttService();
		String keyRow=req.getParameter("name");	
		int[] count=service.getFrshtt(keyRow);
		String jsonStr=JSON.toJSONString(count);
    	resp.getWriter().write(jsonStr);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
}
