package cn.nnw.service.impl;

import cn.nnw.dao.UserDao;
import cn.nnw.dao.impl.UserDaoImpl;
import cn.nnw.domain.User;
import cn.nnw.exception.UserExistException;
import cn.nnw.utils.ServiceUtils;

public class BusinessServiceImpl {
	
	private UserDao dao = new UserDaoImpl();
	
	
	
	public void register(User user) throws UserExistException{
		
		boolean b = dao.find(user.getUseremail());
		if(b){
			throw new UserExistException();
		}else{
			//System.out.println(user.getUseremail()+"-----"+user.getPassword());
			user.setPassword(ServiceUtils.md5(user.getPassword()));
			dao.add(user);
			
		}
		
	}
	
	public User login(String username,String password){
		
	    password = ServiceUtils.md5(password);
		
		return dao.find(username, password);
	}

}
