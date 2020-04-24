package youzg.aop.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class IntercepterAfter implements IAfter {
	private IntercepterLink intercepterLink;
	private Object object;
	private Method method;
	
	public IntercepterAfter() {
	}
	
	void setIntercepterLink(IntercepterLink intercepterLink) {
		this.intercepterLink = intercepterLink;
	}

	void setObject(Object object) {
		this.object = object;
	}

	void setMethod(Method method) {
		this.method = method;
	}

	private Object[] getParaValue() {
		Object[] paraValues = new Object[] {};
		int paraCount = method.getParameterCount();
		
		if (paraCount <= 0) {
			return paraValues;
		}
		
		if (paraCount > 1) {
			return paraValues;
		}
		
		Class<?> paraType = method.getParameterTypes()[0];
		JoinPoint joinPoint = intercepterLink.getJoinPoint();
		if (paraType.equals(JoinPoint.class)) {
			paraValues = new Object[] { joinPoint };
		} else if (paraType.equals(ReturnValue.class)) {
			paraValues = new Object[] { joinPoint.getReturnValue() };
		}
		
		return paraValues;
	}
	
	@Override
	public Object after(Object result) {
		Object[] para = getParaValue();		
		
		Object res = null;
		try {
			res = method.invoke(object, para);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (res == null) {
			return result;
		}
		intercepterLink.getJoinPoint().setResult(result);
		
		return res;
	}

}
