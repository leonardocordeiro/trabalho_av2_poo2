package org.lemaframework.web.servlet;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lemaframework.web.annotation.URI;
import org.lemaframework.web.infra.HandleMapping;
import org.lemaframework.web.infra.exception.RequestMappingNotFoundException;
import org.lemaframework.web.infra.param.ParameterResolver;
import org.lemaframework.web.model.ActionDefination;
import org.lemaframework.web.model.RequestModelAndView;

public class FrontControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			String requestUri = request.getRequestURI();
			requestUri = requestUri.substring(requestUri.lastIndexOf("/") + 1);
			
			ActionDefination actionDefination = HandleMapping.uris.get(requestUri);
			
			if(actionDefination == null)
				throw new RequestMappingNotFoundException();
			
			Class<?> klass = actionDefination.getController();
			Object controller = klass.newInstance();
			
			Method method = actionDefination.getMethod();
			
			// tirar lista 
			List<Object> paramObjects = new ParameterResolver().resolve(request, method);
			RequestModelAndView requestModel = (RequestModelAndView) method.invoke(controller, paramObjects.toArray());
			
			if(requestModel.getModelMap() != null) { 
				for(Entry<String, Object> entry : requestModel.getModelMap().entrySet()) { 
					request.setAttribute(entry.getKey(), entry.getValue());
				}
			}
			
			String to = null;
			if(requestModel != null && requestModel.getViewName().contains("redirect:")) {
				to = requestModel.getViewName().substring(requestModel.getViewName().indexOf(":") + 1);
				
				response.sendRedirect(to);
			} else {
				to = requestModel.getViewName();
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/" + to + ".jsp");
				dispatcher.forward(request, response);
			}
			
		} catch(Exception e) { 
			throw new RuntimeException(e);
		}
		
	}

	

	@Override
	public void init() throws ServletException {
		super.init();

		try {
			InputStream configs = getClass().getResourceAsStream("/META-INF/configurations.properties");
			
			Properties props = new Properties();
			props.load(configs);
			
			String basePackage = props.getProperty("base-package");
			
			String replacedBasePackage = basePackage.replaceAll("\\.", "/");

			URL resource = getClass().getResource("/");
			File directory = new File(resource.getFile() + "/" + replacedBasePackage);

			for(String className : directory.list()) { 
				Class<?> klass = Class.forName(basePackage + "." + className.substring(0, className.indexOf(".class")));
				Method[] methods = klass.getDeclaredMethods();
				for (Method method : methods) {
					ActionDefination defination = new ActionDefination(klass, method);
					
					URI uri = method.getAnnotationsByType(URI.class)[0];
					String key = uri.value();
					
					HandleMapping.uris.put(key, defination);
					
				}
			}
			

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
