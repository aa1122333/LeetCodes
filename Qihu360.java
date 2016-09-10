package leetcodeTest;

import java.util.HashMap;
import java.util.Scanner;


class handler{
	int index;
	int ju;
	int length;
	public handler(int index,int ju,int length){
		this.index = index;
		this.ju = ju;
		this.length = length;
	}
}
public class Qihu360 {
	
	public static void main(String args[])
    {
		Scanner cin = new Scanner(System.in);
		 while(cin.hasNextInt()){
			 int m = cin.nextInt();
			 int n = cin.nextInt();
			 HashMap<Integer,handler> indexs = new HashMap<>();
			 int [] mem = new int[n];
			 int [] poi = new int[n];
			 int ju = 1;
			 for(int i=0;i<n;i++){
				 mem[i] = n-i;
			 }
			 for(int i=0;i<m;i++){
				 String oper = cin.next();
				 
				 if(oper.equals("new")){
					 int num = cin.nextInt();
					 boolean cannew = false;
					 for(int j=0;j<n;j++){
						 if(mem[j]>=num){
							 int k = j;
							 cannew = true;
							 for(;k<j+num;k++){
								 mem[k] = 0;
							 }
							 while(k<n && mem[j]!=0){
								 mem[k] = mem[k]-num;
								 k++;
							 }
							 handler h = new handler(j,ju,num);
							 indexs.put(ju, h);
							 poi[j] = ju;
							 System.out.println(ju);
							 ju++;
							 break;
						 }
					 }
					 if(!cannew)
						 System.out.println("NULL");
				 }
				 else if(oper.equals("del")){
					 int num = cin.nextInt();
					 handler curr = indexs.get(num);
					 if(curr==null) System.out.println("ILLEGAL_OPERATION");
					 else {
						 int next ;
						 if(curr.index+curr.length==n){
							 next = 0;
						 }
						 else next = mem[curr.index+curr.length];
						 int j=curr.index+curr.length-1;
						 for(;j>=curr.index;j--){
							 next++;
							 mem[j] = next;
						 }
						 while(j>=0 && mem[j]!=0){
							 next++;
							 mem[j--] = next;
						 }
						 indexs.remove(num);
						 poi[curr.index] = 0;
					 }
				 }
				 else if(oper.equals("def")){
					 for(int j=0;j<n;j++){
						 int start = -1;
						 if(mem[j]!=0){
							 start = j;
							 int s2 = -1;
							 for(int k=j+1;k<n;k++){
								 if(mem[k]==0){
									 s2 = k;
									 break;
								 }
							 }
							 if(s2!=-1){
								 handler h = indexs.get(poi[s2]);
								 int end = h.index+h.length;
								 int ends = 0;
								 if(end<n)
									 ends = mem[end];
								 int kk=end-1;
								 int currlength=0;
								 for(;currlength<h.length;currlength++){
									 ends++;
									 mem[kk-currlength] = ends;
								 }
								 while(kk-currlength>=0 && mem[kk-currlength]!=0){
									 ends++;
									 mem[kk-currlength] = ends;
									 currlength++;
								 }
								 for(int k=start;k<h.length+start;k++){
									 mem[k] = 0;
								 }
								 handler ht = indexs.get(h.ju);
								 poi[h.index] = 0;
								 ht.index = start;
								 indexs.put(h.ju, ht);
								 poi[start] = h.ju;
								 
							 }
						 }
					 }
				 }
			 }
		 }
		
    }
}
