package com.floatinity.toolIt.dao.Impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.floatinity.toolIt.constants.SQLConstants;
import com.floatinity.toolIt.dao.IUserDAO;
import com.floatinity.toolIt.dao.beans.User;


/**
 * CREATED BY AKSHAY KHANDAGALE ON 01-Mar-2022
 */
@Repository
public class UserDAOImpl extends GenericDAOImpl<User> implements IUserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public User getUser(String emailId) {
		User user = null;
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", emailId));
		criteria.add(Restrictions.ne("userStatus", Boolean.FALSE));
		List<User> users = criteria.list();
		if (users != null && users.size() > 0) {
			user = users.get(0);
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> getAllUsers() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("userStatus", Boolean.TRUE));
		return (List<User>) criteria.list();
	}

	@Override
	@Transactional
	public Integer getUserCount(Class<?> className) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(className);
		criteria.add(Restrictions.ne("userStatus", Boolean.FALSE));
		criteria.setProjection(Projections.rowCount());
		return this.getCount(className, criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> getUsersByRole(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(SQLConstants.GET_USER_BY_ROLE);
		query.setParameter("role", id);
		query.setResultTransformer(Transformers.aliasToBean(User.class));
		return (List<User>) query.list();
	}

	@Override
	public int getCount(Class<?> tableToQuery) {
		// TODO Auto-generated method stub
		return 0;
	}

}
