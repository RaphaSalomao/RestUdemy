package br.com.erudio.data.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DefaultResponse implements Serializable {
	
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	private String service;
	private String method;
	private String message;
	
	public DefaultResponse(String service, String method, String message) {		
		this.service = service;
		this.method = method;
		this.message = message;
	}

	public String getService() {
		return service;
	}
	
	public void setService(String service) {
		this.service = service;
	}
	
	public String getMethod() {
		return method;
	}
	
	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
