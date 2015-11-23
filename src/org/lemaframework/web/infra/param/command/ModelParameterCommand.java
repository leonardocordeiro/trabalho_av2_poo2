package org.lemaframework.web.infra.param.command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.lemaframework.web.annotation.Model;
import org.lemaframework.web.infra.param.FirstLetterUpperCaseUtil;
import org.lemaframework.web.infra.param.model.Property;

public class ModelParameterCommand implements ParameterResolverCommand {

	@Override
	public void resolve(List<Object> parameterObjects, Parameter[] params, Map<String, String[]> paramMap) {
		Parameter firstParam = null;
		
		try {
			firstParam = params[0];
		} catch(ArrayIndexOutOfBoundsException e) {
			return;
		}
		
		if(isModel(firstParam)) {
			try {
				Object model = firstParam.getType().newInstance();
				
				Set<Property> properties = getProperties(model, paramMap);
				populateModel(model, properties);
				
				parameterObjects.add(0, model);
			} catch(Exception e) { 
				throw new RuntimeException(e);
			}
		}
		
	}
	
	private boolean isModel(Parameter param) {
		return param.isAnnotationPresent(Model.class);
	}

	private void populateModel(Object model, Set<Property> properties) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for(Property property : properties) {
			String name = property.getName();
			String value = property.getValue();
			
			Method method = findModelMethodByName(model, name);
			method.invoke(model, value);
		}
	}

	private Method findModelMethodByName(Object model, String name)
			throws NoSuchMethodException {
		return model.getClass().getMethod("set" + name, String.class);
	}

	private Set<Property> getProperties(Object model, Map<String, String[]> paramMap) {
		Set<Property> properties = new LinkedHashSet<>();
		
		for(Entry<String, String[]> param : paramMap.entrySet()) { 
			String name = param.getKey();
			String value = param.getValue()[0];
			
			FirstLetterUpperCaseUtil firstLetterLowerCaseUtil = new FirstLetterUpperCaseUtil(name);
			Property property = new Property(firstLetterLowerCaseUtil.toUpperCase(), value);
			
			properties.add(property);
		}
		return properties;
		
	}

}
