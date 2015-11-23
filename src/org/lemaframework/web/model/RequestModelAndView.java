package org.lemaframework.web.model;

import java.util.HashMap;
import java.util.Map;

public class RequestModelAndView {

	private String viewName;
	private Map<String, Object> models = new HashMap<>();

	public RequestModelAndView(String viewName) {
		this.viewName = viewName;
	}

	public String getViewName() {
		return viewName;
	}
	
	public Map<String, Object> getModelMap() {
		return models;
	}
	
	public void put(String name, Object model) { 
		models.put(name, model);
	}

}
