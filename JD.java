package leetcodeTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class JD {
	public static void main(String args[])
    {
		Scanner cin = new Scanner(System.in);
		 while(cin.hasNextInt())
	     {
			 int n = cin.nextInt();
			 int m = cin.nextInt();
			 int [] prices = new int[n]; 
			 for(int i=0;i<n;i++){
				 prices[i] = cin.nextInt();
			 }
			 HashMap<String,Integer> map = new HashMap<>();
			 for(int i=0;i<m;i++){
				 String s = cin.next();
				 if(map.containsKey(s)){
					 map.put(s, map.get(s)+1);
				 }
				 else {
					 map.put(s, 1);
				 }
			 }
			 Arrays.sort(prices);
			 
			 int size = map.size();
			 Iterator iter = map.entrySet().iterator();
			 int [] things = new int [size];
			 int l=0;
			 while (iter.hasNext()){
				 Map.Entry entry = (Map.Entry) iter.next();
				 things[l++]=(int)entry.getValue();
			 }
			 Arrays.sort(things);
			 int min = 0;
			 int max = 0;
			 for(int i=0;i<size;i++){
				 min += prices[i]*things[size-1-i];
				 max += prices[n-i-1]*things[size-1-i];
			 }
			 System.out.println(min+" "+max);
	     }
    }
	
	public static void main2(String args[])
    {
		Scanner cin = new Scanner(System.in);
		int zu = cin.nextInt();
		int[] nums = new int[100001];
		nums[0] = 0;
		for(int i=1;i<=100000;i++){
            int sum1 = 0;
            int sum2 = 0;
            int t = i;
            while(t>0){
            	sum1+=t%10;
            	t/=10;
            }
            t = i;
            while(t>0){
            	sum2+=t%2;
            	t/=2;
            }
            if(sum1==sum2)
            	nums[i] = nums[i-1]+1;
            else nums[i] = nums[i-1];
        }
		for(int z = 0;z<zu;z++){
	            int n = cin.nextInt();
	            System.out.println(nums[n]);
		}
    }
}
