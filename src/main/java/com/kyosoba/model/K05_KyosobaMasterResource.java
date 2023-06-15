package com.kyosoba.model;

import java.util.Date;

import lombok.Data;

/**
 * 競走馬マスタResource
 */
@Data
public class K05_KyosobaMasterResource {

	private int id;
	private String bamei;
	private Date birthday;
	private String kyusya;
	private String banushi;
	private String seisansya;
	private boolean seibetsu;
	private String keiro;
	private int fatherId;
	private int matherId;
}
