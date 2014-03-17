package cn.nnw.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.nnw.dao.UserDao;
import cn.nnw.dao.impl.UserDaoImpl;
import cn.nnw.domain.User;
import cn.nnw.service.impl.BusinessServiceImpl;
import cn.nnw.utils.ServiceUtils;


public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String username=null;
		String password=null;
		/*Cookie myCookie[]=request.getCookies();//����һ��Cookie�������� 
		for(int i=0;i<myCookie.length;i++){
			 //����һ��ѭ����������Cookie���������ÿһ��Ԫ�� 
			Cookie newCookie= myCookie[i]; 
			
			if(newCookie.getName().equals("username")){
				username = newCookie.getValue();
				System.out.println(">>>>>>>>>>>>>>"+username);
			}
			if(newCookie.getName().equals("password")){
				password = newCookie.getValue();
				System.out.println(">>>>"+password);
			}
		}
		if(username!=null&&password!=null){
			UserDao dao = new UserDaoImpl();
			User use = dao.find(username,password);
			if(use!=null){
				request.getSession().setAttribute("user", use);
				//���û���½�ɹ�����ת��ҳ
				response.sendRedirect(request.getContextPath() + "/index.jsp");
				return;
			}
		}
		*/
		
		username = request.getParameter("LoginForm_email");
		password = request.getParameter("LoginForm_password");
		String checkbox = request.getParameter("LoginForm_checkbox");
		BusinessServiceImpl service = new BusinessServiceImpl();
		User user = service.login(username, password);
		if(user!=null){
			if(checkbox!=null){
				if(checkbox.equals("on")){
					password = ServiceUtils.md5(password);
					Cookie theusername = new Cookie("username", username);
					Cookie thepassword = new Cookie("password", password);
					theusername.setPath("/");
					thepassword.setPath("/");
					theusername.setMaxAge(604800);
					thepassword.setMaxAge(604800);
					response.addCookie(theusername);
					response.addCookie(thepassword);
					request.getSession().setAttribute("user", user);
					//System.out.println("=================="+username);
					//���û���½�ɹ�����ת��ҳ
					response.sendRedirect(request.getContextPath() + "/index.jsp");
					return;
				}
			}
			request.getSession().setAttribute("user", user);
			//���û���½�ɹ�����ת��ҳ
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return;
		}
		//response.getWriter().print("<script> alert(\"��ȷ�������˺�����!\"); </script>");
		request.setAttribute("error", "�û�����������󣡣�");
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
