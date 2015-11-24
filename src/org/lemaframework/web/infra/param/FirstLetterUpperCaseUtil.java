package org.lemaframework.web.infra.param;

public class FirstLetterUpperCaseUtil {
	
	private final String value;

	public FirstLetterUpperCaseUtil(String value) {
		this.value = value;
	}
	
	public String toUpperCase() { 
		char firstLetter = value.charAt(0);
		String valueWithoutFirstLetter = value.substring(1);
		
		return new StringBuilder()
				.append(String.valueOf(firstLetter).toUpperCase())
				.append(valueWithoutFirstLetter).toString();
	}
}
