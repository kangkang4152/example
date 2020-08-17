package com.kan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kan.dao.UserDao;
import com.kan.dao.entity.UserEntity;

@Service
public class UserService {

	@Autowired
	private  UserDao  userDao;
	
	public List<UserEntity> list(){
		Map map = new HashMap();
		return userDao.list(map);
	}
	
	
	@Transactional
	public void testWithTranstaction() {
		UserEntity user = userDao.getById(2);
		System.out.println(user.getName());
		
		UserEntity user1 = userDao.getById(2);
		System.out.println(user1.getName());
		
		System.out.println(user == user1);
	}
	
	public void testWithOutTranstaction() {
		UserEntity user = userDao.getById(2);
		System.out.println(user.getName());
		
		UserEntity user1 = userDao.getById(2);
		System.out.println(user1.getName());
		
		System.out.println(user == user1);
	}
}
