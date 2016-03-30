package leetcodeTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Solution {
	//54. Spiral Matrix
	public static List<Integer> spiralOrder(int[][] matrix) {
		int xlength=matrix.length;
		int ylength=0;
		List<Integer> l = new ArrayList<Integer>();
		if(xlength != 0 ){ 
			ylength=matrix[0].length;
			if(ylength!=0){
				int t[][]=new int[xlength][ylength];
				int sumNumber = xlength*ylength;
				int i=0;
				int x=0,y=0;
				l.add(matrix[0][0]) ;
				t[0][0] = 1;
				while(i<sumNumber-1){
					while((y+1)<ylength && t[x][y+1]==0) {l.add(matrix[x][++y]);i++;t[x][y]=1;}
					while((x+1)<xlength && t[x+1][y]==0) {l.add(matrix[++x][y]);i++;t[x][y]=1;}
					while((y-1)>=0 && t[x][y-1]==0) {l.add(matrix[x][--y]);i++;t[x][y]=1;}
					while((x-1)>=0 && t[x-1][y]==0) {l.add(matrix[--x][y]);i++;t[x][y]=1;}
				}
			}
		}
        return l;
    }
	//43. Multiply Strings
	public String multiply(String num1, String num2) {
		int n1=num1.length();
		int n2=num2.length();
		if(n1>0 && n2>0){
			char[][] l = new char[n2][n2*n1];
			
			for(int i=0;i<n2;i++){
				int s2 =Integer.parseInt(num2.substring(num2.length()-i-1, num2.length()-i));
				int sub=0;
				for(int j=0;j<n1;j++){
					
					int s1 =Integer.parseInt(num1.substring(num1.length()-j-1, num1.length()-j));
					int s = s2*s1+sub;
					sub = s/10;
					l[i][j]=(char) (s%10);//数字倒序
					if(j==n1-1 && sub!=0){
						l[i][j+1]=(char)sub;
					}
				}
			}
			char [] sum = new char[n2*n1];
			for(int i=0;i<n2-1;i++){
				int sub=0;
				for(int j=i;j<l[i+1].toString().length()+i;j++){
					
				}
				
			}
			
		}
        return null;
    }
	//105. Construct Binary Tree from Preorder and Inorder Traversal
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	public static TreeNode build(TreeNode t,int i,int j,int length,int[] preorder, int[] inorder,Map<Integer,Integer> imap){
		if(length<=0) return null;
		t = new TreeNode(preorder[i]);
		int m = imap.get(t.val);
		t.left = build(t.left,i+1,j,m-j,preorder,inorder,imap);
		t.right = build(t.right,i+(m-j)+1,m+1,length-(m-j)-1,preorder,inorder,imap);
		return t;
	}
	
	public static TreeNode buildTree(int[] preorder, int[] inorder) {
		if(preorder.length==0) return null;
		Map<Integer,Integer> imap = new HashMap<Integer,Integer>();
		
		for(int i=0;i<inorder.length;i++){
			imap.put( inorder[i],i);
		}
		int i=0,j=0;
		TreeNode root = null;
		root = build(root,i,j,inorder.length,preorder,inorder,imap);
		Stack<TreeNode> s = new Stack<TreeNode>();
		s.push(root);
		while(!s.isEmpty()){
			TreeNode t = s.pop();
			if(null!=t.left) s.add(t.left);
			if(t.right!=null) s.add(t.right);
			System.out.print(t.val);
		}
		
        return null;
    }
	//46. Permutations
	public static void dfs(int i,int []nums,List<List<Integer>> result){
		if(i==nums.length){
			ArrayList<Integer> t = new ArrayList<Integer>();
			for(int j=0;j<nums.length;j++){
				t.add(nums[j]);
			}
			result.add(t);
			return ;
		}
		
		for(int j=i;j<nums.length;j++){
			int t = nums[i];
			nums[i] = nums[j];
			nums[j] = t;
			dfs(i+1,nums,result);
			t = nums[i];
			nums[i] = nums[j];
			nums[j] = t;
		}
	}
	
	public static List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if(nums.length == 0){
			ArrayList<Integer> s = new ArrayList<Integer>();
			result.add(s);
			
			return result;
		}
		dfs(0,nums,result);
		return result;
    }
	//46-2.
	public static List<List<Integer>> permute2(int[] nums) {
	    List<List<Integer>> result = new ArrayList<List<Integer>>();
	    List<Integer> element = new ArrayList<Integer>();
	    generate(result, element, nums, 0);
	    return result;
	}

	private static void generate(List<List<Integer>> result, List<Integer> element, int[] nums, int n){
	    if(n==nums.length){
	        result.add(element);
	        return;
	    }

	    int size = element.size();
	    for(int i = 0; i <= size; i++){
	        List<Integer> temp = new ArrayList<Integer>(element);//将以前的element装入 
	        temp.add(i,nums[n]);//将i位置放入nums在n（n动态变化）的值
	        System.out.println(i+"for:" + temp + "*|*" + result +"*|*"   + (n+1)+"*|*"+size);
	        generate(result, temp, nums, n+1);//n+1是为了动态插入
	    }
	    return;
	}
	//67. Add Binary
	public static String addBinary(String a, String b) {
		
		char [] ac = null;
		char [] bc = null;
		
		int p=0;
		char[] ta = a.toCharArray();
		char[] tb = b.toCharArray();
		while(ta[p]!='1'){ta[p]='\0';p++;if(p==a.length()) break;}
		p=0;
		while(tb[p]!='1'){tb[p]='\0';p++;if(p==b.length()) break;}
		String at = String.valueOf(ta).trim();
		String bt = String.valueOf(tb).trim();
		ac = at.toCharArray();
		bc = bt.toCharArray();
		if(ac.length == 0){
			if(bc.length == 0)
				return "0";
			return bt;
		}
		else if(bc.length == 0){
			return at;
		}
		if(at.length()<bt.length()){
			ac = bt.toCharArray();
			bc = at.toCharArray();
		}
		else {
			ac = at.toCharArray();
			bc = bt.toCharArray();
		}
		int l = ac.length;
		char [] result = new char[l+1];
		int flag= 0;
		int c = l - bc.length;
		for(int i=l-1;i>=0;i--){
			if(i-c>=0){
				if(Integer.parseInt(ac[i]+"")+Integer.parseInt(bc[i-c]+"")+flag == 1){
					result[i+1] = '1';
					flag = 0;
				}
				else if(Integer.parseInt(ac[i]+"")+Integer.parseInt(bc[i-c]+"")+flag == 0 ){
					result[i+1] = '0';
					flag = 0;
				}
				else if(Integer.parseInt(ac[i]+"")+Integer.parseInt(bc[i-c]+"")+flag == 2 ){
					result[i+1] = '0';
					flag = 1;
				}
				else if(Integer.parseInt(ac[i]+"")+Integer.parseInt(bc[i-c]+"")+flag == 3 ){
					result[i+1] = '1';
					flag = 1;
				}
			}
			else {
				if(Integer.parseInt(ac[i]+"")+flag == 1){
					result[i+1] = '1';
					flag = 0;
				}
				else if(Integer.parseInt(ac[i]+"")+flag == 0 ){
					result[i+1] = '0';
					flag = 0;
				}
				else if(Integer.parseInt(ac[i]+"")+flag == 2 ){
					result[i+1] = '0';
					flag = 1;
				}
			}
		}
		if(flag == 1) result[0] = '1';
        return String.valueOf(result).trim();
    }
	//67-2. Add Binary
	public static String addBinary2(String a, String b) {
	    if(a == null || b ==null)
	        return a == null? b: a;

	    int carry =0;
	    StringBuilder sb = new StringBuilder();        

	    for(int i = a.length()-1, j = b.length() -1;  i >=0 || j >=0 || carry >0 ; i --, j --){
	        int sum = 0;
	        sum += (i >=0) ? a.charAt(i) - '0' : 0;
	        sum += (j >=0) ? b.charAt(j) - '0' : 0;
	        sum += carry;

	        carry = sum /2;
	        sum %=2;
	        sb.append(sum);
	    }

	    return sb.reverse().toString();
	}
	
	//123 BestTimeToBuy
	public static int maxProfit(int[] prices) {
		int []state = {-99999,0,-99999,0};
		for(int i = 0;i<prices.length;i++){
			state[3] = state[3]>(prices[i]+state[2])?state[3]:(state[2]+prices[i]);
			state[2] = state[2]>(state[1]-prices[i])?state[2]:(state[1]-prices[i]);
			state[1] = state[1]>(state[0]+prices[i])?state[1]:(state[0]+prices[i]);
			state[0] = state[0]>-prices[i]?state[0]:-prices[i];
		}
		return state[3];
    }
	//168. Excel Sheet Column Title
	public static String convertToTitle(int n) {
		char[] p = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		char[] s = new char[10];
		int i=0;
		while((n-1)/26>0){
			s[i] = p[(n-1)%26];
			i++;
			n=(n-1)/26;
		}
		s[i]=p[(n-1)%26];
		StringBuffer sb = new StringBuffer(String.valueOf(s).trim());
        return String.valueOf(sb.reverse());
    }
	//224. Basic Calculator
	public static int calculate(String s) {
		if(s.length() == 0) return 0;
		char[] a = s.replaceAll(" ","").toCharArray();
		Stack<String>num = new Stack<String>();
		Stack<Character>cal = new Stack<Character>();
		for(int i=a.length-1;i>=0;i--){
			String t = "";
			while(Character.isDigit(a[i])){
				t=a[i]+t;
				i--;
				if(i<0) break;
			}
			if(t!=null && t!="") num.add(t);
			if(i>=0){
				if(a[i]=='('){
					while(cal.peek()!=')'){
						num.add(String.valueOf(cal.pop()));
					}
					cal.pop();
				}else cal.add(a[i]);
			}
		}
		while(!cal.isEmpty()){
			num.add(String.valueOf(cal.pop()));
		}
		Stack<String> pre = new Stack<String>();
		Stack<Integer> u = new Stack<Integer>();
		while(!num.isEmpty()){
			pre.push(num.pop());
		}
		while(!pre.isEmpty()){
			if(Character.isDigit(pre.peek().charAt(0))){
				u.push(Integer.parseInt(pre.pop()));
			}
			else {
				if(pre.peek().equals("+")){
					pre.pop();
					int x1 = u.pop();
					int x2 = u.pop();
					u.push(x1+x2);
				}else if(pre.peek().equals("-")){
					pre.pop();
					int x1 = u.pop();
					int x2 = u.pop();
					u.push(x1-x2);
				}
			}
		}
        return u.peek();
    }
	//224-2. Basic Calculator
	public static int calculate2(String s) {
		int [] p = {0};
		return s.length()==0?0:eval("("+s+")",p);
	}
	public static int eval(String s,int[] p){
		int i = p[0];
		int val = 0;
		int oper = 1;
		int num = 0;
		while(i<s.length()){
			char m = s.charAt(i);
			switch(m){
			case '+': val = val + num*oper;oper = 1;num = 0;i++;break; 
			case '-': val = val + num*oper;oper =-1;num = 0;i++;break;
			case '(': p[0] = i+1;val = val + oper*eval(s,p);i = p[0];break;
			case ')': p[0] = i+1;return val + num*oper;
			case ' ': i++;continue;
			default : num = num*10 + m - '0';i++;
			}
		}
		return val;
	}
	//62. Unique Paths
	public static int uniquePaths(int m, int n) {
		int [][] p = new int [m][n];
		for(int i = m-1;i>=0;i--){
			p[i][n-1]=1;
		}
		for(int j=n-1;j>=0;j--){
			p[m-1][j]=1;
		}
		for(int i = m-2;i>=0;i--){
			for(int j = n-2;j>=0;j--){
				p[i][j] = p[i+1][j]+p[i][j+1];
			}
		}
		return p[0][0];
	}
	//62-2. Unique Paths
	public int uniquePaths2(int m, int n) {
        if(m == 1 || n == 1)
            return 1;
        m--;
        n--;
        if(m < n) {              // Swap, so that m is the bigger number
            m = m + n;
            n = m - n;
            m = m - n;
        }
        long res = 1;
        int j = 1;
        for(int i = m+1; i <= m+n; i++, j++){       // Instead of taking factorial, keep on multiply & divide
            res *= i;
            res /= j;
        }

        return (int)res;
    }
	//279. Perfect Squares 
	/* TLE
	 * public static int numSquares(int n) {
		if(n == 0) return 0;

		int length = 99999;
		for(int j=1;j*j<=n;j++){
			int t = n % (j*j);
			int u = n / (j*j);
			int l = numSquares(t)+u;
			length=length>l?l:length;
		}
		return length;
    }
*/
	public static ArrayList<Integer> nums = new ArrayList<Integer>();
	public static int numSquares(int n) {
		if(nums.size() == 0)
			nums.add(0);
		while(nums.size()<=n){;
			int m = nums.size();
			int l = Integer.MAX_VALUE;
			for(int j=1;j*j<=m;j++){
				l = Math.min(nums.get(m-j*j)+1, l);
			}
			nums.add(l);
		}
		return nums.get(n);
	}
	//330. Patching Array using https://leetcode.com/discuss/82822/solution-explanation
	public int minPatches(int[] nums, int n) {
		Long miss = 1L;int i=0;int addnum=0;
		while(miss<=n){
			if(i<nums.length&&nums[i]<=miss){
				miss+=nums[i++];
			}
			else {
				miss+=miss;
				addnum++;
			}
		}
        return addnum;
    }
	//223. Rectangle Area
	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
		int left = Math.max(A,E), right = Math.max(Math.min(C,G), left);
	    int bottom = Math.max(B,F), top = Math.max(Math.min(D,H), bottom);
	    return (C-A)*(D-B) - (right-left)*(top-bottom) + (G-E)*(H-F);
        
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//54.testCase
		/*int [][] matrix = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
		List<Integer> l = new ArrayList<Integer>();
		l = spiralOrder(matrix);
		System.out.println(l);*/
		
		//105.testCase
		/*int [] pre = {1,2,3,4,5,6,7,8,9};
		int [] ino = {2,4,3,1,6,5,8,7,9};
		buildTree(pre,ino);*/
		
		//46.testCase
		/*int [] nums = {1,2,3};
		List<List<Integer>> re = new ArrayList<List<Integer>>();
		re = permute2(nums);
		System.out.println(re);
		System.out.println(re.toArray().length);*/
		
		//addBinary("101","1");
		/*String s = addBinary2("00001","000010");
		System.out.print(s);*/
		//123testCase
		/*System.out.println(maxProfit(new int[]{0,1,4,2,1,7,5,3,7,1,3}));*/
		
		//168.testCase
/*		System.out.println(convertToTitle(52));
		System.out.println(convertToTitle(53));
		System.out.println(convertToTitle(25));
		System.out.println(convertToTitle(26));
		System.out.println(convertToTitle(27));
		System.out.println(convertToTitle(54));
		System.out.println(convertToTitle(55));
		System.out.println(convertToTitle(78));
		System.out.println(convertToTitle(83838833));*/
		//224.testCase
		//System.out.println(calculate("(1+(4+ 5+2)-3)+(6+8)"));
		//System.out.println(calculate2("1+2-(3+4-5)+((9+10)-11)"));
		//62.testCase
		//System.out.println(uniquePaths(69,99));
		//279.testCase
		for(int i=0;i<5000;i++)
		System.out.println(numSquares(i));
	}

}
