package org.lemaframework.web.servlet;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lemaframework.web.annotation.URI;
import org.lemaframework.web.infra.HandleMapping;
import org.lemaframework.web.infra.exception.RequestMappingNotFoundException;
import org.lemaframework.web.model.ActionDefination;

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
			
			Method method = klass.getMethod(actionDefination.getMethodName());
			method.invoke(controller);
		} catch(Exception e) { 
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void init() throws ServletException {
		super.init();

		try {
			InputStream configs = getClass().getResourceAsStream("/META-INF/controller-package.properties");
			
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
					ActionDefination defination = new ActionDefination(klass, method.getName());
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
