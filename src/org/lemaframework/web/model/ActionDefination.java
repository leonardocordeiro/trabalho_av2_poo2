package org.lemaframework.web.model;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public final class ActionDefination {

	private final Class<?> controller;
	private final Method method;
	private Parameter[] parameters;

	public ActionDefination(Class<?> controller, Method method) {
		this.controller = controller;
		this.method = method;
	}

	public Class<?> getController() {
		return controller;
	}

	public Method getMethod() {
		return method;
	}

	public Parameter[] getParameters() {
		return parameters;
	}

}
