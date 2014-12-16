package org.wid.jless.dispatcher;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.wid.jless.container.AccessContainer;
import org.wid.jless.json.JLessJson;
import org.wid.jless.log.JLessLog;
import org.wid.jless.ssist.JLessMethod;

public class JLessFilter implements Filter{

	private static Log log = JLessLog.getLog(JLessFilter.class);
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		log.debug("===================进入jless========================");
		
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		PrintWriter writer = response.getWriter();
		
		String url = request.getRequestURI();
		log.debug("拦截到的URI:"+url);
		
		//根据url确定请求想要访问的类
		List<JLessMethod> methods = null;
		try {
			methods = UrlMethod.uriToMethods(url);
		} catch (Exception e) {
			log.error("URI映射方法时异常!",e);
			e.printStackTrace(writer);
			writer.flush();
		}
		
		log.debug("根据方法名称映射出的方法:"+JLessJson.stringify(methods));
		
		RequestParameter requestParameter = new RequestParameter(request.getParameterMap());
		//根据参数确定方法
		JLessMethod method = UrlMethod.matchMethodByParam(methods, requestParameter.getParamNames());
		log.debug("接收到的请求参数:"+JLessJson.stringify(request.getParameterMap()));
		log.debug("根据参数筛选出的方法："+JLessJson.stringify(method));
		
		if (method == null) {
			writer.write("未匹配到相应的方法!请根据如下规则检查!");
			writer.write(UrlMethod.matechMethodRule(null));
			writer.flush();
		}
		
		log.debug("将本次请求参数注入到容器中!");
		injectRequestInfo(request, response);
		
		log.debug("执行方法!");
		Object result = UrlMethod.invokeMethod(method, requestParameter);
		log.debug("执行结果:"+JLessJson.stringify(result));
		writer.write(JLessJson.stringify(result));
		writer.flush();
		
		log.debug("执行后面的过滤器!");
		try {
			//进入下一个过滤器
			arg2.doFilter(request, response);
		} catch (Exception e) {
			log.info("没有其他过滤器拦截到此请求!");
		}	
		
		log.debug("从容器中移除本次请求参数!");
		removeRequestInof();
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 将请求信息注入到容器中
	 * @param request
	 * @param response
	 */
	private void injectRequestInfo(HttpServletRequest request,HttpServletResponse response){
		AccessContainer.inject(request, response);
	}
	/**
	 * 执行完之后就remove掉信息，避免内存泄漏
	 */
	private void removeRequestInof(){
		AccessContainer.reomove();
	}
	
}
