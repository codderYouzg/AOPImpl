package youzg.aop.core;

public class IntercepterBeforeNode {
	private IBefore before;
	private IntercepterBeforeNode next;
	
	IntercepterBeforeNode() {
		this.next = null;
	}

	IntercepterBeforeNode(BeforeIntercepterLink link, IBefore before) {
		this.before = before;
		this.next = null;
	}

	void setBefore(IBefore before) {
		this.before = before;
	}
	
	void addNode(IntercepterBeforeNode node) {
		if (next == null) {
			next = node;
			return;
		}
		next.addNode(node);
	}
	
	boolean doBefore() {
		boolean ok = before.before();
		if (!ok) {
			return ok;
		}
		
		if (next != null) {
			return next.doBefore();
		}
		
		return ok;
	}
	
}
