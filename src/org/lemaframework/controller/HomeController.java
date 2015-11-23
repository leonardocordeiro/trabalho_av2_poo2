package org.lemaframework.controller;

import org.lemaframework.web.annotation.Model;
import org.lemaframework.web.annotation.URI;
import org.lemaframework.web.model.RequestModelAndView;

import teste.Usuario;

public class HomeController {
	
	@URI("home")
	public RequestModelAndView heyyyy(@Model Usuario usuario) {
		System.out.println("Nome: " + usuario.getNome());
		
		RequestModelAndView model = new RequestModelAndView("index");
		model.put("nome", "leonardo c");
		
		return model;
	}
	
	@URI("redirectTeste")
	public RequestModelAndView heyyyy2() { 
		return new RequestModelAndView("redirect:home");
	}
	
}
