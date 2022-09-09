package com.floatinity.toolIt.service;

import java.util.List;

import com.floatinity.toolIt.dao.beans.User;
import com.floatinity.toolIt.exceptions.ToolItException;

public interface IUserService {

	public List<User> getAllUsers() throws ToolItException;

}
