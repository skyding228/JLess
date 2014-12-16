/**
 * Create at Dec 10, 2014 by wid
 */
package org.wid.jless.dispatcher;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.logging.Log;
import org.wid.jless.log.JLessLog;
import org.wid.jless.ssist.Classes;
import org.wid.jless.ssist.JLessMethod;

import sun.reflect.misc.MethodUtil;

/**
 * 
 * @author wid
 * Create at Dec 10, 2014
 */
class UrlMethod{
	
	private static JLessUriToMethod uriToMethod = null;
	private static Log log = JLessLog.getLog(UrlMethod.class);
	/**
	 * 根据uri获取方法路径
	 * @param uri
	 * @return
	 * add at Dec 10, 2014
	 * @throws Exception 
	 */
	public static List<JLessMethod> uriToMethods(String uri) throws Exception {
		//获取到方法路径
		String methodPath =	uriToMethod == null
				? uri.substring(uri.toLowerCase().lastIndexOf("jless/")+6)
				: uriToMethod.uriToMethodPath(uri);
		String classPath = methodPath.substring(0,methodPath.lastIndexOf("."));
		String methodName = methodPath.substring(methodPath.lastIndexOf(".")+1);
	
		return Classes.getMethodParamsList(classPath, methodName);
	}
	/**
	 * 根据参数名称匹配对应的方法
	 * 1.如果只有一个方法，那就无论参数是什么都执行
	 * 2.如果存在多个同名方法，就匹配一个全部参数都可以获取到的参数最多的方法
	 * @param methods 按照参数个数倒序排列的参数
	 * @param paramNames 请求中的所有参数的名称
	 * @return
	 * add at Dec 11, 2014
	 */
	public static JLessMethod matchMethodByParam(List<JLessMethod> methods,List<String> paramNames){
		//匹配第一种情况
		if (methods.size() == 1) {
			return methods.get(0);
		}
		//匹配第二种情况
		for (int i = 0; i < methods.size(); i++) {
			boolean has_find = true;
			//判断全部参数是否都可以获取到
			for (int j = 0; j < methods.get(i).getParamNames().size(); j++) {
				if (!paramNames.contains(methods.get(i).getParamNames().get(j))) {
					has_find = false;
				}
			}
			//全部都能找到说明匹配成功
			if (has_find) {
				return methods.get(i);
			}
		}
		//没有匹配到
		return null;
	}
	/**
	 * 方法匹配规则
	 * @param newLine 指定换行符 默认<br/>
	 * @return 
	 * add at Dec 13, 2014
	 */
	public static String matechMethodRule(String newLine){
		newLine = newLine == null? "<br/>" : newLine;
		StringBuilder tips = new StringBuilder();
		tips.append(newLine);
		tips.append("匹配规则是:");
		tips.append(newLine);
		tips.append("1.如果只有一个方法，那就无论参数是什么都执行");
		tips.append(newLine);
		tips.append("2.如果存在多个同名方法，就匹配一个全部参数都可以获取到的参数最多的方法");
		
		return tips.toString();
	}
	/**
	 * 根据参数执行方法
	 * @param jLessMethod
	 * @param parameter
	 * @return
	 * add at Dec 13, 2014
	 */
	public static Object invokeMethod(JLessMethod jLessMethod,RequestParameter parameter) {
		log.debug("//新建一个对象");
		Object object = null;
		try {
			object = Classes.loadClass(jLessMethod.getClassPath()).newInstance();
		} catch (Exception e) {
			log.error("加载类失败!"+jLessMethod.getClassPath(),e);
			throw new RuntimeException("执行方法时获取对象失败："+jLessMethod.getClassPath(),e);
		}

		
		log.debug("//参数以及属性注入");
		List<Object> params = InjectAttrsParams.inject(object, jLessMethod,parameter.getParamMap());
		
		log.debug("//执行");
		try {
			Method method = Classes.getMethod(jLessMethod);
			return MethodUtil.invoke(method, object, params.toArray());
		} catch (Exception e) { 
			log.error("方法执行失败!",e);
			throw new RuntimeException("方法执行时异常!",e);
		}
	}
}
