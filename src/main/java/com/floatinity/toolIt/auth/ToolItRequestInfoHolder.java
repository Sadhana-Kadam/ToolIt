package com.floatinity.toolIt.auth;

/**
 * Created by Rohan Bharadwaj on May 30, 2020
 */
public class ToolItRequestInfoHolder {

	private static final ThreadLocal<ToolItRequestInfo> REQUESTINFOHOLDER = new ThreadLocal<ToolItRequestInfo>();

	public static synchronized void setRequestInfo(ToolItRequestInfo requestInfo) {
		REQUESTINFOHOLDER.set(requestInfo);
	}

	public static ToolItRequestInfo getRequestInfo() {
		ToolItRequestInfo requestInfo = REQUESTINFOHOLDER.get();
		if (requestInfo == null) {
			requestInfo = new ToolItRequestInfo();
		}
		return requestInfo;
	}

	public static void clearRequestInfo() {
		REQUESTINFOHOLDER.remove();
	}

	public static void setUserId(Integer userId) {
		ToolItRequestInfo requestInfo = ToolItRequestInfoHolder.getRequestInfo();
		requestInfo.setUserId(userId);
		ToolItRequestInfoHolder.setRequestInfo(requestInfo);
	}

	public static Integer getUserId() {
		ToolItRequestInfo requestInfo = ToolItRequestInfoHolder.getRequestInfo();
		return requestInfo.getUserId() != null ? requestInfo.getUserId() : 1;
	}
}
