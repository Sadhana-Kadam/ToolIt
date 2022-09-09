package com.floatinity.toolIt.request;

import java.sql.Timestamp;

import com.floatinity.toolIt.enums.UserSessionStatus;

/**
 * CREATED BY AKSHAY KHANDAGALE ON 01-Mar-2022
 */
public class UserSessionCriteria {

	private Integer sessionId;

	private Integer userId;

	private String sessionToken;

	private UserSessionStatus status;

	private Timestamp createdTS;

	private Timestamp expiredTS;

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public UserSessionStatus getStatus() {
		return status;
	}

	public void setStatus(UserSessionStatus status) {
		this.status = status;
	}

	public Timestamp getCreatedTS() {
		return createdTS;
	}

	public void setCreatedTS(Timestamp createdTS) {
		this.createdTS = createdTS;
	}

	public Timestamp getExpiredTS() {
		return expiredTS;
	}

	public void setExpiredTS(Timestamp expiredTS) {
		this.expiredTS = expiredTS;
	}

}
