package com.floatinity.toolIt.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.floatinity.toolIt.response.BaseResponse;

/**
 * CREATED BY AKSHAY KHANDAGALE ON 01-Mar-2022
 */
@ControllerAdvice
public class ToolItExceptionController {

	private static final Logger LOGGER = LogManager.getLogger(ToolItExceptionController.class);

	@ExceptionHandler(value = HibernateException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	BaseResponse handleException(HibernateException dbException) {
		LOGGER.error("Error in controller", dbException);
		return BaseResponse.getFailureResponse(ToolItError.TI_DB_ERROR);
	}

	@ExceptionHandler(value = Throwable.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	BaseResponse handleException(Throwable exception) {
		LOGGER.error("Error in controller", exception);
		return BaseResponse.getFailureResponse(ToolItError.TI_APPLICATION_ERROR);
	}

	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	BaseResponse handleException(HttpMessageNotReadableException exception) {
		LOGGER.error("Error in controller", exception);
		return BaseResponse.getFailureResponse(ToolItError.TI_ERR_INVALID_PAYLOAD);
	}

	@ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ResponseBody
	BaseResponse handleException(HttpMediaTypeNotSupportedException exception) {
		LOGGER.error("Error in controller", exception);
		return BaseResponse.getFailureResponse(ToolItError.TI_ERR_INVALID_PAYLOAD);
	}

	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ResponseBody
	BaseResponse handleException(HttpRequestMethodNotSupportedException exception) {
		LOGGER.error("Error in controller", exception);
		return BaseResponse.getFailureResponse(ToolItError.TI_ERR_INVALID_HTTP_METHOD);
	}

	@ExceptionHandler(value = ToolItException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	BaseResponse handleException(ToolItException toolItException) {
		LOGGER.error("Error in controller", toolItException);
		return BaseResponse.getFailureResponse(toolItException.getToolItErrors());
	}

	@ExceptionHandler(value = AccessDeniedException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	BaseResponse handleException(AccessDeniedException permissionError) {
		LOGGER.error("Error in controller", permissionError);
		return BaseResponse.getFailureResponse(ToolItError.TI_ERR_PERMISSION_DENIED, permissionError.getMessage());
	}
}
