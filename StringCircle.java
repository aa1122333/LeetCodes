package leetcodeTest;

import java.util.HashMap;

public class StringCircle {
	
	public static boolean isCircle(String[] s){
		if(s.length==0) return false;
		boolean[] visited = new boolean [s.length]; 
		return dfs(s,visited,s[0].charAt(s[0].length()-1),s[0].charAt(0));
	}
	
	public static boolean dfs(String []s,boolean[] visited,char c,char des){
		
		for(int i=0;i<s.length;i++){
			if(!visited[i] && s[i].charAt(0)==c){
				visited[i] = true;
				char t = s[i].charAt(s[i].length()-1);
				if(t==des) return true;
				else if(dfs(s,visited,t,des)) return true;
				visited[i] = false;
			}
		}
		return false;
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String [] s = {"abc","cde","efg","ghi","ijn","cep","efe","ggc"};
		System.out.println(isCircle(s));
	}

}
