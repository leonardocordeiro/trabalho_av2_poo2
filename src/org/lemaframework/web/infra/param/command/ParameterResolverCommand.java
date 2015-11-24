package org.lemaframework.web.infra.param.command;

import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;

public interface ParameterResolverCommand {
	
	void resolve(List<Object> parameterObjects, Parameter[] params, Map<String, String[]> requestMapParams);
	void setProximo(ParameterResolverCommand command);

}
