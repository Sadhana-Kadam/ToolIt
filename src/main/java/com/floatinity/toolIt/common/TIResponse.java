package com.floatinity.toolIt.common;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.floatinity.toolIt.enums.TIStatus;
import com.floatinity.toolIt.exceptions.ToolItError;
/**
 * 
 * @author Floatinity-Laptop-1
 * 
 */
public class TIResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private TIStatus status;

	private Set<ToolItError> toolItErrors = new HashSet<ToolItError>();

	private String info;

	private String details;

	public TIResponse() {
		this.status = TIStatus.SUCCESS;
	}

	public TIResponse(TIStatus success) {
		this.status = TIStatus.SUCCESS;
	}

	public static TIResponse getSuccessResponse() {
		TIResponse response = new TIResponse();
		response.setStatus(TIStatus.SUCCESS);
		return response;
	}

	public static TIResponse getSuccessResponse(String info) {
		TIResponse response = new TIResponse();
		response.setStatus(TIStatus.SUCCESS);
		response.setInfo(info);
		return response;
	}

	public static TIResponse getFailureResponse() {
		TIResponse response = new TIResponse();
		response.setStatus(TIStatus.FAILED);
		return response;
	}

	public static TIResponse getFailureResponse(ToolItError error) {
		TIResponse response = getFailureResponse();
		response.setToolItError(error);
		return response;
	}

	public static TIResponse getFailureResponse(ToolItError error, String info) {
		TIResponse response = getFailureResponse();
		response.setToolItError(error);
		response.setInfo(info);
		return response;
	}

	public static TIResponse getFailureResponse(Set<ToolItError> error) {
		TIResponse response = getFailureResponse();
		response.setToolItErrors(error);
		return response;
	}
	
	public static TIResponse getFailureResponse(String message) {
		TIResponse response = new TIResponse();
		response.setStatus(TIStatus.SUCCESS);
		response.setInfo(message);
		return response;
	}
	
	public TIStatus getStatus() {
		return status;
	}

	public void setStatus(TIStatus status) {
		this.status = status;
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

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setToolItError(ToolItError toolItError) {
		this.toolItErrors.add(toolItError);
	}

}
