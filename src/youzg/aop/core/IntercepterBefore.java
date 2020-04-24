package youzg.aop.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class IntercepterBefore implements IBefore {
	private IntercepterLink intercepterLink;
	private Object object;
	private Method method;

	IntercepterBefore() {
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
	
	private Object[] getParaValues() {
		Object[] paraValues = new Object[] {};
		int argsCount = method.getParameterCount();
		
		if (argsCount > 0) {
			Class<?>[] paraTypes = method.getParameterTypes();
			JoinPoint joinPoint = intercepterLink.getJoinPoint();
			Object[] parasValue = joinPoint.getArgs();
			
			if (argsCount == 1 && paraTypes[0].equals(JoinPoint.class)) {
				paraValues = new Object[] { joinPoint };
			} else {
				paraValues = new Object[argsCount];
				Class<?>[] methodParaTypes = joinPoint.getParaTypes();
				for (int index = 0; index < argsCount; index++) {
					if (!methodParaTypes[index].equals(paraTypes[index])) {
						continue;
					}
					paraValues[index] = parasValue[index];
				}
			}
		}
		
		return paraValues;
	}

	@Override
	public boolean before() {
		Object result = null;
		try {
			Object[] paraValues = getParaValues();
			result = method.invoke(object, paraValues);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		if (result != null 
				&& method.getReturnType().equals(boolean.class)) {
			return (boolean) result;
		}
		
		return true;
	}

}
