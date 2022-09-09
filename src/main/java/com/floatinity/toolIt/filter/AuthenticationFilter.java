package com.floatinity.toolIt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.floatinity.toolIt.auth.ToolItRequestInfoHolder;
import com.floatinity.toolIt.constants.ToolItConstants;
import com.floatinity.toolIt.dao.beans.UserSession;
import com.floatinity.toolIt.exceptions.ToolItError;
import com.floatinity.toolIt.exceptions.ToolItException;
import com.floatinity.toolIt.service.IAuthService;


/**
 * CREATED BY AKSHAY KHANDAGALE ON 01-Mar-2022
 */
@Component
public class AuthenticationFilter implements Filter {

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationFilter.class);

	public static final int LOCAL_PORT = 8080;

	@Autowired
	private IAuthService authService;

	public AuthenticationFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse resp = (HttpServletResponse) servletResponse;

		if (LOCAL_PORT != request.getLocalPort()) {
			LOG.error("Application is not running on expected port, current port : {} ", request.getLocalPort());
			System.exit(0);
		}
		String url = request.getRequestURL().toString();
		if (url.contains("/login") || url.contains("/logout")) {
			LOG.debug("Skip Authentication for login and logout APIs, URL : {}", url);
			chain.doFilter(request, servletResponse);
			return;
		}
		try {
			String token = request.getHeader(ToolItConstants.TI_COOKIE_NAME);
			if (StringUtils.isEmpty(token)) {
				LOG.debug("Token cookie not found in request");
				throw new ToolItException(ToolItError.TI_ERR_TOKEN_AUTH_FAILURE);
			}
			UserSession userSession = authService.authenticateUser(token);
			LOG.debug("User is authenticated , userId : {}", userSession.getUserId());
			ToolItRequestInfoHolder.setUserId(userSession.getUserId());
		} catch (ToolItException e) {
			if (e.getToolItErrors().contains(ToolItError.TI_ERR_TOKEN_AUTH_FAILURE)) {
				resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
				resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getStackTrace().toString());
				LOG.error("Token Authentication failed, error : {}", e);
				return;
			} else {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getStackTrace().toString());
				LOG.error("Token Authentication failed, error : {}", e);
				return;
			}
		}
		// pass the request along the filter chain
		chain.doFilter(request, servletResponse);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}