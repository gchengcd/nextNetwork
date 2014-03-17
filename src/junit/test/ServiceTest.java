package junit.test;

import java.util.Date;

import org.hibernate.Session;
import org.junit.Test;

import cn.nnw.domain.User;
import cn.nnw.exception.UserExistException;
import cn.nnw.service.impl.BusinessServiceImpl;
import cn.nnw.utils.HibernateUtils;

public class ServiceTest {
	
	
	@Test
     public void testRegister(){
		Session session = null;
    	 try {
    		 session = HibernateUtils.getSession();
    		 session.beginTransaction();
    		 
    		 User user = new User();
    		 user.setUseremail("popo");
    		 user.setPassword("123456");
    		 user.setNickname("poopo");
    		 session.save(user);
    		 session.getTransaction().commit();
    	 }catch(Exception e){
    		 e.printStackTrace();
    		 session.getTransaction().rollback();
    	 }finally{
    		 HibernateUtils.closeSession(session);
    	 }
       
     }
     
     @Test
     public void testLogin(){
    	 BusinessServiceImpl service = new BusinessServiceImpl();
    	 User user = service.login("kkk", "1234");
    	 System.out.println(user);
     }
}
