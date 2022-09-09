package com.floatinity.toolIt.dao;

import java.util.List;

import com.floatinity.toolIt.dao.beans.UserRoleMap;
import com.floatinity.toolIt.pojo.RoleMap;

/**
 * CREATED BY AKSHAY KHANDAGALE ON 01-Mar-2022
 */
public interface IUserRoleMapDAO extends IGenericDAO<UserRoleMap> {

	public List<RoleMap> getUserRoleMap(Integer userId);

	public int deleteRoleMap(Integer userId);
}
