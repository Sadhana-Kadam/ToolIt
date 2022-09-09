package com.floatinity.toolIt.dao;

import java.util.List;

import com.floatinity.toolIt.dao.beans.User;
import com.floatinity.toolIt.response.UserResponse;


/**
 * CREATED BY AKSHAY KHANDAGALE ON 01-Mar-2022
 */
public interface IUserDAO extends IGenericDAO<User> {

	public User getUser(String emailId);

	public List<User> getAllUsers();

	public Integer getUserCount(Class<?> className);

	public List<User> getUsersByRole(Integer id);

}
