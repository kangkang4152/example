package com.kan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kan.dao.entity.UserEntity;


@Service
public interface UserDao {

	public UserEntity getById(int id);
	
	public UserEntity getUser(int id);
	
	public boolean insert(UserEntity user);
	
	public boolean delete(int id);
	
	public List list(Map map);
}
