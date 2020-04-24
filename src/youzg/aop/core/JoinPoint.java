package youzg.aop.core;

import java.lang.reflect.Method;

public class JoinPoint {
	private Class<?> klass;
	private Method method;
	private Class<?>[] paraTypes;
	private Object[] args;
	private ReturnValue result;
	
	public JoinPoint() {
		result = new ReturnValue();
	}

	public ReturnValue getReturnValue() {
		return result;
	}
	
	public Class<?> getKlass() {
		return klass;
	}

	public Method getMethod() {
		return method;
	}

	Class<?>[] getParaTypes() {
		return paraTypes;
	}

	void setKlass(Class<?> klass) {
		this.klass = klass;
	}

	void setMethod(Method method) {
		this.method = method;
	}

	void setParaTypes(Class<?>[] paraTypes) {
		this.paraTypes = paraTypes;
	}

	void setArgs(Object[] args) {
		this.args = args;
	}

	void setResult(Object result) {
		this.result.setReturnType(result.getClass());
		this.result.setValue(result);
	}

	public Object[] getArgs() {
		return args;
	}

	public Object getResult() {
		return result;
	}
	
}
