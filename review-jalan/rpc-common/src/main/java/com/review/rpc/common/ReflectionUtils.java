package com.review.rpc.common;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射工具类
 * @author Administrator
 *
 */
public class ReflectionUtils {
	/**
	 *通过一个class  创建一个对象
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public static <T> T newInstance(Class<T> clazz) {
		try {
			return clazz.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} 
	}
	
	public static Method[]  getPublickMethod(Class clazz) {
		Method[] metheds=clazz.getDeclaredMethods();
		List<Method> pmethods=new ArrayList<Method>(); 
		for(Method m:metheds) {
			if(Modifier.isPublic(m.getModifiers())) {
				pmethods.add(m);
			}
		}
		return pmethods.toArray(new Method[0]);
	}
	
	public static Object invoke(Object obj,Method method,Object... args) {
		try {
			return method.invoke(obj, args);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} 
	}

}
