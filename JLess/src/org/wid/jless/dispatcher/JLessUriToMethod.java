package org.wid.jless.dispatcher;
/**
 * 将JLess拦截到的uri转换为对应的方法路径
 * @author wid
 * Create at Dec 10, 2014
 */
public interface JLessUriToMethod {
	/**
	 * 根据拦截到的uri获取方法都完整路径，如 org.wid.jless.Method 类路径+方法名
	 * <br/> 返回null 可以用于屏蔽那些不能被访问到的方法
	 * @param uri 拦截到的uri
	 * @return
	 * add at Dec 10, 2014
	 */
	public String uriToMethodPath(String uri);

}
