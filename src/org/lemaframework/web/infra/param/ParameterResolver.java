package org.lemaframework.web.infra.param;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.lemaframework.web.infra.param.command.ModelParameterCommand;
import org.lemaframework.web.infra.param.command.ParameterResolverCommand;

public class ParameterResolver {
	
	private static Set<ParameterResolverCommand> commands = new LinkedHashSet<>();
	
	static {
		commands.add(new ModelParameterCommand());
	}

	public List<Object> resolve(HttpServletRequest request, Method method) throws InstantiationException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException {
		
		Parameter[] parameters = method.getParameters();
		Map<String, String[]> parameterMap = request.getParameterMap();
		
		List<Object> params = resolve(parameters, parameterMap);
		return params;

//		Object model = null;
//		if (modelParameter != null) {
//			Class<?> metaModel = modelParameter.getType();
//
//			model = metaModel.newInstance();
//
//			for (Entry<String, String[]> entry : request.getParameterMap()
//					.entrySet()) {
//				String key = entry.getKey();
//				String value = entry.getValue()[0];
//
//				Method modelMethod = model.getClass().getDeclaredMethod(
//						"set" + key, String.class);
//				modelMethod.invoke(model, value);
//
//			}
//		}
//		return model;
	}

	private List<Object> resolve(Parameter[] params, Map<String, String[]> requestMapParams) {
		List<Object> parameterObjects = new ArrayList<>();
		
		for (ParameterResolverCommand command : commands) {
			command.resolve(parameterObjects, params, requestMapParams);
		}
		
		return parameterObjects;
				
	}

}
