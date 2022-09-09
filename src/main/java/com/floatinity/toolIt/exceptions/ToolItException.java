package com.floatinity.toolIt.exceptions;

import java.util.HashSet;
import java.util.Set;

import com.floatinity.core.exception.FLOException;

public class ToolItException extends FLOException {

	private static final long serialVersionUID = 1L;

	private Set<ToolItError> toolItErrors = new HashSet<ToolItError>();

	private String info;

	public ToolItException(Throwable cause) {
		super(cause);
	}

	public ToolItException(String message) {
		super(message);
	}

	public ToolItException(ToolItError toolItErrors) {
		super(toolItErrors.getErrKey());
		this.toolItErrors.add(toolItErrors);
	}

	public ToolItException(Set<ToolItError> toolItErrors) {
		super("");
		this.toolItErrors = toolItErrors;
	}

	public ToolItException(ToolItError toolItError, String info) {
		super(toolItError.getErrKey());
		this.toolItErrors.add(toolItError);
		this.setInfo(info);
	}

	public Set<ToolItError> getToolItErrors() {
		return toolItErrors;
	}

	public void setToolItErrors(Set<ToolItError> toolItErrors) {
		this.toolItErrors = toolItErrors;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
