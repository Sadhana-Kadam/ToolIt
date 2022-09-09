package com.floatinity.toolIt.dao.Impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.floatinity.core.object.FLOUtil;
import com.floatinity.toolIt.dao.IUserSessionDAO;
import com.floatinity.toolIt.dao.beans.UserSession;
import com.floatinity.toolIt.enums.UserSessionStatus;
import com.floatinity.toolIt.request.UserSessionCriteria;

@Repository
public class UserSessionDAOImpl implements IUserSessionDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Integer saveSession(UserSession userSession) {
		Session session = sessionFactory.getCurrentSession();
		Integer sessionId = (Integer) session.save(userSession);
		return sessionId;
	}

	@Override
	@Transactional
	public UserSession getUserSession(int sessionId) {
		Session session = sessionFactory.getCurrentSession();
		UserSession userSession = (UserSession) session.get(UserSession.class, sessionId);
		return userSession;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public UserSession getActiveUserSession(UserSessionCriteria sessionCriteria) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserSession.class);
		if (StringUtils.isNotBlank(sessionCriteria.getSessionToken())) {
			criteria.add(Restrictions.eq("sessionToken", sessionCriteria.getSessionToken()));
		}
		if (sessionCriteria.getUserId() != null) {
			criteria.add(Restrictions.eq("userId", sessionCriteria.getUserId()));
		}
		criteria.add(Restrictions.eq("status", UserSessionStatus.ACTIVE));
		UserSession userSesion = null;
		List<UserSession> userSessions = (List<UserSession>) criteria.list();
		if (!FLOUtil.isNullOrEmpty(userSessions)) {
			userSesion = userSessions.get(0);
		}
		return userSesion;
	}

	@Override
	@Transactional
	public boolean clearUserSession(UserSessionCriteria sessionCriteria) {
		boolean sessionCleared = false;
		String queryString = "DELETE FROM UserSession WHERE ";
		boolean conatcAndClause = false;
		if (sessionCriteria.getUserId() != null) {
			queryString = queryString + "userId = :userId ";
			conatcAndClause = true;
		}
		if (StringUtils.isNotEmpty(sessionCriteria.getSessionToken())) {
			if (conatcAndClause) {
				queryString = queryString + " AND ";
			}
			queryString = queryString + " sessionToken = :sessionToken ";
			conatcAndClause = true;
		}
		Query query = sessionFactory.getCurrentSession().createQuery(queryString);
		if (sessionCriteria.getUserId() != null) {
			query.setParameter("userId", sessionCriteria.getUserId());
		}
		if (StringUtils.isNotEmpty(sessionCriteria.getSessionToken())) {
			query.setParameter("sessionToken", sessionCriteria.getSessionToken());
		}
		int updateCount = query.executeUpdate();
		if (updateCount > 0) {
			sessionCleared = true;
		}
		return sessionCleared;
	}

}
