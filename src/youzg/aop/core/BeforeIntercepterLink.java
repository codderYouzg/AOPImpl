package youzg.aop.core;

public class BeforeIntercepterLink {
	private IntercepterLink intercepterLink;
	private IntercepterBeforeNode head;
	
	public BeforeIntercepterLink() {
		this.head = null;
	}

	void setIntercepterLink(IntercepterLink intercepterLink) {
		this.intercepterLink = intercepterLink;
	}

	Object[] getArgs() {
		return intercepterLink.getArgs();
	}
	
	void addBeforeIntercepter(IBefore before) {
		IntercepterBeforeNode node =
				new IntercepterBeforeNode(this, before);
		
		if (head == null) {
			head = node;
		} else {
			head.addNode(node);
		}
	}
	
	boolean before() {
		if (head == null) {
			return true;
		}
		
		return head.doBefore();
	}
	
}
