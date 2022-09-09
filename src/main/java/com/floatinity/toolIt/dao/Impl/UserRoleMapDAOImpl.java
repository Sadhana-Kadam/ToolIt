package com.floatinity.toolIt.dao.Impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.floatinity.toolIt.constants.SQLConstants;
import com.floatinity.toolIt.dao.IUserRoleMapDAO;
import com.floatinity.toolIt.dao.beans.UserRoleMap;
import com.floatinity.toolIt.pojo.RoleMap;


/**
 * CREATED BY AKSHAY KHANDAGALE ON 01-Mar-2022
 */
@Repository
public class UserRoleMapDAOImpl extends GenericDAOImpl<UserRoleMap> implements IUserRoleMapDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<RoleMap> getUserRoleMap(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(SQLConstants.GET_USER_ROLE_MAP);
		query.setParameter("userId", userId);
		query.setResultTransformer(Transformers.aliasToBean(RoleMap.class));
		return (List<RoleMap>) query.list();
	}

	@Override
	@Transactional
	public int deleteRoleMap(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(SQLConstants.DELETE_USER_ROLE_MAP);
		query.setParameter("userId", userId);
		return query.executeUpdate();
	}

	@Override
	public int getCount(Class<?> tableToQuery) {
		// TODO Auto-generated method stub
		return 0;
	}

}
