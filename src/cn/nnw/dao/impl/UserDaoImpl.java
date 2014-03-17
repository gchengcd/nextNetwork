package cn.nnw.dao.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.hibernate.Query;
import org.hibernate.Session;

import cn.nnw.dao.UserDao;
import cn.nnw.domain.User;
import cn.nnw.utils.HibernateUtils;
import cn.nnw.utils.XmlUtils;
 
public class UserDaoImpl implements UserDao  {

	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.UserDao#add(cn.itcast.domain.User)
	 */
	public void add(User user){
		Session session = null;
	   	 try {
	   		 session = HibernateUtils.getSession();
	   		 session.beginTransaction();
	   		 session.save(user);
	   		 session.getTransaction().commit();
	   	 }catch(Exception e){
	   		 e.printStackTrace();
	   		 session.getTransaction().rollback();
	   	 }finally{
	   		 HibernateUtils.closeSession(session);
	   	 }
	}
	
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.UserDao#find(java.lang.String, java.lang.String)
	 */
	public User find(String username,String password){
		Session session = null;
		try{
			
			session = HibernateUtils.getSession();
			session.beginTransaction();
			String hql = "select useremail,password from User";      
	         Query query = session.createQuery(hql);           
	         List<Object[]> list = query.list(); 
	         session.getTransaction().commit();
	        for(Object[] object : list){      
	             String useremail = (String)object[0];   
	             if(useremail.equals(username)){
	            	 if(password.equals((String)object[1])){
	            		 User user = new User();
	            		 user.setUseremail(username);
	            		 user.setPassword(password);
	            		 System.out.println("");
	            		 return user;
	            	 }
	             }
	         }
			return null;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//查找注册的用户是否在数据库中存在
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.UserDao#find(java.lang.String)
	 */
	public boolean find(String username){
		 Session session = null;
	   	 try {
	   		 session = HibernateUtils.getSession();
	   		 session.beginTransaction();
	   		 //User user = new User();
	   		 String hql = " select useremail,password from User";      
	         Query query = session.createQuery(hql);           
	         List<Object[]> list = query.list(); 
	         session.getTransaction().commit();
	        for(Object[] object : list){      
	             String useremail = (String)object[0];   
	             if(useremail.equals(username)){
	            	 return true;
	             }
	         }
	         return false;
	   		 
	   	 }catch(Exception e){
	   		 e.printStackTrace();
	   		 session.getTransaction().rollback();
	   	 }finally{
	   		 HibernateUtils.closeSession(session);
	   	 }
	   	 return true;
		
	}
}
