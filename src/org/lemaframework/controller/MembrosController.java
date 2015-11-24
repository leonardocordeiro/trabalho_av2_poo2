package org.lemaframework.controller;

import java.util.List;

import org.lemaframework.web.annotation.Model;
import org.lemaframework.web.annotation.PathVariable;
import org.lemaframework.web.annotation.URI;
import org.lemaframework.web.model.Membro;
import org.lemaframework.web.model.RequestModelAndView;
import org.lemaframework.web.model.dao.MembroDao;

public class MembrosController {

	private final MembroDao membroDao = new MembroDao();
	
	@URI("membros")
	public RequestModelAndView list() {
		List<Membro> membros = membroDao.list();

		RequestModelAndView requestModelAndView = new RequestModelAndView("membros/list");
		requestModelAndView.put("membros", membros);
		
		return requestModelAndView;
	}
	
	@URI("membros/form")
	public RequestModelAndView form() {
		return new RequestModelAndView("membros/form");
	}
	
	@URI("membros/editar")
	public RequestModelAndView editar(@PathVariable("id") int id) {
		Membro membro = membroDao.buscarPor(id);
		
		RequestModelAndView requestModelAndView = new RequestModelAndView("membros/form");
		requestModelAndView.put("membro", membro);
		
		return requestModelAndView;
	}
	
	@URI("membros/deletar")
	public RequestModelAndView deletar(@PathVariable("id") int id) {
		Membro membro = membroDao.buscarPor(id);
		membroDao.remover(membro);
		
		return new RequestModelAndView("redirect:../");
	}
	
	@URI("membro")
	public RequestModelAndView cadastrar(@Model Membro membro) { 
		if(membro.getId() == null)
			membroDao.inserir(membro);
		else 
			membroDao.atualiza(membro);
			
		return new RequestModelAndView("redirect:membros");
	}
	
}
