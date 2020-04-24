package youzg.proxy.core.cglib.core;

import java.lang.reflect.Method;

import edu.youzg.aop.core.IIntercepter;
import edu.youzg.aop.core.INewInstanceMaker;
import edu.youzg.aop.core.MethodInvoker;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLibProxy {
	private INewInstanceMaker maker;
	private IIntercepter intercepter;

	public CGLibProxy() {
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
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(klass);
		enhancer.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object object, Method method, Object[] args, 
					MethodProxy proxy) throws Throwable {
				
				MethodInvoker methodInvoker =
						new MethodInvoker(target, method, args);
				methodInvoker.setIntercepter(intercepter);
				Object result = methodInvoker.methodInvoke();
				
				return result;
			}
		});
		return (T) enhancer.create();
	}
	
}
