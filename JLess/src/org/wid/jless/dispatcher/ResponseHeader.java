/**
 * Create at Dec 13, 2014 by wid
 */
package org.wid.jless.dispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.wid.jless.log.JLessLog;

/**
 * 响应头信息设置
 * @author wid
 * Create at Dec 13, 2014
 */
class ResponseHeader {
	private static Log log = JLessLog.getLog(ResponseHeader.class);
	/**
	 * 根据请求头信息设置响应头信息
	 * @param request
	 * @param response
	 * add at Dec 16, 2014
	 */
	public static void setResposeHeader(HttpServletRequest request,HttpServletResponse response){
		
		response.setContentType(request.getContentType());
		log.debug("设置响应类型:"+response.getContentType());
		
		String charset = request.getCharacterEncoding();
		response.setCharacterEncoding(charset == null ? Constant.DEFAULT_CHARSET : charset);
		log.debug("设置响应编码:"+response.getCharacterEncoding());
	}
}
