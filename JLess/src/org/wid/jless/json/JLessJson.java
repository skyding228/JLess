package org.wid.jless.json;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * json 工具类，实现javabean与json字符串都互转
 * @author wid
 *
 */
public class JLessJson {

	private static ObjectMapper mapper = new ObjectMapper();
	/**
	 * 将java对象转换为json字符串,方便日志打印
	 * @param obj
	 * @return 转换后的字符串或者null
	 */
	public static String stringify(Object obj){
		
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
