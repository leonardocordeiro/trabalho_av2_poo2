package org.lemaframework.web.infra.param;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.lemaframework.web.infra.param.command.ModelParameter;
import org.lemaframework.web.infra.param.command.NullResolver;
import org.lemaframework.web.infra.param.command.PathParameter;


public class ParameterResolverChain {
	
	public List<Object> resolve(HttpServletRequest request, Method method) throws InstantiationException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException {
		
		Parameter[] parameters = method.getParameters();
		
		if(parameters.length == 0)
			return Collections.emptyList();
		
		Map<String, String[]> parameterMap = request.getParameterMap();
		
		List<Object> params = resolve(parameters, parameterMap);
		return params;

	}

	private List<Object> resolve(Parameter[] params, Map<String, String[]> requestMapParams) {
		PathParameter pathParamater = new PathParameter();
		ModelParameter modelParameter = new ModelParameter();
		
		pathParamater.setProximo(modelParameter);
		modelParameter.setProximo(new NullResolver());
		
		List<Object> parameterObjects = new ArrayList<Object>();
		pathParamater.resolve(parameterObjects, params, requestMapParams);
		
		return parameterObjects;
				
	}

}
