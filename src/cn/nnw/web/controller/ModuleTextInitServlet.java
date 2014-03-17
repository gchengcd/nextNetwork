package cn.nnw.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;

import cn.nnw.domain.Usermodule;
import cn.nnw.utils.HibernateUtils;

public class ModuleTextInitServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Session session = null;
		String textvalue="";
	   	try {
	   		 session = HibernateUtils.getSession();
	   		 session.beginTransaction();
	   		
	   		 String hql = "from Usermodule where useremail='"+request.getParameter("username")
	   				 +"' and moduleid='"+request.getParameter("module_id")+"'";
	   		 Query query = session.createQuery(hql);
	   		 List <Usermodule> list = query.list();
	   		 for(Usermodule usermd:list){
	   			 textvalue = usermd.getModuletitle();
	   		 }
             session.getTransaction().commit();
             System.out.println(textvalue);
             if(textvalue==null){
            	 textvalue = "";
             }
             String json = "{\"textvalue\":\""+textvalue+"\", \"app_id\":\""+request.getParameter("app_id")+"\"}";
             response.setContentType("application/x-json");
             response.getWriter().write(json);
            
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