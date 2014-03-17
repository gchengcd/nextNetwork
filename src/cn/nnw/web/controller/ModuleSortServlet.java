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

import cn.nnw.domain.Moduleinfo;
import cn.nnw.utils.HibernateUtils;

public class ModuleSortServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		Session session = null;
	   	try {
	   		 session = HibernateUtils.getSession();
	   		 session.beginTransaction();
	   		
	   		 String hql = "update User set column1Sort = '"+request.getParameter("newid1")+"',column2Sort = '"+request.getParameter("newid2")+
	   				"',column3Sort = '"+request.getParameter("newid3")+"' where useremail = '"+request.getParameter("username")+"'";
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
