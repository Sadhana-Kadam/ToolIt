package com.floatinity.toolIt.util;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.floatinity.core.helper.FLOHelper;
import com.floatinity.core.object.FLOUtil;
import com.floatinity.toolIt.constants.ToolItConstants;
import com.floatinity.toolIt.dao.beans.User;
import com.floatinity.toolIt.enums.RoleName;
import com.floatinity.toolIt.enums.RoleType;
import com.floatinity.toolIt.exceptions.ToolItError;
import com.floatinity.toolIt.exceptions.ToolItException;
import com.floatinity.toolIt.pojo.RoleMap;

/**
 * CREATED BY AKSHAY KHANDAGALE ON 01-Mar-2022
 */
public class CommonUtil {

	private static final Logger LOG = LoggerFactory.getLogger(CommonUtil.class);

	public static void closeStreams(AutoCloseable... streamArray) {
		if (ArrayUtils.isEmpty(streamArray))
			return;
		for (AutoCloseable stream : streamArray) {
			if (stream != null) {
				try {
					stream.close();
				} catch (Exception e) {
					LOG.error("Error occurred while closing streams: {}", e);
				}
			}
		}
	}

	public static void closeStreams(Closeable... streamArray) {
		if (ArrayUtils.isEmpty(streamArray))
			return;
		for (Closeable stream : streamArray) {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					LOG.error("Error occurred while closing streams: {}", e);
				}
			}
		}
	}

	public static Cookie getCookie(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		Cookie cookieToReturn = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					cookieToReturn = cookie;
					break;
				}
			}
		}
		return cookieToReturn;
	}

	public static String generateToken(Integer userId) throws ToolItException {
		LOG.info("Inside generate token method");
		String unencyptedToken = FLOHelper.getRandomAlpha(ToolItConstants.SALT_LENGTH)
				+ DateUtil.getCurrentUTCDateAsString(DateUtil.UTC_DATE_FORMAT_IN_MS)
				+ FLOHelper.getRandomAlpha(ToolItConstants.SALT_LENGTH) + userId
				+ FLOHelper.getRandomAlpha(ToolItConstants.SALT_LENGTH);
		try {
			return AESEncrypter.getInstance(AESEncrypter.KEY).encrypt(unencyptedToken);
		} catch (Exception e) {
			throw new ToolItException(e);
		}
	}

	public static String decryptToken(String encryptedToken) throws ToolItException {
		LOG.info("Inside decrypt token method");
		String userId = "";
		try {
			String decryptedToken = AESEncrypter.getInstance(AESEncrypter.KEY).decrypt(encryptedToken);
			userId = decryptedToken.substring(24, decryptedToken.length() - 5);
		} catch (ToolItException ce) {
			LOG.error("Error occurred while decrypting token :" + ce);
			throw new ToolItException(ToolItError.TI_ERR_TOKEN_AUTH_FAILURE);
		}
		return userId;
	}

//	public static List<Integer> getDeptIds(String depts) {
//		List<Integer> deptIds = null;
//		if (StringUtils.isNotEmpty(depts)) {
//			List<String> deptList = Arrays.asList(StringUtils.split(depts, ","));
//			deptIds = deptList.stream().map(Integer::parseInt).collect(Collectors.toList());
//		}
//		return deptIds;
//	}

	public static boolean containsSuperAdmin(List<RoleMap> userRoleMap) {
		boolean isSuperAdmin = false;
		if (!FLOUtil.isNullOrEmpty(userRoleMap)) {
			for (RoleMap roleMap : userRoleMap) {
				if (RoleType.SUPER_ADMIN.ordinal() == roleMap.getRoleType()) {
					isSuperAdmin = true;
					break;
				}
			}
		}
		return isSuperAdmin;
	}

	public static String getUserName(User user) {
		StringBuilder userName = new StringBuilder("");
		if (user != null) {
			if (StringUtils.isNotEmpty(user.getFirstName())) {
				userName.append(user.getFirstName());
			}
//			if (StringUtils.isNotEmpty(user.getMiddleName())) {
//				userName.append(ToolItConstants.SPACE);
//				userName.append(user.getMiddleName());
//			}
			if (StringUtils.isNotEmpty(user.getLastName())) {
				userName.append(ToolItConstants.SPACE);
				userName.append(user.getLastName());
			}
		}
		return userName.toString();
	}

	public static int getFileSizeInKB(MultipartFile file) {
		return (int) Math.ceil(file.getSize() / 1024);
	}

	public static int getFileSizeInMB(MultipartFile file) {
		return (int) Math.ceil(getFileSizeInKB(file) / 1024);
	}

	public static boolean isOnlyCompanyAdmin(List<RoleMap> userRoleMap) {
		boolean isOnlyCompanyAdmin = false;
		if (!FLOUtil.isNullOrEmpty(userRoleMap) && userRoleMap.size() == 1
				&& RoleName.COMPANY_ADMIN.getRoleName().equals(userRoleMap.get(0).getRoleName())) {
			isOnlyCompanyAdmin = true;
		}
		return isOnlyCompanyAdmin;
	}
}
