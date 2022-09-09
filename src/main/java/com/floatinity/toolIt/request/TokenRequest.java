package com.floatinity.toolIt.request;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.floatinity.core.object.FLOUtil;
import com.floatinity.toolIt.exceptions.ToolItError;
import com.floatinity.toolIt.exceptions.ToolItException;


/**
 * CREATED BY AKSHAY KHANDAGALE ON 01-Mar-2022
 */
public class TokenRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public void validate() throws ToolItException {
		Set<ToolItError> toolItErrors = new HashSet<ToolItError>();
		if (StringUtils.isEmpty(this.token)) {
			toolItErrors.add(ToolItError.TI_ERR_INVALID_TOKEN);
		}
		if (!FLOUtil.isNullOrEmpty(toolItErrors)) {
			throw new ToolItException(toolItErrors);
		}
	}

}
