package cn.nnw.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.Session;

import cn.nnw.domain.Moduleinfo;
import cn.nnw.domain.Usermodule;
import cn.nnw.utils.HibernateUtils;

public class HotLinkChangeServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Session session = null;
		String colorvalue="";
		String up_hotmodule = "";//"<li class='widget color-white' id='appid-4'><div class='widget-head'><h3>实时热点</h3></div><div class='widget-content'><a id='hotchange' href='#' style='float: right; color:black'><i class='icon-repeat'></i> 换一换</a><ul>";
	   // String down_hotmodule = "";//"</ul></div></li>";
	    //String mid_hotmodule;
	    String hotmodule;
	    String hothtml;
	    String hotlink;
	    String title;
	    String hotvideo="";
	    //Session session = null;
	   // 显示的热点数
	    int hotcount = 4;
	   	try {
	   		 session = HibernateUtils.getSession();
	   		 session.beginTransaction();
	   		 
	   		 String hql = " select hotlink, title from Hotlink";      
	         Query query = session.createQuery(hql);      
	        //默认查询出来的list里存放的是一个Object数组      
	         List<Object[]> list = query.list();
	         int[] oldi = new int[hotcount];
	         for(int i=0;i<list.size()&&i<hotcount;i++){ 
	        	 Random ran=null;
	        	 int j=0;
	        	 boolean contn=true;
	        	 while(contn){
	        		 ran = new Random();
	        		 j= ran.nextInt(list.size());
		        	 for(int n=0;n<hotcount;n++){
		        		  if(oldi[n]==j){
		        			  break;
		        		  }
		        		  else if(oldi[n]!=j&&n==hotcount-1){
		        			  contn=false;
		        		  }
		        	 }
	        	 }
	        	
	        	 Object[] object =list.get(j);
	             hotlink = (String)object[0];      
	             title = (String)object[1];
	             System.out.println("---------"+title);
	            // hothtml = "<li><a href='"+hotlink+"' style='color:black'>"+title+"</a></li>";
	             hothtml = "<li><a href='#' style='color:black' onclick='vlcstart(this);' value='"+hotlink+"'>"+title+"</a></li>";
	             up_hotmodule += hothtml;
	         }
	         session.getTransaction().commit();
	         //JSONObject hotmodule_html = JSONObject.fromObject(up_hotmodule);
	         String hotmodule_html=up_hotmodule.replaceAll("\"", "\'");
	         String json = "{module_html:\""+hotmodule_html+"\"}";

	         JSONObject jo = JSONObject.fromObject(json);
	         response.setContentType("application/x-json");
             response.getWriter().write(jo.toString());

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