package cn.nnw.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.Session;

import cn.nnw.domain.Moduleinfo;
import cn.nnw.utils.HibernateUtils;

public class GetHotModuleServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("lllllll");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String modulevalue="",videovalue="";
		Session session = null;
	   	try {
	   		 session = HibernateUtils.getSession();
	   		 session.beginTransaction();
	   		 String hql = "from Moduleinfo where moduleid=? ";      
	         Query query = session.createQuery(hql);          
		     query.setString(0, "moduleid-4");         
		              
	         List<Moduleinfo> list = query.list();      
	         for(Moduleinfo moduleif : list){      
	             modulevalue = moduleif.getModulehtml();    
	         }  
	         hql = "from Moduleinfo where moduleid=? ";      
	         query = session.createQuery(hql);          
		     query.setString(0, "moduleid-6");         
		     list = null;         
	         list = query.list();      
	         for(Moduleinfo moduleif : list){      
	             videovalue = moduleif.getModulehtml();    
	         } 
             session.getTransaction().commit();
             //modulevalue = modulevalue.replaceAll("\'", "\"");
             String json = "{\"modulevalue\":\""+modulevalue+"\", \"videovalue\":\""+videovalue+"\"}";
         System.out.println("kkkkkkkkkkkkk"+json);
            // json = new String(json.getBytes(),"GBK");
            // System.out.println("-----"+json);
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
