package com.football.standings.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InternalException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	private String errorMessage;

}
