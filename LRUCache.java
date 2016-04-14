package leetcodeTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class LRUCache {
	//Map<Integer,Integer> list ;
	LinkedHashMap<Integer,Integer> nums ;
	int capacity;
	
	public LRUCache(int capacity) {
		this.capacity = capacity;
		nums = new LinkedHashMap<Integer,Integer>(capacity+1);
    }
    
	public int get(int key) {
        if(nums.get(key)== null) return -1;
        else{
            int val = nums.get(key);
            nums.remove(key);
            nums.put(key,val);
            return val;
        } 
    }
    
    public void set(int key, int value) {
    	nums.remove(key);
    	nums.put(key, value);
    	if(nums.size()>capacity)
    		nums.remove(nums.entrySet().iterator().next().getKey());
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime = System.nanoTime(); 
		LRUCache c = new LRUCache(3);
		System.out.println(c.get(123));
		c.set(1, 1);
		c.set(2, 2);
		c.set(3, 3);
		c.set(4, 4);
		System.out.println(c.get(1));
		System.out.println(c.get(3));
		c.set(5, 5);
		c.set(1, 1);
		System.out.println(c.get(1));
		System.out.println(c.get(3));
		c.set(4, 4);
		c.set(4, 5);
		c.set(5, 5);
		c.set(2, 2);
		System.out.println(c.get(1));
		System.out.println(c.get(4));
		for(int i=5;i<100;i++){
			c.set(i,i);
			c.set(i-4,i-4);
			System.out.println(i+": " +c.get(i-7));
		}
			
		long consumingTime = System.nanoTime() - startTime;
		 System.out.println(consumingTime/1000000+"ms");
	}

}
