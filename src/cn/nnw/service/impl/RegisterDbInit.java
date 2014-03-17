package cn.nnw.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import cn.nnw.domain.Moduleinfo;
import cn.nnw.domain.Usermodule;
import cn.nnw.utils.HibernateUtils;

public class RegisterDbInit {

	public void dbInit(String username){
		Session session = null;
	   	try {
	   		 session = HibernateUtils.getSession();
	   		 session.beginTransaction();
	   		 Usermodule module4 = new Usermodule();
	   		 module4.setUseremail(username);
	   		 module4.setModuleid("moduleid-4");
	   		 session.save(module4);
	   		Usermodule module5 = new Usermodule();
	   		 module5.setUseremail(username);
	   		 module5.setModuleid("moduleid-5");
	   		 session.save(module5);
	   		Usermodule module6 = new Usermodule();
	   		 module6.setUseremail(username);
	   		 module6.setModuleid("moduleid-6");
	   		 session.save(module6);
	   		Usermodule module8 = new Usermodule();
	   		 module8.setUseremail(username);
	   		 module8.setModuleid("moduleid-8");
	   		 session.save(module8);
	   		Usermodule module9 = new Usermodule();
	   		 module9.setUseremail(username);
	   		 module9.setModuleid("moduleid-9");
	   		 session.save(module9);
	   		Usermodule module10 = new Usermodule();
	   		 module10.setUseremail(username);
	   		 module10.setModuleid("moduleid-10");
	   		 session.save(module10);
	
             session.getTransaction().commit();
	   	 }catch(Exception e){
	   		 e.printStackTrace();
	   		 session.getTransaction().rollback();
	   	 }finally{
	   		 HibernateUtils.closeSession(session);
	   	 }
	}
}
