package com.floatinity.toolIt.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.floatinity.core.object.FLOUtil;
import com.floatinity.toolIt.dao.beans.User;
import com.floatinity.toolIt.pojo.RoleMap;
import com.floatinity.toolIt.response.UserResponse;


/**
 * CREATED BY AKSHAY KHANDAGALE ON 01-Mar-2022
 */
public class UserPojoConverter {

	public static UserResponse getUser(User user, List<RoleMap> userRoleMap) {
		UserResponse userReponse = null;
		if (user != null) {
			userReponse = new UserResponse();
			BeanUtils.copyProperties(user, userReponse);
			userReponse.setUserId(user.getId());
			userReponse.setRoleMap(userRoleMap);
		}
		return userReponse;
	}

	public static UserResponse getUser(UserResponse user, List<RoleMap> userRoleMap) {
		UserResponse userReponse = null;
		if (user != null) {
			userReponse = new UserResponse();
			BeanUtils.copyProperties(user, userReponse);
			userReponse.setRoleMap(userRoleMap);
		}
		return userReponse;
	}

	public static List<String> extractMailIds(List<UserResponse> users) {
		List<String> emailIds = new ArrayList<String>();
		if(!FLOUtil.isNullOrEmpty(users)) {
			for (UserResponse user : users) {
				emailIds.add(user.getEmail());
			}
		}
		return emailIds;
	}

}
