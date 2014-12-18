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
				paramList.add(convert(method.getParamTypes().get(i),param));
			}
		}
		
		return paramList;
	}
	/**
	 * 将参数数组转换为对应的类型
	 * @param clazz
	 * @param value
	 * @return
	 * add at Dec 17, 2014
	 */
	@SuppressWarnings("rawtypes")
	public static Object convert(Class clazz,String[] value){
		if (clazz.getName().equals("int")) {
			return Integer.valueOf(value[0]);
		}
		if (clazz.getName().equals("long")) {
			return Long.valueOf(value[0]);
		}
		return value[0];
	}
}
