package com.kyosoba.model;

import lombok.Data;

/**
 * 馬の祖先リソース
 */
@Data
public class SosenResource {

	private String FatherName;
	private String MatherName;
	private String FathersFatherName;
	private String FathersMatherName;
	private String MathersFatherName;
	private String MathersMatherName;
	private String FathersFathersFatherName;
	private String FathersFathersMatherName;
	private String FathersMathersFatherName;
	private String FathersMathersMatherName;
	private String MathersFathersFatherName;
	private String MathersFathersMatherName;
	private String MathersMathersFatherName;
	private String MathersMathersMatherName;
}
