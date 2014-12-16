package org.wid.jless.container;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * jless 的容器 threadlocal的方式保存请求信息
 * @author wid
 *
 */
public class JLessContainer {
	//单例模式
	private JLessContainer(){}
	
	private static ThreadLocal<RequestInfo> requesThreadLocal =  new ThreadLocal<RequestInfo>();
	/**
	 * 将request/response保存到容器中
	 * @param request
	 * @param response
	 */
	protected static void inject(HttpServletRequest request,HttpServletResponse response){
		RequestInfo requestInfo = new RequestInfo();
		requestInfo.setRequest(request);
		requestInfo.setResponse(response);
		requestInfo.setRequestParameterMap(request.getParameterMap());
		requestInfo.setSession(request.getSession());
		
		requesThreadLocal.set(requestInfo);
	}
	/**
	 * 当请求会话结束后，要把信息移除，以免造成内存泄漏
	 */
	protected static void remove(){
		requesThreadLocal.remove();
	}
	/**
	 * 获取当前请求中的请求对象
	 * 请在web容器内调用，否则会收到异常
	 * @return
	 */
	public static HttpServletRequest getRequest(){
		return requesThreadLocal.get().getRequest();
	}
	/**
	 * 获取当前请求中的响应对象
	 * 请在web容器内调用，否则会收到异常
	 * @return
	 */
	public static HttpServletResponse getResponse(){
		return requesThreadLocal.get().getResponse();
	}
	/**
	 * 获取当前请求的session
	 * 请在web容器内调用，否则会收到异常
	 * @return
	 */
	public static HttpSession getSession() {
		return requesThreadLocal.get().getSession();
	}
	/**
	 * 获取当前请求中的参数map
	 * 请在web容器内调用，否则会收到异常
	 * @return
	 */
	public static Map<String, String[]> getParameterMap(){
		return requesThreadLocal.get().getRequestParameterMap();
	}
}
