package youzg.aop.core;

public class AfterIntercepterLink {
	private IntercepterAfterNode head;
	
	AfterIntercepterLink() {
		this.head = null;
	}

	public void addAfterIntercepter(IAfter after) {
		IntercepterAfterNode node = new IntercepterAfterNode();
		node.setAfter(after);
		
		if (head == null) {
			head = node;
			return;
		}
		head.addNode(node);
	}
	
	public Object after(Object result) {
		if (head == null) {
			return result;
		}
		return head.doAfter(result);
	}
	
}
