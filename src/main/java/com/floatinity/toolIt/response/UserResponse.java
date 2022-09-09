package com.floatinity.toolIt.response;

import java.util.List;

import com.floatinity.toolIt.pojo.RoleMap;

public class UserResponse {

	private Integer userId;

	private String firstName;

	private String middleName;

	private String lastName;

	private String email;

	private String mobileNo;

	private String phoneNo;

	private String employeeId;

	private boolean loginAccess;

	private Boolean userStatus;

	private List<RoleMap> roleMap;

	private String depts;

	private List<IdName> deptList;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public boolean isLoginAccess() {
		return loginAccess;
	}

	public void setLoginAccess(boolean loginAccess) {
		this.loginAccess = loginAccess;
	}

	public Boolean getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Boolean userStatus) {
		this.userStatus = userStatus;
	}

	public List<RoleMap> getRoleMap() {
		return roleMap;
	}

	public void setRoleMap(List<RoleMap> roleMap) {
		this.roleMap = roleMap;
	}

	public String getDepts() {
		return depts;
	}

	public void setDepts(String depts) {
		this.depts = depts;
	}

	public List<IdName> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<IdName> deptList) {
		this.deptList = deptList;
	}

	@Override
	public String toString() {
		return "UserResponse [userId=" + userId + ", email=" + email + ", userStatus=" + userStatus + "]";
	}

}
