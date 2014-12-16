package org.wid.jless.dispatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.wid.jless.ssist.JLessMethod;

/**
 * 将请求参数注入到对象的属性以及方法参数中
 * @author wid
 * Create at Dec 13, 2014
 */
class InjectAttrsParams {
	/**
	 * 将请求参数注入到对象的属性以及方法参数中
	 * 1.根据名称注入
	 * 2.如果类型转换失败，就注入null
	 * 3.如果属性名与参数名相同，优先参数，属性不再注入
	 * @param object
	 * @param paramMap
	 * @return 注入的参数列表
	 * add at Dec 13, 2014
	 */
	public static List<Object> inject(Object object,JLessMethod method,Map<String, String[]> paramMap){
		List<Object> paramList = new ArrayList<Object>();
		String[] param = null;
		for (int i = 0; i < method.getParamNames().size(); i++) {
			param = paramMap.get(method.getParamNames().get(i));
			if (param == null) {
				paramList.add(null);
			}else {
				paramList.add(param[0]);
			}
		}
		
		return paramList;
	}
}
