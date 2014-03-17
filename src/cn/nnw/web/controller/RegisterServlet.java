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
		
		//1.���ύ�����ֶν��кϷ���У�飨�ѱ����ݷ�װ��formbean��
		//System.out.println("��ʼ  ");
		//RegisterForm form =  WebUtils.request2Bean(request, RegisterForm.class);
		/*String server_checkcode = (String) request.getSession().getAttribute("checkcode");*/
		RegisterForm form = new RegisterForm();
		form.setRegisterForm_email(request.getParameter("RegisterForm_email"));
		form.setRegisterForm_nickname(request.getParameter("RegisterForm_nickname"));
		form.setRegisterForm_password(request.getParameter("RegisterForm_password"));
		form.setRegisterForm_password_confirmation(request.getParameter("RegisterForm_password_confirmation"));
		boolean b = form.validate();
		
		//2.���У��ʧ�ܣ����ص���ҳ�棬����У��ʧ����Ϣ
		if(!b){
			request.setAttribute("form", form);   
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		//System.out.println("00000"+form.getRegisterForm_email());
		//3.���У��ɹ��������service����ע������
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
			//6.���serivce����ɹ�����ת����վ��ȫ����Ϣ��ʾҳ�棬Ϊ�û�ע��ɹ�����Ϣ
			request.setAttribute("error", "��ϲ����ע��ɹ�����");
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return;
		}catch (Exception e) {
			//5.���serivce�����ɹ�,���Ҳ��ɹ���ԭ������������Ļ�����ת����վ��ȫ����Ϣ��ʾҳ�棬Ϊ�û���ʾ�Ѻô�����Ϣ
			e.printStackTrace();
			request.setAttribute("error", "����������δ֪���󣡣���");
			//response.getWriter().print("<script> alert(\"��ȷ�������˺�����!\"); </script>");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
