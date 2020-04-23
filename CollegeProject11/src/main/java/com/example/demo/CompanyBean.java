package com.example.demo;

public class CompanyBean {


	public int getCompanyid() {
		return companyid;
	}
	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}
	public String getCompanyname() {
		return companyname;
	}
	@Override
	public String toString() {
		return "CompanyBean [companyid=" + companyid + ", companyname=" + companyname + ", companypassword="
				+ companypassword + "]";
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getCompanypassword() {
		return companypassword;
	}
	public void setCompanypassword(String companypassword) {
		this.companypassword = companypassword;
	}
	private int companyid;
	private String companyname;
	private String companypassword;

}
