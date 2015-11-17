package org.lemaframework.web.infra;

import java.util.HashMap;
import java.util.Map;

import org.lemaframework.web.model.ActionDefination;

public final class HandleMapping {
	public static final Map<String, ActionDefination> uris;
	
	static {
		uris = new HashMap<String, ActionDefination>();
	}

}
