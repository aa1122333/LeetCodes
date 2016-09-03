package leetcodeTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class UTFToUnicode {
	
	public static int get(String s){
		int t = 1;
		int sum = 0;
		for(int i=s.length()-1;i>=0;i--){
			if(s.charAt(i)=='1')
			sum+=t;
			t*=2;
		}
		return sum;
	}
	
	public static void main(String args[])
    {
        Scanner cin = new Scanner(System.in);
        boolean first = true;
        
        while(cin.hasNextInt())
        {
        	String[] input = cin.nextLine().split(" ");
        	List<Integer> solu = new ArrayList<Integer>();
        	int  p = 0;
        	while(p<input.length){
        		int curr = Integer.valueOf(input[p]);
        		p++;
	        	if(curr<128) solu.add(curr);
	        	else if(curr>=128 && curr<192) {System.out.println("no");break;}
	        	else if(curr<224){
	        		int s = Integer.valueOf(input[p]);
	        		p++;
	        		if(s<128 || s>192) {System.out.println("no");break;}
	        		String c = Integer.toBinaryString(s);
	        		String t2 = Integer.toBinaryString(curr);
	        		String sol = t2.substring(3)+c.substring(2);
	        		solu.add(get(sol));
	        	}
	        	else if(curr<240){
	        		int s = Integer.valueOf(input[p]);
	        		p++;
	        		if(s<128 || s>192) {System.out.println("no");break;}
	        		int s2 = Integer.valueOf(input[p]);
	        		p++;
	        		if(s2<128 || s2>192) {System.out.println("no");break;}
	        		String c = Integer.toBinaryString(s);
	        		String c2 = Integer.toBinaryString(s2);
	        		String t2 = Integer.toBinaryString(curr);
	        		String sol = t2.substring(4)+c.substring(2)+c2.substring(2);
	        		solu.add(get(sol));
	        	}
	        	else if(curr<248){
	        		int s = Integer.valueOf(input[p]);
	        		p++;
	        		if(s<128 || s>192) {System.out.println("no");break;}
	        		int s2 = Integer.valueOf(input[p]);
	        		p++;
	        		if(s2<128 || s2>192) {System.out.println("no");break;}
	        		int s3 = Integer.valueOf(input[p]);
	        		p++;
	        		if(s3<128 || s3>192) {System.out.println("no");break;}
	        		String c = Integer.toBinaryString(s);
	        		String c2 = Integer.toBinaryString(s2);
	        		String c3 = Integer.toBinaryString(s3);
	        		String t2 = Integer.toBinaryString(curr);
	        		String sol = t2.substring(4)+c.substring(2)+c2.substring(2)+c3.substring(2);
	        		solu.add(get(sol));
	        	}
	        	else if(curr<252){
	        		int s = Integer.valueOf(input[p]);
	        		p++;
	        		if(s<128 || s>192) {System.out.println("no");break;}
	        		int s2 = Integer.valueOf(input[p]);
	        		p++;
	        		if(s2<128 || s2>192) {System.out.println("no");break;}
	        		int s3 = Integer.valueOf(input[p]);
	        		p++;
	        		if(s3<128 || s3>192) {System.out.println("no");break;}
	        		int s4 = Integer.valueOf(input[p]);
	        		p++;
	        		if(s4<128 || s4>192) {System.out.println("no");break;}
	        		String c = Integer.toBinaryString(s);
	        		String c2 = Integer.toBinaryString(s2);
	        		String c3 = Integer.toBinaryString(s3);
	        		String c4 = Integer.toBinaryString(s4);
	        		String t2 = Integer.toBinaryString(curr);
	        		String sol = t2.substring(5)+c.substring(2)+c2.substring(2)+c3.substring(2)+c4.substring(2);
	        		solu.add(get(sol));
	        	}
        	}
        	for(int i=0;i<solu.size();i++){
        		if(i!=0) System.out.print(" ");
        		System.out.print(solu.get(i));
        	}
        	System.out.println();
        }
        
    }
}
