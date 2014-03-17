package cn.nnw.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import cn.nnw.domain.User;
import cn.nnw.utils.HibernateUtils;

public class ModuleInitServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String useremail = request.getParameter("username");
		List <String> module_id1 = new ArrayList<String>();
		List <String> module_html1 = new ArrayList<String>();
		List <String> module_id2 = new ArrayList<String>();
		List <String> module_html2 = new ArrayList<String>();
		List <String> module_id3 = new ArrayList<String>();
		List <String> module_html3 = new ArrayList<String>();
		
		String[] moduleid1=null,moduleid2=null,moduleid3=null;
		String[] modulehtml1 = null,modulehtml2=null,modulehtml3=null;
		//System.out.println("-----"+useremail);
		Session session = null;
	   	try {
	   		 session = HibernateUtils.getSession();
	   		 session.beginTransaction();
	   		 String hql = "from User where useremail=? ";      
	         Query query = session.createQuery(hql);          
		     query.setString(0, useremail);         
		              
	         List<User> list = query.list();      
	         for(User user : list){      
	        	 if(user.getColumn1Sort().equals("")==false&&user.getColumn1Sort()!=null){
	        		 moduleid1 = user.getColumn1Sort().split(",");
	        		 modulehtml1 = new String[moduleid1.length];
	        		 for(int i=0;i<moduleid1.length;i++){
	        			 String htmlhql = "from Moduleinfo where moduleid=?";
	        			 Query htmlqr = session.createQuery(htmlhql);
	        			 String [] arr = moduleid1[i].split("-");
	        			 htmlqr.setString(0, "moduleid-"+arr[1]);
	        			 module_id1.add("moduleid-"+arr[1]);
	        			 List<Moduleinfo> modulelist = htmlqr.list();
	        			 for(Moduleinfo module : modulelist){
	        				 modulehtml1[i] = module.getModulehtml();
	        				 module_html1.add(modulehtml1[i]);
	        			 }
	        		 }
	        	 }
	        	 if(user.getColumn2Sort().equals("")==false&&user.getColumn2Sort()!=null){
	        		 moduleid2 = user.getColumn2Sort().split(",");
	        		 modulehtml2 = new String[moduleid2.length];
	        		 for(int i=0;i<moduleid2.length;i++){
	        			 String htmlhql = "from Moduleinfo where moduleid=?";
	        			 Query htmlqr = session.createQuery(htmlhql);
	        			 String [] arr = moduleid2[i].split("-");
	        			 htmlqr.setString(0, "moduleid-"+arr[1]);
	        			 module_id2.add("moduleid-"+arr[1]);
	        			 List<Moduleinfo> modulelist = htmlqr.list();
	        			 for(Moduleinfo module : modulelist){
	        				 modulehtml2[i] = module.getModulehtml();
	        				 module_html2.add(modulehtml2[i]);
	        			 }
	        		 }
	        	 }
	        	 if(user.getColumn3Sort().equals("")==false&&user.getColumn3Sort()!=null){
	        		 moduleid3 = user.getColumn3Sort().split(",");
	        		 modulehtml3 = new String[moduleid3.length];
	        		 for(int i=0;i<moduleid3.length;i++){
	        			 String htmlhql = "from Moduleinfo where moduleid=?";
	        			 Query htmlqr = session.createQuery(htmlhql);
	        			 String [] arr = moduleid3[i].split("-");
	        			 htmlqr.setString(0, "moduleid-"+arr[1]);
	        			 module_id3.add("moduleid-"+arr[1]);
	        			 List<Moduleinfo> modulelist = htmlqr.list();
	        			 for(Moduleinfo module : modulelist){
	        				 modulehtml3[i] = module.getModulehtml();
	        				 module_html3.add(modulehtml3[i]);
	        			 }
	        		 }
	        	 }
	         }  
	         List <String> sss = new ArrayList<String>();
	         sss.add("1111".toString());
	         sss.add("2222".toString());
	        // System.out.println("sss--"+sss);
	        // System.out.println(moduleid1+"----"+modulehtml1);
	         session.getTransaction().commit();
	         JSONArray armodule_html1 = JSONArray.fromObject(module_html1);
	         JSONArray armodule_html2 = JSONArray.fromObject(module_html2);
	         JSONArray armodule_html3 = JSONArray.fromObject(module_html3);
	         JSONArray armodule_id1 = JSONArray.fromObject(module_id1);
	         JSONArray armodule_id2 = JSONArray.fromObject(module_id2);
	         JSONArray armodule_id3 = JSONArray.fromObject(module_id3);
	         String json = "{module_html1:"+armodule_html1+", module_id1:"+armodule_id1+"" +
	         		     ",module_html2:"+armodule_html2+", module_id2:"+armodule_id2+"" +
	         			",module_html3:"+armodule_html3+", module_id3:"+armodule_id3+"}";
	        // System.out.println("-----"+json);
	         JSONObject jo = JSONObject.fromObject(json);
	         //System.out.println("======="+jo);
	         response.setContentType("application/x-json");
             response.getWriter().write(jo.toString());
	        /* hql = "from Moduleinfo where moduleid=? ";      
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
            // json = new String(json.getBytes(),"GBK");
             System.out.println("-----"+json);
             response.setContentType("application/x-json");
             response.getWriter().write(json);*/
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
