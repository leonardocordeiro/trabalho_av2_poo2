package org.lemaframework.controller;

import org.lemaframework.web.annotation.URI;

import teste.Usuario;

public class HomeController {
	
	@URI("home")
	public String heyyyy(Usuario usuario) {
		System.out.println("Nome:" + usuario.getNome());
		return "index";
	}
	
	@URI("redirectTeste")
	public String heyyyy2() { 
		return "redirect:home";
	}
	
}
