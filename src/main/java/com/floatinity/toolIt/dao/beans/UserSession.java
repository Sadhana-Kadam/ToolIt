package com.floatinity.toolIt.dao.beans;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.floatinity.toolIt.enums.UserSessionStatus;

/**
 * CREATED BY AKSHAY KHANDAGALE ON 01-Mar-2022
 */
@Entity
@Table(name = "ti_user_session")
public class UserSession {

	@Id
	@GeneratedValue
	@Column(name = "user_session_id", nullable = false)
	private Integer userSeesionId;

	@Column(name = "user_id", nullable = false)
	private Integer userId;

	@Column(name = "session_token", nullable = false)
	private String sessionToken;

	@Column(name = "status", nullable = false)
	private UserSessionStatus status;

	@Column(name = "created_ts", insertable = false, updatable = false)
	private Timestamp createdTS;

	@Column(name = "expired_ts", nullable = true)
	private Timestamp expiredTS;

	public UserSession() {

	}

	public Integer getUserSeesionId() {
		return userSeesionId;
	}

	public void setUserSeesionId(Integer userSeesionId) {
		this.userSeesionId = userSeesionId;
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

	public void setStatus(UserSessionStatus active) {
		this.status = active;
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
