package org.wid.jless.ssist;

import java.util.List;

/**
 * 单个方法的参数信息
 * @author wid
 *
 */
@SuppressWarnings("rawtypes")
public class JLessMethod {
	private int modify;	
	private List<Class> paramTypes;
	private List<String> paramNames ; 
	
	private String classPath; //所属类的完整路径
	private String methodName;
	
	public JLessMethod() {
		
	}

	public JLessMethod(int modify, List<Class> paramTypes,
			List<String> paramNames,String classPath,String methodName) {
		super();
		this.modify = modify;
		this.paramTypes = paramTypes;
		this.paramNames = paramNames;
		this.classPath = classPath;
		this.methodName = methodName;
	}


	public int getModify() {
		return modify;
	}
	public void setModify(int modify) {
		this.modify = modify;
	}
	public List<Class> getParamTypes() {
		return paramTypes;
	}
	public void setParamTypes(List<Class> paramTypes) {
		this.paramTypes = paramTypes;
	}
	public List<String> getParamNames() {
		return paramNames;
	}
	public void setParamNames(List<String> paramNames) {
		this.paramNames = paramNames;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
}
