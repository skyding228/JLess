package org.wid.jless.dispatcher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 请求参数
 * @author wid
 * Create at Dec 11, 2014
 */
class RequestParameter {
	
	private Map<String, String[]> paramMap = null;
	
	private List<String> paramNames = null;
	public RequestParameter(Map<String, String[]> paramMap){
		this.paramMap = paramMap;
	}
	/**
	 * 获取所有的参数名称
	 * @return
	 * add at Dec 11, 2014
	 */
	public List<String> getParamNames(){
		if (paramNames == null) {
			paramNames = new ArrayList<String>();
			Iterator<String> iterator = paramMap.keySet().iterator();
			while (iterator.hasNext()) {
				paramNames.add(iterator.next());
			}
		}
		
		return paramNames;
	}
	/**
	 * 获取参数个数
	 * @return
	 * add at Dec 11, 2014
	 */
	public int getParamCount(){
		if (paramNames == null) {
			getParamNames();
		}
		return paramNames.size();
	}
	/**
	 * 获取参数
	 * @return
	 * add at Dec 13, 2014
	 */
	public Map<String, String[]> getParamMap() {
		return paramMap;
	}

	
}
