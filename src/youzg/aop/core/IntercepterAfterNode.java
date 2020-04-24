package youzg.aop.core;

public class IntercepterAfterNode {
	private IAfter after;
	private IntercepterAfterNode next;
	
	IntercepterAfterNode() {
		this.next = null;
	}

	void setAfter(IAfter after) {
		this.after = after;
	}
	
	void addNode(IntercepterAfterNode node) {
		if (next == null) {
			this.next = node;
			return;
		}
		next.addNode(node);
	}
	
	Object doAfter(Object result) {
		if (next == null) {
			return after.after(result);
		}
		// ϣ�������á����ݡ���ʽ���ú�������
		result = next.doAfter(result);
		result = after.after(result);
		
		return result;
	}
	
}
