package org.lemaframework.web.infra.param.command;

import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;

public class LastResolver implements ParameterResolverCommand {

	@Override
	public void resolve(List<Object> parameterObjects, Parameter[] params,
			Map<String, String[]> requestMapParams) {
		// nada
	}

	@Override
	public void setProximo(ParameterResolverCommand command) {
		// nada
		
	}

}
