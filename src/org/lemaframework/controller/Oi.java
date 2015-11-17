package org.lemaframework.controller;

import org.lemaframework.web.annotation.URI;

public class Oi {
	
	@URI("oi")
	public void leo() { 
		System.out.println("EXECUTOU!");
	}
	
	@URI("ola")
	public void leo2() { 
		System.out.println("EXECUTOU TAMBÉM!");
	}

}
