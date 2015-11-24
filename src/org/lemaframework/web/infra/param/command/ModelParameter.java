package org.lemaframework.web.infra.param.command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.DecimalFormat;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.lemaframework.web.annotation.Model;
import org.lemaframework.web.infra.param.FirstLetterUpperCaseUtil;
import org.lemaframework.web.infra.param.model.Property;

public class ModelParameter implements ParameterResolverCommand {

	private ParameterResolverCommand proximo;

	@Override
	public void resolve(List<Object> parameterObjects, Parameter[] params, Map<String, String[]> paramMap) {
		Parameter firstParam = params[0];
		
		if(isModel(firstParam)) {
			try {
				Object model = firstParam.getType().newInstance();
				
				Set<Property> properties = getProperties(model, paramMap);
				populateModel(model, properties);
				
				parameterObjects.add(0, model);
			} catch(Exception e) { 
				throw new RuntimeException(e);
			}
		} else { 
			proximo.resolve(parameterObjects, params, paramMap);
		}
		
	}
	
	private boolean isModel(Parameter param) {
		return param.isAnnotationPresent(Model.class);
	}

	private void populateModel(Object model, Set<Property> properties) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for(Property property : properties) {
			String name = property.getName();
			Object value = property.getValue();
			
			Class<?> type = String.class;
			
			try {
				Number valueNumber = new DecimalFormat().parse(value.toString());
				type = valueNumber.getClass();
				
				value = valueNumber;
			} catch(Exception e) { 
				
			}
			
			Method method = findModelMethodByName(model, name, type);
			method.invoke(model, value);
		}
	}

	private Method findModelMethodByName(Object model, String name, Class<?> type) throws NoSuchMethodException {
		return model.getClass().getMethod("set" + name, type);
	}

	private Set<Property> getProperties(Object model, Map<String, String[]> paramMap) {
		Set<Property> properties = new LinkedHashSet<>();
		
		for(Entry<String, String[]> param : paramMap.entrySet()) { 
			String name = param.getKey();
			String value = param.getValue()[0];
			
			if(value == null || value.isEmpty())
				continue;
			
			FirstLetterUpperCaseUtil firstLetterLowerCaseUtil = new FirstLetterUpperCaseUtil(name);
			Property property = new Property(firstLetterLowerCaseUtil.toUpperCase(), value);
			
			properties.add(property);
		}
		return properties;
		
	}

	@Override
	public void setProximo(ParameterResolverCommand proximo) {
		this.proximo = proximo;
		
	}

}
