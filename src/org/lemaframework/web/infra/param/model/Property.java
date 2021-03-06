package org.lemaframework.web.infra.param.model;

public final class Property {
	private final String name;
	private final String value;
	
	public Property(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	public String getValue() {
		return value;
	}

}
