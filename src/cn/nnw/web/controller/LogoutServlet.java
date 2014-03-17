package cn.nnw.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if(session!=null){
			session.removeAttribute("user");
		}
		
		Cookie deleteusername=new Cookie("username",null); 
		deleteusername.setMaxAge(0); //删除该Cookie 
		deleteusername.setPath("/"); 
		response.addCookie(deleteusername); 
		Cookie deletepassword=new Cookie("password",null); 
		deletepassword.setMaxAge(0); //删除该Cookie 
		deletepassword.setPath("/"); 
		response.addCookie(deletepassword); 
		
		//注销成功，跳到全局消息显示页面，显示注销成功消息，并控制消息显示页面过3秒后跳转到首页
		//request.setAttribute("message", "注销成功,浏览器将在3秒后跳转，如果没有跳转，你就点....！！<meta http-equiv='refresh' content='3;url="+request.getContextPath()+"/index.jsp'>");
		//request.getRequestDispatcher("/index.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
