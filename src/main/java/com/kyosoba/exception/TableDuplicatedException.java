package com.kyosoba.exception;

/**
 * データ登録時にテーブルにデータが既に存在する場合のException
 */
public class TableDuplicatedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TableDuplicatedException(String message) {
		super(message);
	}
}
