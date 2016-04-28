package leetcodeTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import javax.management.Query;
import javax.swing.text.AbstractDocument.BranchElement;

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
	//164. Maximum Gap -- bucket sort
	public  static int maximumGap(int[] nums) {
		if(nums.length <2) return 0;
		int length = nums.length;
		List<Integer> bucket0 = new ArrayList<Integer>();
        List<Integer> bucket1 = new ArrayList<Integer>();
        List<Integer> sorted = new ArrayList<Integer>(length);
        for(int i : nums) {
            sorted.add(i);
        }
        int mask = 1;
        while(mask > 0) {
            for(int i = 0; i < length; i ++) {
	            int n = sorted.get(i);
	            if((n & mask) == 0) {
	                 bucket0.add(n);
	            } else {
	                 bucket1.add(n);
	            }
            }
            sorted.clear();
            sorted.addAll(bucket0);
            sorted.addAll(bucket1);
            bucket0.clear();
            bucket1.clear();
            mask <<= 1;
        }
        int maxDiff = 0;
        for(int i = 1; i < length; i ++) {
            int n = sorted.get(i) - sorted.get(i - 1);
            if(n > maxDiff) maxDiff = n;
        }
        return maxDiff;
    }
	//93. Restore IP Addresses --useless for some conditions like 010000
	public static List<String> restoreIpAddresses(String s) {
		List<String> ips = new ArrayList<String>();
		if(s.length()<4) return ips;
		findIps(ips,s,0,0,"");
        return ips;
    }
	
	public static void findIps(List<String> ips,String s,int n,int l,String str){
		if(n == 3 && l<s.length() && Integer.valueOf(s.substring(l, s.length()))<=255){
			ips.add(str+s.substring(l, s.length()));
			return;
		}
		for(int i=l+1;i<s.length()&&i<=l+3;i++){
			String t = null;
			if(Integer.valueOf(s.substring(l, i))<=255){
				String strt = str+s.substring(l, i)+".";
				if(n+1<=4 && (4-n)*3>=s.length()-i){
					findIps(ips,s,n+1,i,strt);
				}
			}
		}
		return ;
	}
	//93-2
	public static List<String> restoreIpAddresses2(String s) {
		List<String> ips = new ArrayList<String>();
		for(int a=1;a<=3;a++)
			for(int b=1;b<=3;b++)
				for(int c=1;c<=3;c++)
					for(int d=1;d<=3;d++){
						if(a+b+c+d==s.length()){
							int aa = Integer.parseInt(s.substring(0, a));
							int bb = Integer.parseInt(s.substring(a,a+b));
							int cc = Integer.parseInt(s.substring(a+b,a+b+c));
							int dd = Integer.parseInt(s.substring(a+b+c,a+b+c+d));
							String sol = aa+"."+bb+"."+cc+"."+dd;
							if(sol.length()==s.length()+3 && aa<=255 && bb<=255&& cc<=255&& dd<=255){
								ips.add(sol);
							}
						}
					}
		return ips;
	}
	//257. Binary Tree Paths
	public static List<String> binaryTreePaths(TreeNode root) {
		List<String> s = new ArrayList<String>();
		if(root == null) return s;
		else dfs_257(s, root, "\"");
        return s;
    }
	public static void dfs_257(List<String> s,TreeNode t,String a){
		if(t.left == null && t.right == null){
			s.add(a+String.valueOf(t.val)+"\"");
			return ;
		}
		String aa = a+String.valueOf(t.val)+"->";
		if(t.left!=null) dfs_257(s, t.left, aa);
		if(t.right!=null) dfs_257(s, t.right, aa);
		return ;
	}
	//199. Binary Tree Right Side View
	public static List<Integer> rightSideView(TreeNode root) {
		List<Integer> r = new ArrayList<Integer>();
		if(root == null) return r;
		dfs_199(r,0,root);
        return r;
    }
	public static void dfs_199(List<Integer> r,int i,TreeNode t){
		if(r.size()<=i)
			r.add(i, t.val);
		if(t.right!=null)
			dfs_199(r,i+1,t.right);
		if(t.left!=null)
			dfs_199(r,i+1,t.left);
		return ;
	}
	//334. Increasing Triplet Subsequence
	public static boolean increasingTriplet(int[] nums) {
        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;
        for(int n:nums){
        	if(n<x){
        		x = n;
        	}
        	else if(n<y){
        		y = n;
        	}
        	else return true;
        }
        return false;
    }
	//201. Bitwise AND of Numbers Range
	//basic 
	public static int rangeBitwiseAnd(int m, int n) {
		int num =m;
		int x=m,y=n;
		while(x>1 && y>1){
			x>>=1;
			y>>=1;
		}
		if(x<y) return 0;
		for(int i=m+1;i<n+1;i++){
			num= num&i;
		}
        return num;
    }
	public static int rangeBitwiseAnd2(int m, int n) {
		int num=0;
		int x=m,y=n;
		while(x>1 && y>1){
			x>>=1;
			y>>=1;
		}
		if(x<y) return 0;
		x=m;
		y=n;
		int l=0;
		while(x>=1 && y>=1 ){
			int flag = 0;
			for(int i=x;i<=y&&i>=0;i++){
				if((i&1)==0){
					l++;
					x>>=1;
					y>>=1;
					flag = 1;
					break;
				}
			}
			if((x&1)==1 && (y&1)==1 && flag==0){
				num+=Math.pow(2, l);
				l++;
				x>>=1;
				y>>=1;
			}
			
		}
			
		/*for(int i=m+1;i<n+1;i++){
			num= num&i;
		}*/
        return num;
    }
	//short by slow
	public static int rangeBitwiseAnd3(int m, int n) {
		while(n>m)
			n&=(n-1);
		return m&n;
	}
	//86. Partition List
	public static class ListNode {
	     int val;
	     ListNode next;
	     ListNode(int x) { val = x; }
	}
	public static ListNode partition(ListNode head, int x) {
		ListNode s = null;
		ListNode b = null;
		ListNode pointer = head;
		ListNode p1 = null;
		ListNode p2 = null;
		while(pointer!=null){
			if(pointer.val<x){
				if(s==null) {
					s = new ListNode(pointer.val);
					p1 = s;
				}
				else {
					p1.next = new ListNode(pointer.val);
					p1 = p1.next;
				}
			}
			else {
				if(b==null) {
					b = new ListNode(pointer.val);
					p2 = b;
				}
				else {
					p2.next = new ListNode(pointer.val);
					p2 = p2.next;
				}
			}
			pointer = pointer.next;
				
		}
		if(p1!=null){
			
			p1.next = b;
		}
		else {
			if(p2!=null)
				s = b;
		}
        return s;
    }
	//198. House Robber
	public static int rob(int[] nums) {
		int rob =0;
		int didnt = 0;
		for(int i=0;i<nums.length;i++){
			int currob = didnt + nums[i];
			int curdidnt = Math.max(rob, didnt);
			rob = currob;
			didnt = curdidnt;
		}
        return Math.max(rob, didnt);
    }
	//213. House Robber II
	public static int rob2(int[] nums) {
		if(nums.length == 0) return 0;
		if(nums.length == 1) return nums[0];
		
		return Math.max(rob(nums,0,nums.length-2), rob(nums,1,nums.length-1));
	}
	public static int rob(int[] nums,int l,int j ) {
		int rob =0;
		int didnt = 0;
		for(int i=l;i<=j;i++){
			int currob = didnt + nums[i];
			int curdidnt = Math.max(rob, didnt);
			rob = currob;
			didnt = curdidnt;
		}
        return Math.max(rob, didnt);
    }
	//337. House Robber III
	public static int rob(TreeNode root) {
		
        return dfs_337(root,0,0);
    }
	public static int dfs_337(TreeNode t,int rob,int didnt){
		if(t == null) return 0;
		
		/*int currob = didnt + t.val;
		int curdidnt = Math.max(rob,didnt);
		rob = currob;
		didnt = curdidnt;
		int cur = Math.max(rob,didnt);
		int lc = 0;
		int rc = 0;
		if(t.left!=null) lc=dfs_337(t.left, rob, didnt);
		if(t.right!=null) rc=dfs_337(t.right,rob,didnt);
		cur = Math.max(cur, lc+rc);*/
		return 0;
	}
	//217.
	public static boolean containsDuplicate(int[] nums) {
		Set<Integer> hash = new HashSet<Integer>();
		for(int i=0;i<nums.length;i++)
			if(!hash.add(nums[i]))
			  return true;
        return false;
    }
	//125
	public static boolean isPalindrome(String s) {
		s = s.replaceAll("[\\pP\\pZ\\pS\\pC\\pM‘’“”]", "");
		s = s.toLowerCase();
		if(s.isEmpty()) return true;
		System.out.println(s);
		char[] c = s.toCharArray();
		int l = s.length();
		boolean solution = true;
		int i=0;
		if(l%2==0){
			while(l/2-1-i>=0){
				if(c[l/2-1-i]!=c[l/2+i] ){
					solution = false;
					break;
				}
				else {
					i++;
				}
			}
		}else {
			while(l/2-1-i>=0){
				if(c[l/2-1-i]!=c[l/2+1+i] ){
					solution = false;
					break;
				}
				else {
					i++;
				}
			}
		}
        return solution;
    }
	//100
	public static boolean dfs(TreeNode p,TreeNode q){
		boolean solution = true;
		if(p==null&&q==null) return true;
		if(p==null&&q!=null) return false;
		if(p!=null&&q==null) return false;
		if(p.val!=q.val ) return false;
		
		solution = dfs(p.left,q.left);
		solution = solution &&dfs(p.right,q.right);
		return solution;
	}
	public static boolean isSameTree(TreeNode p, TreeNode q) {
		boolean solution=true;
		solution = dfs(p,q);
        return solution;
    }
	//171. Excel Sheet Column Number
		public static int titleToNumber(String s) {
			int sum=0;
			for(int i=0;i<s.length();i++){
				//System.out.println((s.charAt(i)-'@')+" "+Math.pow(26,s.length()-i-1));
				sum+=(s.charAt(i)-'@')*Math.pow(26, s.length()-i-1);
				System.out.println(sum);
			}
	        return sum;
	    }

	//136. Single Number
	public static int singleNumber(int[] nums) {
		int result =0;
		for(int i=0;i<nums.length;i++) result^=nums[i];
		return result;
	}
	//137. Single Number II
	public static int singleNumber2(int[] nums) {
		int ones = 0, twos = 0;
	    for(int i = 0; i < nums.length; i++){
	        ones = (ones ^ nums[i]) & ~twos;
	        twos = (twos ^ nums[i]) & ~ones;
	    }
	    return ones;
    }
	//260. Single Number III
	public static int[] singleNumber3(int[] nums) {
		int f = 0;
		for(int i=0;i<nums.length;i++){
			f^=nums[i];
		}
		f&=-f;
		int [] sol = {0,0};
		for(int i=0;i<nums.length;i++){
			if((nums[i]&f)==0){
				sol[0]^=nums[i];
			}
			else if((nums[i]&f)!=0){
				sol[1]^=nums[i];
			}
		}
        return sol;
    }

		
	//78. Subsets
	public static List<List<Integer>> subsets(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> sol = new ArrayList<List<Integer>>();
		int sum = 1<<nums.length;
		for(int i=0;i<sum;i++){
			List<Integer> set = new ArrayList<Integer>();
			for(int j=0;j<nums.length;j++){
				if((i&(1<<j))!=0){
					set.add(nums[j]);
				}
			}
			sol.add(set);
		}
        return sol;
    }	
	//165. Compare Version Numbers
	public static int compareVersion(String version1, String version2) {
		if(version1.length()==0 && version2.length()==0) return 0;
		else if(version1.length() == 0 && version2.length() != 0) return -1;
		else if(version1.length() != 0 && version2.length() == 0) return 1;
		String[] v1 = version1.split("\\.");
		String[] v2 = version2.split("\\.");
		int length = Math.min(v1.length, v2.length);
		for(int i=0;i<length;i++){
			if(Integer.valueOf(v1[i])>Integer.valueOf(v2[i])) return 1;
			else if(Integer.valueOf(v1[i])<Integer.valueOf(v2[i])) return -1;
		}
		if(v1.length>v2.length){
			for(int i=v2.length;i<v1.length;i++){
				if(Integer.valueOf(v1[i])!=0) return 1;
			}
			return 0;
		}
		else if(v1.length<v2.length){
			for(int i=v1.length;i<v2.length;i++){
				if(Integer.valueOf(v2[i])!=0) return -1;
			}
			return 0;
		}
		else return 0;
    }
	
	
	//77. Combinations
	public static List<List<Integer>> combine(int n, int k) {//1...n Kth
		List<List<Integer>> s = new ArrayList<List<Integer>>();
		List<Integer> l = new ArrayList<Integer>();
		dfs_77(s, n, k, 1, 0, l);
        return s;
    }
	public static void dfs_77(List<List<Integer>> s,int n,int k,int start,int num,List<Integer> l){
		//if(n==k) return ;
		if(l.size()==k){ 
			List<Integer> t = new ArrayList<Integer>(l);
			s.add(t);
			
			return ;
		}
		for(int i=start;i<=n;i++){
			//List<Integer> t = new ArrayList<Integer>();
			l.add(i);
			dfs_77(s,n,k,i+1,num++,l);
			l.remove(l.size()-1);
			
		}
	}
	//77-2 
	public List<List<Integer>> combine2(int n, int k) {
        if (k == n || k == 0) {
            List<Integer> row = new LinkedList<>();
            for (int i = 1; i <= k; ++i) {
                row.add(i);
            }
            return new LinkedList<>(Arrays.asList(row));
        }
        List<List<Integer>> result = this.combine2(n - 1, k - 1);
        //resultforEach(e -> e.add(n));
        result.addAll(this.combine(n - 1, k));
        return result;
    }
	//69. Sqrt(x)
	public static int mySqrt(int x) {
        //return (int) Math.sqrt(x);(;-))
		int min = 1;
		int max = Integer.MAX_VALUE-1;
		
		while(true){
			int mid = (max-min)/2+min;
			if(x/mid<mid){
				max = mid -1;
			}
			else{
				if(x/(mid+1)<(mid+1)){
					return mid;
				}
				else 
					min = mid + 1;
			}
		}
		
    }
	//238. Product of Array Except Self
	public static int[] productExceptSelf(int[] nums) {
        Long sum = 1L;
        int num = 0;
        for(int n:nums){
        	if(n==0){
        		num++;
        		if(num==2){
        			Arrays.fill(nums, 0);
        			return nums;
        		}
        	}
        	else 
        	sum*=n;
        }
        for(int i=0;i<nums.length;i++){
        	if(nums[i]==0 && num==1){ 
        		Arrays.fill(nums, 0);
        		nums[i] = (int)(sum*1);
        		return nums;
        	}
        	else nums[i] = (int) (sum/nums[i]);
        }
        return nums;
    }
	//25. Reverse Nodes in k-Group
	public static ListNode reverseKGroup(ListNode head, int k) {
		if(k<=1) return head;
		ListNode l = head;
		int n = 1;
		if(head == null || head.next == null) return head;
		while(l.next!=null){
			l=l.next;
			n++;
		}
		ListNode p ;
		ListNode last;
		ListNode first = null;
		ListNode c = null;
		last = head;
		while(n/k>0){
			n-=k;
			for(int i=0;i<k;i++){
				if(i==0){
					l = last.next;
					first = last;
					p = l.next;
					l.next = last;
					last = l;
					l = p;
				}
				else if(i==k-1){
					if(c !=null)
						c.next = last;
					else {
						head = last;
					}
					first.next = l;
					c = first;
					if(l!=null){
						p = l.next;
						last = l;
						l = p;
					}
				}
				else {
					
					p = l.next;
					l.next = last;
					last = l;
					l = p;
				}
			}
		}
        return head;
    }
	//25-2
	public ListNode reverseKGroup2(ListNode head, int k) {
	    ListNode curr = head;
	    int count = 0;
	    while (curr != null && count != k) { // find the k+1 node
	        curr = curr.next;
	        count++;
	    }
	    if (count == k) { // if k+1 node is found
	        curr = reverseKGroup(curr, k); // reverse list with k+1 node as head
	        // head - head-pointer to direct part, 
	        // curr - head-pointer to reversed part;
	        while (count-- > 0) { // reverse current k-group: 
	            ListNode tmp = head.next; // tmp - next head in direct part
	            head.next = curr; // preappending "direct" head to the reversed list 
	            curr = head; // move head of reversed part to a new node
	            head = tmp; // move "direct" head to the next node in direct part
	        }
	        head = curr;
	    }
	    return head;
	}
	//222. Count Complete Tree Nodes
	public int height(TreeNode root){
		return root==null? -1:1+height(root.left);
	}
	public int countNodes(TreeNode root) {
		int h = height(root);
		return h < 0 ? 0 : height(root.right) == h-1 ? (1<<h) + height(root.right):(1<<h-1) + height(root.left);
		
    }
	//222-2
	public static int countNodes2(TreeNode root) {
		if(root == null) return 0;
		Stack<TreeNode> t = new Stack<TreeNode>();
		t.push(root);
		int num = 1;
		while(!t.isEmpty()){
			TreeNode tmp = t.pop();
			if(tmp.val!=-100){
                tmp.val=-100;
				if(tmp.left != null){
					t.push(tmp.left);
					num++;
				}
				if(tmp.right!= null){
					t.push(tmp.right);
					num++;
				}
			}
		}
		return num;
	}
	//222-3
	public int countNodes3(TreeNode root) {  
        if(root==null) return 0;  
          
        int l = getLeft(root) + 1;  
        int r = getRight(root) + 1;  
          
        if(l==r) {  
            return (2<<(l-1)) - 1;  
        } else {  
            return countNodes(root.left) + countNodes(root.right) + 1;  
        }  
    }  
      
    private int getLeft(TreeNode root) {  
        int count = 0;  
        while(root.left!=null) {  
            root = root.left;  
            ++count;  
        }  
        return count;  
    }  
      
    private int getRight(TreeNode root) {  
        int count = 0;  
        while(root.right!=null) {  
            root = root.right;  
            ++count;  
        }  
        return count;  
    }  
    //89. Gray Code
    public List<Integer> grayCode(int n) {
    	List<Integer> r = new ArrayList<Integer>();
    	for(int i=0;i<1<<n;i++){
    		r.add(i^i>>1);
    	}
        return r;
    }
    //8. String to Integer (atoi)
    public static int myAtoi(String str) {
    	
            if(str.length() == 0) return 0;
        	boolean z = true;
        	int l = 0;
        	/*while(l<str.length() ){
        		if(str.charAt(l) == ' '){ 
        			l++;
        			continue;
        		}
        		
        		else if(str.charAt(l)=='-'){
        			z = false;
        			l++;
        			break;
        		}
        		else if((str.charAt(l)-'0')>=0 && (str.charAt(l)-'0')<=9){
        			break;
        		}
        		else if(str.charAt(l)=='+'){
        			z = true;
        			l++;
        			break;
        		}
        		else return 0;
        	}*/
        	while (str.charAt(l) == ' ') { l++; }
            if (str.charAt(l) == '-') {
            	z = false;
            }
        	int sum=0;
        	Long t = 0L;
        	while(l<str.length()){
        		if((str.charAt(l)-'0')>=0 && (str.charAt(l)-'0')<=9){
        			if((t*10+(str.charAt(l)-'0'))>Integer.MAX_VALUE && (z == true))
        				return Integer.MAX_VALUE;
        			else if((t*10+(str.charAt(l)-'0')-1)>Integer.MAX_VALUE && (z == false))
        				return Integer.MIN_VALUE;
        			else {
        				t*=10;
        				t+=str.charAt(l)-'0';
        			}
    	    		sum*=10;
    	    		sum+=str.charAt(l)-'0';
    	    		l++;
        		}
        		
        		else break;
        	}
        	if(z == false) sum = - sum;
            return sum;
        }
    //47. Permutations II
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> sol = new ArrayList<List<Integer>>();
        if(nums.length == 0) return sol;
        List<Integer> s = new ArrayList<Integer>();
        boolean [] used = new boolean[nums.length];
        Arrays.sort(nums);
        dfs_47(sol,s,used,nums);
        return sol;
    }
    public static void dfs_47(List<List<Integer>> sol,List<Integer> s,boolean [] used,int []nums){
    	if(s.size() == nums.length){
    		sol.add(new ArrayList<Integer>(s));
    		return ;
    	}
    	for(int i=0;i<nums.length;i++){
    		if(used[i]) continue;
    		if(i>0 && nums[i]==nums[i-1] && !used[i-1]) continue;
    		used[i] = true;
    		s.add(nums[i]);
    		dfs_47(sol, s, used, nums);
    		used[i] = false;
    		s.remove(s.size()-1);
    		
    	}
    }
    //47-2
    public static List<List<Integer>> permuteUnique2(int[] num) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        res.add(new ArrayList<Integer>());
        for (int i = 0; i < num.length; i++) {
            Set<String> cache = new HashSet<String>();
            
            while (res.get(0).size() == i) {
                List<Integer> l = res.remove(0);
                for (int j = 0; j <= l.size(); j++) {
                    List<Integer> newL = new ArrayList<>(l.subList(0,j));
                    newL.add(num[i]);
                    newL.addAll(l.subList(j,l.size()));
                    if (cache.add(newL.toString())) res.add(newL);
                }
            }
        }
        return res;
    }
    //121. Best Time to Buy and Sell Stock
    public static int maxProfit_121(int[] prices) {
    	if(prices.length == 0|| prices == null) return 0;
    	int [] status = {-99999,0};
    	for(int i=0;i<prices.length;i++){
    		status[1] = status[1]>(status[0]+prices[i])?status[1]:(status[0]+prices[i]);
    		status[0] = status[0]>-prices[i]?status[0]:-prices[i];
    	}
        return status[1];
    }
    
    //212. Word Search II
    public static List<String> findWords(char[][] board, String[] words) {
    	List<String> sol = new ArrayList<String>();
    	if(board.length == 0 || words.length == 0 || board[0].length == 0) return sol;
    	boolean [][]visited = new boolean[board.length][board[0].length];
    	Trie root = new Trie();
    	for(String t:words)
    		root.insert(t);
    	Set<String> res = new HashSet<String>();
    	for(int i=0;i<board.length;i++)
    		for(int j=0;j<board[0].length;j++)
    			insert(board,i,j,visited,root,"",res);

    	return new ArrayList<String>(res);	
    }
    public static void insert(char [][]board,int x,int y,boolean [][] visited,Trie root,String str,Set<String> res){
    	if(x<0 || x>=board.length || y<0 || y>=board[0].length || visited[x][y] == true){
			return ;
		}
    	str+=board[x][y];
    	if(!root.startsWith(str)) return ;
    	if(root.search(str))
    		res.add(str);
    	visited[x][y] = true;
    	insert(board,x-1,y,visited,root,str,res);
    	insert(board,x+1,y,visited,root,str,res);
    	insert(board,x,y-1,visited,root,str,res);
    	insert(board,x,y+1,visited,root,str,res);
    	visited[x][y] = false;
    }
    //212-2
    static class TrieNode{
    	TrieNode[] next = new TrieNode[26];
    	String word;
    }
    public static TrieNode buildTrie(String[] words){
    	TrieNode root = new TrieNode();
    	for(String w:words){
    		TrieNode p = root;
    		for(char a:w.toCharArray()){
    			int i = a  - 'a';
    			if(p.next[i] == null) p.next[i] = new TrieNode();
    			p = p.next[i];
    		}
    		p.word = w;
    	}
    	return root;
    }
    public static void dfs_212(char[][] board,int x,int y,TrieNode p,List<String> res){
    	char c = board[x][y];
    	if(c == '#' || p.next[c-'a'] == null) return ;
    	p = p.next[c-'a'];
    	if(p.word != null){
    		res.add(p.word);
    		p.word = null;
    	}
    	board[x][y] = '#';
    	if(x>0) dfs_212(board,x-1,y,p,res);
    	if(x<board.length-1) dfs_212(board,x+1,y,p,res);
    	if(y>0) dfs_212(board,x,y-1,p,res);
    	if(y<board[0].length-1) dfs_212(board,x,y+1,p,res);
    	board[x][y] = c;
    }
    public static List<String> findWords2(char [][] board,String[] words){
    	List<String> res = new ArrayList<String>();
    	TrieNode root = buildTrie(words);
    	for(int i=0;i<board.length;i++){
    		for(int j=0;j<board[0].length;j++){
    			dfs_212(board,i,j,root,res);
    		}
    	}
    	return res;
    }
    //79. Word Search
    public static boolean exist(char[][] board, String word) {
    	if(word.length() == 0 || board.length ==0 || board[0].length == 0) return false;
    	
    	for(int i=0;i<board.length;i++)
    		for(int j=0;j<board[0].length;j++)
    			if(board[i][j] == word.charAt(0))
    				if(dfs_79(board,word,0,i,j))
    					return true;
        return false;
    }
    public static boolean dfs_79(char[][] board,String word,int l,int x,int y){
    	if(l == word.length()-1) return true;
    	char t = board[x][y];
    	board[x][y] = '#';
    	if(x>0 && board[x-1][y]==word.charAt(l+1)) 
    		if(dfs_79(board,word,l+1,x-1,y))
    			return true;
    	if(y>0 &&board[x][y-1]==word.charAt(l+1))
    		if(dfs_79(board,word,l+1,x,y-1))
    			return true;
    	if(x<board.length-1 &&board[x+1][y]==word.charAt(l+1))
    		if(dfs_79(board,word,l+1,x+1,y))
    			return true;
    	if(y<board[0].length-1 &&board[x][y+1]==word.charAt(l+1))
    		if(dfs_79(board,word,l+1,x,y+1))
    			return true;
    	board[x][y] = t;
    	return false;
    	
    }
    //60. Permutation Sequence
    public static String getPermutation(int n, int k) {
    	LinkedList<Integer> l = new LinkedList<Integer>();
    	for(int i=1;i<=n;i++) l.add(i);
    	int sum = 1;
    	for(int i=2;i<=n;i++) sum*=i;
    	k--;
    	StringBuffer sb = new StringBuffer();
    	for(;n>0;n--){
    		sum/=n;
    		sb.append(l.remove(k/sum));
    		k%=sum;
    	}
    	return sb.toString();
    }
    //127. Word Ladder
    public static int ladderLength(String beginWord, String endWord, Set<String> wordList) {
    	Set<String> begin = new HashSet<String>(),end = new HashSet<String>(),visited = new HashSet<String>();
    	int length = beginWord.length();
    	begin.add(beginWord);
    	end.add(endWord);
    	int len = 1;
    	while(!begin.isEmpty()&&!end.isEmpty()){
    		HashSet<String> tmp = new HashSet<String>();
    		if(begin.size() > end.size()){
    			Set<String> t = begin;
    			begin = end;
    			end = t;
    		}
    		for(String cur:begin){
    			char[] word = cur.toCharArray();
    			for(int i=0;i<length;i++){
        			for(char a='a';a<='z';a++){
        				char o = word[i];
        				word[i] = a;
        				String msg = String.valueOf(word);
        				if(end.contains(msg)){
        					return len+1;
        				}
        				if(!visited.contains(msg)&&wordList.contains(msg)){
        					visited.add(msg);
        					tmp.add(msg);
        				}
        				word[i] = o;
        			}
        		}
    		}
    		len++;
    		begin = tmp;
    		
    	}
        return 0;
    }
    //2. Add Two Numbers
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	if(l1 == null) return l2;
    	if(l2 == null) return l1;
    	ListNode t1 = l1;
    	ListNode t2 = l2;
    	ListNode sol = new ListNode(0);
    	ListNode p = sol;
    	ListNode head = sol;
    	int  w = 0;
    	while(t1!=null || t2!=null){
    		if(t1 == null) t1 = new ListNode(0);
    		else if(t2 == null) t2 = new ListNode(0);
    		int sum = t1.val + t2.val + w;
    		w = sum>9?1:0;
    		sol = new ListNode(sum%10);
    		p.next = sol;
    		p = sol;
    		sol =sol.next;
    		t1 = t1.next;
    		t2 = t2.next;
    	}
    	if(w == 1){
    		sol = new ListNode(1);
    		p.next = sol;
    	}
        return head.next;
    }
    //57. Insert Interval
      public class Interval {
          int start;
          int end;
          Interval() { start = 0; end = 0; }
          Interval(int s, int e) { start = s; end = e; }
      }
     
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    	int length = intervals.size();
    	List<Interval> sol = new ArrayList<Interval>();
    	int i=0;
    	if(length == 0){ sol.add(newInterval);return sol;}
    		while(intervals.get(i).end<newInterval.start && i<length){
    			sol.add(intervals.get(i++));
    		}
    		while(intervals.get(i).start<=newInterval.end && i<length){
    			newInterval = new Interval(Math.min(intervals.get(i).start,newInterval.start),
    									   Math.max(intervals.get(i).end, newInterval.end));
    			i++;
    		}
    		sol.add(newInterval);
    		while(i<length) sol.add(intervals.get(i++));
    		return sol;
    }
    //202. Happy Number
    public static boolean isHappy(int n) {
    	Set<Integer> nums = new HashSet<Integer>();
    	int curr = n;
    	while(nums.add(curr)){
    		int sum = 0;
    		while(curr/10!=0){
    			sum+=(curr%10)*(curr%10);
    			curr/=10;
    		}
    		sum+=(curr%10)*(curr%10);
			curr/=10;
    		if(sum == 1) return true;
    		else curr = sum;
    	}
        return false;
    }
    //202-2
    public boolean isHappy2(int n) {
        int x = n;
        int y = n;
        while(x>1){
            x = cal(x) ;
            if(x==1) return true ;
            y = cal(cal(y));
            if(y==1) return true ;

            if(x==y) return false;
        }
        return true ;
    }
    public int cal(int n){
        int x = n;
        int s = 0;
        while(x>0){
            s = s+(x%10)*(x%10);
            x = x/10;
        }
        return s ;
    }
    //26. Remove Duplicates from Sorted Array
    public static int removeDuplicates(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return 1;
        int length = 1;
        int j = 1;
        while(j<nums.length){
        	if(nums[j]!=nums[length-1]){
        		length ++;
        		nums[length-1] = nums[j];
        	}
        	j++;
        }
        for(int i:nums)
        System.out.println(i);
        return length;
    }
    //331. Verify Preorder Serialization of a Binary Tree
    public static boolean isValidSerialization(String preorder) {
        String [] s = preorder.split(",");
        Stack<String> stack = new Stack<String>();
        
        for(int i=0;i<s.length;i++){
        	if(s[i].equals("#")){
        		while(!stack.isEmpty()&&stack.peek().equals("#")){
        			stack.pop();
        			if(stack.isEmpty()||stack.pop().equals("#")) return false;
        		}
        		
        	}
        	stack.add(s[i]);
        }
        if(stack.size()==1 && stack.peek().equals("#")) return true;
        
        else return false;
    }
    //331-2
    public boolean isValidSerialization2(String preorder) {
        String s = preorder.replaceAll("\\d+,#,#", "#");
        return s.equals("#") || !s.equals(preorder) && isValidSerialization(s);
    }
    //331-3 
    public boolean isValidSerialization3(String preorder) {
        int len = preorder.length();
        int count = 0;
        char[] s = preorder.toCharArray();

        for(int i=len-1; i>=0; i--){

            char tmp = s[i];

            if(tmp == ','){
                continue;
            }else if(tmp == '#'){
                count++;
            }else if(tmp != ',' && tmp != '#' && i!=0 && s[i-1]!=','){
                continue;
            }else{
                if(count<2){
                    return false;
                }else{
                    count--;
                }
            }
        }

        if(count ==1){
            return true;
        }else{
            return false;
        }
    }
    //138. Copy List with Random Pointer
    public static class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
     };
    public RandomListNode copyRandomList(RandomListNode head) {
    	if(head == null) return null;
    	RandomListNode p = head;
    	RandomListNode next;
    	while(p!=null){
    		next = p.next;
    		RandomListNode t = new RandomListNode(p.label);
    		p.next = t;
    		t.next = next;
    		p = next;
    	}
    	p = head;
    	while(p!=null){
    		if(p.random!=null)
    		p.next.random = p.random.next;
    		p = p.next.next;
    	}
    	RandomListNode newList = new RandomListNode(0);
    	RandomListNode cp = newList;
    	RandomListNode q = newList;
    	p = head;
    	while(p!=null){
    		next = p.next.next;
    		cp = p.next;
    		q.next = cp;
    		q = cp;
    		p.next = next;
    		p = next;
    	}
    	
    	return newList.next;
    }
    //172. Factorial Trailing Zeroes
    public static int trailingZeroes(int n) {
    	int sum = 0;
    	while(n/5>0){
    		sum += n/5;
    		n/=5;
    	}
        return sum;
    }
    //120. Triangle  top-bottom
    public static  int minimumTotal(List<List<Integer>> triangle) {
    	if(triangle.size()==0) return 0;
    	if(triangle.size()==1) return triangle.get(0).get(0); 
    	int length = triangle.size();
    	int min = Integer.MAX_VALUE;
    	for(int i=1;i<triangle.size();i++){
    		for(int j=0;j<=i;j++){
    			if(j==0){
    				triangle.get(i).set(0, triangle.get(i-1).get(0)+triangle.get(i).get(0));
    			}
    			else if(j==i){
    				triangle.get(i).set(i, triangle.get(i-1).get(i-1)+triangle.get(i).get(i));
    			}
    			else {
    				triangle.get(i).set(j,Math.min(triangle.get(i-1).get(j-1), triangle.get(i-1).get(j))+triangle.get(i).get(j));
    			}
    		}
    	}
    	for(int i=0;i<triangle.get(length-1).size();i++){
    		int t = triangle.get(length-1).get(i);
    		if(t < min)  min = t;
    	}
        return min;
    }
    //120-2   bottom-up
    public static  int minimumTotal2(List<List<Integer>> triangle) {
    	if(triangle.size() == 0 ) return 0;
    	
    	for(int i = triangle.size()-2;i >= 0;i--){
    		List<Integer> next = triangle.get(i+1);
    		for(int j=0;j<=i;j++){
    			triangle.get(i).set(j, Math.min(next.get(j), next.get(j+1)+triangle.get(i).get(j)));
    		}
    	}
    	return triangle.get(0).get(0);
    }
    //120-3 buttom-up and use array instead of List
    public static int minimumTotal3(List<List<Integer>> triangle) {
    	int length = triangle.size();
        if(length==1) return triangle.get(0).get(0);
        int[] ans=new int[length];
        List<Integer> list=triangle.get(length-1);
        for(int i=0;i<ans.length;i++){
            ans[i]=list.get(i);
        }
        int[] ret=action(triangle,ans,length-1);
        return ret[0];
    }
    public static int[] action(List<List<Integer>> triangle,int[] ans,int n){
        if(n==0) return ans;
        List<Integer> list=triangle.get(n-1);
        int[] ans2=new int[list.size()];
        for(int i=0;i<ans2.length;i++){
            ans2[i]=list.get(i);
        }
        System.out.println(ans2.length);
        for(int i=0;i<ans2.length;i++){
            ans2[i]+=Math.min(ans[i],ans[i+1]);
            
        }
        return action(triangle,ans2,n-1);

    }
    //116. Populating Next Right Pointers in Each Node
    //117 can also use this code
    public static class TreeLinkNode {
         int val;
         TreeLinkNode left, right, next;
         TreeLinkNode(int x) { val = x; }
     }
    public static void connect(TreeLinkNode root) {
    	Stack<TreeLinkNode> stack = new Stack<TreeLinkNode>();
        dfs_116(root, stack);
        System.out.println();
    }
    public static void dfs_116(TreeLinkNode root,Stack<TreeLinkNode> s){
    	if(root == null) return ;
    	if(s.isEmpty()) root.next = null;
    	else root.next = s.pop();
    	if(root.right!=null){ 
    		dfs_116(root.right, s);
    		s.add(root.right);
    	}
    	if(root.left!=null){ 
    		dfs_116(root.left, s);
    		s.add(root.left);
    	}
    }
    //116-2
    public void connect2(TreeLinkNode root) {
        if (root == null){
            return;
        }

        if (root.left != null){
            root.left.next = root.right;
            if (root.next != null){
                root.right.next = root.next.left;
            }
        }

        connect2(root.left);
        connect2(root.right);
    }
    //117-2
    public void connect3(TreeLinkNode root){ 
    	while (root != null) { root = handler(root); } 
    }
    private TreeLinkNode handler(TreeLinkNode node) {
        TreeLinkNode res = null;
        TreeLinkNode cur = null;
        while(node != null) {
            if (node.left != null) {
                res = node.left;
                break;
            }
            else if (node.right != null) {
                res = node.right;
                break;
            }
            node = node.next;
        }

        if (node != null) {
            if (node.left != null) {
                cur = node.left;
                if (node.right != null) {
                    cur.next = node.right;
                    cur = node.right;
                }
            }
            else if (node.right != null) {
                cur = node.right;
            }
            node = node.next;
        }

        while (node != null) {
            if (node.left != null) {
                cur.next = node.left;
                cur = node.left;
            }
            if (node.right != null) {
                cur.next = node.right;
                cur = node.right;
            }
            node = node.next;
        }
        return res;
    }
    //149. Max Points on a Line
    class Point {
         int x;
         int y;
         Point() { x = 0; y = 0; }
         Point(int a, int b) { x = a; y = b; }
     }
    public int maxPoints(Point[] points) {
    	if(points == null) return 0;
    	if(points.length <= 2) return points.length;
    	HashMap<Integer,Map<Integer,Integer>> map = new HashMap<Integer,Map<Integer,Integer>>();
    	int result = 0;
    	for(int i=0;i<points.length;i++){
    		map.clear();
    		int samepoint = 0;
    		int max = 0;
    		for(int j=i+1;j<points.length;j++){
    			int x = points[j].x-points[i].x;
    			int y = points[j].y-points[i].y;
    			if(x == 0 && y == 0){
    				samepoint++;
    				continue;
    			}
    			int gcd=GCD(x,y);
    			if(gcd!=0){
    				x/=gcd;
    				y/=gcd;
    			}
    			if(map.containsKey(x)){
    				if(map.get(x).containsKey(y))
    					map.get(x).put(y, map.get(x).get(y)+1);
    				else 
    					map.get(x).put(y, 1);
    			}
    			else {
    				Map<Integer,Integer> newx = new HashMap<Integer,Integer>();
    				newx.put(y, 1);
    				map.put(x,newx);
    			}
    			max = Math.max(max, map.get(x).get(y));
    		}
    		result = Math.max(result, max+samepoint+1);
    	}
        return result;
    }
    public int GCD(int a,int b){
    	return b==0?a:GCD(b,a%b);
    }
    //153. Find Minimum in Rotated Sorted Array
    public static int findMin(int[] nums) {
    	if(nums.length==0) return 0;
        return findmin(nums,0,nums.length-1);
    }
    public static int findmin(int []nums,int s,int e){
    	if(s+1==e) return Math.min(nums[s], nums[e]);
    	int t = (s+e)/2;
    	
    	if(nums[s]<=nums[t]){
    		if(nums[t]<nums[e]) return nums[s];
    		else return findmin(nums,t,e);
    	}
    	else 
    		return findmin(nums,s,t);
    	
    }
    //169. Majority Element
    public static int majority(int [] nums){
    	int n = 0;
    	int maj = Integer.MAX_VALUE;
    	for(int p:nums){
    		if(p!=maj){
    			n--;
    			if(n<0){
    				maj = p;
    				n = 0;
    			}
    		}
    		else n++;
    	}
    	return maj;
    }
    //229. Majority Element II 
    public static List<Integer> majorityElement(int[] nums) {
    	List<Integer> sol = new ArrayList<Integer>();
    	if(nums.length==0) return sol;
    	int n1=0,n2=0,count1=0,count2=0;
    	for(int n:nums){
    		if(n==n1) count1++;
    		else if(n==n2) count2++;
    		else if(count1<=0){
				count1=1;
				n1=n;
			}
			else if(count2<=0){
				count2=1;
				n2=n;
			}
    		else {
    			count1--;
    			count2--;
    			
    		}
    	}
    	count1=0;
    	count2=0;
    	for(int n:nums){
    		if(n==n1) count1++;
    		else if(n==n2) count2++;
    	}
    	if(count1>nums.length/3) sol.add(n1);
    	if(count2>nums.length/3) sol.add(n2);
        return sol;
    }
    //70. Climbing Stairs
    public static int climbStairs(int n) {
    	//return (n==0||n==1)?1:climbStairs(n-1)+climbStairs(n-2);//febonacci -TLE
    	int f1 = 1,f2 = 1;
    	int curr = 1;
    	while(curr<n){
    		curr++;
    		int t = f1+f2;
    		f1 = f2;
    		f2 = t;
    	}
    	return f2;
    }
    //91. Decode Ways
    public static int numDecodings(String s) {
    	if(s.length()==0) return 0;
    	char[] str = s.toCharArray();
    	int l = s.length();
    	int sum[] = new int[l+1];
    	sum[l] = 1;
    	sum[l-1] = str[l-1]=='0'?0:1;
    	for(int i=l-2;i>=0;i--)
    		if(str[i]=='0') continue;
    		else sum[i] = (((str[i]=='2'&&str[i+1]<'7')||str[i]=='1'))?sum[i+1]+sum[i+2]:sum[i+1];
    	
        return sum[0];
    }
    //91-2
    public int numDecodings2(String s) {
        if(s.length() == 0) return 0;
        int pre = 27, digit, answer = 0, first = 1, second = 1;
        for(int i = s.length()-1; i >= 0; i--){
            digit = s.charAt(i) - '0';
            if(digit == 0) answer = 0;
            else answer = first + (digit*10 + pre < 27 ? second : 0);
            second = first; first = answer; pre = digit;
        }
        return answer;
    }
    //11. Container With Most Water
    public int maxArea(int[] height) {
        if(height.length<=1) return 0;
        int max = 0,low=0,high=height.length-1;
        while(low<high){
        	int currlow= height[low],currhigh = height[high];
        	max=Math.max(Math.min(currlow, currhigh)*(high-low),max);
        	while(height[low]<currlow && low<high) low++;
        	while (height[high]<currhigh && low<high) high++;
        }
        return max;
    }
    //42. Trapping Rain Water
    public int trap(int[] height) {
    	if(height.length<3) return 0;
        int low = 0,high=height.length-1,sum=0;
        while(height[low]<=0 && low<high) low++;
        while(height[high]<=0 && high>low) high--;
        while(low<high){
        	if(height[low]<=height[high]){
        		int th = height[low];
        		while(low<high && th>=height[low]){
        			sum+=th-height[low];
        			low++;
        		}
        	}
        	else {
        		int th = height[high];
        		while(low<high && th>=height[high]){
        			sum+=th-height[high];
        			high--;
        		}
        	}
        }
        
        return sum;
    }
    //42-2
    public int trap2(int[] height) {
    	if(height.length<3) return 0;
    	int max = -1,maxindex=0;
    	for(int i=0;i<height.length;i++){
    		if(max<height[i]) {
    		    max=height[i];
    		    maxindex=i;
    		}
    	}
    	int area = 0,root = height[0];
    	for(int i=0;i<maxindex;i++){
    		if(root<height[i]) root = height[i];
    		else area += (root-height[i]);
    	}
    	root=height[height.length-1];
    	for(int i=height.length-1;i>maxindex;i--){
    		if(root<height[i]) root = height[i];
    		else area+=(root-height[i]);
    	}
    	return area;
    }
    //48. Rotate Image
    public static void rotate(int[][] matrix) {
    	if(matrix.length<=1) return ;
        int l = matrix.length/2;
        int length = matrix.length-1 ;
        for(int i=0;i<l;i++){
        	for(int j=i;j<(length-i);j++){
        		int t = matrix[i][j];
        		matrix[i][j] = matrix[length-j][i];
        		matrix[length-j][i] = matrix[length-i][length-j];
        		matrix[length-i][length-j] = matrix[j][length-i];
        		matrix[j][length-i] = t; 
        		
        	}
        	
        }
        for(int i=0;i<matrix.length;i++){
        	for(int j=0;j<matrix[0].length;j++)
        		System.out.print(matrix[i][j]+" ");
        	System.out.println();
        }
    }
    //242. Valid Anagram
    public boolean isAnagram(String s, String t) {
    	if(s.equals(t)) return true;
    	if(s.length()==0 && t.length()==0) return true;
    	if(s.length()==0 || t.length()==0) return false;
    	int slength = s.length(),tlength = t.length();
    	if(slength!=tlength) return false;
    	char[] schar=s.toCharArray(),tchar=t.toCharArray();
    	
    	Arrays.sort(schar);
    	Arrays.sort(tchar);
    	for(int i=0;i<slength;i++){
    		if(schar[i]!=tchar[i]) return false;
    	}
        return true;
    }
    //242-2
    //3ms basic ASCII 
      public boolean isAnagram2(String s, String t) {
          if(s==null || t==null || s.length()!=t.length())
              return false;
          int[] alphabets = new int[256];
          char[] sc = s.toCharArray();
          char[] tc = t.toCharArray();
          for(char c : sc){
              alphabets[c]++;
          }
          for(char c : tc){
              if(alphabets[c]>0)
                  alphabets[c]--;
              else
                  return false;
          }
          return true;
      }
    //JosephusProblem
    public static int[] JosephusProblem(int n,int m){
    	if(n<1||m<1) return null;
    	int [] list = new int [n+1];
    	list[0] = 0;
    	list[1] =1;
    	for(int i=2;i<=n;i++)
    		list[i]= (list[i-1]+m)%i;
    	return list;
    }
    //30. Substring with Concatenation of All Words
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> sol = new ArrayList<Integer>();
        if(s.length()==0 || words.length==0) return sol;
        int length = words[0].length();
        int size = words.length;
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        for(int i=0;i<size;i++)
        	map.put(words[i],  map.containsKey(words[i]) ? map.get(words[i]) + 1 : 1);
        for(int i=0;i<=(s.length()-length*size);i++){
        	Map<String, Integer> copy = new HashMap<String, Integer>(map);
        		for(int j=0;j<words.length;j++){
        			String tstring = s.substring(j*length+i, (j+1)*length+i);
        			if(copy.containsKey(tstring)){
        				int count = copy.get(tstring);
        				if(count==1)
        					copy.remove(tstring);
        				else 
        					copy.put(tstring,count-1);
        				if(copy.isEmpty()) {
        					sol.add(i);
        					break;
        				}
        				
        			}
        			else break;
        		}
        }
        return sol;
    }
    //30-2
    public List<Integer> findSubstring2(String s, String[] words) {
    	List<Integer> res = new ArrayList<Integer>();
    	int n = s.length();
    	int m = words.length;
    	int k;
    	if(n==0||m==0||(k=words[0].length())==0) return res;
    	HashMap<String,Integer> wDict = new HashMap<String,Integer>();
    	for(String word:words){
    		if(wDict.containsKey(word))
    			wDict.put(word, wDict.get(word)+1);
    		else wDict.put(word,1);
    	}
    	int start ,x,wordsLen = m*k;
    	HashMap<String,Integer> curDict = new HashMap<String,Integer>();
    	String test,temp;
    	for(int i=0;i<k;i++){
    		curDict.clear();
    		start = i;
    		if(start+wordsLen>n)
    			return res;
    		for(int j=i;j+k<=n;j+=k){
    			test = s.substring(j,j+k);
    			if(wDict.containsKey(test)){
    				if(!curDict.containsKey(test)){
    					curDict.put(test, 1);
    					start = checkFound(res,start,wordsLen,j,k,curDict,s);
    					continue;
    				}
    			
	    			x = curDict.get(test);
	    			if(x<wDict.get(test)){
	    				curDict.put(test, x+1);
	    				start = checkFound(res, start, wordsLen, j, k, curDict, s);
	    				continue;
	    			}
	    			while(!(temp=s.substring(start, start+k)).equals(test)){
	    				decreaseCount(curDict, temp);
	    				start+=k;
	    			}
	    			start+=k;
	    			if(start+wordsLen>n) break;
	    			continue;
    			}
    			start = j+k;
        		if(start+wordsLen>n) break;
        		curDict.clear();
    		}
    		
    	}
    	return res;
    }
    public int checkFound(List<Integer> res,int start,int wordsLen,int j,int k,HashMap<String,Integer> curDict,String s){
    	if(start + wordsLen == j+k){
    		res.add(start);
    		decreaseCount(curDict,s.substring(start, start+k));
    		return start + k;
    	}
    	return start;
    }
    public void decreaseCount(HashMap<String,Integer> curDict,String key){
    	int x = curDict.get(key);
    	if(x==1)
    		curDict.remove(key);
    	else curDict.put(key, x-1); 
    }
    //73. Set Matrix Zeroes
    public static void setZeroes(int[][] matrix) {
        if(matrix.length==0||matrix[0].length==0) return ;
        
        int col= 1;
        for(int i=0;i<matrix.length;i++){
        	if(matrix[i][0]==0) col=0;
        	for(int j=1;j<matrix[0].length;j++)
        		if(matrix[i][j]==0)
        			matrix[i][0] = matrix[0][j] = 0;
        }
        
        for(int i=matrix.length-1;i>=0;i--){
        	for(int j=matrix[0].length-1;j>=1;j--)
        		if(matrix[i][0]==0 || matrix[0][j]==0)
        			matrix[i][j] = 0;
        	if(col==0) matrix[i][0] = 0;
        }
    }
    //39. Combination Sum
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
    	List<List<Integer>> sol = new ArrayList<List<Integer>>();
    	List<Integer> tmp = new ArrayList<Integer>();
    	if(candidates.length==0) return sol;
    	Arrays.sort(candidates);
    	findTheSum(sol, candidates, target, 0, tmp,0);
        return sol;
    }
    public static void findTheSum(List<List<Integer>> sol,int[]cadidates,int target,int currsum,List<Integer> tmp,int begin){
    	for(int i=begin;i<cadidates.length;i++){
    		if(currsum+cadidates[i]==target){
    			tmp.add(cadidates[i]);
    			sol.add(new ArrayList<Integer>(tmp));
    			tmp.remove(tmp.size()-1);
    			return ;
    		}
    		else if(currsum+cadidates[i]<target){
    			tmp.add(cadidates[i]);
    			findTheSum(sol, cadidates, target, currsum+cadidates[i], tmp,i);
    			tmp.remove(tmp.size()-1);
    		}
    		else  return ;
    	}
    }
    //264. Ugly Number II
    static int []num = new int[3000];
    static boolean  ini = false;
    public static int nthUglyNumber(int n) {
    	
    	if(!ini){
    		ini = true;
    		init();
    	}
    	return num[n-1];
    }
    public static void init(){
    	int n2=0,n3=0,n5=0;
    	num[0] = 1;
    	for(int i=1;i<3000;i++){
    		int min = Math.min(num[n2]*2,Math.min(num[n3]*3, num[n5]*5));
    		if(num[n2]*2==min){
    			num[i] = num[n2]*2;
    			n2++;
    		}
    		if(num[n3]*3==min){
    			num[i] = num[n3]*3;
    			n3++;
    		}
    		if(num[n5]*5==min){
    			num[i] = num[n5]*5;
    			n5++;
    		}
    	}
    }
    //283. Move Zeroes
    public static void moveZeroes(int[] nums) {
        if(nums.length==0) return ;
        int i=0,j=0;
        while(i<nums.length&&j<nums.length){
        	while(i<nums.length && nums[i]!=0 ) i++;
        	j = i+1;
        	while(j<nums.length && nums[j]==0 ) j++;
        	if(i<nums.length && j<nums.length){
        		nums[i] = nums[j];
        		nums[j] = 0;
        	}
        }
    }
    //283-2
    public static void moveZeroes2(int[] nums) {
    	int j = 0;
    	for(int i=0;i<nums.length;i++){
    		if(nums[i]!=0){
    			nums[j++] = nums[i];
    		}
    	}
    	for(;j<nums.length;j++)
    		nums[j] = 0;
    }
    
    //297. Serialize and Deserialize Binary Tree
    // Encodes a tree to a single string.
    //bfs runtime:428ms
    public static String serialize(TreeNode root) {
    	if(root==null) return null;
    	StringBuffer s = new StringBuffer();
    	List<TreeNode> nodes = new LinkedList<TreeNode>();
    	nodes.add(root);
    	int i = 0;
    	while(i<nodes.size()){
    		TreeNode t = nodes.get(i);
    		i++;
    		if(t == null){
    			s.append("#;");
    			continue;
    		}
    		else {
    			s.append(t.val);
    			s.append(';');
	    		nodes.add(t.left);
	    		nodes.add(t.right);
    		}
    	}
        return s.toString();
    }
    
    

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
    	if(data==null || data.equals("#")) return null;
    	String[] s = data.split(";");
    	int i = 1,j=0;
    	List<TreeNode> nodes = new LinkedList<TreeNode>();
    	TreeNode root = new TreeNode(Integer.parseInt(s[0]));
    	nodes.add(root);
    	while(i<s.length && j<nodes.size()){
    		TreeNode t = nodes.get(j);
    		
    		if(!s[i].equals("#")){
    			t.left = new TreeNode(Integer.parseInt(s[i]));
    			nodes.add(t.left);
    		}
    		i++;
    		if(!s[i].equals("#")){
    			t.right = new TreeNode(Integer.parseInt(s[i]));
    			nodes.add(t.right);
    		}
    		i++;
    		j++;
    	}
    	
         return root;
    }
    //297-2
    static char[] chars;
    static int length;
	static int c;

    // Encodes a tree to a single string.
    public static String serialize2(TreeNode root) {
        length = 128;
        chars = new char[length];
        c = 0;
        sdfs(root);
        return new String(chars).substring(0,c);
    }

    static void sdfs(TreeNode root){
        add(root);
        if(root == null) return;
        sdfs(root.left);
        sdfs(root.right);
    }

    static void add(TreeNode root){
        int v;
        if(root == null){
            v = Integer.MIN_VALUE;
        }else{
            v = root.val;
        }
        if(c == length) grow();
        chars[c++] = (char) (v >>> 16);
        chars[c++] = (char) v;
    }

    static void grow(){
        int nl = length * 2;
        char[] n = new char[nl];
        System.arraycopy(chars,0,n,0,length);
        chars = n;
        length = nl;
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize2(String data) {
        chars = data.toCharArray();
        c = 0;
        TreeNode root = next();
        ddfs(root);
        return root;
    }

    static void ddfs(TreeNode root){
        if(root == null) return;
        root.left = next();
        ddfs(root.left);
        root.right = next();
        ddfs(root.right);
    }

    static TreeNode next(){
        if(c == length) return null;
        char a = chars[c++];
        char b = chars[c++];
        int v = (((int) a) << 16) | b;
        if(v == Integer.MIN_VALUE) return null;
        return new TreeNode(v);
    }
    
    //297-3 dfs
    public String serialize3(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        _serialize(root, sb);
        return sb.toString();
    }

    private void _serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#,");
        } else {
            sb.append(root.val);
            sb.append(",");
            _serialize(root.left, sb);
            _serialize(root.right, sb);
        }
    }
    
    public TreeNode deserialize3(String data) {
        TreeNode dummy = new TreeNode(-1);
        _deserialize(dummy, true, data, 0);
        return dummy.left;
    }

    private int _deserialize(TreeNode root, boolean left, String data, int start) {
        if (start < data.length() && data.charAt(start) != '#') {
            int end = start + 1;
            while (data.charAt(end) != ',') {
                ++end;
            }
            TreeNode node = new TreeNode(Integer.parseInt(data.substring(start, end)));
            if (left) {
                root.left = node;
            } else {
                root.right = node;
            }
            int deserialized = _deserialize(node, true, data, end + 1);
            return _deserialize(node, false, data, deserialized + 1);
        }
        return start + 1;
    }
    
    //290. Word Pattern
    public static boolean wordPattern(String pattern, String str) {
    	if(str == null || pattern == null) return false;
    	str.replaceAll(" +"," ");
        String [] s = str.split(" ");
        HashMap<Character,String> map = new HashMap<Character,String>();
        HashMap<String,Character> map2 = new HashMap<String,Character>(); 
        char[] p = pattern.toCharArray();
        if(s.length!=p.length) return false;
        for(int i=0;i<s.length;i++){
        	char t = p[i];
        	if(map.containsKey(t)){
        		String v = map.get(t);
        		if(!v.equals(s[i]))
        			return false;
        	}
        	else if(map2.containsKey(s[i])){
        		char k = map2.get(s[i]);
        		if(k!=t)
        			return false;
        	}
        	else {
        		map.put(p[i], s[i]);
        		map2.put(s[i], p[i]);
        	}
        }
        	
        return true;
    }
    
    //290-2
    public static boolean wordPattern2(String pattern,String str){
    	ArrayList<String> s = new ArrayList<String>();
    	if(pattern==null || str == null) return false;
    	int lastindex = -1,index = str.indexOf(" ");
    	char[] p = pattern.toCharArray();
    	while(index!=-1){
    		s.add(str.substring(lastindex+1,index));
    		lastindex = index;
    		index = str.indexOf(" ", index+1);
    	}
    	s.add(str.substring(lastindex+1));
    	if(s.size()!=pattern.length()) return false;
    	String[] letters = new String[26];
    	HashSet<String> set = new HashSet<String>();
    	for(int i=0;i<pattern.length();i++){
    		if(letters[p[i]-'a']==null){
    			String t = s.get(i);
    			if(set.contains(t)) return false;
    			letters[p[i]-'a'] = t;
    			set.add(t);
    		}
    		else 
    			if(!letters[p[i]-'a'].equals(s.get(i)))
    				return false;
    	}
    	return true;
    }
    
    //12. Integer to Roman
    public static String intToRoman(int num) {
         String[][]s = {
        		 {"","I","II","III","IV","V","VI","VII","VIII","IX"},
                 {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"},
                 {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"},
                 {"","M","MM","MMM"}
         };
         StringBuilder str = new StringBuilder();
         if(num>=1000)
         str.append(s[3][num/1000%10]);
         if(num>=100)
         str.append(s[2][num/100%10]);
         if(num>=10)
         str.append(s[1][num/10%10]);
         
         str.append(s[0][num%10]);
         return str.toString();
         
    }
    private static int index = 0;
    private static char[] result = new char[15];

    public static String intToRoman2(int num) {
        index = 0;
        if (num > 999) {
            if (num > 2999) {
                result[index++] = 'M';
                result[index++] = 'M';
            } else if (num > 1999) 
                result[index++] = 'M';
            result[index++] = 'M';
            num = num % 1000;
        }
        if (num > 99) {
            ten(result, num / 100, 'M', 'D', 'C');
            num = num % 100;
        }
        if (num > 9) {
            ten(result, num / 10, 'C', 'L', 'X');
            num = num % 10;
        }
        ten(result, num, 'X', 'V', 'I');
        return new String(result, 0, index);
    }

    public static char[] ten(char[] result, int val, char f1, char f2, char f3) {
        if (val > 8) {
            result[index++] = f3;
            result[index++] = f1;
        } else if (val > 5) {
            result[index++] = f2;
            while (val-- > 5) result[index++] = f3;
        } else if (val > 4) {
            result[index++] = f2;
        } else if (val == 4) {
            result[index++] = f3;
            result[index++] = f2;
        } else while (val-- > 0) result[index++] = f3;
        return result;
    }
    //33. Search in Rotated Sorted Array 
    public static int search(int[] nums, int target) {
    	if(nums.length == 0) return 0;
		return find(nums,0,nums.length-1,target);
    }
    
    public static int find(int[] nums,int s,int e,int target){
    	if(s>e) return -1;
    	int t = (s+e)/2;
    	if(nums[t]==target) return t;
    	if(nums[s]==target) return s;
    	if(nums[e]==target) return e;
    	if(nums[t]>nums[e]){
    		if(nums[t]<target || target<nums[e]  ){
    			return find(nums,t+1,e,target);
    		}
    		else 
    			return find(nums,s,t-1,target);
    	}
    	else  {
    		if(target<nums[e] && target>nums[t])
    			return find(nums,t+1,e,target);
    		else 
    			return find(nums,s,t-1,target);
    	}
    	
    }
    //81. Search in Rotated Sorted Array II
    public boolean search_II(int[] nums, int target) {
    	return Recur(0, nums.length - 1, target, nums);
    }
    
	public boolean Recur(int left, int right, int target, int[] nums) {
		int mid = (left + right) / 2;

		if (right - left >= 2) {
			mid = (left + right) / 2;
			if (nums[mid] == target)
				return true;
			else {

				if (Recur(left, mid - 1, target, nums))
					return true;
				else if (Recur(mid + 1, right, target, nums))
					return true;

				if (Recur(mid + 1, right, target, nums))
					return true;
				else if (Recur(left, mid - 1, target, nums))
					return true;
			}

		} else {
			if (right - left == 0)
				if (nums[right] == target)
					return true;
			if (right - left == 1)
				if (nums[right] == target || nums[left] == target)
					return true;

		}

		return false;

	}
    //81-2 
	//1ms
    public boolean search_II2(int[] nums, int target) {
    	return Recur2(0, nums.length - 1, target, nums);
    }
    
    public boolean Recur2(int left, int right, int target, int[] nums)
    {
        int mid = (left + right) / 2;
        boolean right_sorted = false;
        boolean left_sorted = false;
        if(right - left >= 2)
        {
            mid = (left + right) / 2;
            if(nums[mid] == target)
                return true;
            else
            {
                //left sorted
                if(mid - left - 1 > 2 && nums[left] < nums[mid - 1])
                    left_sorted = true;
                //right sorted
                if(right - mid - 1 > 2  && nums[right] > nums[mid + 1])
                    right_sorted = true;
                if(target < nums[mid] && right_sorted)
                    if(Recur(left, mid - 1, target, nums)) 
                        return true;      
                if(target < nums[mid] && !right_sorted)
                    if(Recur(left, mid - 1, target, nums)) 
                        return true;
                    else if(Recur(mid + 1, right, target, nums)) 
                        return true;

                if(target > nums[mid] && left_sorted)
                    if(Recur(mid + 1, right, target, nums)) 
                        return true;      
                if(target > nums[mid] && !left_sorted)
                    if(Recur(mid + 1, right, target, nums)) 
                        return true;
                    else if(Recur(left, mid - 1, target, nums)) 
                        return true;
            }

        }
        else
        {
            if(right - left == 0)
                if(nums[right] == target)
                    return true;
            if(right - left == 1)
                if(nums[right] == target || nums[left] == target)
                    return true;

        }

        return false;

    }
    
    //74. Search a 2D Matrix
    public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length==0 || matrix[0].length ==0) return false;
        int x = matrix.length;
        int y = matrix[0].length;
        int xt = x/2;
        int yt = 0;
        int start = 0;
        int end = x-1;
        while(start<end){
        	xt = (start+end)/2;
        	if(matrix[xt][yt]>target) {
        		end = xt-1;
        	}
        	else if(matrix[xt][0]<target ){
        		if(matrix[xt][y-1]>=target)
        			break;
        		start = xt+1;
        	}
        	else return true;
        }
        if(start == end) xt = start;
        if(matrix[xt][yt]==target) return true;
        start = 0;
        end = y-1;
        while(start<end){
        	yt = (start+end)/2;
        	if(matrix[xt][yt]>target){
        		end = yt-1;
        	}
        	else if(matrix[xt][yt]<target){
        		start = yt+1;
        	}
        	else 
        		return true;
        }
        if(start == end && matrix[xt][start] == target) return true; 
        return false;
    }
    
    //50. Pow(x, n)
    public static double myPow(double x, int n) {
        return pow(n<0?1/x:x,n);
    }
    public static double pow(double x,int n){
    	if(n == 0) return 1;
    	if(n == 1) return x;
    	double t = pow(x,n/2);
    	return t*t*(n%2==0?1:x);
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
		/*for(int i=0;i<5000;i++)
		System.out.println(numSquares(i));*/
		//164.testCase
		/*int []num = {45,87,41,12,24,35,57,79,95,54,19,50,10,67};
		
        System.out.println(maximumGap(num));*/
		/*System.out.println(restoreIpAddresses2("25525511135"));*/
		//257.testCase
/*		TreeNode t = new TreeNode(5);
		t.left = new TreeNode(4);
		t.right = new TreeNode(3);
		t.left.left = new TreeNode(2);
		t.right.right = new TreeNode(1);
		System.out.println(binaryTreePaths(t));*/
		//199.testCase
		/*TreeNode t = new TreeNode(5);
		t.left = new TreeNode(4);
		t.right = new TreeNode(3);
		t.left.left = new TreeNode(2);
		t.right.right = new TreeNode(1);
		t.right.right.left = new TreeNode(6);
		t.right.right.right = new TreeNode(7);
		t.left.left.left = new TreeNode(8);
		t.left.left.left.left = new TreeNode(9);
		System.out.println(rightSideView(t));*/
		/*int [] c = {3,9,8,7,4,1,5,2};
		System.out.println(increasingTriplet(c));*/
		
		//201.testCase
		/*int m=236236,n=646238;
		while(m>1 && n>1){
			System.out.println(m +" "+n);
			m>>=1;
			n>>=1;
			
		}
		System.out.println("->"+m +"  "+n);
		System.out.println();
		m = 2; n =2;
		while(m>1 && n>1){
			System.out.println(m +" "+n);
			m<<=1;
			n<<=1;
		}
		System.out.println("-------------------");
		//111316236 132346767
		System.out.println(rangeBitwiseAnd(111316236,132346767));
	   System.out.println(rangeBitwiseAnd2(111316236,132346767));
	    System.out.println(rangeBitwiseAnd3(111316236,132346767));*/
		//86.testCase
		/*ListNode root = new ListNode(11);*/
		/*ListNode root = new ListNode(0);
		root.next = new ListNode(4);
		root.next.next = new ListNode(3);
		root.next.next.next= new ListNode(2);
		root.next.next.next.next = new ListNode(5);
		root.next.next.next.next.next = new ListNode(2)
		ListNode root2 = partition(root,1);
		while(root2!=null){
			System.out.print(root2.val+" ");
			root2 = root2.next;
		};*/
		/*int p[] = {1,2,3};
		System.out.println(rob2(p));*/
		/*TreeNode t = new TreeNode(3);
		t.left = new TreeNode(2);
		t.right = new TreeNode(3);
		t.left.left = new TreeNode(2);
		t.right.right = new TreeNode(1);
		t.left.right = new TreeNode(3);
		t.right.right.left = new TreeNode(6);
		t.right.right.right = new TreeNode(7);
		t.left.left.left = new TreeNode(8);
		t.left.left.left.left = new TreeNode(9);
		System.out.println(rob(t));*/
		/*int p[] = {2,5,7,5,2,2,5};
		System.out.println(singleNumber2(p));*/
		/*int []diff = {1,2,3,4,5,6,7,8,1,2,3,5,6,7};
		diff =singleNumber3(diff);
		System.out.println(diff[0]+" "+diff[1]);*/
		//System.out.println(compareVersion("1.0.0.0.0.1","1.0.0"));
		//System.out.println(combine(5,3));

		//System.out.print(mySqrt(897975));
		/*int []s = {8,9,8,5,4,0,2,4};
		productExceptSelf(s);
		for(int i:s)
		System.out.println(i);*/
/*		RandomListNode head = new RandomListNode(1);
		RandomListNode itnode = head;
		head.label = 2;
		System.out.println(itnode.label);*/
		/*for(int i=0;i<10000;i++)
			System.out.println(trailingZeroes(Integer.MAX_VALUE));*/
		
		/*List<List<Integer>> s = new ArrayList<List<Integer>>();
		
		List<Integer> t1 = new ArrayList<Integer>();
		t1.add(1);
		List<Integer> t2 = new ArrayList<Integer>();
		t2.add(2);
		t2.add(3);
		List<Integer> t3 = new ArrayList<Integer>();
		t3.add(4);
		t3.add(5);
		t3.add(6);
		List<Integer> t4 = new ArrayList<Integer>();
		t4.add(7);
		t4.add(8);
		t4.add(9);
		t4.add(10);
		s.add(t1);
		s.add(t2);
		s.add(t3);
		s.add(t4);
		
		System.out.println(minimumTotal3(s));*/
		/*int [] m = {2,2,1,2};
		System.out.println(findMin(m));*/
		/*int [] m = {2,2,2,4,5,4,4,4,9};
		System.out.println(majorityElement(m));*/
		/*System.out.println(numDecodings("2102510255167123643151341035"));*/
		/*int p[] = JosephusProblem(8,4);*/
		/*int p[][] = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
		int q[][] = {{1,2},{3,4}};
		rotate(q);*/
		/*String s = "wordgoodgoodgoodbestword";
		String[] words = {"word","good","best","good"};
		System.out.println(findSubstring(s,words));*/
		/*int p[][] = {{0,0,0,5},{6,7,8,10},{0,12,13,15},{16,17,18,20},{0,0,24,25}};
		int q[][] = {{1,2},{3,4}};
		setZeroes(p);
		for(int i=0;i<p.length;i++){
			for(int j=0;j<p[0].length;j++)
				System.out.print(p[i][j]+" ");
			System.out.println();
		}*/
		/*int []p = {1,2,3,4,5,6,7};
		System.out.println(combinationSum(p,45));*/
		//System.out.println(nthUglyNumber(10));
		/*int [] p = {0, 1, 0, 3, 12, 1, 0, 3, 12, 1, 0, 3, 12, 1, 0, 3, 12, 1, 0, 3, 12, 1, 0, 3, 12, 1, 0, 3, 12, 1, 0, 3, 12, 1, 0, 3, 12, 1, 0, 3, 12, 1, 0, 3, 12, 1, 0, 3, 12, 1, 0, 3, 12, 1, 0, 3, 12, 1, 0, 3, 12, 1, 0, 3, 12, 1, 0, 3, 12, 1, 0, 3, 12, 1, 0, 3, 12, 1, 0, 3, 12, 1, 0, 3, 12, 1, 0, 3, 12};
		moveZeroes2(p);
		for(int i=0;i<p.length;i++)
			System.out.print(p[i]+" ");*/
		/*TreeNode t = new TreeNode(3);
		t.left = new TreeNode(2);
		t.right = new TreeNode(3);
		t.left.left = new TreeNode(2);
		t.right.right = new TreeNode(1);
		t.left.right = new TreeNode(3);
		t.right.right.left = new TreeNode(6);
		t.right.right.right = new TreeNode(7);
		t.left.left.left = new TreeNode(8);
		t.left.left.left.left = new TreeNode(9);
		TreeNode n = null;
		String s = serialize2(t);
		System.out.println(s);
		TreeNode root = deserialize2(s);*/
		/*char[]t = new char[10];
		String str = "abba";
		String str2 = "bog dog dog bog";
		System.out.println(wordPattern2(str, str2));*/
		/*for(int i=0;i<3999;i++)
		System.out.println(intToRoman(i));*/
		/*int [] n ={4,5,6,7,8,9,10,11,12,1,2,3};
		for(int i=0;i<10;i++)
		System.out.println(search(n, i));*/
		/*int [][] m = {
				{1,3,4,5,6},
				{8,9,12,13,15},
				{21,22,25,28,31}
		};
		for(int i=0;i<35;i++)
		System.out.print(searchMatrix(m,i)+" ");*/
		Long  time = System.nanoTime();
		System.out.println(Math.pow(4.52, 212));
		System.out.println((System.nanoTime()-time)/1000000+"ms");
		time = System.nanoTime();
		
		System.out.println(myPow(4.52, 212));
		System.out.println();
		System.out.println((System.nanoTime()-time)/1000000+"ms");
	}

}
