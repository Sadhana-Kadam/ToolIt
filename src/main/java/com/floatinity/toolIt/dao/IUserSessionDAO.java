package com.floatinity.toolIt.dao;

import com.floatinity.toolIt.dao.beans.UserSession;
import com.floatinity.toolIt.request.UserSessionCriteria;

public interface IUserSessionDAO {

	public Integer saveSession(UserSession userSession);

	public UserSession getUserSession(int sessionId);

	public UserSession getActiveUserSession(UserSessionCriteria criteria);

	public boolean clearUserSession(UserSessionCriteria criteria);
}
