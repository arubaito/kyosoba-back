package com.kyosoba.model;

import lombok.Data;

/**
 * エラー情報を保持するResource
 */
@Data
public class K05_ApiErrorResource {

	private static final long serialVersionUID = 1L;
	
	// エラーメッセージ
	private String message;

}
