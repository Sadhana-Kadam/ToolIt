package com.floatinity.toolIt.service;

import com.floatinity.toolIt.exceptions.ToolItException;

public interface IValidationService {

	public String validatePassword(String pass) throws ToolItException;

	public String validateEncryption(String string) throws ToolItException;
}
