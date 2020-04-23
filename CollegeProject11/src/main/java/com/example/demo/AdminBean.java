package com.example.demo;

public class AdminBean {
	private int adminuserid;
	private String Adminusername;
	private String Adminuserpassword;
	@Override
	public String toString() {
		return "AdminBean [adminuserid=" + adminuserid + ", Adminusername=" + Adminusername + ", Adminuserpassword="
				+ Adminuserpassword + "]";
	}
	public int getAdminuserid() {
		return adminuserid;
	}
	public void setAdminuserid(int adminuserid) {
		this.adminuserid = adminuserid;
	}
	public String getAdminusername() {
		return Adminusername;
	}
	public void setAdminusername(String adminusername) {
		Adminusername = adminusername;
	}
	public String getAdminuserpassword() {
		return Adminuserpassword;
	}
	public void setAdminuserpassword(String adminuserpassword) {
		Adminuserpassword = adminuserpassword;
	}

}
