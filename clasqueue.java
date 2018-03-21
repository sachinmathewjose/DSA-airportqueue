package airpporque;

public class clasqueue {
	protected Node front, end;
	protected int count;
	static protected class Node {
		Object element;
		Node next;
	}
	public clasqueue() {
		front = null;
		end = null;
		count = 0;
	}
	
	public void enQueue(Object item) {
		Node n = new Node();
		n.element = item;
		n.next = null;
		if (front != null)
			end.next = n;
		else
			front = n; 
		end = n; 
		count ++;
	}
	
	public Object deQueue() {
		if (isEmptyQueue())
			return null;
		Object item = front.element;
		if (front.next == null)
			end = null; 
		front = front.next; 
		count--;
		return item;
	}

	public Object front() {
		return (front == null ? null : front.element);
	}

	public int queueSize() {
		return count;
	}

	public boolean isEmptyQueue() {
		return (front == null);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Node n = front; n != null; n = n.next) {
			sb.append(n.element);
			sb.append("\n");
		}
		return sb.toString();
	}
}
