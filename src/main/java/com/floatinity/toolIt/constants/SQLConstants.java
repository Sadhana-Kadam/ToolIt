package com.floatinity.toolIt.constants;

public class SQLConstants {

	public static final String GET_USER_ROLE_MAP = new StringBuilder(
			"SELECT rm.role_id as roleId, rm.default_role as defaultRole, r.role_name as roleName, r.role_type as roleType ")
					.append("FROM gms.user_role_map rm join gms.role r on rm.role_id  = r.role_id where rm.user_id =:userId")
					.toString();

	public static final String DELETE_USER_ROLE_MAP = new StringBuilder(
			"DELETE FROM gms.user_role_map where user_id =:userId").toString();

	public static final String DELETE_GR_USER_DEPT_MAP = new StringBuilder(
			"DELETE FROM gms.gauge_room_user_dept_map where gr_id =:grId").toString();

	public static final String GET_GR_UNASSIGNED_USERS = new StringBuilder(
			"select gu.user_id as userId, gu.f_name as firstName, gu.m_name as middleName, gu.l_name as lastName, gu.email as email ")
					.append(" from gms.gms_user gu where gu.user_id not in (select user_id from gauge_room_user_dept_map ) and gu.user_status = 1 ")
					.toString();

	public static final String GET_GR_ASSIGNED_USERS = new StringBuilder(
			"select gu.user_id as userId, gu.f_name as firstName, gu.m_name as middleName, gu.l_name as lastName, gu.email as email,  gu.user_status as userStatus, grudm.depts as depts ")
					.append("from gms.gauge_room_user_dept_map grudm join gms.gms_user gu on grudm.user_id = gu.user_id where grudm.gr_id = :grId ")
					.toString();

	public static final String GET_ACTIVE_GAUGE_ROOMS = new StringBuilder(
			"select gr_id as id, gr_name as grName, gr_desc as grDesc, gr.gr_status as grStatus, ").append(
					" gr.created_ts as createdTs, gr.updated_ts as updatedTs, d.div_id as divId, d.div_name as divName ")
					.append(" from gms.gauge_room gr join gms.division d on gr.div_id = d.div_id where gr.gr_status != 'DELETED' ")
					.toString();

	public static final String GET_ACTIVE_GAUGE_ROOM_BY_ID = new StringBuilder(
			"select gr_id as id, gr_name as grName, gr_desc as grDesc, gr.gr_status as grStatus, ").append(
					" gr.created_ts as createdTs, gr.updated_ts as updatedTs, d.div_id as divId, d.div_name as divName ")
					.append(" from gms.gauge_room gr join gms.division d on gr.div_id = d.div_id where gr.gr_id = :grId ")
					.toString();

	public static final String GET_ACTIVE_LINES = new StringBuilder(
			"select l.line_id as lineId, l.line_no as lineNo, l.line_name as lineName, l.line_details as lineDetails, ")
					.append(" l.line_status as lineStatus, l.created_ts as createdTs, l.updated_ts as updatedTs, d.div_id as divId, d.div_name as divName ")
					.append(" from gms.line l join gms.division d on l.div_id = d.div_id where l.line_status != 0 ")
					.toString();

	public static final String GET_LINE_BY_ID = new StringBuilder(
			"select l.line_id as lineId, l.line_no as lineNo, l.line_name as lineName, l.line_details as lineDetails, ")
					.append(" l.line_status as lineStatus, l.created_ts as createdTs, l.updated_ts as updatedTs, d.div_id as divId, d.div_name as divName ")
					.append(" from gms.line l join gms.division d on l.div_id = d.div_id where l.line_id = :lineId ")
					.toString();

	public static final String GET_USERS_GR_DETAILS = new StringBuilder(
			"select  gr.gr_id as grId, gr.gr_name as grName, d.div_id as divId, d.div_name as divName, grudm.depts as depts ")
					.append(" from gms.gauge_room_user_dept_map grudm join gms.gauge_room gr on grudm.gr_id = gr.gr_id ")
					.append(" join gms.division d on d.div_id = gr.div_id where grudm.user_id = :userId ").toString();

	public static final String GET_ACTIVE_LINES_BY_DIV_ID = new StringBuilder(
			"select l.line_id as lineId, l.line_no as lineNo, l.line_name as lineName, l.line_details as lineDetails, ")
					.append(" l.line_status as lineStatus, l.created_ts as createdTs, l.updated_ts as updatedTs, d.div_id as divId, d.div_name as divName ")
					.append(" from gms.line l join gms.division d on l.div_id = d.div_id where l.line_status != 0 and l.div_id = :divId ")
					.toString();

	public static final String GET_LATEST_CERT_FILE_IDS = new StringBuilder(
			"select file_id from gms.calib_cert_map where gauge_id = :gaugeId and created_ts = (select max(created_ts) from gms.calib_cert_map where gauge_id = :gaugeId) ")
					.toString();

	public static final String GET_USER_BY_ROLE = new StringBuilder(
			"select gu.user_id as id, gu.f_name as firstName, gu.m_name as middleName, gu.l_name as lastName, gu.email as email,  gu.user_status as userStatus ")
					.append("from gms_user gu ").append("inner join user_role_map urm on urm.user_id = gu.user_id ")
					.append("inner join `role` r on r.role_id = urm.role_id ").append("where r.role_id = :role ")
					.toString();

}
