package org.wid.jless.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import test.bean.Hello;


/**
 * 记录jless的流程日志
 * @author wid
 *
 */
public class JLessLog {
	//日志前缀
	protected static final String LOG_PREFIX = "[==JLess==]";
	
	//日志级别
	public static final int DEBUG = 1,INFO = 2,WARN= 3,ERROR=4;
	protected static int logLevel= DEBUG;
	
	/**
	 * 设置日志等级,可以使用本类中都常量
	 * @param logLevel
	 */
	public static void setLogLevel(int logLevel) {
		JLessLog.logLevel = logLevel;
	}
	

	/**
	 * 根据类获取日志
	 * @param c
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Log getLog(Class c){
		return new LogBean(LogFactory.getLog(c));
	}
	public static void main(String[] args) {
		Log log = JLessLog.getLog(JLessLog.class);
		log.debug(new Hello());
	}
}
