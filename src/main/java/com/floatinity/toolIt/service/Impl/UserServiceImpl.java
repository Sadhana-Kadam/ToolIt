package com.floatinity.toolIt.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.floatinity.toolIt.dao.IUserDAO;
import com.floatinity.toolIt.dao.beans.User;
import com.floatinity.toolIt.exceptions.ToolItException;
import com.floatinity.toolIt.service.IUserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class UserServiceImpl implements IUserService{
	
	private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);
	
	@Autowired
	private IUserDAO userDAO;

	@Override
	public List<User> getAllUsers() throws ToolItException {
		// TODO Auto-generated method stub
		LOG.info("inside get all user");
		return userDAO.getAll(User.class);
	}

}
