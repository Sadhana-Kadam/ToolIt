package com.floatinity.toolIt.auth;

/**
 * CREATED BY AKSHAY KHANDAGALE ON 01-Mar-2022
 */
public class ToolItRequestInfo implements Cloneable {

	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public ToolItRequestInfo clone() throws CloneNotSupportedException {
		ToolItRequestInfo info = ToolItRequestInfo.class.cast(super.clone());
		return info;
	}

}
