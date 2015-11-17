package org.lemaframework.controller;

import org.lemaframework.web.annotation.URI;

public class Oi2 {
	
	@URI("sergio")
	public void leo() { 
		System.out.println("SERGIO!");
	}
	
	@URI("nico")
	public void leo2() { 
		System.out.println("NICO!");
	}

}
