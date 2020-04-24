package youzg.aop.core;

public class ReturnValue {
	private Class<?> returnType;
	private Object value;
	
	public ReturnValue() {
	}

	Class<?> getReturnType() {
		return returnType;
	}

	void setReturnType(Class<?> returnType) {
		this.returnType = returnType;
	}

	Object getValue() {
		return value;
	}

	void setValue(Object value) {
		this.value = value;
	}
	
}
