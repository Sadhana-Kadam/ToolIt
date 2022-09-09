package com.floatinity.toolIt.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;

public interface IGenericDAO<T> {

	public <E> void update(E entity);

	public <E> List<E> getAll(Class<?> tableToQuery);

	public <E> List<E> get(Criteria criteria);

	public <E> List<E> get(String hql);

	public <E> E getById(Class<?> tableToQuery, Integer id);

	public int deleteByIDs(Class<?> tableToQuery, Set<Integer> ids);
	
	public int deleteByID(Class<?> tableToQuery, Integer id);

	public int getCount(Class<?> tableToQuery, Criteria criteria);

	public int getCount(Class<?> tableToQuery);

	public <E> Integer save(E orm);

	public <E> boolean delete(E entity);

	public boolean executeQuery(String hql);

	public <E> List<E> getByIds(Class<?> tableToQuery, Set<Integer> ids);

}
