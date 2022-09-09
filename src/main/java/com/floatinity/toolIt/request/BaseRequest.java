package com.floatinity.toolIt.request;

import java.io.Serializable;

import com.floatinity.toolIt.exceptions.ToolItException;

/**
 * CREATED BY AKSHAY KHANDAGALE ON 01-Mar-2022
 */
public abstract class BaseRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract void validate() throws ToolItException;

}