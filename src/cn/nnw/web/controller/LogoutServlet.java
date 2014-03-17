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
		deleteusername.setMaxAge(0); //ɾ����Cookie 
		deleteusername.setPath("/"); 
		response.addCookie(deleteusername); 
		Cookie deletepassword=new Cookie("password",null); 
		deletepassword.setMaxAge(0); //ɾ����Cookie 
		deletepassword.setPath("/"); 
		response.addCookie(deletepassword); 
		
		//ע���ɹ�������ȫ����Ϣ��ʾҳ�棬��ʾע���ɹ���Ϣ����������Ϣ��ʾҳ���3�����ת����ҳ
		//request.setAttribute("message", "ע���ɹ�,���������3�����ת�����û����ת����͵�....����<meta http-equiv='refresh' content='3;url="+request.getContextPath()+"/index.jsp'>");
		//request.getRequestDispatcher("/index.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
