package com.wesdm.springmvc.spittr;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.wesdm.spittr.soap.Country;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface CountryService {
	@WebMethod
	Country getCountry(String name);
}
