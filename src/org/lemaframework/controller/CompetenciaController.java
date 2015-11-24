package org.lemaframework.controller;

import java.util.List;

import org.lemaframework.web.annotation.Model;
import org.lemaframework.web.annotation.PathVariable;
import org.lemaframework.web.annotation.URI;
import org.lemaframework.web.model.Competencia;
import org.lemaframework.web.model.RequestModelAndView;
import org.lemaframework.web.model.dao.CompetenciaDao;

public class CompetenciaController {
	
	private CompetenciaDao competenciaDao = new CompetenciaDao();
	
	@URI("competencias")
	public RequestModelAndView list() {
		List<Competencia> competencia = competenciaDao.list();

		RequestModelAndView requestModelAndView = new RequestModelAndView("competencias/list");
		requestModelAndView.put("competencias", competencia);
		
		return requestModelAndView;
	}
	
	@URI("competencia/form")
	public RequestModelAndView form() {
		return new RequestModelAndView("competencias/form");
	}
	
	@URI("competencia/editar")
	public RequestModelAndView editar(@PathVariable("id") int id) {
		Competencia competencia = competenciaDao.buscarPor(id);
		
		RequestModelAndView requestModelAndView = new RequestModelAndView("competencias/form");
		requestModelAndView.put("competencia", competencia);
		
		return requestModelAndView;
	}
	
	@URI("competencia/deletar")
	public RequestModelAndView deletar(@PathVariable("id") int id) {
		Competencia competencia = competenciaDao.buscarPor(id);
		competenciaDao.remover(competencia);
		
		return new RequestModelAndView("redirect:../");
	}
	
	@URI("competencia")
	public RequestModelAndView cadastrar(@Model Competencia competencia) { 
		if(competencia.getId() == null)
			competenciaDao.inserir(competencia);
		else 
			competenciaDao.atualiza(competencia);
			
		return new RequestModelAndView("redirect:competencias");
	}

}
