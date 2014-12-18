
package org.wid.jless.ssist;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import org.apache.commons.logging.Log;
import org.wid.jless.log.JLessLog;


 
/**
 * <p>
 * <font color="red">依赖javassit</font>的工具类，获取方法的参数名
 * </p>
 *
 * @author wid
 * @date 2014.12.08
 */
public class Classes {
    private Classes() {}
    private static Log log = JLessLog.getLog(Classes.class);
    
    //初始化类池
    private static ClassPool pool = null;
    static{
    	log.debug("初始化javassist 的ClassPool!");
    	pool = ClassPool.getDefault();
    	pool.insertClassPath(new ClassClassPath(Classes.class));
    }
    /**
     * 根据类路径加载类
     * @param classPath
     * @return
     * add at Dec 11, 2014
     */
    @SuppressWarnings("rawtypes")
	public static Class loadClass(String classPath){
    	if ("int".equals(classPath)) {
			return int.class;
		}
    	try {
			return Class.forName(classPath);
		} catch (ClassNotFoundException e) {
			log.error("加载类失败:"+classPath,e);
		}
    	return null;
    }
    /**
     * 获取java反射的方法
     * @param jLessMethod
     * @return
     * add at Dec 13, 2014
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static Method getMethod(JLessMethod jLessMethod){
    	Method method = null;
    	Class clazz = loadClass(jLessMethod.getClassPath());
    	Class[] paramTypes = new Class[jLessMethod.getParamTypes().size()];
    	try {
			method = clazz.getDeclaredMethod(jLessMethod.getMethodName(), jLessMethod.getParamTypes().toArray(paramTypes));
		} catch (Exception e) {
			log.error("获取反射方法时异常!",e);
		} 
    	return method;
    }
    
    /**
     * 获取此方法的参数信息
     * @param cm
     * @return
     * @throws Exception 
     */
    @SuppressWarnings("rawtypes")
	private static JLessMethod getMethodParams(CtMethod cm) throws Exception{
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute
                .getAttribute(LocalVariableAttribute.tag);

        List<String> paramNames = new ArrayList<String>();
        List<Class> paramTypes = new ArrayList<Class>();
        CtClass[] typeCtClass = cm.getParameterTypes();       
        int count =  typeCtClass.length;
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        
        //取出所有的参数信息
        for (int i = 0; i < count; i++) {
           paramNames.add(attr.variableName(i + pos));
           //获取参数类型
           paramTypes.add(loadClass(typeCtClass[i].getName()));
        }
        
        return new JLessMethod(cm.getModifiers(), paramTypes, paramNames,cm.getDeclaringClass().getName(),cm.getName());
    }

	/**
	 * 获取某个类某个方法名的参数信息
	 * @param clazz
	 * @param method
	 * @return
	 * @throws Exception 
	 */
    public static List<JLessMethod> getMethodParamsList(String clazz, String method) throws Exception {
    	//获取类文件
        CtClass cc = null;
        CtMethod[] cm = null;
        try {
            cc = pool.get(clazz);
            cm = cc.getDeclaredMethods();
        } catch (NotFoundException e) {
        	log.error("第一次未查找到类"+clazz+",将会进行第二次查找!",e);
        	//第二次查找，添加到路径之后
        	try {
				pool.insertClassPath(clazz);
	            cc = pool.get(clazz);
	            cm = cc.getDeclaredMethods();
			} catch (NotFoundException e1) {
				log.error("未找到类"+clazz,e1);
				throw new Exception("未找到类"+clazz,e1);
			}
        }

        List<JLessMethod> methodParams = new ArrayList<JLessMethod>();
        //查找方法
        for (int i = 0; i < cm.length; i++) {
        	if (cm[i].getName().equals(method)) {
        		methodParams.add(getMethodParams(cm[i]));
			}
		}
        return sortListBySizeDesc(methodParams);
    }
    /**
     * 按照方法中参数个数降序排列
     * @param lists
     * @return
     */
    private static List<JLessMethod> sortListBySizeDesc(List<JLessMethod> lists){
    	if (lists == null) {
			return lists;
		}
    	lists.sort(new Comparator<JLessMethod>() {
			@Override
			public int compare(JLessMethod o1, JLessMethod o2) {
				return o2.getParamNames().size() - o1.getParamNames().size();
			}
		});
    	
    	return lists;
    }
   
}