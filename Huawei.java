package leetcodeTest;

import java.util.HashSet;
import java.util.Scanner;



public class Huawei {
	
	public static String getNewString(int a){
		String as = Integer.toBinaryString(a);
		int length = as.length();
		if(as.length()>16){
			as = as.substring(as.length()-16);
		}
		else if(as.length()<16){
			for(int i=0;i<(16-length);i++)
				as = "0"+as;
		}
		return as;
	}
	
	public static String getNewString2(int a){
		String as = Integer.toBinaryString(a);
		int length = as.length();
		if(as.length()>10){
			as = as.substring(as.length()-10);
		}
		else if(as.length()<10){
			for(int i=0;i<(10-length);i++)
				as = "0"+as;
		}
		return as;
	}
	
	public static int calc(String s){
		int curr = 1;
		int sum = 0;
		for(int i=s.length()-1;i>=0;i--){
			if(s.charAt(i)=='1'){
				sum+=curr;
			}
			curr =curr<<1;
		}
		return sum;
	}
	
	public static void main(String args[])
    {
		Scanner cin = new Scanner(System.in);
		int a = cin.nextInt();
		int b = cin.nextInt();
		int c = cin.nextInt();
		String as = getNewString(a);
		String bs = getNewString(b);
		String cs = getNewString(c);
		String cl = as+bs+cs;
		String curr = cl + cl;
		int start = curr.length()-5;
		int end = curr.length();
		String maps = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
		int sumstring = 0;
		char[] sol = new char[19];
		for(int i=0;i<17;i++){
			if(i==4 || i==9 || i == 14) sol[i++] = '-';
			sol[i] = maps.charAt(calc(curr.substring(start, end)));
			sumstring +=sol[i];
			start -=5;
			end -=5;
		}
		String currcheck = getNewString2(sumstring);
		start = currcheck.length()-5;
		end = currcheck.length();
		for(int i=17;i<19;i++){
			sol[i] = maps.charAt(calc(currcheck.substring(start, end)));
			start -=5;
			end -=5;
		}
		System.out.println(String.valueOf(sol));
		
    }
	
	public static void main3(String args[])
    {
		Scanner cin = new Scanner(System.in);
		String inn = cin.nextLine();
		char[] s = inn.toCharArray();
		for(int i=0;i<s.length;i++){
			if(s[i]=='.' || s[i]==',') 
				s[i]=' ';
		}
		String curr = String.valueOf(s);
		String[] str = curr.trim().split(" +");
		HashSet<String> set = new HashSet<>();
		
		for(int i=0;i<str.length;i++){
			if(i==0){
				System.out.print(str[i]);
				set.add(str[i]);
			}
			else if(!set.contains(str[i])){
				System.out.print(" "+str[i]);
				set.add(str[i]);
			}
		}
		System.out.println();
			
    }
	
	public static void main2(String args[])
    {
		Scanner cin = new Scanner(System.in);
		int n = cin.nextInt();
		int m = cin.nextInt();
		if(n==0){
			if(m==0) System.out.println("1 0 0");
			else System.out.println("0 0 0");
		}
		else if(m%2!=0) System.out.println("0 0 0");
		else if(n*4<m || n*2>m) System.out.println("0 0 0");
		else {
			int rabbits = m/2-n;
			int pegin = n*2-m/2;
			System.out.println("1 "+pegin+" "+rabbits);
		}
		
    }

}
