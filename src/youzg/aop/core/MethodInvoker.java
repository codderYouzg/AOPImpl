package youzg.aop.core;

import java.lang.reflect.Method;

public class MethodInvoker {
	private Object object;
	private Method method;
	private Object[] paras;
	
	private IIntercepter intercepter;
	
	public MethodInvoker(Object object, Method method, Object[] paras) {
		this.object = object;
		this.method = method;
		this.paras = paras;
	}

	public void setIntercepter(IIntercepter intercepter) {
		this.intercepter = intercepter;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public void setParas(Object[] paras) {
		this.paras = paras;
	}
	
	public Object methodInvoke() throws Throwable {
		// ����Ĵ���Ӧ������Ҫʵ��AOP���ܵĳ���Ա����д
		IntercepterLink intercepterLink = new IntercepterLink();
		// ����intercepterLinkӦ���Ǹ��ݵ�ǰmethd��
		// ��������ӳ�����ҵ��ģ���������������new�����ģ�
		
		JoinPoint joinPoint = new JoinPoint();
		joinPoint.setKlass(object.getClass());
		joinPoint.setMethod(method);
		joinPoint.setParaTypes(method.getParameterTypes());
		joinPoint.setArgs(paras);
		intercepterLink.setJoinPoint(joinPoint);
		
		if (intercepter != null) {
			boolean ok = intercepter.before(object, paras);
			if (!ok) {
				return null;
			}
		}
		
		Object result = method.invoke(object, paras);
		joinPoint.setResult(result);
		
		if (intercepter != null) {
			result = intercepter.after(result);
		}
		// ����Ĵ���Ӧ������Ҫʵ��AOP���ܵĳ���Ա����д
		
		return result;
	}
	
}
