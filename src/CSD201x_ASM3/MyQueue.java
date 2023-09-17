package CSD201x_ASM3;

import java.util.LinkedList;

public class MyQueue {
	 LinkedList<Object> a;
	    public MyQueue() {
	        a = new LinkedList<>();
	    }
	    public boolean isEmpty() {
	        return a.isEmpty();
	    }
	    public void enqueue(Object obj) {
	        a.addLast(obj);
	    }
	    public Object dequeue() {
	    	if(isEmpty()) {
	    		return null;
	    	}else {
	        return a.removeFirst();
	    	}
	    }
	    public Object peek() {
	    	if(isEmpty()) {
	    		return null;
	    	}else {
	    	return a.getFirst();
	    	}
	    }
	    
}
