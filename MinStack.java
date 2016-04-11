package leetcodeTest;

import java.util.Arrays;
import java.util.Stack;

public class MinStack {
	
	Stack<Integer> s ;
	int min;
	MinStack(){
		s = new Stack<Integer>();
		min = Integer.MAX_VALUE;
	}
	public void push(int x) {
       if(x<min){
    	   s.push(min);
    	   min =x;
       }
       s.push(x);
    }

    public void pop() {
    	if(s.peek() == min){
    		s.pop();
    		min = s.peek();
    		
    	}
    	s.pop();
    	if(s.empty()){
    		min = Integer.MAX_VALUE;
    	}
    }

    public int top() {
        return s.peek();
    }

    public int getMin() {
    	return min;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MinStack ms = new MinStack();
		for(int i=0;i<5;i++)
		ms.push(i);
		ms.pop();
		System.out.println(ms.top());
		System.out.println(ms.getMin());
	}

}
