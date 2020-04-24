package youzg.aop.core;

public class IntercepterLink {
	private JoinPoint joinPoint;
	
	private BeforeIntercepterLink beforeLink;
	private AfterIntercepterLink afterLink;
	
	public IntercepterLink() {
		this.beforeLink = null;
		this.afterLink = null;
	}

	void setJoinPoint(JoinPoint joinPoint) {
		this.joinPoint = joinPoint;
	}

	JoinPoint getJoinPoint() {
		return joinPoint;
	}

	public void addBeforeIntercepter(IBefore before) {
		if (beforeLink == null) {
			beforeLink = new BeforeIntercepterLink();
			beforeLink.setIntercepterLink(this);
		}
		beforeLink.addBeforeIntercepter(before);
	}
	
	public void addAfterIntercepter(IAfter after) {
		if (afterLink == null) {
			afterLink = new AfterIntercepterLink();
		}
		afterLink.addAfterIntercepter(after);
	}
	
	boolean before() {
		if (beforeLink == null) {
			return true;
		}
		
		return beforeLink.before();
	}
	
	Object after(Object result) {
		if (afterLink == null) {
			return result;
		}
		
		return afterLink.after(result);
	}
	
	Object[] getArgs() {
		return joinPoint.getArgs();
	}

	public void setArgs(Object[] args) {
		joinPoint.setArgs(args);
	}

	public Object getResult() {
		return joinPoint.getResult();
	}

	public void setResult(Object result) {
		joinPoint.setResult(result);
	}
	
}
