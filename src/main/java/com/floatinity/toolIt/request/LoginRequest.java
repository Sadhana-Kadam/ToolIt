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
public class LoginRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;

	private String un;

	private String pwd;

	public String getUn() {
		return un;
	}

	public void setUn(String un) {
		this.un = un;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public void validate() throws ToolItException {
		Set<ToolItError> toolItErrors = new HashSet<ToolItError>();
		if (StringUtils.isEmpty(this.un)) {
			toolItErrors.add(ToolItError.TI_ERR_USERNAME_INVALID);
		}
		if (StringUtils.isEmpty(this.pwd)) {
			toolItErrors.add(ToolItError.TI_ERR_PASSWORD_INVALID);
		}
		if (!FLOUtil.isNullOrEmpty(toolItErrors)) {
			throw new ToolItException(toolItErrors);
		}
	}

}
