package youzg.aop.core;

import edu.youzg.proxy.core.cglib.core.CGLibProxy;
import edu.youzg.proxy.core.jdk.core.JDKProxy;

public class MecProxy {
	public static final int JDK_PROXY = 0;
	public static final int CGLIB_PROXY = 1;
	
	private int proxyType = JDK_PROXY;
	private INewInstanceMaker maker;
	private IIntercepter intercepter;
	
	public MecProxy() {
	}

	public void setMaker(INewInstanceMaker maker) {
		this.maker = maker;
	}

	public void setIntercepter(IIntercepter intercepter) {
		this.intercepter = intercepter;
	}

	public void setProxyType(int proxyType) {
		if (proxyType != JDK_PROXY && proxyType != CGLIB_PROXY) {
			proxyType = JDK_PROXY;
		}
		this.proxyType = proxyType;
	}

	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<?> klass) {
		Object target = null;
		if (maker == null) {
			try {
				target = klass.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} else {
			target = maker.getNewInstance(klass);
		}
		
		return (T) getProxy(target);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy(Object target) {
		if (proxyType == JDK_PROXY) {
			JDKProxy jdkProxy = new JDKProxy();
			jdkProxy.setMaker(maker);
			jdkProxy.setIntercepter(intercepter);
			
			return (T) jdkProxy.getProxy(target);
		}
		CGLibProxy cgLibProxy = new CGLibProxy();
		cgLibProxy.setMaker(maker);
		cgLibProxy.setIntercepter(intercepter);
		
		return (T) cgLibProxy.getProxy(target);
	}
	
}
