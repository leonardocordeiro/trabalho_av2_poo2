package org.lemaframework.web.infra.param.command;

import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;

import org.lemaframework.web.annotation.PathVariable;

public class PathParameter implements ParameterResolverCommand {

	private ParameterResolverCommand proximo;

	@Override
	public void resolve(List<Object> parameterObjects, Parameter[] params, Map<String, String[]> requestMapParams) {
		Parameter param = params[0];
		
		if(isPathVariable(param)) {
			String paramName = param.getAnnotation(PathVariable.class).value();
			int pathParam = Integer.valueOf(requestMapParams.get(paramName)[0]);
			
			parameterObjects.add(0, pathParam);
		} else {
			proximo.resolve(parameterObjects, params, requestMapParams);
		}
	}
	
	private boolean isPathVariable(Parameter param) {
		return param.isAnnotationPresent(PathVariable.class);
	}

	@Override
	public void setProximo(ParameterResolverCommand proximo) {
		this.proximo = proximo;
	}

}
