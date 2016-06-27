package leetcodeTest;

import java.util.Stack;

public class MyQueue {
	Stack<Integer> front = new Stack<Integer>();
	Stack<Integer> end = new Stack<Integer>();
	boolean curr = true;
	// Push element x to the back of queue.
    public void push(int x) {
        if(!curr)
        	change();
        front.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        if(curr)
        	change();
        end.pop();
    }

    // Get the front element.
    public int peek() {
        if(curr)
        	change();
        return end.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        if(curr)
        	return front.isEmpty();
        else 
        	return end.isEmpty();
    }
    
    public void change(){
    	if(curr){
    		while(!front.isEmpty()){
    			end.push(front.pop());
    			
    		}
    		curr = !curr;
    	}
    	else {
    		while(!end.isEmpty()){
    			front.push(end.pop());
    			
    		}
    		curr = !curr;
    		
    	}
    }
}
