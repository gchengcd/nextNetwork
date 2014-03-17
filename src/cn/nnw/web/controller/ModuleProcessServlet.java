package cn.nnw.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.Session;

import cn.nnw.domain.Moduleinfo;
import cn.nnw.domain.Usermodule;
import cn.nnw.utils.HibernateUtils;

public class ModuleProcessServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Session session = null;
		String module_html="";
	   	try {
	   		 session = HibernateUtils.getSession();
	   		 session.beginTransaction();
	   		 if(request.getParameter("actiongoal").equals("plus")){
	   			//System.out.println("mmmmmm");
	   			 String hql = "from  Moduleinfo where moduleid='"+request.getParameter("module_id")+"'";
	   			 Query query = session.createQuery(hql);
	   			 List <Moduleinfo> list = query.list();
	   			 for (Moduleinfo moduleif:list){
	   				 module_html = moduleif.getModulehtml();
	   				// System.out.println("mmmmmm"+module_html);
	   			 }
	   			 Usermodule usermd = new Usermodule();
	   			 usermd.setUseremail(request.getParameter("username"));
	   			 usermd.setModuleid(request.getParameter("module_id"));
	   			 session.save(usermd);
	             session.getTransaction().commit();
	             module_html=module_html.replaceAll("\"", "\'");
	            // JSONObject jomodule_html = JSONObject.fromObject(module_html);
	            // System.out.println(jomodule_html);
	   			 String json = "{module_html:\""+module_html+"\"}";
		 	     // System.out.println("-----"+json);
		          JSONObject jo = JSONObject.fromObject(json);
		         // System.out.println("======="+jo);
		 	   	 response.setContentType("application/x-json");
	            response.getWriter().write(jo.toString());
	   		 }
	   		 else if(request.getParameter("actiongoal").equals("remove")){
	   			 Usermodule usermd = new Usermodule();
	   			 usermd.setUseremail(request.getParameter("username"));
	   			 usermd.setModuleid(request.getParameter("module_id"));
	   			 session.delete(usermd);
	             session.getTransaction().commit();
	   		 }

            
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
