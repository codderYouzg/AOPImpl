package youzg.proxy.core.jdk.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import edu.youzg.aop.core.IIntercepter;
import edu.youzg.aop.core.INewInstanceMaker;
import edu.youzg.aop.core.MethodInvoker;

public class JDKProxy {
	private INewInstanceMaker maker;
	private IIntercepter intercepter;
	
	public JDKProxy() {
	}
	
	public void setMaker(INewInstanceMaker maker) {
		this.maker = maker;
	}

	public void setIntercepter(IIntercepter intercepter) {
		this.intercepter = intercepter;
	}

	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<?> klass) {
		try {
			Object target = null;
			target = maker == null 
					? klass.newInstance()
					: maker.getNewInstance(klass);

			return (T) getProxy(target);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy(T target) {
		Class<?> klass = target.getClass();
		ClassLoader classLoader = klass.getClassLoader();
		Class<?>[] interfaces = klass.getInterfaces();
		
		return (T) Proxy.newProxyInstance(classLoader, interfaces, 
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						
						MethodInvoker methodInvoker =
								new MethodInvoker(target, method, args);
						methodInvoker.setIntercepter(intercepter);
						Object result = methodInvoker.methodInvoke();
										
						return result;
					}
				});
	}
	
}
