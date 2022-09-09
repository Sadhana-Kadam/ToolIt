package com.floatinity.toolIt.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.floatinity.toolIt.dao.beans.UserSession;
import com.floatinity.toolIt.exceptions.ToolItException;
import com.floatinity.toolIt.request.LoginRequest;
import com.floatinity.toolIt.request.TokenRequest;
import com.floatinity.toolIt.response.UserResponse;

/**
 * CREATED BY AKSHAY KHANDAGALE ON 01-Mar-2022
 */
public interface IAuthService {

	public UserResponse login(HttpServletResponse httResponse, LoginRequest request) throws ToolItException;

	public void logout(HttpServletResponse httResponse, HttpServletRequest request) throws ToolItException;

	public UserSession authenticateUser(String token) throws ToolItException;

	public UserResponse getUserDetails(TokenRequest request) throws ToolItException;
}
