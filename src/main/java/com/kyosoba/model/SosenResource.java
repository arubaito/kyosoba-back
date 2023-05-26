package com.kyosoba.model;

/**
 * 馬の祖先リソース
 */
public class SosenResource {

	private String FatherName;
	private String MatherName;
	private String FathersFatherName;
	private String FathersMatherName;
	private String MathersFatherName;
	private String MathersMatherName;

	public String getFatherName() {
		return FatherName;
	}
	public void setFatherName(String fatherName) {
		FatherName = fatherName;
	}
	public String getMatherName() {
		return MatherName;
	}
	public void setMatherName(String matherName) {
		MatherName = matherName;
	}
	public String getFathersFatherName() {
		return FathersFatherName;
	}
	public void setFathersFatherName(String fathersFatherName) {
		FathersFatherName = fathersFatherName;
	}
	public String getFathersMatherName() {
		return FathersMatherName;
	}
	public void setFathersMatherName(String fathersMatherName) {
		FathersMatherName = fathersMatherName;
	}
	public String getMathersFatherName() {
		return MathersFatherName;
	}
	public void setMathersFatherName(String mathersFatherName) {
		MathersFatherName = mathersFatherName;
	}
	public String getMathersMatherName() {
		return MathersMatherName;
	}
	public void setMathersMatherName(String mathersMatherName) {
		MathersMatherName = mathersMatherName;
	}
	
	
}
