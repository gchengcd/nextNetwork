package cn.nnw.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.nnw.domain.User;
import cn.nnw.exception.UserExistException;
import cn.nnw.service.impl.BusinessServiceImpl;
import cn.nnw.service.impl.RegisterDbInit;
import cn.nnw.utils.WebUtils;
import cn.nnw.web.formbean.RegisterForm;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//1.对提交表单的字段进行合法性校验（把表单数据封装到formbean）
		//System.out.println("开始  ");
		//RegisterForm form =  WebUtils.request2Bean(request, RegisterForm.class);
		/*String server_checkcode = (String) request.getSession().getAttribute("checkcode");*/
		RegisterForm form = new RegisterForm();
		form.setRegisterForm_email(request.getParameter("RegisterForm_email"));
		form.setRegisterForm_nickname(request.getParameter("RegisterForm_nickname"));
		form.setRegisterForm_password(request.getParameter("RegisterForm_password"));
		form.setRegisterForm_password_confirmation(request.getParameter("RegisterForm_password_confirmation"));
		boolean b = form.validate();
		
		//2.如果校验失败，跳回到表单页面，回显校验失败信息
		if(!b){
			request.setAttribute("form", form);   
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		//System.out.println("00000"+form.getRegisterForm_email());
		//3.如果校验成功，则调用service处理注册请求
		User user = new User();
		user.setUseremail(form.getRegisterForm_email());
		user.setPassword(form.getRegisterForm_password());
		user.setNickname(form.getRegisterForm_nickname());
		user.setColumn1Sort("appid-8,appid-4,appid-5");
		user.setColumn2Sort("appid-6");
		user.setColumn3Sort("appid-9,appid-10");
		
		BusinessServiceImpl service = new BusinessServiceImpl();
		try {
			service.register(user);
			RegisterDbInit dbinit = new RegisterDbInit();
			dbinit.dbInit(user.getUseremail());
			//6.如果serivce处理成功，跳转到网站的全局消息显示页面，为用户注册成功的消息
			request.setAttribute("error", "恭喜您，注册成功！！");
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return;
		}catch (Exception e) {
			//5.如果serivce处理不成功,并且不成功的原因是其它问题的话，跳转到网站的全局消息显示页面，为用户显示友好错误消息
			e.printStackTrace();
			request.setAttribute("error", "服务器出现未知错误！！！");
			//response.getWriter().print("<script> alert(\"请确认您的账号密码!\"); </script>");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
