package org.lemaframework.controller;

import org.lemaframework.web.annotation.URI;
import org.lemaframework.web.model.RequestModelAndView;

public class HomeController {

	@URI
	public RequestModelAndView home() { 
		return new RequestModelAndView("redirect:membros");
	}
	
}
