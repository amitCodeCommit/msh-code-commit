package com.model;

public class DetailRespModel {

	private long EMPLOYEE_ID;
	private String EMPLOYEE_NAME;
	private String DEPARTMENT_NAME;
	private String ORGANIZATION_ADDRESS;

	public long getEMPLOYEE_ID() {
		return EMPLOYEE_ID;
	}

	public void setEMPLOYEE_ID(long eMPLOYEE_ID) {
		EMPLOYEE_ID = eMPLOYEE_ID;
	}

	public String getEMPLOYEE_NAME() {
		return EMPLOYEE_NAME;
	}

	public void setEMPLOYEE_NAME(String eMPLOYEE_NAME) {
		EMPLOYEE_NAME = eMPLOYEE_NAME;
	}

	public String getDEPARTMENT_NAME() {
		return DEPARTMENT_NAME;
	}

	public void setDEPARTMENT_NAME(String dEPARTMENT_NAME) {
		DEPARTMENT_NAME = dEPARTMENT_NAME;
	}

	public String getORGANIZATION_ADDRESS() {
		return ORGANIZATION_ADDRESS;
	}

	public void setORGANIZATION_ADDRESS(String oRGANIZATION_ADDRESS) {
		ORGANIZATION_ADDRESS = oRGANIZATION_ADDRESS;
	}

}
