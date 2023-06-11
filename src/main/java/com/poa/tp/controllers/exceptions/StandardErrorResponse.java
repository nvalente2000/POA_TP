package com.poa.tp.controllers.exceptions;

import java.io.Serializable;

public class StandardErrorResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer status; // Statis http
	private String msg; 	// Mensaje error
	private Long timeStamp;	// Tiempo
	
	public StandardErrorResponse(Integer status, String msg, Long timeStamp) {
		super();
		this.status = status;
		this.msg = msg;
		this.timeStamp = timeStamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
}
