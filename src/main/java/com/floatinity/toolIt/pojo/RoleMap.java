package com.floatinity.toolIt.pojo;

/**
 * CREATED BY AKSHAY KHANDAGALE ON 01-Mar-2022
 */
public class RoleMap {

	private Integer roleId;

	private String roleName;

	private Boolean defaultRole;

	private int roleType;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Boolean getDefaultRole() {
		return defaultRole;
	}

	public void setDefaultRole(Boolean defaultRole) {
		this.defaultRole = defaultRole;
	}

	public int getRoleType() {
		return roleType;
	}

	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}

	@Override
	public String toString() {
		return "RoleMap [roleId=" + roleId + ", roleName=" + roleName + ", defaultRole=" + defaultRole + "]";
	}

}
