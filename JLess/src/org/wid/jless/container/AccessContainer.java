package org.wid.jless.container;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 借用此类进行容器的访问，避免容器公共不必要都方法
 * @author wid
 *
 */
public class AccessContainer {
	
	/**
	 * 将request/response保存到容器中
	 * @param request
	 * @param response
	 */
	public static void inject(HttpServletRequest request,HttpServletResponse response){
		JLessContainer.inject(request, response);
	}
	/**
	 * 将请求信息从容器中移除，避免内存泄漏
	 */
	public static void reomove(){
		JLessContainer.remove();
	}
}
