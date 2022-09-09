package com.floatinity.toolIt.service.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.floatinity.toolIt.constants.ToolItConstants;
import com.floatinity.toolIt.converter.UserPojoConverter;
import com.floatinity.toolIt.dao.IUserDAO;
import com.floatinity.toolIt.dao.IUserRoleMapDAO;
import com.floatinity.toolIt.dao.IUserSessionDAO;
import com.floatinity.toolIt.dao.beans.User;
import com.floatinity.toolIt.dao.beans.UserSession;
import com.floatinity.toolIt.enums.UserSessionStatus;
import com.floatinity.toolIt.exceptions.ToolItError;
import com.floatinity.toolIt.exceptions.ToolItException;
import com.floatinity.toolIt.pojo.RoleMap;
import com.floatinity.toolIt.request.LoginRequest;
import com.floatinity.toolIt.request.TokenRequest;
import com.floatinity.toolIt.request.UserSessionCriteria;
import com.floatinity.toolIt.response.UserResponse;
import com.floatinity.toolIt.service.IAuthService;
import com.floatinity.toolIt.service.IValidationService;
import com.floatinity.toolIt.util.CommonUtil;
import com.floatinity.toolIt.util.DateUtil;

@Service
public class AuthServiceImpl implements IAuthService{

	private static final Logger LOG = LogManager.getLogger(AuthServiceImpl.class);

	@Autowired
	private IUserDAO userDAO;

	@Autowired
	private IUserSessionDAO userSessionDAO;

	@Autowired
	private IUserRoleMapDAO userRoleMapDAO;

	@Autowired
	private IValidationService validationService;

	public UserResponse login(HttpServletResponse httResponse, LoginRequest request) throws ToolItException {
		request.validate();
		
		// validate demo license
		validateDemoLicense();

		// 1. Get user with email id
		User dbUser = userDAO.getUser(request.getUn());
		if (dbUser == null) {
			throw new ToolItException(ToolItError.TI_ERR_USER_DOES_NOT_EXIST);
		}
		// 2. check if user is active
//		if (!dbUser.getUserStatus()) {
//			throw new ToolItException(ToolItError.TI_ERR_USER_NOT_ACTIVE);
//		}
		// 3. check if user has login access
		if (!dbUser.getLoginAccess()) {
			throw new ToolItException(ToolItError.TI_ERR_USER_DOES_NOT_HAVE_LOGIN_ACCESS);
		}
		// 4. Check password in request with DB
		// Validate new password
		validationService.validatePassword(request.getPwd());
		if (!dbUser.getPassword().equals(request.getPwd())) {
			throw new ToolItException(ToolItError.TI_ERR_NEW_PASSWORD_INVALID);
		}
		// 5. After all validation prepare response

		List<RoleMap> userRoleMap = userRoleMapDAO.getUserRoleMap(dbUser.getId());
		UserResponse response = UserPojoConverter.getUser(dbUser, userRoleMap);

		// 5. set token cookie in response
		String token = CommonUtil.generateToken(dbUser.getId());
		Cookie tk = new Cookie(ToolItConstants.TI_COOKIE_NAME, token);
		tk.setDomain("localhost");
		httResponse.addCookie(tk);

		httResponse.addHeader(ToolItConstants.TI_COOKIE_NAME, token);

		// 6. Create user session entry
		UserSession userSession = new UserSession();
		userSession.setUserId(dbUser.getId());
		userSession.setExpiredTS(DateUtil.getTodaysEndTimestamp());
		userSession.setSessionToken(token);
		userSession.setStatus(UserSessionStatus.ACTIVE);
		Integer sessionId = userSessionDAO.saveSession(userSession);
		LOG.debug("User session details saved in DB : " + sessionId);

		return response;
	}

	private void validateDemoLicense() throws ToolItException {
		String expiryDateString = ToolItConstants.LICENSE_EXPIRY_DATE;
		int diffDays = 0;
		try {
			Date expiryDate = new SimpleDateFormat("dd-MM-yyyy").parse(expiryDateString);
			Date currentDate = new Date();
			long diff = expiryDate.getTime() - currentDate.getTime();
			diffDays = (int) (diff / (24 * 60 * 60 * 1000));
		} catch (ParseException e) {
			LOG.error("date parser exception", e);
		}
		if (diffDays < 0) {
			LOG.error("demo license expired");
			throw new ToolItException(ToolItError.TI_ERR_DEMO_LICENSE_EXPIRED);
		}

	}

	
	public void logout(HttpServletResponse response, HttpServletRequest request) throws ToolItException {
		String token = request.getHeader(ToolItConstants.TI_COOKIE_NAME);
		if (StringUtils.isEmpty(token)) {
			LOG.debug("Token not found in request");
			throw new ToolItException(ToolItError.TI_ERR_TOKEN_AUTH_FAILURE);
		}
		UserSessionCriteria criteria = new UserSessionCriteria();
		criteria.setSessionToken(token);
		boolean sessionCleared = userSessionDAO.clearUserSession(criteria);
		LOG.debug("User session details cleared from DB : " + sessionCleared);
	}

	
	public UserSession authenticateUser(String token) throws ToolItException {
		UserSessionCriteria criteria = new UserSessionCriteria();
		criteria.setSessionToken(token);
		UserSession userSession = userSessionDAO.getActiveUserSession(criteria);
		if (userSession == null) {
			throw new ToolItException(ToolItError.TI_ERR_TOKEN_AUTH_FAILURE);
		}

		// Check if session is timed out
		if (new Date().after(new Date(userSession.getExpiredTS().getTime()))) {
			throw new ToolItException(ToolItError.TI_ERR_TOKEN_AUTH_FAILURE);
		}
		return userSession;
	}

	
	public UserResponse getUserDetails(TokenRequest request) throws ToolItException {
		request.validate();
		LOG.debug("Inside get user details from token" + request.getToken());
		UserSessionCriteria criteria = new UserSessionCriteria();
		criteria.setSessionToken(request.getToken());
		UserSession userSession = userSessionDAO.getActiveUserSession(criteria);
		if (userSession == null) {
			LOG.debug("User Session not available for token");
			throw new ToolItException(ToolItError.TI_ERR_TOKEN_AUTH_FAILURE);
		}
		User dbUser = userDAO.getById(User.class, userSession.getUserId());
		List<RoleMap> userRoleMap = userRoleMapDAO.getUserRoleMap(dbUser.getId());
		return UserPojoConverter.getUser(dbUser, userRoleMap);
	}

}
