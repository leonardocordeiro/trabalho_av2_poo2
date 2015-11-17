package org.lemaframework.controller;

import org.lemaframework.web.annotation.URI;

public class HomeController {
	
	@URI("home")
	public void leo() { 
		System.out.println("teste!");
	}
	
}
