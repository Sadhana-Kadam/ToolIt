package com.floatinity.toolIt.response;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.floatinity.toolIt.enums.APIStatus;
import com.floatinity.toolIt.exceptions.ToolItError;

/**
 * CREATED BY AKSHAY KHANDAGALE ON 01-Mar-2022
 */
public class BaseResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private APIStatus status;

	private Set<ToolItError> toolItError = new HashSet<ToolItError>();

	private Object data;

	public BaseResponse() {
		this.status = APIStatus.SUCCESS;
	}

	public BaseResponse(APIStatus success) {
		this.status = APIStatus.SUCCESS;
	}

	public static BaseResponse getSuccessResponse() {
		BaseResponse response = new BaseResponse();
		response.setStatus(APIStatus.SUCCESS);
		return response;
	}

	public static BaseResponse getSuccessResponse(String info) {
		BaseResponse response = new BaseResponse();
		response.setStatus(APIStatus.SUCCESS);
		return response;
	}

	public static BaseResponse getFailureResponse() {
		BaseResponse response = new BaseResponse();
		response.setStatus(APIStatus.FAILED);
		return response;
	}

	public static BaseResponse getFailureResponse(ToolItError error) {
		BaseResponse response = getFailureResponse();
		response.setToolItError(error);
		return response;
	}

	public static BaseResponse getFailureResponse(ToolItError error, String info) {
		BaseResponse response = getFailureResponse();
		response.setToolItError(error);
		return response;
	}

	public static BaseResponse getFailureResponse(Set<ToolItError> error) {
		BaseResponse response = getFailureResponse();
		response.setToolItError(error);
		return response;
	}

	public static BaseResponse getFailureResponse(String message) {
		BaseResponse response = new BaseResponse();
		response.setStatus(APIStatus.SUCCESS);
		return response;
	}

	public APIStatus getStatus() {
		return status;
	}

	public void setStatus(APIStatus status) {
		this.status = status;
	}

	public Set<ToolItError> getToolItError() {
		return toolItError;
	}

	public void setToolItError(Set<ToolItError> toolItError) {
		this.toolItError = toolItError;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setToolItError(ToolItError toolItError) {
		this.toolItError.add(toolItError);
	}

}
