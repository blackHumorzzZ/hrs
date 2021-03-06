package com.qfedu.service.impl;

import org.apache.commons.codec.digest.DigestUtils;

import com.qfedu.domain.User;
import com.qfedu.persistence.UserDao;
import com.qfedu.persistence.impl.UserDaoImpl;
import com.qfedu.service.UserService;

/**
 * 用户相关业务实现类
 * @author 羊波
 *
 */
public class UserServiceImpl implements UserService {
	private UserDao userDao = new UserDaoImpl();
	
	@Override
	public boolean login(String username, String password) {
		User user=userDao.findById(username);
		if (user!=null){
			String pass=user.getPassword();
			String md5=DigestUtils.md5Hex(password);
			return pass.equals(md5);
		}
		return false;
	}

	@Override
	public boolean register(User user) {
		String username=user.getUsername();
		User temp = userDao.findById(username);
		if (temp!=null){
			String md5=DigestUtils.md5Hex(user.getPassword());
			user.setPassword(md5);
			return  userDao.save(user)!=null;
		}
		return false;
	}

}
