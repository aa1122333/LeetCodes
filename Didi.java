package leetcodeTest;

import java.io.*;
import java.util.*;

class Pair {
	int people;
	int value;
	public Pair(int p,int v){
		this.people = p;
		this.value = v;
	}
}

public class Didi{
	
	public static void main(String args[])
    {
		Scanner cin = new Scanner(System.in);
		int n = cin.nextInt();
		int m = cin.nextInt();
		int[] tables = new int[n];
		boolean[] visited = new boolean[n];
		int maxm = 0;
		for(int i=0;i<n;i++){
			tables[i] = cin.nextInt();
			if(tables[i]>maxm) maxm = tables[i];
		}
		Arrays.sort(tables);
		PriorityQueue<Pair> queue = new PriorityQueue<Pair>(111,new Comparator<Pair>() {

			@Override
			public int compare(Pair o1, Pair o2) {
			
				if(o1.value<o2.value) return 1;
				else if(o1.value==o2.value) return 0;
				else return  -1;
				
			}
			
		});
		for(int i=0;i<m;i++){
			int p = cin.nextInt();
			int v = cin.nextInt();
			Pair tp = new Pair(p,v);
			queue.add(tp);
		}
		int used = 0;
		int sumcash = 0;
		List<Integer> tbs = new ArrayList<>();
		for(int i=0;i<n;i++){
			tbs.add(tables[i]);
		}
		while(!queue.isEmpty()&& (used!=n)){
			Pair t = queue.poll();
			if(t.people>maxm) continue;
			boolean flag = false;
			for(int i=0;i<tbs.size();i++){
				if(tbs.get(i)>t.people){
					flag = true;
					sumcash+=t.value;
					tbs.remove(i);
					break;
				}
			}
			if(flag)
				used++;
		}
		System.out.println(sumcash);
    }
	
	
	public static void main4(String args[])
    {
		Scanner cin = new Scanner(System.in);
		int n = cin.nextInt();
		//if(n==0)
		int [] nums = new int[n];
		for(int i=0;i<n;i++){
			nums[i] = cin.nextInt();
		}
		int max = Integer.MIN_VALUE;
		int curr = 0;
		for(int i=0;i<n;i++){
			if(curr<0){
				curr = nums[i];
			}
			else 
				curr +=nums[i];
			if(curr>max)
				max = curr;
		}
		System.out.println(max);
    }
}