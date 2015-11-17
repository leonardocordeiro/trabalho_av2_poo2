package org.lemaframework.web.model;

public final class ActionDefination {
	
	private final Class<?> controller;
	private final String methodName;
	
	public ActionDefination(Class<?> controller, String methodName) {
		this.controller = controller;
		this.methodName = methodName;
	}
	
	public Class<?> getController() {
		return controller;
	}
	
	public String getMethodName() {
		return methodName;
	}
	
}
