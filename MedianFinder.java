package leetcodeTest;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MedianFinder {
	//295. Find Median from Data Stream

	// Adds a number into the data structure.
	public PriorityQueue<Integer> max = new PriorityQueue<Integer>(111,new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			// TODO Auto-generated method stub
			return o2-o1;
		}
		
	});
	public PriorityQueue<Integer> min = new PriorityQueue<Integer>();
	
	public void addNum(int num) {
		if(max.size()==0 || max.peek()>=num){
			if(max.size()>min.size()){
				min.offer(max.poll());
			}
			max.offer(num);
		}
		else if(min.size() == 0 ||min.peek()<num){
			if(max.size()<min.size()){
				max.offer(min.poll());
			}
			min.offer(num);
		}
		else {
			if(max.size()>min.size()){
				min.offer(num);
			}
			else max.offer(num);
		}
		
		
	}

	// Returns the median of current data stream
	public double findMedian() {
		if(max.size() == 0 && min.size() == 0){
			return 0;
		}else if(max.size() == min.size()){
			return (max.peek() + min.peek())/2.0; 
		}else if(max.size()>min.size()){
			return max.peek();
		}else if(max.size()<min.size()){
			return min.peek();
		}
	    return 0;
	}
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MedianFinder mf = new MedianFinder();
		/*mf.addNum(61);*/
		System.out.println(mf.findMedian());
		mf.addNum(62);
		System.out.println(mf.findMedian());
		/*mf.addNum(2);
		System.out.println(mf.findMedian());
		mf.addNum(3);
		System.out.println(mf.findMedian());
		mf.addNum(67);
		System.out.println(mf.findMedian());
		mf.addNum(17);
		System.out.println(mf.findMedian());
		mf.addNum(50);
		System.out.println(mf.findMedian());
		mf.addNum(32);
		System.out.println(mf.findMedian());
		mf.addNum(74557);
		System.out.println(mf.findMedian());*/
		
		
	}

}
