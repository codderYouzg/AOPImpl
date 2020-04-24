package youzg.aop.core;

public interface IIntercepter {
	boolean before(Object object, Object[] args);
	Object after(Object result);
}
