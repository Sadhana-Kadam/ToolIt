package com.floatinity.toolIt.response;

import java.util.List;

import com.floatinity.toolIt.common.TIResponse;

/**
 * Created by Rohan Bharadwaj on Sep 2, 2017
 */
public class UserListResponse extends TIResponse {

	private static final long serialVersionUID = 1L;

	private List<Object> aaData;

	public List<Object> getAaData() {
		return aaData;
	}

	public void setAaData(List<Object> aaData) {
		this.aaData = aaData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
