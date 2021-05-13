package com.gaja.error;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{http://gaja.com/country/stubs}001_COURSE_NOT_FOUND")
public class CountryNotFound extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7900117622235782604L;

	public CountryNotFound(String message) {
		super(message);
	}

	
}
