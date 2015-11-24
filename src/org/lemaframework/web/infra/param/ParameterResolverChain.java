package org.lemaframework.web.infra.param;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.lemaframework.web.infra.param.command.ModelParameterResolver;
import org.lemaframework.web.infra.param.command.LastResolver;
import org.lemaframework.web.infra.param.command.PathParameterResolver;


public class ParameterResolverChain {
	
	public List<Object> resolve(HttpServletRequest request, Method method) throws InstantiationException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException {
		
		Parameter[] parameters = method.getParameters();
		
		if(parameters.length == 0)
			return Collections.emptyList();
		
		Map<String, String[]> parameterMap = request.getParameterMap();
		
		return resolve(parameters, parameterMap);
	}

	private List<Object> resolve(Parameter[] params, Map<String, String[]> requestMapParams) {
		PathParameterResolver pathParamater = new PathParameterResolver();
		ModelParameterResolver modelParameter = new ModelParameterResolver();
		
		pathParamater.setProximo(modelParameter);
		modelParameter.setProximo(new LastResolver());
		
		List<Object> parameterObjects = new ArrayList<Object>();
		pathParamater.resolve(parameterObjects, params, requestMapParams);
		
		return parameterObjects;
				
	}

}
