package org.wid.jless.container;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 所有的请求信息
 * @author wid
 *
 */
public class RequestInfo {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;

	private Map<String, String[]> requestParameterMap;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public Map<String, String[]> getRequestParameterMap() {
		return requestParameterMap;
	}

	public void setRequestParameterMap(Map<String, String[]> requestParameterMap) {
		this.requestParameterMap = requestParameterMap;
	}
	
	
}
