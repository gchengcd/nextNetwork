package cn.nnw.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import cn.nnw.utils.HibernateUtils;

public class ModuleTextServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Session session = null;
	   	try {
	   		 session = HibernateUtils.getSession();
	   		 session.beginTransaction();
	   		
	   		 String hql = "update Usermodule set moduletitle = '"+request.getParameter("textvalue")+"' where useremail = '"+request.getParameter("username")+"' and moduleid= '"+request.getParameter("module_id")+"'";
	   		 session.createQuery(hql).executeUpdate();
             session.getTransaction().commit();
            
	   	 }catch(Exception e){
	   		 e.printStackTrace();
	   		 session.getTransaction().rollback();
	   	 }finally{
	   		 HibernateUtils.closeSession(session);
	   	 }
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
