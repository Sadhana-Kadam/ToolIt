package com.floatinity.toolIt.enums;


/**
 * CREATED BY AKSHAY KHANDAGALE ON 01-Mar-2022
 */
public enum RoleName {

	SUPER_ADMIN("Super Admin"), // 0
	COMPANY_ADMIN("Admin"), // 1
	GR_MANAGER("Manager"), // 2
	GR_ISSUER("Issuer"), // 3
	CALIBRATOR("Calibrator"); // 4

	private RoleName(String roleName) {
		this.roleName = roleName;
	}

	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}