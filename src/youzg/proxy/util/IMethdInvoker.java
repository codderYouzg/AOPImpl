package youzg.proxy.util;

import java.lang.reflect.Method;

public interface IMethdInvoker {
	//通过反射机制执行 被代理的方法
	Object methodInvoke(Object object, Method method, Object[] args);
}