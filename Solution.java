package leetcodeTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import javax.management.Query;
import javax.swing.text.AbstractDocument.BranchElement;
import javax.swing.text.StyledEditorKit.UnderlineAction;
import javax.swing.text.html.HTMLDocument.Iterator;

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
		dfs_337(root);
        return root.val;
    }
	public static int dfs_337(TreeNode t){
		if(t == null) return 0;
		int lc = 0;
		int rc = 0;
		int pre = 0;
		if(t.left!=null) {
			lc=dfs_337(t.left);
			pre+=t.left.val;
		}
		if(t.right!=null){
			rc=dfs_337(t.right);
			pre+=t.right.val;
		}
		t.val = Math.max(lc + rc + t.val,pre); 
		return pre;
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
      public static class Interval {
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
    //50-2
    static double myPow2(double x, int n) { 
        if(n==0) return 1;
        if(n<0) {
            n = -n;
            x = 1/x;
        }
        double ans = 1;
        while(n>0){
            if((n&1)==1) ans *= x;
            x *= x;
            n >>= 1;
        }
        return ans;
    }
    
    //50-3
    public static double myPow3(double x, int n) {
        int m = n < 0 ? -n - 1 : n; 
        double p = 1.0;
        for (double q = x; m > 0; m = m >>> 1) {
            if ((m & 1) != 0) {
                p *= q;
            }
            q *= q;
        }
        return n < 0 ? 1.0 / p / x : p;
    }
    //51. N-Queens
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> sol = new ArrayList<List<String>>();
        if(n == 0 ) return sol;
        int [][] map = new int[n][n];
        for(int i=0;i<n;i++)
        Arrays.fill(map[i], 0);
        Nqueens(sol, map, n, 0);
        return sol;
    }
    
    public static void Nqueens(List<List<String>> s,int[][]map,int n,int x){
    	if(x==n){
    		List<String> l = new ArrayList<String>();
    		
    		for(int i=0;i<n;i++){
    			StringBuffer sb = new StringBuffer();
    			for(int j=0;j<n;j++){
    				if(map[i][j]==1)
    					sb.append("Q");
    				else sb.append(".");
    			}
    			l.add(sb.toString());
    		}
    		s.add(l);
    		return ;
    	}
    	for(int i=0;i<n;i++){
    		boolean able = true;
    		
    		for(int j = 1;j<n&&able&&(x-j)>=0;j++){
    			if(i>=j){
    				if(map[x-j][i-j]==1){
        				able = false;
        				break;
        			}
    			}
    			
    			if(i+j<n){
    				if(map[x-j][i+j]==1){
        				able = false;
        				break;
        			}
    			}
    			if(map[x-j][i]==1){
    				able = false;
    				break;
    			}
    		}
    		if(able){
    			map[x][i] = 1;
    			Nqueens(s, map, n, x+1);
    			map[x][i] = 0;
    		}
    	}
    }
    //52. N-Queens II
    public static int s = 0;
    public static int totalNQueens(int n) {
        if(n == 0 ) return 0;
        int [][] map = new int[n][n];
        for(int i=0;i<n;i++)
        Arrays.fill(map[i], 0);
        Nqueens_II(map, n, 0);
        return s;
    }
    public static void Nqueens_II(int[][]map,int n,int x){
    	if(x==n){
    		s++;
    		return ;
    	}
    	for(int i=0;i<n;i++){
    		boolean able = true;
    		
    		for(int j = 1;j<n&&able&&(x-j)>=0;j++){
    			if(i>=j){
    				if(map[x-j][i-j]==1){
        				able = false;
        				break;
        			}
    			}
    			
    			if(i+j<n){
    				if(map[x-j][i+j]==1){
        				able = false;
        				break;
        			}
    			}
    			if(map[x-j][i]==1){
    				able = false;
    				break;
    			}
    		}
    		if(able){
    			map[x][i] = 1;
    			Nqueens_II(map, n, x+1);
    			map[x][i] = 0;
    		}
    	}
    }
    //52-2
    int count = 0;
    public int totalNQueens2(int n) {
        boolean[] cols = new boolean[n];     // columns   |
        boolean[] d1 = new boolean[2 * n];   // diagonals \
        boolean[] d2 = new boolean[2 * n];   // diagonals /
        backtracking(0, cols, d1, d2, n);
        return count;
    }

    public void backtracking(int row, boolean[] cols, boolean[] d1, boolean []d2, int n) {
        if(row == n) count++;

        for(int col = 0; col < n; col++) {
            int id1 = col - row + n;
            int id2 = col + row;
            if(cols[col] || d1[id1] || d2[id2]) continue;

            cols[col] = true; d1[id1] = true; d2[id2] = true;
            backtracking(row + 1, cols, d1, d2, n);
            cols[col] = false; d1[id1] = false; d2[id2] = false;
        }
    }
    //43. Multiply Strings
    public static String multiply_43(String num1, String num2) {
    	int [] intNum1 = changetoArray(num1);
    	int [] intNum2 = changetoArray(num2);
     	int length1=intNum1.length;   
        int length2=intNum2.length;   
        int[] result=new int[length1+length2];   
        for(int i=0;i<length1;i++)   
        for(int j=0;j<length2;j++){   
            int temp = result[i + j] + intNum1[i] * intNum2[j];   
            result[i + j] = temp % 10;   
            result[i + j + 1] += temp / 10;   
            if (result[i + j + 1] > 10) {   
                result[i + j + 1] %= 10;   
                result[i + j + 2]++;   
            }   
        }      
        StringBuffer sb=new StringBuffer();   
        boolean flag = true;
        for(int i=result.length-1;i>=0;i--)  { 
        	if(flag && result[i]==0) continue;
        	else flag = false;
        	sb.append(result[i]);    
        }
        if(sb.length() == 0 ) return "0";
        return sb.toString();   
    }
    public static int[] changetoArray(String numStr){   
        int length=numStr.length();   
        int[] intNum=new int[length];   
        for(int i=0;i<length;i++)   
        intNum[length-i-1]=Integer.parseInt(String.valueOf((numStr.charAt(i))));   
        return intNum;   
    }   
    public static String multiply2(String num1, String num2) {
        int m=num1.length(), n=num2.length(), zero=0;
        int[] a = new int[m], c = new int[m+n];
        for(int i=0,k=m; i<m; i++) a[--k]=num1.charAt(i)-'0';  
        for(int i=n-1; i>=0; i--)
            add(c,a,num2.charAt(i)-'0',zero++);    
        carry(c);            
        int i=m+n;
        while(i>0 && c[--i]==0); 
        i++;
        StringBuilder ret = new StringBuilder(i);
        while(i>0) ret.append((char)(c[--i]+'0'));
        return ret.toString();
    }
    static void carry(int[] a){
        int i;
        for(int k=0,d=0; k<a.length; k++){
            i=a[k]+d;
            a[k]=i%10;
            d=i/10;
        }
    }
    static void add(int[] c, int[] a, int b, int zero){
        for(int i=zero,j=0; j<a.length; j++,i++)
            c[i]+=a[j]*b;
    }
    //66. Plus One
    public static int[] plusOne(int[] digits) {
    	boolean carry = (digits[digits.length-1] = digits[digits.length-1]+1)==10?true:false;
    	if(carry)
    		digits[digits.length-1] = 0;
    	int i = digits.length-2;
    	while(carry && i>=0){
    		if(digits[i]+1!=10){
    			digits[i]+=1;
    			carry = false;
    		}
    		else {
    			digits[i] = 0;
    		}
    		i--;
    	}
    	if(carry){
    		int [] n = new int[digits.length+1];
    		n[0] = 1;
    		for(int j=0;j<digits.length-1;j++)
    			n[j+1] = digits[j];
    		digits = n;
    	}
        return digits;
    }
    //66-2
    public int[] plusOne2(int[] digits) {

        int n = digits.length;
        for(int i=n-1; i>=0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            digits[i] = 0;
        }

        int[] newNumber = new int [n+1];
        newNumber[0] = 1;

        return newNumber;
    }
    //114. Flatten Binary Tree to Linked List
    public static void flatten(TreeNode root) {
        dfs_114(root);
        System.out.println("--");
    }
    public static void dfs_114(TreeNode root){
    	if(root == null) return ;
    	if(root.left!=null) dfs_114(root.left);
    	if(root.right!=null) dfs_114(root.right);
    	TreeNode t = root.right;
    	TreeNode l = root.left;
    	if(l!=null){
    		while(l.right!=null)
    			l=l.right;
    		l.right = t;
    	}
    	if(root.left !=null)
    		root.right = root.left;
    	
    	root.left = null;
    }
    //63. Unique Paths II 
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
    	int m = obstacleGrid.length;
    	if(m==0) return 0;
    	int n = obstacleGrid[0].length;
    	int [][] p = new int [m][n];
		for(int i = m-1;i>=0;i--){
			p[i][n-1]=1;
		}
		for(int j=n-1;j>=0;j--){
			p[m-1][j]=1;
		}
		for(int i = m-2;i>=0;i--){
			for(int j = n-2;j>=0;j--){
				if(obstacleGrid[i][j]!=1 && obstacleGrid[i+1][j]!=1&&obstacleGrid[i][j+1]!=1)
				p[i][j] = p[i+1][j]+p[i][j+1];
			}
		}
		return p[0][0];
        
    }
    //53. Maximum Subarray
    //Kadane's Algorithm
    public static int maxSubArray(int[] nums) {
    	int curleft = 0,curight = 0;
    	int curmax = 0,max = Integer.MIN_VALUE,left = 0,right = 0;
    	int neg = 0;
    	for(int i=0;i<nums.length;i++){
    		curmax+=nums[i];
    		if(nums[i]<0) neg++;
    		if(curmax>0){
    			curight = i;
    			if(max<curmax){
    				max = curmax;
    				left = curleft;
    				right = curight;
    			}
    		}
    		else {
    			curmax = 0;
    			curleft = curight = i+1;
    		}
    	}
    	if(neg==nums.length)
    		for(int i=0;i<nums.length;i++){
    			if(nums[i]>max)
    				max = nums[i];
    		}
        return max;
    }
    //53-2
    public int maxSubArray2(int[] nums) {
        int sum = nums[0], highest = nums[0];
        for(int i = 1; i < nums.length; i++){
            sum = sum < 0 ? nums[i] : sum + nums[i];
            highest = Math.max(highest, sum);
        }
        return highest;
    }
    
    //18. 4Sum
    public List<List<Integer>> fourSum(int[] nums, int target) {
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        int len = nums.length;
        if (nums == null || len < 4)
            return res;

        Arrays.sort(nums);

        int max = nums[len - 1];
        if (4 * nums[0] > target || 4 * max < target)
            return res;

        int i, z;
        for (i = 0; i < len; i++) {
            z = nums[i];
            if (i > 0 && z == nums[i - 1])// avoid duplicate
                continue;
            if (z + 3 * max < target) // z is too small
                continue;
            if (4 * z > target) // z is too large
                break;
            if (4 * z == target) { // z is the boundary
                if (i + 3 < len && nums[i + 3] == z)
                    res.add(Arrays.asList(z, z, z, z));
                break;
            }

            threeSumForFourSum(nums, target - z, i + 1, len - 1, res, z);
        }

        return res;
    }

    /*
     * Find all possible distinguished three numbers adding up to the target
     * in sorted array nums[] between indices low and high. If there are,
     * add all of them into the ArrayList fourSumList, using
     * fourSumList.add(Arrays.asList(z1, the three numbers))
     */
    public void threeSumForFourSum(int[] nums, int target, int low, int high, ArrayList<List<Integer>> fourSumList,
            int z1) {
        if (low + 1 >= high)
            return;

        int max = nums[high];
        if (3 * nums[low] > target || 3 * max < target)
            return;

        int i, z;
        for (i = low; i < high - 1; i++) {
            z = nums[i];
            if (i > low && z == nums[i - 1]) // avoid duplicate
                continue;
            if (z + 2 * max < target) // z is too small
                continue;

            if (3 * z > target) // z is too large
                break;

            if (3 * z == target) { // z is the boundary
                if (i + 1 < high && nums[i + 2] == z)
                    fourSumList.add(Arrays.asList(z1, z, z, z));
                break;
            }

            twoSumForFourSum(nums, target - z, i + 1, high, fourSumList, z1, z);
        }

    }

    /*
     * Find all possible distinguished two numbers adding up to the target
     * in sorted array nums[] between indices low and high. If there are,
     * add all of them into the ArrayList fourSumList, using
     * fourSumList.add(Arrays.asList(z1, z2, the two numbers))
     */
    public void twoSumForFourSum(int[] nums, int target, int low, int high, ArrayList<List<Integer>> fourSumList,
            int z1, int z2) {

        if (low >= high)
            return;

        if (2 * nums[low] > target || 2 * nums[high] < target)
            return;

        int i = low, j = high, sum, x;
        while (i < j) {
            sum = nums[i] + nums[j];
            if (sum == target) {
                fourSumList.add(Arrays.asList(z1, z2, nums[i], nums[j]));

                x = nums[i];
                while (++i < j && x == nums[i]) // avoid duplicate
                    ;
                x = nums[j];
                while (i < --j && x == nums[j]) // avoid duplicate
                    ;
            }
            if (sum < target)
                i++;
            if (sum > target)
                j--;
        }
        return;
    }
    //15. 3Sum
    public static List<List<Integer>> threeSum(int[] nums) {
    	List<List<Integer>> s = new ArrayList<List<Integer>>();
        
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
        	if(nums[i]>0) break;
			if (i > 0 && nums[i] == nums[i - 1])
				continue;
			int low = i + 1, high = nums.length - 1, sum =  - nums[i];
			while (low < high) {
				if (nums[low] + nums[high] == sum) {
					s.add(Arrays.asList(nums[i], nums[low], nums[high]));
					low++;
					high--;
					while (low < high && nums[low] == nums[low - 1])
						low++;
					while (low < high && nums[high] == nums[high + 1])
						high--;
					
				} else if (nums[low] + nums[high] < sum)
					low++;
				else
					high--;
			}
        	
        }
        return s;
    }
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = Integer.MAX_VALUE;
        int sum = 0;
        for(int i=0;i<nums.length-2;i++){
        	if(i> 0&& nums[i] == nums[i-1]) continue;
        	int low = i+1,high = nums.length-1;
        	while(low<high){
        		if(Math.abs(nums[i]+nums[low]+nums[high]-target)<closest){
        			sum=nums[i]+nums[low]+nums[high];
        			System.out.println(nums[i]+" "+nums[low]+" "+nums[high]);
        			closest = Math.abs(nums[i]+nums[low]+nums[high]-target);

        		}
        		if(nums[i]+nums[low]+nums[high]>target){
        			 high--; 
        		}
        		else if(nums[i]+nums[low]+nums[high]<target){
        			 low++;
        		}
        		else return sum;
        	}
        }
        return sum;
    }
    
    //154. Find Minimum in Rotated Sorted Array II
    public static int findMin2(int[] nums) {
    	 int l = 0, r = nums.length-1;
         while (l < r) {
             int mid = (l + r) / 2;
             if (nums[mid] < nums[r]) {
                 r = mid;
             } else if (nums[mid] > nums[r]){
                 l = mid + 1;
             } else {  
                 r--; 
             }
         }
         return nums[l];
    }
    
    //204. Count Primes
    public static int countPrimes(int n) {
        boolean []isnotprime = new boolean[n+1];
        double t = Math.sqrt(n);
        for(int i=2;i<=t;i++){
        	if(!isnotprime[i]){
        		for(int j=2;j*i<=n;j++){
        			isnotprime[j*i] = true;
        		}
        	}
        }
        int sum = 0;
        for(int i=2;i<n;i++){
        	if(!isnotprime[i])
        		sum++;
        }
        return sum;
    }
    //204-2
    public int countPrimes2(int n) {
        if (n < 3) return 0;
        int primeNum = n / 2; 
        boolean[] isPrime = new boolean[n];
        int sqrtN = (int) Math.sqrt(n);
        for (int i = 3; i <= sqrtN; i += 2) {
            if (!isPrime[i]) {
                for (int j = i * i; j < n; j += i * 2) {
                    primeNum -= (isPrime[j])? 0: 1;
                    isPrime[j] = true;
                }
            }
        }
        return primeNum;  
    }
    //14. Longest Common Prefix
    public static String longestCommonPrefix(String[] strs) {
        if(strs.length==0 ) return "";
        int min = strs[0].length();
        for(int i=0;i<strs.length;i++){
        	if(strs[i].length()<min)
        		min = strs[i].length();
        }
        StringBuffer sb = new StringBuffer();
        boolean brk = false;
        for(int i=0;i<min && !brk;i++){
        	char t = strs[0].charAt(i);
        	for(int j=0;j<strs.length;j++){
        		if(strs[j].charAt(i)!=t){
        			brk = true;
        			break;
        		}
        	}
        	if(!brk)
        		sb.append(t);
        }
        return sb.toString();
    }
    
    //14-2
    public String longestCommonPrefix2(String[] strs) {
        if (strs.length < 1 || strs == null) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        int shortest = 0;
        int len = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            int curLen = strs[i].length();
            if (curLen < len) {
                len = curLen;
                shortest = i;
            }
        }
        String sub = strs[shortest];
        for (int i = 0; i < strs.length; i++) {
            while (strs[i].indexOf(sub) != 0) {
                sub = sub.substring(0, sub.length()-1);
            }
        } 
        return sub;
    }
    //344. Reverse String
    public String reverseString(String s) {
    	StringBuffer sb = new StringBuffer(s);
    	sb.reverse();
        return sb.toString();
    }
    
    public void quicksort(int left,int right,int []s){
		if(left<right){
			int i = left;
			int j = right;
			int tmp = s[i];
			while(i<j){
				while(j>i && s[j]>=tmp) j--;
				while(i<j && s[i]<=tmp) i++;
				if(i<j){
					int t = s[i];
					s[i] = s[j];
					s[j] = t;
				}
			}
			s[left] = s[i];
			s[i] = tmp;
			quicksort(left, i-1, s);
			quicksort(i+1, right, s);
		}
	}
    //342. Power of Four
    public static boolean isPowerOfFour(int num) {
    	
        return Integer.toString(num,4).matches("10*");
    }
    public static boolean isPowerOfFour2(int num) {
    	
    	return (num > 0) && ((num & (num - 1)) == 0) && ((num & 0x55555555) == num);
    }
    //231. Power of Two
    public static boolean isPowerOfTwo(int n) {
    	
        return (n > 0) && ((n & (n - 1)) == 0);
    }
    
    //128. Longest Consecutive Sequence
    public static int longestConsecutive(int[] nums) {
    	int max = 0;
    	HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
    	for(int n:nums){
    		if(!map.containsKey(n)){
    			int left = map.containsKey(n-1)?map.get(n-1):0;
    			int right = map.containsKey(n+1)?map.get(n+1):0;
    			int sum = left+right+1;
    			map.put(n,sum);
    			max = Math.max(sum, max);
    			
    			map.put(n-left, sum);
    			map.put(n+right, sum);
    			
    		}
    		else 
    			continue;
    	}
        return max;
    }
    //128-2
    public int longestConsecutive2(int[] num) {
        Set<Integer> set = new HashSet<Integer>(num.length);
        for (int n: num) {
            set.add(n);
        }

        int maxLength = 0;
        for (int n: num) {
            if (set.contains(n)) {
                int length = 1;
                int next = n - 1;
                while (set.contains(next)) {
                    length++;
                    set.remove(next);
                    next--;
                }
                next = n+1;
                while (set.contains(next)) {
                    length++;
                    set.remove(next);
                    next++;
                }

                if (length > maxLength) {
                    maxLength = length;
                }
            }
        }

        return maxLength;
    }
    
    //128-3 using union-find
    public int longestConsecutive3(int[] nums) {
        UF uf = new UF(nums.length);
        Map<Integer,Integer> map = new HashMap<Integer,Integer>(); // <value,index>
        for(int i=0; i<nums.length; i++){
            if(map.containsKey(nums[i])){
                continue;
            }
            map.put(nums[i],i);
            if(map.containsKey(nums[i]+1)){
                uf.union(i,map.get(nums[i]+1));
            }
            if(map.containsKey(nums[i]-1)){
                uf.union(i,map.get(nums[i]-1));
            }
        }
        return uf.maxUnion();
    }


    class UF{
	    private int[] list;
	    public UF(int n){
	        list = new int[n];
	        for(int i=0; i<n; i++){
	            list[i] = i;
	        }
	    }
	
	    private int root(int i){
	        while(i!=list[i]){
	            list[i] = list[list[i]];
	            i = list[i];
	        }
	        return i;
	    }
	
	    public boolean connected(int i, int j){
	        return root(i) == root(j);
	    }
	
	    public void union(int p, int q){
	      int i = root(p);
	      int j = root(q);
	      list[i] = j;
	    }
	
	    // returns the maxium size of union
	    public int maxUnion(){ // O(n)
	        int[] count = new int[list.length];
	        int max = 0;
	        for(int i=0; i<list.length; i++){
	            count[root(i)] ++;
	            max = Math.max(max, count[root(i)]);
	        }
	        return max;
	    }
    }
    //40. Combination Sum II
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> s = new ArrayList<List<Integer>>();
        if(candidates.length==0) return s;
        Arrays.sort(candidates);
        List<Integer> t = new ArrayList<Integer>();
        backtracking(candidates, t, 0, s, target, 0);
        return s;
    }
    
    public static void backtracking(int[]nums,List<Integer> t,int currsum,List<List<Integer>> sol,int target,int index){
		if (currsum == target) {
			sol.add(new ArrayList<Integer>(t));
			return;
		} else if (target < 0)
			return;
		else if (target >= 0) {
			for (int i = index; i < nums.length; i++) {
				if (i > index && nums[i] == nums[i - 1])
					continue;
				if (currsum + nums[i] <= target) {

					t.add(t.size(), nums[i]);
					backtracking(nums, t, currsum + nums[i], sol, target, i + 1);
					t.remove(t.size() - 1);

				} else
					break;
			}
		}
    	
    }
    
    //40-2
    public List<List<Integer>> combinationSum22(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        generate(candidates, res, new ArrayList<Integer>(), target, 0);
        return res;
    }
    private void generate(int[] candidates, List<List<Integer>> res, ArrayList<Integer> cur, int target, int start) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<Integer>(cur));
            return;
        }
        for (int i = start; i < candidates.length; i++) {            
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (target - candidates[i] < 0) break;
            cur.add(candidates[i]);
            generate(candidates, res, cur, target - candidates[i], i + 1);
            cur.remove(cur.size() - 1);
        }
    }   
    //151. Reverse Words in a String
    public static String reverseWords(String s) {
    	String[] strs = s.trim().split(" ");
    	StringBuffer sb = new StringBuffer();
    	
    	for(int i=strs.length-1;i>0;i--){
    		if(strs[i].isEmpty()) continue;
    		sb.append(strs[i]);
    		sb.append(" ");
    	}
    	sb.append(strs[0]);
    	
        return sb.toString();
    }
    //59. Spiral Matrix II
    public static int[][] generateMatrix(int n) {
        int [][] s = new int[n][n];
        if(n==0) return s;
        int curr = 2;
        int i=0,j=0;
        boolean [][] visited = new boolean[n][n];
        visited[0][0] = true;
        s[0][0] = 1;
        while(curr<=n*n){
        	while(j+1<n && !visited[i][j+1]) {visited[i][j+1]=true;s[i][j+1]=curr++;j++;}
        	while(i+1<n && !visited[i+1][j]) {visited[i+1][j]=true;s[i+1][j]=curr++;i++;}
        	while(j-1>=0 &&!visited[i][j-1]){visited[i][j-1]=true;s[i][j-1]=curr++;j--;}
        	while(i-1>=0 &&!visited[i-1][j]){visited[i-1][j]=true;s[i-1][j]=curr++;i--;}
        }
        return s;
    }
    //104. Maximum Depth of Binary Tree
    public static int maxDepth(TreeNode root) {
        if(root==null) return 0;
        
        return dfs_104(root,1);
    }
    
    public static int dfs_104(TreeNode root,int curr){
    	int left = curr;
    	int right = curr;
    	if(root.left!=null) left = dfs_104(root.left,curr+1);
    	if(root.right!=null) right = dfs_104(root.right,curr+1);
    	return Math.max(left, right);
    }
    //115. Distinct Subsequences
    
    public static int numDistinct(String s, String t) {
    	int[] p=new int[t.length()+1];
        p[0]=1;
        for(int i=0;i<s.length();i++){
            for(int j=t.length();j>0;j--){
                if(s.charAt(i)==t.charAt(j-1)) p[j]=p[j]+p[j-1];
            }
        }
        return p[p.length-1];
    }
    //115-2
    public int numDistinct2(String s, String t) {
        int[][] arr = new int[256][t.length()+1];
        int[] cnt = new int[t.length()+1];
        cnt[0] = 1;
        char c;
        for(int i = 0; i < t.length(); i++ ) {
            c = t.charAt(i);
            arr[c][arr[c][0]+1] = i+1;
            arr[c][0]++;
        }
        for( char a: s.toCharArray() ) {
            if( arr[a][0] != 0 ) {
                for( int i = arr[a][0]; i > 0; i-- ) {
                    cnt[arr[a][i]] += cnt[arr[a][i]-1];
                }
            }
        }
        return cnt[t.length()];
    }
    
    //94. Binary Tree Inorder Traversal
    public static List<Integer> inorderTraversal(TreeNode root) {
    	List<Integer> s = new ArrayList<Integer>();
    	if(root == null) return s;
    	dfs_94(root,s);
        return s;
    }
    
    public static void dfs_94(TreeNode root,List<Integer> s){
    	if(root.left!=null) dfs_94(root.left,s);
    	s.add(root.val);
    	if(root.right!=null) dfs_94(root.right,s);
     }
    //191. Number of 1 Bits
    public static int hammingWeight(int n) {
        int num = 0;
        while(n!=0){
        	num+=(n&1);
        	n>>>=1;
        }
        return num;
    }
    //329. Longest Increasing Path in a Matrix 
    //--TLE
    public static int max_329 = 0;
    public static int longestIncreasingPath(int[][] matrix) {
    	max_329 = 0;
    	int x = matrix.length;
    	if(x==0) return 0;
    	int y = matrix[0].length;
    	for(int i=0;i<x;i++)
    		for(int j=0;j<y;j++)
    			dfs_329(matrix,1,i,j,x,y);
        return max_329;
    }
    
    public static void dfs_329(int[][] matrix,int sum,int x,int y,int m,int n){
    	max_329=max_329<sum?sum:max_329;
    	if((x+1)<m && matrix[x][y]<matrix[x+1][y] ){
    		dfs_329(matrix,sum+1,x+1,y,m,n);
    	}
    	if((x-1)>=0 && matrix[x][y]<matrix[x-1][y]){
    		dfs_329(matrix,sum+1,x-1,y,m,n);
    	}
    	if((y+1)<n && matrix[x][y]<matrix[x][y+1] ){
    		dfs_329(matrix,sum+1,x,y+1,m,n);
    	}
    	if((y-1)>=0 && matrix[x][y]<matrix[x][y-1]){
    		dfs_329(matrix,sum+1,x,y-1,m,n);
    	}
    }
    public static int [][]path;
    public static int longestIncreasingPath2(int[][] matrix) {
    	if(matrix == null || matrix.length == 0) return 0;
    	int max = 0;
    	int row = matrix.length;
    	int col = matrix[0].length;
    	path = new int[row][col];
    	for(int i=0;i<row;i++)
    		for(int j=0;j<col;j++){
    			dfs_329_2(matrix,i,j,Integer.MIN_VALUE);
    			max = max > path[i][j]?max:path[i][j];
    		}
    	return max;
    }
    
    private static int dfs_329_2(int[][] matrix, int x, int y, int curr) {
		// TODO Auto-generated method stub
		if(x<0||x>=matrix.length||y<0||y>=matrix[0].length|| matrix[x][y] <= curr)
			return 0;
		if(path[x][y]>0)
			return path[x][y];
		int max = 1;
		int[]dx={-1,1,0,0};
		int[]dy={0,0,-1,1};
		for(int i=0;i<4;i++){
			int curx = x+dx[i];
			int cury = y+dy[i];
			int tmp = dfs_329_2(matrix, curx, cury, matrix[x][y])+1;
			max = max>tmp?max:tmp;
		}
		path[x][y]=max;
		return max;
	}
    //287. Find the Duplicate Number
    public int findDuplicate(int[] nums) {
    	if(nums.length>1){
	    	int fast = nums[nums[0]];
	    	int slow = nums[0];
	    	while(fast!=slow){
	    		fast = nums[nums[fast]];
	    		slow = nums[slow];
	    	}
	    	fast = 0;
	    	while(fast!=slow){
	    		fast = nums[fast];
	    		slow = nums[slow];
	    	}
	    	return slow;
    	}
    	return 0;
        
    }
   //142. Linked List Cycle II
    public static ListNode detectCycle(ListNode head) {
    	if(head == null || head.next==null) return null;
    	ListNode fast = head.next.next;
    	ListNode slow = head.next;
    	while(fast!=null && fast.next!=null){
    		fast = fast.next.next;
    		slow = slow.next;
    		if(fast == slow) break;
    	}
    	if(fast==null || fast.next==null) return null;
    	fast = head ;
    	while(fast!=slow){
    		fast = fast.next;
    		slow = slow.next;
    	}
        return fast;
    }
    
    //141. Linked List Cycle
    public boolean hasCycle(ListNode head) {
    	if(head == null || head.next==null) return false;
    	ListNode fast = head.next.next;
    	ListNode slow = head.next;
    	while(fast!=null && fast.next!=null){
    		fast = fast.next.next;
    		slow = slow.next;
    		if(fast == slow) return true;
    	}
    	return false;
    }
	//219. Contains Duplicate II
    public boolean containsNearbyDuplicate(int[] nums, int k) {
    	HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
    	for(int i=0;i<nums.length;i++){
    		if(map.containsKey(nums[i]) && i-map.get(nums[i])<=k){
    			return true;
    		}
    		else map.put(nums[i], i);
    	}
    		
        return false;
    }
    //110. Balanced Binary Tree
    public static boolean isBalanced(TreeNode root) {
    	return dfs_110(root)==-1?false:true;
    }
    
    public static int  dfs_110(TreeNode root){
    	if(root == null) return 0;
    	int left = dfs_110(root.left);
    	if(left == -1) return -1;
    	int right = dfs_110(root.right);
    	if(right == -1) return -1;
    	if(right - left>1 || left - right>1)
    		return -1;
    	return (left>right?left:right)+1;
     }
    //111. Minimum Depth of Binary Tree
    public int minDepth(TreeNode root) {
    	if(root == null) return 0;
    	if(root.left==null && root.right==null){
    		return Math.min(minDepth(root.left), minDepth(root.right))+1;
    	}
    	else {
    		return Math.max(minDepth(root.left), minDepth(root.right));
    	}
    }
    //347. Top K Frequent Elements
    public static List<Integer> topKFrequent(int[] nums, int k) {
	    	HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
	    	List<Integer>[]bucket = new List[nums.length+1];
	    	for(int num:nums){
	    		/*map.putIfAbsent(num, 0);
	    		map.computeIfPresent(num, (key,oldVal)->oldVal+1);*/
	    	}
	    	for(int key:map.keySet()){
	    		int freq = map.get(key);
	    		if(bucket[freq]==null)
	    			bucket[freq] = new ArrayList<>();
	    		bucket[freq].add(key);
	    	}
	    	List<Integer> res = new ArrayList<>();
	    	for(int p = bucket.length-1;p>=0&&res.size()<k;p--){
	    		if(bucket[p]!=null)
	    			res.addAll(bucket[p]);
	    	}
	    	return res;
    }
    //150. Evaluate Reverse Polish Notation
    public static int evalRPN(String[] tokens) {
        Stack<Integer> s = new Stack<Integer>();
        for(int i=0;i<tokens.length;i++){
        	if(tokens[i].equals("+")){
        		int a = s.pop();
        		int b = s.pop();
        		s.push(a+b);
        	}
        	else if(tokens[i].equals("-")){
        		int a = s.pop();
        		int b = s.pop();
        		s.push(b-a);
        	}
        	else if(tokens[i].equals("*")){
        		int a = s.pop();
        		int b = s.pop();
        		s.push(a*b);
        	}
        	else if(tokens[i].equals("/")){
        		int a = s.pop();
        		int b = s.pop();
        		s.push(b/a);
        	}
        	else s.push(Integer.parseInt(tokens[i]));
        }
        return s.peek();
    }
    //150-2
    public static int evalRPN2(String[] tokens) {
    	int []nums = new int[tokens.length/2+1];
    	int i = 0;
    	for(String s :tokens){
    		switch(s){
    			case "+":
	    			nums[i-2]=nums[i-2]+nums[i-1];
	    			i--;
	    			break;
    			case "-":
    				nums[i-2]=nums[i-2]-nums[i-1];
    				i--;
    				break;
    			case "*":
    				nums[i-2]=nums[i-2]*nums[i-1];
    				i--;
    				break;
    			case "/":
    				nums[i-2]=nums[i-2]/nums[i-1];
    				i--;
    				break;
    			default:
    				nums[i++]=Integer.parseInt(s);
    				break;
    				
    			
    		}
    	}
    	return nums[0];
    }
    //258. Add Digits
    public static int addDigits(int num) {
        return (num-1)%9+1;
    }
    //216. Combination Sum III
    public static List<List<Integer>> combinationSum3(int k, int n) {
    	List<List<Integer>> s = new ArrayList<List<Integer>>();
        if(n==0||k==0) return s;
        List<Integer> p = new ArrayList<Integer>();
        backtracking_216(s, 1, 0, n, k, p);
        return s;
    }
    
    public static void backtracking_216(List<List<Integer>> s,int start,int sum,int n,int k,List<Integer> p){
    	if(p.size()==k && sum==n){
    		s.add(new ArrayList<Integer>(p));
    		return ;
    	}
    	if(p.size()<k){
	    	for(int i=start;i<10;i++){
	    		if(sum+i<=n){
	    			p.add(i);
	    			backtracking_216(s,  i+1, sum+i, n, k, p);
	    			p.remove(p.size()-1);
	    		}
	    		else break;
	    	}
    	}
    }
    //118. Pascal's Triangle
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> t = new ArrayList<List<Integer>>();
        if(numRows==0) return t;
        for(int i=1;i<=numRows;i++){
        	List<Integer> p = new ArrayList<Integer>();
        	for(int j=0;j<i;j++){
        		if(j==0 || j==i-1) p.add(1);
        		else {
        			List<Integer> q = t.get(i-2);
        			p.add(q.get(j)+q.get(j-1));
        		}
        	}
        	t.add(p);
        }
        return t;
    }
    //119. Pascal's Triangle II
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> s = new ArrayList<Integer>();
        s.add(1);
        if(rowIndex==0) return s;
        for(int i=1;i<=rowIndex;i++){
        	for(int j=i;j>0;j--){
        		if(j==i) s.add(1);
        		else {
        			s.set(j, s.get(j)+s.get(j-1));
        		}
        	}
        }
        return s;
    }
    //237. Delete Node in a Linked List
    public static void deleteNode(ListNode node) {
        if(node == null||node.next==null) return ;
        node.val = node.next.val;
        node.next = node.next.next;
    }
    //203. Remove Linked List Elements
    public static ListNode removeElements(ListNode head, int val) {
        if(head==null ) return null;
        if(head.next==null){
        	if(head.val==val) return null;
        	else return head;
        }
        ListNode t = head;
        while(t.val==val && t.next!=null) 
        	deleteNode(t);
        while(t.next!=null){
        	if(t.next.val==val){
        		t.next = t.next.next;
        	}
        		
        	else t = t.next;
        }
        if(head.next==null){
        	if(head.val==val) return null;
        	else return head;
        }
        return head;
    }
    //55. Jump Game
    public static boolean canJump(int[] nums) {
       int n =nums.length;
       if(n==1) return true;
       if(n==0) return false;
       int tar = n-1;
       for(int i=n-2;i>=0;i--){
    	   int gap = tar-i;
    	   if(gap<=nums[i]) tar = i;
       }
       return tar==0;
    }
    //113. Path Sum II
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> s = new ArrayList<List<Integer>>();
        if(root == null) return s;
        List<Integer> p = new ArrayList<Integer>();
        dfs_113(s, sum, 0, p, root);
        return s;
    }
    
    public static void dfs_113(List<List<Integer>> s,int sum,int curr,List<Integer> p,TreeNode root){
    	if(root == null) return;
    	else if(root.left ==null &&root.right==null){
    		if((curr+root.val)==sum){
    			p.add(root.val);
    			s.add(new ArrayList<Integer>(p));
    			p.remove(p.size()-1);
    			return ;
    		}
    	}
    	else {
    		p.add(root.val);
    		if(root.left!=null) {
    			
    			dfs_113(s, sum, curr+root.val, p, root.left);
    		}
    		if(root.right!=null) {
    			dfs_113(s, sum, curr+root.val, p, root.right);
    		}
    		p.remove(p.size()-1);
    	}
    	
    }
    //220. Contains Duplicate III
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    	if (nums.length <= 0 || k <= 0) {
            return false;
        }
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if (set.floor(val) != null && (set.floor(val) + t) >= val)
                return true;
            if (set.ceiling(val) != null && (set.ceiling(val) - t) <= val)
                return true;
            set.add(val);
            if (i >= k)
                set.remove(nums[i - k]);
        }
        return false;
    }
    //147. Insertion Sort List
    public ListNode insertionSortList(ListNode head) {
        ListNode hhead = new ListNode(Integer.MIN_VALUE);
        while(head!=null){
        	ListNode th = hhead;
        	ListNode thn = hhead.next;
        	while(thn!=null &&thn.val<head.val){
        		th = thn;
        		thn = thn.next;
        	}
        	th.next = head;
        	head = head.next;
        	th.next.next = thn;
        }
        return hhead.next;
    }
    //189. Rotate Array
    public static void rotate(int[] nums, int k) {
    	k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
    //64. Minimum Path Sum
    public static int minPathSum(int[][] grid) {
    	if(grid.length==0 || grid[0].length==0) return 0;
    	int x = grid.length;
    	int y = grid[0].length;
    	int[][] sum = new int[x][y];
    	sum[x-1][y-1] = grid[x-1][y-1];
    	for(int i=x-2;i>=0;i--){
    		sum[i][y-1] = grid[i][y-1]+sum[i+1][y-1];
    	}
    	for(int i=y-2;i>=0;i--){
    		sum[x-1][i] = grid[x-1][i]+sum[x-1][i+1];
    	}
    	for(int i=x-2;i>=0;i--)
    		for(int j=y-2;j>=0;j--){
    			sum[i][j] = Math.min(sum[i+1][j]+grid[i][j], sum[i][j+1]+grid[i][j]);
    		}
    			
    	
        return sum[0][0];
    }
    //64-2
    public int minPathSum2(int[][] grid) {
        if(grid.length == 0)  return 0;

        int r = grid.length;
        int c = grid[0].length;

        for(int i=0;i<r; i++) {
            for(int j=0; j<c; j++) {
                int leftSum = (j>0) ? grid[i][j-1] : Integer.MAX_VALUE;
                int topSum = (i>0) ? grid[i-1][j] : Integer.MAX_VALUE;
                if(i==0 && j==0) continue;

                grid[i][j] += Math.min(leftSum, topSum);
            }
        }
        return grid[r-1][c-1];
    }
    //134. Gas Station
    public static int canCompleteCircuit(int[] gas, int[] cost) {
    	if(gas.length==0||cost.length==0) return -1;
    	int length = gas.length;
    	int [] yugas = new int[length];
    	for(int i=0;i<length;i++){
    		yugas[i] = gas[i]-cost[i];
    	}
    	for(int i=0;i<length;i++){
    		if(yugas[i]>=0){
    			int currgas = gas[i]-cost[i];
    			int n = i;
    			for(int j=n+1;j!=n;j++){
        			if(j==gas.length){
        				if(n==0) break;
        				j=0;
        			
        			}
        			currgas = currgas + gas[j] - cost[j];
        			if(currgas<0)  break;
        		}
        		if(currgas>=0) return n;
    		}
    	}
    	return -1;
    }
    //134-2
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int startPoint = 0;
        int sum = 0;
        int totalLeftGas = 0;
        for (int i = 0; i < gas.length; i++) {
            int diff = gas[i] - cost[i];
            totalLeftGas += diff;
            sum += diff;
            if (sum < 0) {
                startPoint = i + 1;
                sum = 0;
            }
        }
        return totalLeftGas >= 0 ? startPoint : -1;
    }
    
    //58. Length of Last Word
    public static int lengthOfLastWord(String s) {
    	String t = s.replaceAll(" +", " ");
        String [] ss = t.split(" ");
        if(ss.length==1) return 0;
        return ss[ss.length-1].length();
    }
    //58-2
    public static int lengthOfLastWord2(String s) {
    	char[] chars = s.toCharArray();
    	int sum = 0;
    	boolean flag = false;
    	for(int i=chars.length-1;i>=0;i--){
    		if(chars[i]==' ' && !flag) continue;
    		else if(!flag && chars[i]!=' '){
    			flag=true;
    			sum++;
    		}
    		else if(flag && chars[i]==' '){
    			break;
    		}
    		else {
    			sum++;
    		}
    	}
    	return sum;
    }
    //263. Ugly Number
    static Set<Integer> set = new HashSet<Integer>();
    public static boolean isUgly(int nums) {
    	if(!ini){
    		ini = true;
    		init();
    		for(int i=0;i<3000;i++){
        		set.add(num[i]);
        	}
    	}
    	return set.contains(nums);
    }
    public static boolean isUgly2(int num) {
    	if(num==1) return true;
    	if(num<0) {
    		if(num==Integer.MIN_VALUE) return true;
    		num=Math.abs(num);
    	}
    	while(num!=1){
    		if(num%2==0) num/=2;
    		else if(num%3==0) num/=3;
    		else if(num%5==0) num/=5;
    		else {
    			return false;
    		}
    	}
    	return true;
    }
    //230. Kth Smallest Element in a BST
    public int kthSmallest(TreeNode root, int k) {
        if(root==null) return 0;
        Stack<TreeNode> s = new Stack<TreeNode>();
        while(root!=null &&!s.isEmpty()){
        	while(root!=null){
        		s.push(root);
        		root = root.left;
        	}
        	root = s.pop();
        	if(--k == 0 ) break;
        	root = root.right;
        }
        return root.val;
    }
    //319. Bulb Switcher
    public int bulbSwitch(int n) {
    	return (int) Math.sqrt(n);
    }
    //135. Candy
    public int candy(int[] ratings) {
    	if(ratings.length==0) return 0;
        int[] tmp = new int[ratings.length];
        int length = ratings.length;
        tmp[0] =1;
        for(int i=1;i<length;i++){
        	if(ratings[i]>ratings[i-1]){
        		tmp[i] = tmp[i-1]+1;
        	}
        	else 
        		tmp[i] = 1;
        }
        int candy = tmp[length-1];
        for(int i = length-2;i>=0;i--){
        	if(ratings[i]>ratings[i+1] && tmp[i]<=tmp[i+1]){
        		tmp[i] = tmp[i+1]+1;
        	}
        	candy += tmp[i];
        }
        return candy;
    }


    	

    //106. Construct Binary Tree from Inorder and Postorder Traversal
    public static TreeNode buildTree106(int[] inorder, int[] postorder) {
        int i = 0;
        int j = 0;

        TreeNode t = null;
        while(i<inorder.length && j<postorder.length){
        	if(inorder[i]==postorder[j]){
        		TreeNode node = new TreeNode(inorder[i]);
        		node.left = t;
        		t = node;
        		i++;
        		j++;
        	}
        	else {
        		TreeNode node = new TreeNode(inorder[i]);
        		node.left = t;
        		t = node;
        		int k = j;
        		while(inorder[i]!=postorder[k]){
        			k++;
        		}
        		int[]pinorder = Arrays.copyOfRange(inorder, i+1, i+1+k-j);
        		int[]ppostorder = Arrays.copyOfRange(postorder, j, k);
        		
        		node = buildTree106(pinorder, ppostorder);
        		t.right = node;
        		i=k+1;
        		j=k+1;
        	}
        }
        return t;
    }
    //108. Convert Sorted Array to Binary Search Tree
    public static TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = null;
        if(nums.length==0) return root;
        if(nums.length==1) return root = new TreeNode(nums[0]);
        int half = nums.length/2;
        root = new TreeNode(nums[half]);
        int []left = Arrays.copyOfRange(nums, 0, half);
        int []right = Arrays.copyOfRange(nums, half+1, nums.length);
        root.left = sortedArrayToBST(left);
        root.right = sortedArrayToBST(right);
        return root;
    }
    //108-2
    public TreeNode sortedArrayToBST2(int[] nums) {
        return helper(0, nums.length-1, nums);
    }

    public TreeNode helper(int start, int end, int[]nums){
        if(start>end) return null;

        int mid = start + (end - start)/2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(start, mid-1, nums);
        root.right = helper(mid+1, end, nums);

        return root;
    }
    
    //10. Regular Expression Matching
    public static boolean isMatch(String s, String p) {
        if(p.length()==0) return s.length()==0;
        if(p.length()>1 && p.charAt(1)=='*'){
        	if(isMatch(s,p.substring(2))){
        		return true;
        	}
        	if(s.length()>0 &&( p.charAt(0)=='.'||p.charAt(0)==s.charAt(0))){
        		return isMatch(s.substring(1),p);
        	}
        	return false;
        }
        else {
        	if(s.length()>0 &&(p.charAt(0)=='.'||p.charAt(0)==s.charAt(0))){
        		return isMatch(s.substring(1), p.substring(1));
        	}
        	return false;
        }
        	
        
    }
    //10-2
    char[] s1, p;
    int sLength, pLength, noWildcard;
    Boolean[][] dp;
    public boolean isMatch2(String ss, String ps) {

        s1 = ss.toCharArray();
        p = ps.toCharArray();
        sLength = s1.length;
        pLength = p.length;
        noWildcard = pLength - 1;
        dp = new Boolean[pLength + 1][sLength + 1];
        return isMatch(0, 0);
    }

    boolean isMatch(int pi, int si){
        if(dp[pi][si] != null) return dp[pi][si];
        while(true){

            if(pi < noWildcard && p[pi+1] == '*'){
                char c = p[pi];
                pi += 2;

                while(true){

                    boolean isMatch = isMatch(pi, si);
                    dp[pi][si] = isMatch;

                    if(isMatch){
                        return true;
                    }

                    if(si == sLength) return pi == pLength;

                    if(c != '.' && c != s1[si]){
                        return false;
                    }

                    si++;
                }
            }

            if(pi == pLength || si == sLength) return pi == pLength && si == sLength;

            if(p[pi] != '.' && p[pi] != s1[si]){
                return false;
            }

            pi++;
            si++;
        }
    }
    //312. Burst Balloons
    public static int maxCoins(int[] iNums) {
        int[] nums = new int[iNums.length + 2];
        int n = 1;
        for (int x : iNums) if (x > 0) nums[n++] = x;
        nums[0] = nums[n++] = 1;


        int[][] dp = new int[n][n];
        for(int i=2;i<n;i++)
        	for(int left = 0;left<n-i;left++){
        		int right = left + i;
        		for(int k=left+1;k<right;k++){
        			dp[left][right] = Math.max(dp[left][right], nums[k]*nums[left]*nums[right] + dp[left][k]+dp[k][right]);
        		}
        	}
        return dp[0][n-1];
    }
    //350. Intersection of Two Arrays II
    public static int[] intersect(int[] nums1, int[] nums2) {
    	List<Integer> s = new ArrayList<Integer>();
    	int n1 = nums1.length;
    	int n2 = nums2.length;
    	if(n1==0 || n2==0) return new int[0];
    	Arrays.sort(nums1);
    	Arrays.sort(nums2);
    	int i=0;int j=0;
    	while(i<n1 && j<n2){
    		if(nums1[i]==nums2[j]){
    			s.add(nums1[i]);
    			i++;
    			j++;
    		}
    		else if(nums1[i]<nums2[j]){
    			i++;
    		}
    		else if(nums1[i]>nums2[j]){
    			j++;
    		}
    	}
    	int [] sol = new int[s.size()];
    	for(int k=0;k<s.size();k++)
    		sol[k] = s.get(k);
    	return sol;
    }
    //84. Largest Rectangle in Histogram
    public static int largestRectangleArea(int[] heights) {
    	int length = heights.length;
    	int max = 0;
    	Stack<Integer> s = new Stack<Integer>();
    	for(int i=0;i<=length;i++){
    		int curr = i==length?0:heights[i];
    		if(s.isEmpty() || curr>heights[s.peek()]){
    			s.push(i);
    		}
    		else {
    			int curmax = s.pop();
    			max = Math.max(max, heights[curmax]*(s.isEmpty()?i:i-1-s.peek()));
    			i--;
    		}
    	}
        return max;
    }
    //84-2
    public int largestRectangleArea2(int[] height) {
        int[] stack=new int[height.length];
        int index=0,max=0;
        for(int i=0;i<height.length;i++){
        	
            while(index!=0&&height[stack[index-1]]>height[i]){
                if(index==1) max=Math.max(height[stack[--index]]*i,max);
                else max=Math.max(height[stack[--index]]*(i-stack[index-1]-1),max);
            }
            stack[index++]=i;
        }
        
        while(index!=0){
            if(index==1) max=Math.max(height[stack[--index]]*height.length,max);
            else max=Math.max(height[stack[--index]]*(height.length-stack[index-1]-1),max);
        }
        
        return max;
    }
    //122. Best Time to Buy and Sell Stock II
    public static int maxProfit_123(int[] prices) {
    	if(prices==null || prices.length==0||prices.length==1) return 0;
    	if(prices.length==2) return prices[1]-prices[0]>0?prices[1]-prices[0]:0;
    	ArrayList<Integer> bottom = new ArrayList<Integer>();
    	ArrayList<Integer> top = new ArrayList<Integer>();
    	for(int i=1;i<prices.length-1;i++){
    		if(prices[i]-prices[i-1]>0 && prices[i+1]-prices[i]<=0){
    			top.add(prices[i]);
    		}
    		if(prices[i]-prices[i-1]<=0 && prices[i+1]-prices[i]>0){
    			bottom.add(prices[i]);
    		}
    	}
    	int sum=0;
    	if(prices[0]-prices[1]<0)
    		bottom.add(prices[0]);
    	if(prices[prices.length-1]-prices[prices.length-2]>0)
    		top.add(prices[prices.length-1]);
    	for(int i=0;i<bottom.size();i++){
    		sum +=top.get(i)-bottom.get(i);
    	}
        return sum;
    }
    //122-2
    public int maxProfit122(int[] prices) {
        int length = prices.length;
         if (length < 2) {
             return 0;
         }
         int profit = 0;
         int lastBuy = prices[0];
         for (int i = 1; i < length; i++) {
             if (prices[i]>lastBuy) {
                 profit = prices[i] - lastBuy + profit;
             }
             lastBuy = prices[i];
         }
         return profit;
     }
    //209. Minimum Size Subarray Sum
    public static int minSubArrayLen(int s, int[] nums) {
    	int start = 0;
    	int min = Integer.MAX_VALUE;
    	int sum=0;
    	for(int end=0;end<nums.length;end++){
    		sum+=nums[end];
    		if(sum>=s) min = Math.min(min,end-start);
    		while(start<nums.length-1 && sum-nums[start]>=s){
    			sum = sum-nums[start];
    			min = Math.min(min, end-start);
    		}
    	}
    	return min==Integer.MAX_VALUE?0:min+1;
    }
    //209-2
    public int minSubArrayLen2(int sum, int[] nums) {
        int minlen = Integer.MAX_VALUE;
        int curSum = 0;
        int start = 0;
        int end = 0;
        while(start < nums.length){
            if(curSum < sum && end < nums.length){
                curSum += nums[end];
                end++;
            }
            else if(curSum >= sum){
                minlen = Math.min(minlen, end-start);
                curSum -= nums[start];
                start++;
            }
            else{
                break;
            }
        }
        return (minlen == Integer.MAX_VALUE) ? 0 : minlen;
    }
  //130. Surrounded Regions
    public static void solve(char[][] board) {
    	if(board.length==0||board[0].length==0) return ;
    	
        for(int i=0;i<board[0].length;i++){
        		change(board, 0, i);
        		change(board,board.length-1,i);
        }
        for(int i=0;i<board.length;i++){
        	change(board,i,0);
        	change(board,i,board[0].length-1);
        }
        for(int i=0;i<board.length;i++)
        	for(int j=0;j<board[0].length;j++){
        		if(board[i][j]=='*') board[i][j] = 'O';
        		else board[i][j] = 'X';
        	}
        System.out.println();
    }
    
    public static void change(char[][] board,int x,int y){
    	if(x>=board.length || y>=board[0].length || x<0 || y<0 || board[x][y]=='X'||board[x][y]=='*') return ;
    	board[x][y] = '*';
    	if(x-1>0)
    		change(board, x-1, y);
    	if(y-1>0)
    		change(board, x, y-1);
    	if(x+1<board.length)
    		change(board, x+1, y);
    	if(y+1<board[0].length)
    		change(board, x, y+1);
    	
    }
    //315. Count of Smaller Numbers After Self
    public static List<Integer> countSmaller(int[] nums) {
    	if(nums==null||nums.length==0) return null;
    	int [] arr = nums.clone();
    	Arrays.sort(arr);
    	int [] bit = new int[nums.length];
    	List<Integer> sol = new ArrayList<Integer>();
    	for(int i=0;i<arr.length;i++){
    		caculate(bit, Arrays.binarySearch(arr, nums[i]), 1);
    	}
    	for(int i=0;i<arr.length;i++){
    		int index = Arrays.binarySearch(arr, nums[i]);
    		sol.add(sumofall(bit, index-1));
    		caculate(bit, index, -1);
    	}
        return sol;
    }
    public static void caculate(int[]bit,int index,int val){
    	for(int i=index+1;i<bit.length;i+=i&-i)
    		bit[i] +=val; 
    }
    public static int sumofall(int[]bit,int index){
    	int sum=0;
    	for(int i=index+1;i>0;i-=i&-i)
    		sum+=bit[i];
    	return sum;
    }
    //218. The Skyline Problem
    /*public static List<int[]> getSkyline(int[][] buildings) {
    	if(buildings==null) return null;
    	List<int[]> s = new ArrayList<int[]>();
    	
    	if(buildings.length==0 || buildings[0].length==0) return s;
    	int max = 0;
    	
    	for(int i=0;i<buildings.length;i++){
    		if(buildings[i][1]>max)
    			max = buildings[i][1];
    	}
    	int []bit = new int[max+1];
    	for(int i=0;i<buildings.length;i++){
    		AddVal(bit, buildings[i][0], buildings[i][2], buildings[i][1]);
    	}
        return null;
    }
    public static void AddVal(int[] bit,int index,int curr,int end){
    	for(int i=index+1;i<end;i+=i&-i){
    		if(bit[i]<curr)
    			 bit[i] = curr;
    			
    	}
    }*/
    public int findIndex(int buildingLeft,List<int[]> re,int StartIndex,int EndIndex){
        int mid;
        while(StartIndex<=EndIndex){
            mid=(StartIndex+EndIndex)/2;
            if(re.get(mid)[0]==buildingLeft){
                return mid;
            }
            else {
                if(re.get(mid)[0]<buildingLeft){
                    StartIndex=mid+1;
                }
                else{
                    EndIndex=mid-1;
                }
            }
        }
        return StartIndex-1;
    }

    public int update(List<int[]> re,int index,long buildingLeft,int buildingRight,int buildingHeight){

        int newStart=index;

        for(int i=index;i<re.size();i++){
            if(i>0&&re.get(i)[1]==re.get(i-1)[1]){
                re.remove(i);
                i--;
                continue;
            }
            long thisEnd=(i==re.size()-1)?Long.MAX_VALUE:re.get(i+1)[0];
            int thisHeight=re.get(i)[1];
            if(buildingLeft>re.get(i)[0]){
                if(buildingHeight>re.get(i)[1]){
                    int[] temp=new int[2];
                    temp[0]=(int)buildingLeft;
                    temp[1]=buildingHeight;
                    re.add(i+1,temp);
                    newStart=i+1;
                    i++;
                }
            }
            else{
                if(buildingHeight>re.get(i)[1]){
                    re.get(i)[1]=buildingHeight;
                    if(i>0&&re.get(i-1)[1]==buildingHeight){
                        re.remove(i);
                    if(newStart==i)
                        newStart--;
                    i--;
                    }
                }
            }

            if(buildingRight<thisEnd){
                if(buildingHeight>thisHeight){
                int[] temp=new int[2];
                temp[0]=buildingRight;
                temp[1]=thisHeight;
                re.add(i+1,temp);
                i++;
                }
                break;
            }
            else{
                if(buildingRight==thisEnd){
                    break;
                }
                else{
                    buildingLeft=thisEnd;
                }
            }
        }
        return newStart;
    }


    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> re=new ArrayList<>();
        if(buildings.length==0)
            return re;
        int[] temp=new int[2];
        temp[0]=Integer.MIN_VALUE;
        temp[1]=0;
        re.add(temp);
        int StartIndex=0;
        for(int i=0;i<buildings.length;i++){
            int buildingLeft=buildings[i][0];
            int buildingRight=buildings[i][1];
            int buildingHeight=buildings[i][2];
            int index=findIndex(buildingLeft,re,StartIndex,re.size()-1);
            StartIndex=update(re,index,buildingLeft,buildingRight,buildingHeight);
        }
        re.remove(0);
        return re;
    }
    //233. Number of Digit One
    public static int countDigitOne(int n) {
        if (n <= 0) {
            return 0;
        }
        int count = 0;
        int copyN = n;
        for (int weight = 1; n > 0; weight *= 10) {
            int complete = n / 10;
            int lsb = n % 10;
            if (lsb < 1) {
                count += weight * complete;
            } else if (lsb > 1) {
                count += weight * (complete + 1);
            } else {  
                count += weight * complete + copyN % weight + 1;
            }
            n = complete;
        }
        return count;
    }
    //292. Nim Game
    public boolean canWinNim(int n) {
        if(n%4==0)return false;
        else return true;
    }
    //107. Binary Tree Level Order Traversal II
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        Stack<List<Integer>> s = new Stack<List<Integer>>();
        if(root == null) return s;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while(!q.isEmpty()){
        	List<Integer> t = new ArrayList<Integer>();
        	List<TreeNode> tn = new ArrayList<TreeNode>();
        	while(!q.isEmpty())
        		tn.add(q.poll());
        	for(int i=0;i<tn.size();i++){
        		TreeNode tmp = tn.get(i);
        		t.add(tmp.val);
        		if(tmp.left!=null) q.add(tmp.left);
        		if(tmp.right!=null) q.add(tmp.right);
        	}
        	s.push(t);
        }
        /*List<List<Integer>> sol = new ArrayList<List<Integer>>();
        for(int i=s.size()-1;i>=0;i--){
        	sol.add(s.get(i));
        }*/
        
        return s;
    }
     //241. Different Ways to Add Parentheses
    static Map<String, List<Integer>> map = new HashMap<>();
    public static List<Integer> diffWaysToCompute(String input) {
    	if(map.containsKey(input))
            return map.get(input);
    	List<Integer> s = new ArrayList<Integer>();
    	for(int i=0;i<input.length();i++){
    		char t = input.charAt(i);
    		if(t=='+'||t=='-'||t=='*'){
    			String a = input.substring(0, i);
    			String b = input.substring(i+1);
    			List<Integer> t1 = diffWaysToCompute(a);
    			List<Integer> t2 = diffWaysToCompute(b);
    			for(int ta:t1)
    				for(int tb:t2){
    					if(t=='+')
    						s.add(ta+tb);
    					else if(t=='-')
    						s.add(ta-tb);
    					else if(t=='*')
    						s.add(ta*tb);
    					
    				}
    		}
    	}
    	
    	if(s.size()==0)  s.add(Integer.valueOf(input));
    	map.put(input, s);
        return s;

    }
    
    public static class Node{
    	public int x;
    	public int y;
    	public Node(int x,int y){
    		this.x = x;
    		this.y = y;
    	}
    }
    
    //240. Search a 2D Matrix II
    public static  boolean searchMatrix_240(int[][] matrix, int target) {
    	int x = matrix.length;
    	if(x==0) return false;
    	int y = matrix[0].length;
    	if(y==0) return false;
    	
    	return search_240(0, x-1, 0, y-1, matrix, target);
    }
    
    public static boolean search_240(int x1,int x2,int y1,int y2,int[][]matrix,int target){
    	if(x1>x2||y1>y2) return false;
    	if(target<matrix[x1][y1]||target>matrix[x2][y2]) return false;
    	
    	int x = x1+((x2-x1)>>>1);
    	int y = y1+((y2-y1)>>>1);
    	int t = matrix[x][y];
    	if(t==target) return true;
    	else if(t<target){

    		return  search_240(x1,x,y+1,y2,matrix,target)||search_240(x+1, x2, y1, y2, matrix, target);
    	}
    	else  return search_240(x1,x-1,y1,y2,matrix,target)||search_240(x1, x2, y1, y-1, matrix, target);
    	
    }
    
    //240-2
    public boolean searchMatrix2(int[][] matrix, int target) {
        int line=0;
        int len=matrix.length;
        int col=matrix[0].length;
        while (line<len && col>0){
            int num= matrix[line][col-1];
            if (num>target) col--;
            if (num<target) line++;
            if (num==target) return true;}
        
        return false;
    }
    
    public static int binarysearch_(int []a,int start,int end,int key){
    	int from = start;
    	int to = end;
    	while(from<to){
    		int mid = (from+to)>>>1;
            @SuppressWarnings("rawtypes")
			Comparable c = (Comparable)a[mid];
            @SuppressWarnings("unchecked")
			int p = c.compareTo(key);
            if(p>key)
            	to = mid-1;
            else if(p<key)
            	from = mid +1;
            else 
            	return mid;
    	}
    	return -1;
    }
    
    public static int countTheTriangle(int n){
    	if(n==0) return 0;
    	int sum=1;
    	if(n>=2){
    		sum+=n*n;
    		for(int i=n-1;i>1;i--){
    			sum+=(1+i)*i/2;
    		}
    		if(n>=4){
    			for(int i=n-3;i>0;i-=2){
    				sum+=(1+i)*i/2;
    			}
    		}
    	}
    	return sum;
    }
    
    //9. Palindrome Number
    public static boolean isPalindrome(int x) {
    	if(x<0) return false;
    	int t = 0;
    	int r = x;
    	while(r>0){
    		t = t*10+r%10;
    		r/=10;
    	}
    	if(t==x) return true;
        return false;
    }
    //239. Sliding Window Maximum
    public static int[] maxSlidingWindow(int[] nums, int k) {
    	if(nums.length==0 ||nums.length<k) return null;
    	int [] sol = new int[nums.length-k+1];
    	int max = nums[0];
    	int index=0;
    	int maxindex = 0;
    	for(int i=0;i<k;i++){
    		if(nums[i]>max){
    			max = nums[i];
    			maxindex = i;
    		}
    	}
    	sol[index++] = max;
    	for(int i=k;i<nums.length;i++){
    		if(nums[i]>max){
    			max = nums[i];
    			maxindex = i;
    		}
    		else if(i-k>=maxindex){
    			int maxt = nums[i-k+1];
    			int maxindext = i-k+1;
    			for(int j=i-k+1;j<=i;j++){
    				if(nums[j]>maxt){
    					maxt = nums[j];
    					maxindext = j;
    				}
    			}
    			max = maxt;
    			maxindex = maxindext;
    		}
    		
    		sol[index++] = max;
    	}
        return sol;
    }
    //88. Merge Sorted Array
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
    	int i = m - 1, j = n - 1, p = m + n - 1;
        while(i >= 0 && j >= 0) nums1[p --] = nums1[i] > nums2[j] ? nums1[i --] : nums2[j --];
        while(j >= 0) nums1[p --] = nums2[j --]; 
    }
    //322. Coin Change
    public static int coinChange(int[] coins, int amount) {
        if(coins.length==0) return -1;
        int [] dp = new int[amount+1];
        int sum = 0;
        Arrays.sort(coins);
        while(++sum<=amount){
        	int min = -1;
        	for(int coin:coins){
        		if(sum<coin)
        			break;
        		if(dp[sum-coin]!=-1){
        			int tmp = dp[sum-coin]+1;
        			min = min<0?tmp:tmp<min?tmp:min;
        		}
        	}
        	dp[sum] = min;
        }
        return dp[amount];
    }
    //322-2
    private static int currmax ;
    public static int coinChange2(int[] coins, int amount){
    	if (amount == 0) {
            return 0;
        }

        if (coins == null || coins.length == 0) {
            return -1;
        }
        currmax = Integer.MAX_VALUE;
        Arrays.sort(coins);
        dpAndcut(coins, coins.length-1, amount, 0);
        if(currmax==Integer.MAX_VALUE) return -1;
        return currmax;
    }
    public static void dpAndcut(int[]coins,int curindex,int left,int curr){
    	if(curindex<0 || curr+left/coins[curindex] >= currmax) return ;
    	if(left==coins[curindex]){
    		currmax = curr+1;
    		return;
    	}
    	if(left > coins[curindex]){
    		dpAndcut(coins, curindex, left-coins[curindex], curr+1);
    	}
    	dpAndcut(coins, curindex-1, left, curr);
    }
    
    //49. Group Anagrams
    public static List<List<String>> groupAnagrams(String[] strs) {
    	List<List<String>> s = new ArrayList<List<String>>();
    	if(strs.length==0) return s;
    	HashMap<String,List<String>> map = new HashMap<String,List<String>>();
    	for(int i=0;i<strs.length;i++){
    		String tmp = strs[i];
    		char[] chartmp = tmp.toCharArray();
    		Arrays.sort(chartmp);
    		tmp = String.valueOf(chartmp);
    		List<String> list = null;
    		if(map.containsKey(tmp)){
    			list = map.get(tmp);
    		}
    		else list = new ArrayList<String>();
    		list.add(strs[i]);
    		map.put(tmp, list);
    	}
    	for(List<String> list:map.values()){
    		Collections.sort(list);
    		s.add(list);
    	}
    	
        return s;
    }
    
    //49-2
    public static List<List<String>> groupAnagrams2(String[] strs) {
    	int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};
    	List<List<String>> sol = new ArrayList<>();
    	HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
    	for(String str:strs){
    		int key = 1;
    		for(char c :str.toCharArray()){
    			key*=prime[c-'a'];
    		}
    		List<String> list = null;
    		if(map.containsKey(key)){
    			list = sol.get(map.get(key));
    		}
    		else {
    			list = new ArrayList<String>();
    			sol.add(list);
    			map.put(key, sol.size()-1);
    		}
    		list.add(str);
    	}
    	return sol;
    }
    
    //41. First Missing Positive
    public static int firstMissingPositive(int[] nums) {
        if(nums.length==0) return 0;
        int max = Integer.MIN_VALUE;
        for(int n:nums){
        	max=Math.max(max, n);
        }
        byte [] num = new byte[max+1];
        for(int n:nums){
        	if(n<0) continue;
        	num[n] = 1;
        }
        for(int i=1;i<max;i++){
        	if(num[i]==0)
        		return i;
        }
        return max+1;
    }
    //1. Two Sum
    public static int[] twoSum(int[] nums, int target) {
        if(nums.length==0) return new int[0];
        int []unsorted = new int[nums.length];
        for(int i=0;i<nums.length;i++)
        	unsorted[i] = nums[i];
        Arrays.sort(nums);
        int low = 0;
        int high = nums.length-1;
        while(low<high && low<nums.length && high>=0){
        	if(nums[low]+nums[high]>target)
        		high--;
        	else if(nums[low]+nums[high]<target)
        		low++;
        	else {
        		int[] n = {-1,-1};
        		
        		for(int i=0;i<unsorted.length;i++){
        			if(unsorted[i]==nums[low] && n[0]==-1){ n[0] = i;unsorted[i]=Integer.MIN_VALUE;}
        			if(unsorted[i]==nums[high] && n[1]==-1) { n[1] = i;unsorted[i]=Integer.MIN_VALUE;}
        			if(n[0]!=-1 &&n[1]!=-1) break;
        		}
        		 Arrays.sort(n);
        		 return n;
        	}
        }
        return new int[0];
    }
    //206. Reverse Linked List
    public static ListNode reverseList(ListNode head) {
        if(head == null) return null;
        if(head.next==null) return head;
        ListNode newList = null;
        while(head.next!=null){
        	ListNode n = head.next;
        	head.next = newList;
        	newList = head;
        	head = n;
        }
        head.next = newList;
    	newList = head;
    	return newList;
    }    
    //132. Palindrome Partitioning II
    public static int minCut(String s) {
    	char[] c = s.toCharArray();
        int n = c.length;
        int[] cut = new int[n];
        boolean[][] pal = new boolean[n][n];

        for(int i = 0; i < n; i++) {
            int min = i;
            for(int j = 0; j <= i; j++) {
                if(c[j] == c[i] && (j + 1 > i - 1 || pal[j + 1][i - 1])) {
                    pal[j][i] = true;  
                    min = j == 0 ? 0 : Math.min(min, cut[j - 1] + 1);
                }
            }
            cut[i] = min;
        }
        return cut[n - 1];
    }
    //179. Largest Number
    public static PriorityQueue<Integer> max = new PriorityQueue<Integer>(111,new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			
			if(o1.equals(o2)) return 0;
			String a1 = String.valueOf(o1);
			String a2 = String.valueOf(o2);
			
			int i = 0;
			while(i<a1.length() && i<a2.length() && a1.charAt(i)==a2.charAt(i)  )
				i++;
			if(i==a1.length() && i!=a2.length()) return -1;
			else if(i==a2.length() && i!=a1.length()) return 1;
			else return a2.charAt(i)-a1.charAt(i);
			
		}
		
	});
    public static String largestNumber(int[] nums) {
    	String [] strs = new String[nums.length];
    	for(int i=0;i<nums.length;i++)
    		strs[i] = String.valueOf(nums[i]);
    	//Arrays.sort(strs,(str1,str2)->(str1+str2).compareTo(str2+str1));
    	 if(strs[0].equals("0")) return "0";
    	StringBuffer sb = new StringBuffer();
    	for(String st:strs)
    		sb.append(st);
        return sb.toString();
        
    }
    //336. Palindrome Pairs
    public static boolean isPalindrome_336(String s){
    	int i=0;
    	int j=s.length()-1;
    	while(i<=j){
    		if(s.charAt(i)!=s.charAt(j))
    			return false;
    		i++;
    		j--;
    	}
    	return true;
    }
    
    public static String reversestr(String str){
    	StringBuffer sb = new StringBuffer(str);
    	return sb.reverse().toString();
    }
    
    public static List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> sol = new ArrayList<List<Integer>>();
        if(words==null || words.length==0)
        	return sol;
        HashMap<String,Integer> map = new HashMap<>();
        for(int i=0;i<words.length;i++)
        	map.put(words[i], i);
        if(map.containsKey("")){
        	int blank = map.get("");
        	for(int i=0;i<words.length;i++){
        		if(isPalindrome_336(words[i])){
        			if(i==blank) continue;
        			sol.add(Arrays.asList(blank,i));
        			sol.add(Arrays.asList(i,blank));
        		}
        	}
        }
        for(int i=0;i<words.length;i++){
        	String curr = reversestr(words[i]);
        	if(map.containsKey(curr)){
        		int tmp = map.get(curr);
        		if(tmp==i) continue;
        		sol.add(Arrays.asList(i,tmp));
        	}
        }
        for(int i=0;i<words.length;i++){
        	String curr = words[i];
        	for(int b = 1;b<curr.length();b++){
        		if(isPalindrome_336(curr.substring(0, b))){
        			String cutb = reversestr(curr.substring(b));
        			if(map.containsKey(cutb)){
        				int found = map.get(cutb);
        				if(found == i) continue;
        				sol.add(Arrays.asList(found,i));
        			}
        		}
        		if(isPalindrome_336(curr.substring(b))){
        			String cutb = reversestr(curr.substring(0, b));
        			if(map.containsKey(cutb)){
        				int found = map.get(cutb);
        				if(found==i) continue;
        				sol.add(Arrays.asList(i,found));
        			}
        		}
        	}
        }
        return sol;
    }
    //3. Longest Substring Without Repeating Characters
    public static int lengthOfLongestSubstring(String s) {
    	char[] str = s.toCharArray();
    	HashMap<Character,Integer> map = new HashMap<>();
    	int sum = 0;
    	int tsum=0;
    	for(int i=0;i<str.length;i++){
    		if(!map.containsKey(str[i])){
    			map.put(str[i],i);
    			tsum++;
    		}
    		else if(map.containsKey(str[i])){
    			if(tsum>sum)
    				sum = tsum;
    			i = map.get(str[i]);
    			tsum = 0;
    			map.clear();
    		}
    	}
    	if(tsum>sum)
    		sum = tsum;
        return sum;
    } 
    
    //3-2
    public static int lengthOfLongestSubstring2(String s) {
    	int max = 0;
    	Deque<Character> dq = new ArrayDeque<Character>();
    	char[]str = s.toCharArray();
    	for(int i=0;i<str.length;i++){
    		if(dq.contains(str[i])){
    			if(dq.size()>max)
    				max = dq.size();
    			while(dq.peekFirst()!=str[i])
    				dq.removeFirst();
    			dq.removeFirst();
    			dq.addLast(str[i]);
    		}
    		else {
    			dq.addLast(str[i]);
    		}
    	}
    	if(dq.size()>max)
    		max = dq.size();
    	return max;
    }
    //3-3
    public static int lengthOfLongestSubstring3(String s) {
	    int[] map = new int[128];
	    int max = 0, j = 0;
	    char[] str = s.toCharArray();
	    int length = s.length();
	
	    for(int i = 0; i < length; i++) {
	        if(map[str[i]] > 0)
	            j =  Math.max(j, map[str[i]]);
	        map[str[i]] = i + 1;
	        max = Math.max(max, i - j + 1);
	    }
	    return max;
    }
    //83. Remove Duplicates from Sorted List
    public static ListNode deleteDuplicates(ListNode head) {
        if(head==null) return null;
        if(head.next==null) return head;
        ListNode t = head;
        while(head.next!=null){
        	ListNode next = head.next;
        	if(head.val==next.val){
        		head.next=next.next;
        	}
        	else head = head.next;
        }
        return t;
    }
    //38. Count and Say
    public static String countAndSay(int n) {
        
        StringBuffer sb = new StringBuffer("1");
        for(int j=1;j<n;j++){
        	StringBuffer tb = new StringBuffer();
        	int curr = sb.charAt(0);
        	int length = 1;
	        for(int i=1;i<sb.length();i++){
	        	if(curr!=sb.charAt(i)){
	        		sb.append((char)length+curr);
	        	}
	        	else 
	        		length++;
	        }
	        sb = tb;
        }
        return sb.toString();
    }
    //45. Jump Game II
    public static int jump(int[] nums) {
       if(nums.length==1)
    	   return 0;
       int jump = 1;
       int target = nums.length-1;
       int max = nums[0];
       if(max>=target)
    	   return jump;
       int pre = 0;
       int next;
       for(int i=1;i<nums.length;i++){
    	   next = i+nums[i];
    	   if(next>max){
    		   if(i>pre){
    			   jump++;
    			   pre = max;
    		   }
    		   max = next;
    		   if(max>=target){
    			   return jump;
    		   }
    	   }
       }
       return jump;
    }
    //338. Counting Bits
    public static int[] countBits(int num) {
    	int s[] = new int[num+1];
    	int curr = 2;
        for(int i=1;i<=num;i++){
        	if(i>=curr){
        		curr=curr<<1;
        	}
        	if(i%2==1){
        		s[i]=s[i/2]+1;
        	}
        	else {
        		s[i]=s[i/2];
        	}
        }
        return s;
    }
    //126. Word Ladder II
    public static List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
    	List<List<String>> s = new ArrayList<List<String>>();
    	List<String> curr = new ArrayList<String>();
    	curr.add(beginWord);
    	Set<String> visited = new HashSet<String>();
        bfs(beginWord, endWord, curr, s, wordList,visited);
        return s;
    }
    
    public static void bfs(String begin,String end,List<String> curr,List<List<String>> sol,Set<String> list,Set<String> visited){
    	if(onlyOneChange(begin, end)){
    		curr.add(end);
    		if(sol.size()!=0){
	    		if(sol.get(0).size()==curr.size()){
		    		sol.add(new ArrayList<String>(curr));
	    		}
	    		else if(sol.get(0).size()>curr.size()){
	    			for(int i=0;i<sol.size();i++)
	    				sol.remove(i);
	    			sol.add(new ArrayList<String>(curr));
	    		}
    		}
    		else {
    			sol.add(new ArrayList<String>(curr));
    		}
    		curr.remove(curr.size()-1);
    		return ;
    	}
    	for(String curstr:list){
    		if(!visited.contains(curstr) && onlyOneChange(begin, curstr)){
    			visited.add(curstr);
    			curr.add(curstr);
    			bfs(curstr, end, curr, sol,list,visited);
    			curr.remove(curr.size()-1);
    			visited.remove(curstr);
    		}
    	}
    }
    
    public static boolean onlyOneChange(String s1,String s2){
    	char[] c1 = s1.toCharArray();
    	char[] c2 = s2.toCharArray();
    	int dif = 0;
    	for(int i=0;i<s1.length();i++){
    		if(c1[i]!=c2[i]){
    			dif++;
    		}
    		if(dif>1)
    			return false;
    	}
    	if(dif==1)
    		return true;
    	else return false;
    }
    //97. Interleaving String
    public static boolean isInterleave(String s1, String s2, String s3) {
        int l1 = s1.length();
        int l2 = s2.length();
        if(l1+l2!=s3.length())
        	return false;
        boolean [][]map = new boolean [l1+1][l2+1];
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] str3 = s3.toCharArray();
        for(int i=0;i<l1+1;i++){
        	for(int j=0;j<l2+1;j++){
        		if(i==0&&j==0)
        			map[i][j] = true;
        		else if(i==0)
        			map[i][j] = (map[i][j-1] && (str2[j-1]==str3[i+j-1]));
        		else if(j==0)
        			map[i][j] = (map[i-1][j] && (str1[i-1]==str3[i+j-1]));
        		else 
        			map[i][j] = ((map[i][j-1] && (str2[j-1]==str3[i+j-1]) )||(map[i-1][j] && (str1[i-1]==str3[i+j-1])));
        	}
        }
        return map[l1][l2];
    }
    //97-2
    public boolean isInterleave2(String s1, String s2, String s3) {
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        if (l1 + l2 != l3) {
            return false;
        }
        if (l1 > l2) return isInterleave(s2, s1, s3);
        boolean[] dp = new boolean[l1 + 1];
        char[] v1 = s1.toCharArray();
        char[] v2 = s2.toCharArray();
        char[] v3 = s3.toCharArray();
        dp[0] = true;
        for (int j = 0; j < l1; j++) {
            dp[j + 1] = dp[j] && v1[j] == v3[j];
        }
        for (int i = 0; i < l2; i++) {
            dp[0] = dp[0] && v2[i] == v3[i];// initialize the first column in each row
            for (int j = 0; j < l1; j++) {
                int index = i + j + 1;
                dp[j + 1] = (dp[j] && v1[j] == v3[index]) || (dp[j + 1] && v2[i] == v3[index]);
            }
        }
        return dp[l1];
    }
    //205. Isomorphic Strings
    public static boolean isIsomorphic(String s, String t) {
    	if(s.length()!=t.length()) return false;
    	if(s.length()==0 || t.length()==0){
    		if(s.length()==0 && t.length()==0) return true;
    		else return false;
    	}
    	HashMap<Character,Integer> map1 = new HashMap<Character,Integer>();
    	HashMap<Character,Integer> map2 = new HashMap<Character,Integer>();
    	char[]s1 = s.toCharArray();
    	char[]s2 = t.toCharArray();
    	for(int i=0;i<s.length();i++){
    		int q ;
    		int p ;
    		if(!map1.containsKey(s1[i])){
    			map1.put(s1[i], map1.size());
    		}
    		if(!map2.containsKey(s2[i])){
    			map2.put(s2[i], map2.size());
    		}
    		q=map1.get(s1[i]);
    		p=map2.get(s2[i]);
    		if(q!=p)
    			return false;
    	}
    	return true;
    }
    //205-2
    public static boolean isIsomorphic2(String s, String t) {
    	int m = s.length();
        if (m <= 1) {
            return true;
        }
        int[] sToT = new int[256];
        int[] tToS = new int[256];
        char[] sValue = s.toCharArray();
        char[] tValue = t.toCharArray();
        for (int i = 0; i < m; i++) {
            if (sToT[sValue[i]] == 0 && tToS[tValue[i]] == 0) {
                sToT[sValue[i]] = tValue[i];
                tToS[tValue[i]] = sValue[i];
            } else if (sToT[sValue[i]] != tValue[i] || tToS[tValue[i]] != sValue[i]) {
                return false;
            } 
        }
        return true;
    }
    //126. Word Ladder II
    public static List<List<String>> findLadders3(String beginWord, String endWord, Set<String> wordList) {
        Set<String> fwd = new HashSet<String>();
        fwd.add(beginWord);
        Set<String> bwd = new HashSet<String>();
        bwd.add(endWord);
        Map<String,List<String>> hs = new HashMap<String,List<String>>();
        bfs(fwd, bwd, wordList, false, hs);
        List<List<String>> result = new ArrayList<List<String>>();
        if(!isConnected) return result;
        List<String> tmp = new ArrayList<String>();
        tmp.add(beginWord);
        dfs_126(result, tmp, beginWord, endWord, hs);
        return result;
    }   
    static boolean isConnected = false;
    public static void bfs(Set<String> forward,Set<String> backward,Set<String> dict,boolean swap,Map<String,List<String>> hs){
    	if(forward.isEmpty() || backward.isEmpty()){
    		return ;
    	}
    	
    	if(forward.size()>backward.size()){
    		bfs(backward, forward, dict, !swap, hs);
    		return ;
    	}
    	
    	dict.removeAll(forward);
    	dict.removeAll(backward);
    	Set<String> set3 = new HashSet<String>();
    	
    	for(String str:forward){
    		for(int i=0;i<str.length();i++){
    			char[] ary = str.toCharArray();
    			for(char j='a';j<='z';j++){
    				ary[i] = j;
    				String tmp = new String(ary);
    				if(!backward.contains(tmp) && !dict.contains(tmp)){
    					continue;
    				}
    				
    				String key = !swap?str:tmp;
    				String val = !swap?tmp:str;
    				if(!hs.containsKey(key)) hs.put(key, new ArrayList<String>());
    				if(backward.contains(tmp)){
    					hs.get(key).add(val);
    					isConnected = true;
    				}
    				if(!isConnected && dict.contains(tmp)){
    					hs.get(key).add(val);
    					set3.add(tmp);
    				}
    			}
    		}
    	}
    	if(!isConnected){
    		bfs(set3, backward, dict, swap, hs);
    	}
    }
    
    public static void dfs_126(List<List<String>> result,List<String> tmp,String start,String end,Map<String,List<String>> hs){
    	if(start.equals(end)){
    		result.add(new ArrayList<String>(tmp));
    		return ;
    	}
    	
    	if(!hs.containsKey(start)){
    		return ;
    	}
    	for(String s:hs.get(start)){
    		tmp.add(s);
    		dfs_126(result, tmp, s, end, hs);
    		tmp.remove(tmp.size()-1);
    	}
    }
    //76. Minimum Window Substring
    public static String minWindow(String s, String t) {
    	char[] s_array = s.toCharArray();
        char[] t_array = t.toCharArray();
        int[] map = new int[256];
        int end = 0;
        int start = 0;
        int min_length = Integer.MAX_VALUE;
        for(int i = 0; i < t_array.length; i++)
            map[t_array[i]] ++;
        int count = t_array.length;
        int min_start = 0;
        while(end < s_array.length)
        {
            if(map[s_array[end]] > 0)
            {
                count--;
            }
            map[s_array[end]] --;
            while(count == 0)
            {
                if((end - start + 1) < min_length)
                {
                    min_length = end - start + 1;
                    min_start = start;
                }
                map[s_array[start]] ++;
                if(map[s_array[start]] > 0){
                    count ++;
                }
                start++;
            }
            end ++;

        }
        if( min_start+min_length > s_array.length)
            return "";
        return s.substring(min_start, min_start+min_length);
    }
    //268. Missing Number
    public static int missingNumber(int[] nums) {
    	if(nums.length==0) return 0;
    	if(nums.length==1) {
    		if(nums[0]==1) return 0;
    		if(nums[0]==0) return 1;
    	}
    	Arrays.sort(nums);
        return findmissing(0, nums.length-1, nums);
    }
    
    public static int findmissing(int start,int end,int[] nums){
    	if(start+1==end){
    		if(nums[start]+1==nums[end]){
    			if(end==nums[end])
    				return end+1;
    			else 
    				return nums[start]-1;
    		}
    		else 
    			return start+1;
    	}
    	int index = (start+end+1)/2;
    	if(index!=nums[index]){
    		return findmissing(start, index, nums);
    	}
    	else {
    		return findmissing(index, end, nums);
    	}
    }
    //268-2
    public static int missingNumber2(int[] nums) {
    	int curr = nums.length;
    	for(int i=0;i<nums.length;i++){
    		if(nums[i]!=i){
    			int t = nums[i];
    			while(t!=i){
    				if(t==nums.length){
    					nums[i] = t;
    					curr = i;
    					break;
    				}
    				int tmp = nums[t];
    				nums[t] = t;
    				t = tmp;
    			}
    			
    		}
    	}
    	return curr;
    }
    //268-3
    public int missingNumber3(int[] nums) {
        int sum = 0, i = 0;

        for (; i < nums.length; i++)
            sum = sum + i - nums[i];

        return (sum + i);
    }
    //31. Next Permutation
    public static void nextPermutation(int[] nums) {
        int i = nums.length-2;
        for(;i>=0&& nums[i]>=nums[i+1];i--)
        	;
        if(i>=0){
        	int j = i+1;
        	for(;j<nums.length && nums[i]<nums[j];j++)
        		;
        	swap(nums,i,j-1);
        }
        i++;
        int k = nums.length-1;
        for(;i<k;i++,k--)
        	swap(nums,i,k);
    }
    
    public static void swap(int [] nums,int i,int j){
    	int t = nums[i];
    	nums[i] = nums[j];
    	nums[j] = t;
    }
    //224. Basic Calculator
    public static int calculate_224(String s) {
        int []sol = new int[2];
        sol = cal(s.toCharArray(), 0);
        return sol[0];
    }
    
    public static int[] cal(char[] s,int start){
    	int [] tmp = new int[2];
    	int i = start;
    	int sum = 0;
    	int sign = 1;
    	while(i<s.length){
    		if(s[i]-'0'>=0 && s[i]-'0'<=9){
    			int curr = 0;
    			while(i<s.length && s[i]-'0'>=0 && s[i]-'0'<=9){
    				curr =curr*10 + s[i]-'0';
    				i++;
    			}
    			sum += sign*curr;
    			continue;
    		}
    		if(s[i]==' '){
    			i++;
    			continue;
    		}
    		if(s[i]=='+'){
    			i++;
    			sign = 1;
    			continue;
    		}
    		if(s[i]=='-'){
    			i++;
    			sign = -1;
    			continue;
    		}
    		if(s[i]=='('){
    			int[] t = new int[2];
    			t = cal(s, i+1);
    			sum += sign * t[0];
    			i = t[1]+1;
    			continue;
    		}
    		if(s[i]==')'){
    			int []t = new int[2];
    			t[0] = sum;
    			t[1] = i;
    			return t;
    		}
    			
    	}
    	tmp[0]=sum;
    	tmp[1]=i;
    	return tmp;
    }
    //227. Basic Calculator II
    public static int calculate_227(String s) {
        int pre = 0 ,curr = 0,sign = 1,num = 0;
        char op = '+';
        for(int i=0;i<s.length();i++){
        	char c = s.charAt(i);
        	if(Character.isDigit(c)){
        		num = num*10+c-'0';
        		if(i==s.length()-1 || !Character.isDigit(s.charAt(i+1))){
        			switch(op){
        			case '+':curr = num;break;
        			case '-':curr = num;break;
        			case '*':curr = curr * num;break;
        			case '/':curr = curr / num;break;
        			}
        		}
        	}
        	else if(c=='+'||c=='-'||c=='*'||c=='/'){
        		op = c;
        		num = 0;
        		if(c=='+'||c=='-'){
        			pre +=sign*curr;
        			sign = c=='+'?1:-1;
        		}
        	}
        }
        return pre + sign*curr;
    }
    //95. Unique Binary Search Trees II
    public static List<TreeNode> generateTrees(int n) {
    	if(n==0) return new ArrayList<TreeNode>();
        return genTree(1, n);
    }
    
    public static List<TreeNode> genTree(int start,int end){
    	List<TreeNode> list = new ArrayList<TreeNode>();
    	if(start>end){
    		list.add(null);
    		return list;
    	}
    	if(start == end){
    		list.add(new TreeNode(start));
    		return list;
    	}
    	List<TreeNode> left,right;
    	for(int i=start;i<=end;i++){
    		left = genTree(start, i-1);
    		right = genTree(i+1, end);
    		for(TreeNode l:left){
    			for(TreeNode r:right){
    				TreeNode root = new TreeNode(i);
    				root.left = l;
    				root.right = r;
    				list.add(root);
    			}
    		}
    	}
    	return list;
    }
    //162. Find Peak Element
    public static int findPeakElement(int[] nums) {
        int length = nums.length;
        if(length==0) return -1;
        int l = 0;
        int r = length-1;
        while(l<=r){
        	if(l==r)
        		return l;
        	int mid = (l+r)/2;
        	if(nums[mid]<nums[mid+1])
        		l = mid+1;
        	else r = mid;
        }
        return -1;

    }
    //300. Longest Increasing Subsequence
    public static int lengthOfLIS(int[] nums) {
        int length = nums.length;
        if(length==0) return 0;
        int []dp = new int[nums.length+1];
        dp[0] = 1;
        int max = 1;
        for(int i=1;i<nums.length;i++){
        	dp[i]=1;
        	for(int j = i-1;j>=0;j--){
        		if(nums[i]>nums[j]){
        			dp[i] = Math.max(dp[i], dp[j]+1);
        			if(max<dp[i])
        				max = dp[i];
        		}
        	}
        }
        return max;
    }
    //300-2
    public static int lengthOfLIS2(int[] nums) {
    	if(nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int len = 0;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > dp[len]) {
                dp[++len] = nums[i];
            }
            else {
                int index = search(dp, len, nums[i]);
                dp[index] = nums[i];
            }
        }
        return len + 1;
    }
    private static int search(int[] dp, int len, int val) {
        int start = 0;
        while(start <= len) {
            int mid = start + (len - start) / 2;
            if(dp[mid] == val) {
                return mid;
            }
            else if(dp[mid] < val) {
                start = mid + 1;
            }
            else {
                len = mid - 1;
            }
        }
        return start;
    }
    //103. Binary Tree Zigzag Level Order Traversal
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    	List<List<Integer>> sol = new ArrayList<List<Integer>>();
        if(root == null)
        	return sol;
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        int index = 1;
        while(!s.empty()){
        	List<TreeNode> list = new ArrayList<TreeNode>();
        	List<Integer> tmp = new ArrayList<Integer>();
        	while(!s.isEmpty()){
        		TreeNode tn = s.pop();
        		list.add(tn);
        		tmp.add(tn.val);
        	}
        	for(TreeNode t:list){
        		if(index%2==1){
        			if(t.left!=null) s.push(t.left);
        			if(t.right!=null) s.push(t.right);
        		}
        		else {
        			if(t.right!=null) s.push(t.right);
        			if(t.left!=null) s.push(t.left);
        		}
        	}
        	index++;
        	sol.add(tmp);
        }
        return sol;
    }
    //133. Clone Graph
    class UndirectedGraphNode {
    	 int label;
    	 List<UndirectedGraphNode> neighbors;
    	 UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
    };  
    HashMap<Integer,UndirectedGraphNode> map_133 = new HashMap<Integer,UndirectedGraphNode>(); 
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    	if(node == null) return null;
    	UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
    	map_133.put(node.label, clone);
    	for(UndirectedGraphNode t:node.neighbors){
    		if(!map_133.containsKey(t.label)){
    			clone.neighbors.add(cloneGraph(t));
    		}
    		else {
    			clone.neighbors.add(map_133.get(t.label));
    		}
    	}
        return clone;
    }
    //221. Maximal Square
    public static int maximalSquare(char[][] matrix) {
        if(matrix.length==0||matrix[0].length==0) return 0;
        int [][] dp = new int [matrix.length][matrix[0].length];
        int max = 0;
        for(int i=0;i<matrix.length;i++){
        	for(int j=0;j<matrix[0].length;j++){
        		if(matrix[i][j]=='1'){
        			if(i-1>=0 && j-1>=0){
        				int t = dp[i-1][j-1];
        				boolean flag = true;
        				int curr = 1;
        				for(int k = 1;k<=t;k++){
        					flag = (matrix[i-k][j]=='1')&&(matrix[i][j-k]=='1');
        					if(!flag)
        						break;
        					else 
        						curr++;
        				}
        				if(flag){
        					dp[i][j] = dp[i-1][j-1]+1;
        					max = max<dp[i][j]?dp[i][j]:max;
        				}
        				else {
        					dp[i][j] = curr;
        					max = max<dp[i][j]?dp[i][j]:max;
        				}
        					
        			}
        			else{ 
        				dp[i][j] = 1;
        				max = max<dp[i][j]?dp[i][j]:max;
        			}
        		}
        	}
        }
        return (int) Math.pow(max, 2);
    }
    //80. Remove Duplicates from Sorted Array II
    public static int removeDuplicates2(int[] nums) {
    	if(nums.length==0) return 0;
    	if(nums.length==1) return 1;
    	int length = 1;
    	int j = 1;
    	while(j<nums.length){
    		if(nums[j]==nums[length-1]){
    			if(length-1<=0)
    				length++;
    			else if(nums[length-1]==nums[length-2]){
    				
    			}
    			else {
    				nums[length] = nums[j];
    				length++;
    			}
    		}
    		else{
    			nums[length] = nums[j];
				length++;
    		}
    		j++;
    	}

        return length;
    }
  //166. Fraction to Recurring Decimal
    public static String fractionToDecimal(int numerator, int denominator) {
        if(denominator==0) return "";
        if(numerator==0) return "0";
        StringBuffer sb = new StringBuffer();
        Long n = new Long(numerator);
        Long d = new Long(denominator);
        if(n*d<0) sb.append("-");

        n = Math.abs(n);
        d = Math.abs(d);
        sb.append(Long.toString(n/d));
        Long rest = n%d;
        if(rest == 0) return sb.toString();
        else sb.append(".");
        HashMap<Long,Integer> map = new HashMap<Long,Integer>();
        while(rest>0){
        	if(map.containsKey(rest)){
        		sb.insert(map.get(rest),"(");
        		sb.append(")");
        		break;
        	}
        	map.put(rest,sb.length() );
        	rest*=10;
        	sb.append(Long.toString(rest/d));
        	rest%=d;
        }
        return sb.toString();
    }
    //131. Palindrome Partitioning
    public static List<List<String>> partition(String s) {
    	char[] c = s.toCharArray();
    	int length = s.length();
    	boolean [][] dp = new boolean[length][length];
    	for(int i=0;i<length;i++)
    		for(int j=0;j<length-i;j++)
    			dp[j][j+i] = c[j]==c[j+i] && (i<=1 || dp[j+1][j+i-1]);
    	List<String> t = new ArrayList<String>();
    	List<List<String>> sol = new ArrayList<List<String>>();
    	dfs_131(0, s, dp, t, sol);
        return sol;
    }
    
    private static void dfs_131(int curr,String s,boolean[][] dp,List<String> path,List<List<String>> sol){
    	if(curr >=s.length()){
    		sol.add(new ArrayList<String>(path));
    		return ;
    	}
    	for(int i=curr;i<s.length();i++){
    		if(dp[curr][i]){
    			path.add(s.substring(curr, i+1));
    			dfs_131(i+1, s, dp, path, sol);
    			path.remove(path.size()-1);
    			
    		}
    	}
    }
    //215. Kth Largest Element in an Array
    public static int findKthLargest(int[] nums, int k) {
        if(nums.length==0) return 0;
        if(nums.length==1 && k==1) return nums[0];
        return findKth(nums, 0, nums.length-1, k);
    }
    
    public static int findKth(int []nums,int start,int end,int k){
    	 if(nums.length==0) return 0;
         if(nums.length==1 && k==1) return nums[0];
         int curr = nums[start];
         int i = start;
         int j = end;
         while(i<j){
        	 while(i<j  && nums[j]>=curr) j--;
        	 while(i<j  && nums[i]<=curr) i++;
        	 
        	 if(i<j){
        		 int t = nums[i];
        		 nums[i] = nums[j];
        		 nums[j] = t;
        	 }
         }
         nums[start] = nums[i];
         nums[i] = curr;
         if(end-i+1==k) return nums[i];
         else if(end-i+1>k) return findKth(nums, i+1, end, k);
         else return findKth(nums, start, i-1, k-(end-j+1));
    }
    /*public void quicksort(int left,int right,int []s){
		if(left<right){
			int i = left;
			int j = right;
			int tmp = s[i];
			while(i<j){
				while(j>i && s[j]>=tmp) j--;
				while(i<j && s[i]<=tmp) i++;
				if(i<j){
					int t = s[i];
					s[i] = s[j];
					s[j] = t;
				}
			}
			s[left] = s[i];
			s[i] = tmp;
			quicksort(left, i-1, s);
			quicksort(i+1, right, s);
		}
	}*/
    public static int findKthLargest2(int[] nums, int k) {

        return select(nums, k-1);
    }

    // Quick select
    private static int select(int[] nums, int k) {
        int left = 0, right = nums.length-1;
        while(true) {
            if(left == right)
                return nums[left];
            int pivotIndex = medianOf3(nums, left, right);
            pivotIndex = partition(nums, left, right, pivotIndex);
            if(pivotIndex == k)
                return nums[k];
            else if(pivotIndex > k)
                right = pivotIndex-1;
            else
                left = pivotIndex+1;
        }
    }

    //Use median-of-three strategy to choose pivot
    private static int medianOf3(int[] nums, int left, int right) {
        int mid = left + (right - left) / 2;
        if(nums[right] > nums[left])
            swap2(nums, left, right);
        if(nums[right] > nums[mid])
            swap2(nums, right, mid);
        if(nums[mid] > nums[left])
            swap2(nums,left, mid);
        return mid;
    }

    private static int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivotValue = nums[pivotIndex];
        swap2(nums, pivotIndex, right);
        int index = left;
        for(int i = left; i < right; ++i) {
            if(nums[i] > pivotValue) {
                swap2(nums, index, i);
                ++index;
            }
        }
        swap2(nums, right, index);
        return index;
    }

    private static void swap2(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
    
    public static void mergesort(int[] nums){
    	int [] k = new int [nums.length];
    	merge_recursive(nums, k, 0, nums.length-1);
    	
    }
    public static void merge_recursive(int[] nums,int[] k,int start,int end ){
    	if(start>=end) return ;
    	int mid = ((end-start)>>1)+start;
    	int start1 = start;
    	int end1 = mid;
    	int start2 = mid+1;
    	int end2 = end;
    	merge_recursive(nums, k, start1, end1);
    	merge_recursive(nums, k, start2, end2);
    	int curr = start;
    	while(start1<=end1 && start2<=end2)
    		k[curr++] = nums[start1]<nums[start2]?nums[start1++]:nums[start2++];
    	while(start1<=end1)
    		k[curr++] = nums[start1++];
    	while(start2<=end2)
    		k[curr++] = nums[start2++];
    	for(int i= start;i<=end;i++)
    		nums[i] = k[i];
    	
    }
    
    public static void mergesort_2(int []arr){
    	int l = arr.length;
    	int [] rst = new int [l];
    	int block,start;
    	for(block=1;block<l;block*=2){
    		for(start = 0;start<l;start+=2*block){
    			int low = start;
    			int mid = (start+block)<l?(start+block):l;
    			int high = (start+2*block)<l?(start+2*block):l;
    			int start1 = low,end1 = mid;
    			int start2 = mid,end2 = high;
    			while(start1<end1 && start2<end2)
    				rst[low++] = arr[start1]<arr[start2]?arr[start1++]:arr[start2++];
    			while(start1<end1)
    				rst[low++] = arr[start1++];
    			while(start2<end2)
    				rst[low++] = arr[start2++];
    		}
    		int [] tmp = arr;
    		arr = rst;
    		rst = tmp;
    	}
    	rst = arr;
    }
    //148. Sort List
    public static ListNode sortList(ListNode head) {
    	if(head == null || head.next == null) return head;
    	ListNode fast = head.next.next;
    	ListNode slow = head;
    	while(fast!=null && fast.next!=null){
    		fast = fast.next.next;
    		slow = slow.next;
    	}
    	ListNode p1 = sortList(slow.next);
    	slow.next = null;
    	ListNode p2 = sortList(head);
        return mergeList(p2, p1);
    }
    public static ListNode mergeList(ListNode head,ListNode mid){
    	ListNode thead = new ListNode(0);
    	ListNode sol = thead;
    	while(head!=null && mid!=null){
    		if(head.val<mid.val){
    			thead.next = head;
    			head = head.next;
    		}
    		else {
    			thead.next = mid;
    			mid = mid.next;
    		}
    		thead = thead.next;
    	}
    	thead.next = head==null?mid:head;
    	return sol.next;
    }
    //214. Shortest Palindrome
    public static String shortestPalindrome(String s) {
        if(s.length()<=1) return s;
        String st = s+" "+new StringBuffer(s).reverse().toString();
        int [] next = new int[st.length()];
        for(int i=1;i<st.length();i++){
        	int curr = next[i-1];
        	while(curr>0 && st.charAt(i)!=st.charAt(curr))
        		curr = next[curr-1];
        	if(st.charAt(i)==st.charAt(curr))
        		next[i] = curr+1;
        }
        return new StringBuffer(s.substring(next[st.length()-1])).reverse().toString() + s;
    }
    //65. Valid Number
    public boolean isNumber(String str) {
        if ( str == null || str.isEmpty() ) return false;

        int start = 0;
        int end  = str.length() - 1;

        while (start < str.length()-1 &&  str.charAt(start) == ' ')
            start ++;

        while (end >= start +1 &&  str.charAt(end) == ' ')
            end --;

        if( (start - end ==0) &&  (str.charAt(start) < '0' || str.charAt(start) > '9') )
            return false;   

        if (str.charAt(start)== '+' || str.charAt(start)== '-') {
            if (str.charAt(start+1) == '.' && (end - start == 1))
                return false;
            start++;
        }

        int point = -1;  
        int e = -1;      

        for (int i = start; i <=end; i++){

            if (str.charAt(i) == '.') {
                if (point == -1) 
                    point = i;  
                else return false;
            }

            if (str.charAt(i) == 'e')  {
                if (e==-1)   
                    e= i;
                else return false;
            }

            if (e == start || e==end) return false; 
            if (point > e && e!= -1 ) return false;
            if (point == start && e== start +1) return false;

            if ( (str.charAt(i) < '0' || str.charAt(i) > '9')  && str.charAt(i) !='.' && str.charAt(i) != 'e' ) {

                if (  (str.charAt(i) == '+' || str.charAt(i) == '-')  && e== i-1 && i!=end ) {  }

                else    return false;
            }
        }
        return true;
    }
    //236. Lowest Common Ancestor of a Binary Tree
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode l = lowestCommonAncestor(root.left, p, q);
        TreeNode r = lowestCommonAncestor(root.right, p, q);
        return l==null?r:r==null?l:root;// that case means that one of the two nodes was found on the left and the other was found on the right. And then the root is their LCA.
    }
    //316. Remove Duplicate Letters
    public static String removeDuplicateLetters(String s) {
        int []letters = new int[26];
        for(int i=0;i<s.length();i++) letters[s.charAt(i)-'a']++;
        int min = 0;
        for(int i=0;i<s.length();i++){
        	if(s.charAt(i)<s.charAt(min)) min = i;
        	if(--letters[s.charAt(i)-'a']==0) break;
        }
        return s.length()==0?"":s.charAt(min)+""+removeDuplicateLetters(new StringBuffer(s.substring(min+1)).toString().replace(""+s.charAt(min), ""));
    }
    //316-2
    public String removeDuplicateLetters2(String s) {
        int len = s.length();
        if (len == 0) return s;
        int[] l = new int[26];
        char[] c = s.toCharArray();
        for (int i = 0; i < len; i++) {
            l[(int)c[i]-'a']++;
        }
        char[] stack = new char[len];
        int top = 0;
        for (int i = 0; i < len; i++) {
            int val = (int)c[i]-'a';
            if (top == 0) {
                stack[top++] = c[i];
                l[val]--;
                l[val] = -l[val];
            } else {
                while (top!=0 && c[i] <= stack[top-1] && l[(int)stack[top-1]-'a'] != 0 && l[val] > 0) {
                    char cc = stack[--top];
                    l[(int)cc-'a'] = -l[(int)cc-'a'];
                }
                if (l[val] < 0) {
                    l[val]++;
                } else {
                    stack[top++] = c[i];
                    l[val]--;
                    l[val] = -l[val];
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < top; i++) {
            sb.append(stack[i]);
        }
        return sb.toString();
    }
    //34. Search for a Range
    public static int searchmin;
    public static int searchmax;
    public static int[] searchRange(int[] nums, int target) {
    	searchmin = -1;
    	searchmax = -1;
    	bs_min(nums, 0, nums.length-1, target);
    	bs_max(nums, 0, nums.length-1, target);
    	int [] sol = new int[2];
    	sol[0] = searchmin;
    	sol[1] = searchmax;
    	return sol;
        
    }
    public static void bs_min(int []nums,int start,int end,int target){
    	if(start>end) return ;
    	if(start==end && searchmin==-1){
    		if(nums[start]!=target) return ;
    		else searchmin = nums[start];
    	}
    	int mid = ((end-start)>>1)+start;
        if(nums[mid]==target){
        	if(mid>0 &&nums[mid-1]<target) searchmin = mid;
        	else if(mid==0) searchmin = 0;
        	else {
        		bs_min(nums, start, mid-1, target);
        		bs_min(nums, mid+1, end, target);
        	}
        }
        else if(nums[mid]>target) bs_min(nums, start, mid-1, target);
        else if(nums[mid]<target) bs_min(nums, mid+1, end, target);
        
    }
    public static void bs_max(int []nums,int start,int end,int target){
    	if(start>end) return ;
    	if(start==end && searchmax==-1){
    		if(nums[start]!=target) return ;
    		else searchmax = nums[start];
    	}
    	int mid = ((end-start)>>1)+start;
        if(nums[mid]==target){
        	if(mid<nums.length-1 && nums[mid+1]>target) searchmax = mid;
        	else if(mid == nums.length-1)  searchmax = mid;
        	else {
        		bs_max(nums, start, mid-1, target);
        		bs_max(nums, mid+1, end, target);
        	}
        }
        else if(nums[mid]>target) bs_max(nums, start, mid-1, target);
        else if(nums[mid]<target) bs_max(nums, mid+1, end, target);
        
    }
    public int[] searchRange2(int[] A, int target) {
        int start = findPosition(A, target, false);
        int end = findPosition(A, target, true);
        return new int[]{start, end};
    }

    private int findPosition(int[] A, int target, boolean isLast) {
        int low = 0, high = A.length-1, index = -1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if(isLast){
                if (A[mid] <= target) low = mid + 1;
                else high = mid-1;
            } else{
                if (A[mid] < target) low = mid + 1;
                else high = mid-1;
            }
            if(A[mid] == target) index = mid; /** update index */
        }
        return index;
    }
    //24. Swap Nodes in Pairs
    public static ListNode swapPairs(ListNode head) {
        if(head == null || head.next==null) return head;
        
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode thead = pre;
        while(head!=null && head.next!=null){
        	ListNode tail = head.next.next;
        	pre.next = head.next;
        	pre.next.next = head;
        	head.next = tail;
        	pre = pre.next.next;
        	head = head.next;
        }
        return thead.next;
    }
    //210. Course Schedule II
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        if(numCourses==0) return new int[0];
        List<List<Integer>> adjs = new ArrayList<>(numCourses);
        int [] d = new int[numCourses];
        int n = numCourses;
        while(n-->0) adjs.add(new ArrayList<>());
        for(int i=0;i<prerequisites.length;i++){
        	d[prerequisites[i][0]]++;
        	 adjs.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        Stack<Integer> s = new Stack();
        for(int i=0;i<numCourses;i++){
        	if(d[i]==0) s.add(i);
        }
        int [] path = new int [numCourses];
        int length = 0;
        while(!s.isEmpty()){
        	int curr=s.pop();
        	path[length++] = curr;
        	for (int to : adjs.get(curr)) {
                d[to]--;
                if (d[to] == 0) s.push(to);
            }
        	
        }
        return length==numCourses?path:new int[0];
    }
    //210-2
    public static int[] findOrder2(int numCourses, int[][] prerequisites) {
    	List[] map = new List[numCourses];
    	int [] visited = new int[numCourses];
    	List<Integer> ans = new ArrayList<Integer>();
    	for(int i=0;i<numCourses;i++)
    		map[i] = new ArrayList<Integer>();
    	for(int i=0;i<prerequisites.length;i++)
    		map[prerequisites[i][0]].add(prerequisites[i][1]);
    	for(int i=0;i<numCourses;i++)
    		if(dfs_210(map, i, ans, visited)==false) return new int[0];
    	int [] an = new int[ans.size()];
    	for(int i=0;i<ans.size();i++)
    		an[i]=ans.get(i);
    	return an;
    }
    
    public static boolean dfs_210(List[] map,int req,List<Integer> ans,int [] visited){
    	if(visited[req]==0){
    		visited[req] = 1;
    		for(int i=0;i<map[req].size();i++)
    			if(dfs_210(map, (int)map[req].get(i), ans, visited)==false)
    				visited[req]=2;
    	}
    	else if(visited[req]==1){
    		return false;
    	}
    	else if(visited[req]==2){
    		return true;
    	}
    	ans.add(req);
    	return true;
    }
    //207. Course Schedule
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if(numCourses==0) return false;
        int [] degree = new int [numCourses];
        List<List<Integer>> map = new ArrayList<List<Integer>>();
        for(int i=0;i<numCourses;i++)
        	map.add(new ArrayList<Integer>());
        for(int i=0;i<prerequisites.length;i++){
        	degree[prerequisites[i][0]]++;
        	map.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        Stack<Integer> s = new Stack<Integer>();
        for(int i=0;i<numCourses;i++){
        	if(degree[i]==0)
        		s.add(i);
        }
        while(!s.isEmpty()){
        	int curr = s.pop();
        	for(int t:map.get(curr)){
        		degree[t]--;
        		if(degree[t]==0)
        			s.add(t);
        	}
        		
        }
        boolean flag = true;
        for(int i=0;i<numCourses;i++)
        	if(degree[i]!=0){
        		flag = false;
        		break;
        	}
        return flag;
    }
    //145. Binary Tree Postorder Traversal
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> s = new ArrayList<Integer>();
        postT(root, s);
        return s;
    }
    public static void postT(TreeNode root,List<Integer> s){
    	if(root==null) return ;
    	if(root.left!=null) postT(root.left, s);
    	if(root.right!=null) postT(root.right, s);
    	s.add(root.val);
    }
    
    //145-iteratively
    public static List<Integer> postorderTraversal2(TreeNode root) {
    	
    	List<Integer> s = new ArrayList<Integer>();
    	if(root == null) return s;
    	Stack<TreeNode> nodes = new Stack<TreeNode>();
    	Stack<TreeNode> st = new Stack<TreeNode>();
    	
    	nodes.push(root);
    	while(!nodes.isEmpty()){
    		TreeNode curr = nodes.pop();
    		st.add(curr);
    		if(curr.left!=null) nodes.add(curr.left);
    		if(curr.right!=null) nodes.add(curr.right);
    	}
    	while(!st.isEmpty()){
    		s.add(st.pop().val);
    	}
    	return s;
    }
    //56. Merge Intervals
    public static List<Interval> merge(List<Interval> intervals) {
        if(intervals.isEmpty()) return intervals;
        PriorityQueue<Interval> queue = new PriorityQueue<Solution.Interval>(intervals.size(), (i1,i2)->i1.start-i2.start);
        queue.addAll(intervals);
        List<Interval> sol = new ArrayList<Interval>();
        Interval curr = queue.poll();
        int start = curr.start;
        int end = curr.end;
        while(!queue.isEmpty()){
        	Interval t = queue.poll();
        	if(t.start<end){
        		end = Math.max(t.end, end);
        	}
        	else {
        		sol.add(new Interval(start, end));
        		start = t.start;
        		end = t.end;
        	}
        }
        sol.add(new Interval(start, end));
        return sol;
    }
    //309. Best Time to Buy and Sell Stock with Cooldown
    public static int maxProfit2(int[] prices) {
    	if(prices == null || prices.length < 2){
            return 0;
        }
        int len = prices.length;
        int[] sell = new int[len];
        int[] cooldown = new int[len];
        sell[1] = prices[1] - prices[0];
        for(int i = 2; i < prices.length; ++i){
            cooldown[i] = Math.max(sell[i - 1], cooldown[i - 1]);
            sell[i] = prices[i] - prices[i - 1] + Math.max(sell[i - 1], cooldown[i - 2]);
        }
        return Math.max(sell[len - 1], cooldown[len - 1]);
    }
    //309-2
    public static int maxProfit3(int[] prices) {
        int sell = 0, prev_sell = 0, buy = Integer.MIN_VALUE, prev_buy;
        for (int price : prices) {
            prev_buy = buy;
            buy = Math.max(prev_sell - price, prev_buy);
            prev_sell = sell;
            sell = Math.max(prev_buy + price, prev_sell);
        }
        return sell;
    }
    //6. ZigZag Conversion
    public String convert(String s, int numRows) {
        if(numRows == 1) return s;
        char[] str = s.toCharArray();
        int jump = (numRows - 1) * 2;
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < numRows; i++) {
            if(i == 0 || i == numRows - 1) {
                for(int j = i; j < str.length; j += jump) {
                    result.append(str[j]);
                } 
            } else {
                for(int j = i, k = jump - i; j < str.length; j += jump, k += jump) {
                    result.append(str[j]);
                    if(k < str.length) result.append(str[k]);
                }
            }
        }
        return result.toString();
    }
    //187. Repeated DNA Sequences
    
    public static Map<Character,Integer> dna = new HashMap<Character,Integer>();
    static{dna.put('A', 0);dna.put('T', 1);dna.put('G', 2);dna.put('C',3);}
    public static int LPW = (int)Math.pow(dna.size(), 9); 
    public static List<String> findRepeatedDnaSequences(String s) {
        Set<String> res = new HashSet<>();
        Set<Integer> hashs = new HashSet<>();
        for(int i=0,currhash=0;i<s.length();i++){
        	if(i>9) 
        		currhash -= LPW*dna.get(s.charAt(i-10));
        	currhash = dna.size() * currhash + dna.get(s.charAt(i));
        	if(i>8 && !hashs.add(currhash)) {
        		res.add(s.substring(i-9,i+1));
        	}
        }
        return new ArrayList(res);
    }
    //187-2
    public static List<String> findRepeatedDnaSequences2(String DNA) {
        ArrayList<String> res = new ArrayList<String>();
        if(DNA.length()<10)    return res;
        HashSet<Integer> once = new HashSet<Integer>();
        HashSet<Integer> twice = new HashSet<Integer>();
        int[] map = new int[26];
        map['A'-'A'] = 0;
        map['C'-'A'] = 1;
        map['G'-'A'] = 2;
        map['T'-'A'] = 3;
        int enc = 0;
        for(int i=0; i<9; ++i){
            enc <<=2;
            enc |= map[DNA.charAt(i)-'A'];
        }
        for(int j=9; j<DNA.length(); ++j){
            enc <<=2;
            enc &= 0xfffff;
            enc |= map[DNA.charAt(j)-'A'];
            if(!once.add(enc) && twice.add(enc))
                res.add(DNA.substring(j-9,j+1));
        }
        return res;
    }
    
    //given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000)
    //190. Reverse Bits
    public static int reverseBits(long n) {
        int sol = 0;
        int index = 0;
        while(index<32){
        	sol<<=1;
        	long t = n%2;
        	sol+=t;
            index++;
            n>>>=1;
        }
        return sol;
    }
    //190-2
    private final Map<Byte, Integer> cache = new HashMap<Byte, Integer>();
    public int reverseBits(int n) {
        byte[] bytes = new byte[4];
        for (int i = 0; i < 4; i++) 
            bytes[i] = (byte)((n >>> 8*i) & 0xFF);
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result += reverseByte(bytes[i]); 
            if (i < 3)
                result <<= 8;
        }
        return result;
    }

    private int reverseByte(byte b) {
        Integer value = cache.get(b);
        if (value != null)
            return value;
        value = 0;
        // reverse by bit
        for (int i = 0; i < 8; i++) {
            value += ((b >>> i) & 1);
            if (i < 7)
                value <<= 1;
        }
        cache.put(b, value);
        return value;
    }
    //22. Generate Parentheses
    public static List<String> generateParenthesis(int n) {
    	List<String> s = new ArrayList<String>();
        if(n==0) return s;
        backtracking(n, 0, 0, s, "");
        return s;
    }
    
    public static void backtracking(int n,int l,int r,List<String> sol,String curr){
    	if(l==n){
    		String s = curr;
    		for(int i=l+r;i<n*2;i++)
    			s=s+')';
    		sol.add(s);
    		return ;
    	}
    	if(l==r){
    		backtracking(n, l+1, r, sol, curr+"(");
    	}
    	else if(l>r){
    		backtracking(n, l+1, r, sol, curr+"(");
    		backtracking(n, l, r+1, sol, curr+")");
    	}
    }
    //327. Count of Range Sum
    
    public static int countRangeSum(int[] nums, int lower, int upper) {
    	 int n = nums.length;
    	    long[] sums = new long[n + 1];
    	    for (int i = 0; i < n; ++i)
    	        sums[i + 1] = sums[i] + nums[i];
    	    return countWhileMergeSort(sums, 0, n + 1, lower, upper);
    }
    
    private static int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
        if (end - start <= 1) return 0;
        int mid = (start + end) / 2;
        int count = countWhileMergeSort(sums, start, mid, lower, upper) 
                  + countWhileMergeSort(sums, mid, end, lower, upper);
        int j = mid, k = mid, t = mid;
        long[] cache = new long[end - start];
        for (int i = start, r = 0; i < mid; ++i, ++r) {
            while (k < end && sums[k] - sums[i] < lower) k++;
            while (j < end && sums[j] - sums[i] <= upper) j++;
            while (t < end && sums[t] < sums[i]) cache[r++] = sums[t++];
            cache[r] = sums[i];
            count += j - k;
        }
        System.arraycopy(cache, 0, sums, start, t - start);
        return count;
    }
    //82. Remove Duplicates from Sorted List II
    public static ListNode deleteDuplicates2(ListNode head) {
        if(head == null || head.next==null) return head;
        ListNode sol = new ListNode(0);
        sol.next = head;
        ListNode thead = sol;
        int curr = head.val;
        head = head.next;
        while(head!=null){
        	if(head.val==curr){
        		while(head!=null && head.val==curr)
        			head = head.next;
        		if(head == null){
        			thead.next = null;
        			break;
        		}
        		else {
        			thead.next = head;
        			curr = head.val;
        			head = head.next;
        		}
        	}
        	else {
        		thead = thead.next;
        		curr = head.val;
        		head=head.next;
        	}
        }
        return sol.next;
    }
    
    //278. First Bad Version
    /*public static int firstBadVersion(int n) {
        int start = 1, end = n;
        while (start < end) {
            int mid = start + (end-start) / 2;
            if (!isBadVersion(mid)) start = mid + 1;
            else end = mid;            
        }        
        return start;
    }*/
    //28. Implement strStr()
    public static int strStr(String haystack, String needle) {
    	if(haystack.length()<needle.length()) return -1;
        if(needle.length()==0) return 0;
        int [] next = new int [needle.length()+1];
        nextbuilt(next, needle);
        int j=0;
        for(int i=0;i<haystack.length();i++){
        	
        	while(j>0 && haystack.charAt(i)!=needle.charAt(j))
        		j = next[j];
        	if(haystack.charAt(i)==needle.charAt(j)) j++;
        	if(j==needle.length())
        		return i-needle.length()+1;
        }
        return -1;
    }
    
    public static void nextbuilt(int[]next,String sub){
    	int j = 0;
    	next[0]=next[1]=0;
    	for(int i=1;i<sub.length();i++){
    		while(j>0 && sub.charAt(i)!=sub.charAt(j)) 
    			j = next[j];
    		if(sub.charAt(i)==sub.charAt(j)) j++;
    		next[i+1] = j;
    	}
    }
    //345. Reverse Vowels of a String
    public static String reverseVowels(String s) {
    	if(s.length()<=1) return s;
        char[] str = s.toCharArray();
        int i=0;
        int j=s.length()-1;
        HashSet<Character> set = new HashSet<Character>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        while(i<j){
        	while(i<s.length() && !set.contains(str[i])) i++;
        	while(j>=0 && !set.contains(str[j])) j--;
        	if(i<j){
        		char t = str[i];
        		str[i] = str[j];
        		str[j] = t;
        		i++;
        		j--;
        	}
        }
        return String.valueOf(str);
    }
    //112. Path Sum
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null) return false;
        if(root.left!=null){
            
            if(hasPathSum(root.left,sum-root.val)) return true;
        }
        if(root.right!=null){
            
            if(hasPathSum(root.right,sum-root.val)) return true;
        }
        if(root.left==null && root.right==null && root.val==sum) return true;
        
        return false;
    }
    //332. Reconstruct Itinerary
    public static HashMap<String,ArrayList<String>> maps = new HashMap<>();
    public static List<String> findItinerary(String[][] tickets) {
    	List<String> list = new ArrayList<>();
    	if(tickets.length==0 || tickets[0].length==0) return list;
    	maps = new HashMap<>();
    	HashSet<String> has = new HashSet<String>();
    	int length = tickets.length+1;
    	for(int i=0;i<tickets.length;i++){
    		if(!maps.containsKey(tickets[i][0])){
    			ArrayList<String> t = new ArrayList<>();
    			t.add(tickets[i][1]);
    			maps.put(tickets[i][0], t);
    		}
    		else {
    			ArrayList<String> t = maps.get(tickets[i][0]);
    			t.add(tickets[i][1]);
    			maps.put(tickets[i][0], t);
    		}
    	}
    	List<String> sol = new ArrayList<String>();
    	sol.add("JFK");
    	dfs_332(sol,length,"JFK");
        return sol;
    }
    
    public static boolean dfs_332(List<String> sol,int length,String curr){
    	if(sol.size()==length){
    		return true;
    	}
    	ArrayList<String> t = maps.get(curr);
    	if(t==null) return false;
    	Collections.sort(t);
		for (int i=0;i<t.size();i++) {
			String currs = t.get(i);
			t.remove(i);
			maps.put(curr,t);
			sol.add(currs);
			if (dfs_332(sol, length, currs))
				return true;
			sol.remove(sol.size() - 1);
			t.add(i, currs);
			maps.put(curr, t);

		}
    	return false;
    	
    }
    //332-2
    public List<String> findItinerary2(String[][] tickets) {
        for (String[] ticket : tickets)
            targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
        visit("JFK");
        return route;
    }

    Map<String, PriorityQueue<String>> targets = new HashMap<>();
    List<String> route = new LinkedList();

    void visit(String airport) {
        while(targets.containsKey(airport) && !targets.get(airport).isEmpty())
            visit(targets.get(airport).poll());
        route.add(0, airport);
    }
    //72. Edit Distance
    public static int minDistance(String word1, String word2) {
    	int [][] dp = new int[word1.length()+1][word2.length()+1];
    	for(int i=0;i<=word1.length();i++)
    		dp[i][0]=i;
    	for(int i=0;i<=word2.length();i++)
    		dp[0][i]=i;
    	for(int i=1;i<=word1.length();i++)
    		for(int j=1;j<=word2.length();j++){
    			if(word1.charAt(i-1)==word2.charAt(j-1))
    				dp[i][j] = dp[i-1][j-1];
    			else 
    				dp[i][j] = Math.min(dp[i][j-1], Math.min(dp[i-1][j],dp[i-1][j-1]))+1;
    		}
        return dp[word1.length()][word2.length()];
    }
  
    //102. Binary Tree Level Order Traversal
    public static List<List<Integer>> levelOrder(TreeNode root) {
    	List<List<Integer>> sol = new ArrayList<List<Integer>>();
    	if(root == null) return sol;
    	List<TreeNode> list = new ArrayList<>();
    	
    	list.add(root);
    	while(!list.isEmpty()){
    		List<TreeNode> tlist = new ArrayList<TreeNode>();
    		List<Integer> tsol = new ArrayList<>();
    		for(TreeNode t:list){
    			tsol.add(t.val);
    			if(t.left!=null)
    				tlist.add(t.left);
    			if(t.right!=null)
    				tlist.add(t.right);
    		}
    		sol.add(tsol);
    		list = tlist;
    	}
        return sol;
    }
    //102-2
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        levelOrder(root, 0, levels);
        return levels;
    }

    public void levelOrder(TreeNode node, int level, List<List<Integer>> levels) {
        if (node == null) return;
        if (levels.size() == level) levels.add(new ArrayList<>());
        levels.get(level++).add(node.val);
        levelOrder(node.left, level, levels);
        levelOrder(node.right, level, levels);
    }
    //349. Intersection of Two Arrays
    public static int[] intersection(int[] nums1, int[] nums2) {
    	if(nums1.length==0 || nums2.length==0) return new int[0];
    	HashSet<Integer> set = new HashSet<Integer>();
    	HashSet<Integer> sol = new HashSet<Integer>();
    	for(int s:nums1){
    		if(!set.contains(s))
    			set.add(s);
    	}
    	for(int s:nums2){
    		if(set.contains(s))
    			sol.add(s);
    	}
    	int [] intersetion = new int[sol.size()];
    	int index = 0;
    	for(int i:sol)
    		intersetion[index++] = i;
        return intersetion;
    }
    //235. Lowest Common Ancestor of a Binary Search Tree
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
    	if(root == null || root.val==p.val || root.val == q.val) return root;
    	if(p.val>q.val){
    		TreeNode t = p;
    		p = q;
    		q = t;
    	}
    	if(root.val<p.val) return lowestCommonAncestor2(root.right, p, q);
    	else if(root.val >q.val) return lowestCommonAncestor2(root.left, p, q);
    	else {
    		TreeNode t1 = lowestCommonAncestor2(root.left, p, q);
    		TreeNode t2 = lowestCommonAncestor2(root.right, p, q);
    		return t1==null?t2:t2==null?t1:root;
    	}
    }
    //32. Longest Valid Parentheses
    public static int longestValidParentheses(String s) {
    	int sum = 0;
    	Stack<Integer> index = new Stack<Integer>();
    	Stack<Character> stack = new Stack<>();
    	char[] str = s.toCharArray();
    	index.push(-1);
    	stack.push('|');
    	for(int i=0;i<str.length;i++){
    		if(str[i]==')' && stack.peek()=='('){
    			stack.pop();
    			index.pop();
    			sum = Math.max(sum, i-index.peek());
    		}
    		else{
    			stack.push(str[i]);
    			index.push(i);
    		}
    	}
        return sum;
    }
    
    public int longestValidParentheses2(String s) {
		int max = 0, left = 0, len = s.length();
		int[] ans = new int[len];
		int[] leftans = new int[len+10];
		for(int i=0;i<len;i++){
			if(s.charAt(i) == '('){
				leftans[++left] = 0;
			}
			else{
				if(left!=0){
					ans[i] = ans[i-1]+2+leftans[left];
					if(ans[i] > max) max = ans[i];
					left--;
					if(i+1<len && s.charAt(i+1) == '('){
						leftans[++left] = ans[i++];
					}
				}else{
					ans[i] = 0 ;
				}
			}
		}
		return max;
	}
    //299. Bulls and Cows
    public static String getHint(String secret, String guess) {
    	HashMap<Character,Integer> map = new HashMap<>();
    	char []s = secret.toCharArray();
    	char []g = guess.toCharArray();
    	int A = 0;
    	int B = 0;
    	boolean [] same = new boolean[s.length];
    	for(int i=0;i<s.length;i++){
    		if(s[i]==g[i]){
    			A++;
    			same[i] = true;
    		}
    		else {
    			if(!map.containsKey(s[i])){
    				map.put(s[i], 1);
        		}
        		else {
        			map.put(s[i], map.get(s[i])+1);
        		}
    		}
    	}
    	for(int i=0;i<s.length;i++){
    		if(!same[i]){
    			if(map.containsKey(g[i])){
    				B++;
    				int t = map.get(g[i])-1;
    				if(t==0)
    					map.remove(g[i]);
    				else 
    					map.put(g[i], t);
    			}
    		}
    	}
    	
        return A+"A"+B+"B";
    }
    //299-2
    public static String getHint2(String secret, String guess) {
    	int [] nums = new int[10];
    	char []s = secret.toCharArray();
    	char []g = guess.toCharArray();
    	int A = 0;
    	int B = 0;
    	boolean [] same = new boolean[s.length];
    	for(int i=0;i<s.length;i++){
    		if(s[i]==g[i]){
    			A++;
    			same[i] = true;
    		}
    		else {
    			nums[s[i]-'0']++;
    		}
    	}
    	for(int i=0;i<s.length;i++){
    		if(!same[i]){
    			if(nums[g[i]-'0']!=0){
    				nums[g[i]-'0']--;
    				B++;
    			}
    		}
    	}
		return A+"A"+B+"B";
    }
    //29. Divide Two Integers
    public static int divide(int dividend, int divisor) {
    	if(divisor==1) return dividend;
    	if(dividend==0) return 0;
    	if(divisor==0 || (dividend==Integer.MIN_VALUE&&divisor==-1)) return Integer.MAX_VALUE;
    	int sign = (((divisor<0)^(dividend<0))?-1:1);
    	long s1 = Math.abs((long)divisor);
    	long s2 = Math.abs((long)dividend);
    	long sol = 0;
    	while(s2>=s1){
    		long tmp = s1;
    		long t = 1;
    		while(s2>(tmp<<1)){
    			t<<=1;
    			tmp<<=1;
    		}
    		s2-=tmp;
    		sol+=t;
    	}
    	return (int)(sol*sign);
    }
    //101. Symmetric Tree
    public static boolean isSymmetric(TreeNode root) {
    	if(root == null) return true;
		return isSym(root.left, root.right);
    }
    
    public static boolean isSym(TreeNode left,TreeNode right){
    	if(left==null && right==null) return true;
    	if((left==null && right!=null)||left!=null && right==null) return false;
    	if(left.val!=right.val){
    		return false;
    	}
    	return isSym(left.left,right.right)&&isSym(left.right, right.left);
    }
    //143. Reorder List
    public static void reorderList(ListNode head) {
    	ListNode bNode = head, fNode = head;
        while(fNode != null && fNode.next != null){
            fNode = fNode.next.next;
            bNode = bNode.next;
        }
        if(bNode == null || bNode.next == null) return;
        fNode = bNode.next;
        //reverse the last half of the linklist eg:1,2,3,4,5,6,7,8 => 1,2,3,4,5,8,7,6 
        while(fNode != null && fNode.next != null){
            ListNode tmp = fNode.next;
            fNode.next = tmp.next;
            tmp.next = bNode.next;
            bNode.next = tmp;
        }
        fNode = bNode.next;//fnode point to the reversed half list fnode->8->7->6->null
        bNode.next = null;
        bNode = head;
        while(fNode != null){
            ListNode tmp = fNode.next;
            fNode.next = bNode.next;
            bNode.next = fNode;
            bNode = fNode.next;
            fNode = tmp;
        }
    }
    //310. Minimum Height Trees
    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
    	if(n==1) return Collections.singletonList(0);
    	List<Set<Integer>> sets = new ArrayList<>(n);
    	for(int i=0;i<n;i++) sets.add(i, new HashSet<>());
    	for(int[] edge:edges){
    		sets.get(edge[0]).add(edge[1]);
    		sets.get(edge[1]).add(edge[0]);
    	}
    	List<Integer> lf = new ArrayList<>();
    	for(int i=0;i<sets.size();i++){
    		if(sets.get(i).size()==1)
    			lf.add(i);
    	}
    	while(n>2){
    		n-=lf.size();
    		List<Integer> tmp = new ArrayList<>();
    		for(int curr:lf){
    			int t=sets.get(curr).iterator().next();
    			sets.get(t).remove(curr);
    			if(sets.get(t).size()==1) tmp.add(t);
    		}
    		lf = tmp;
    	}
        return lf;
    }
    //35. Search Insert Position
    public static int searchInsert(int[] nums, int target) {
    	if(nums.length==0) return 0;
    	int start = 0;
    	int end = nums.length;
    	while(start<end){
    		int mid = start+ (end-start)/2;
    		if(nums[mid]==target)
    			return mid;
    		else if(nums[mid]<target){
    			if(mid<nums.length-1 && nums[mid+1]>target) 
    				return mid+1;
    			start = mid+1;
    		}
    		else {
    			if(mid>0 && nums[mid-1]<target)
    				return mid;
    			end = mid-1;
    		}
    	}
    	
    	if(start==nums.length) return nums.length;
    	if(nums[start]>target){
    		if(start==0) return 0;
    		return start-1;
    	}
    	else if(nums[start]==target) return start;
    	else return start+1;
    }
    //75. Sort Colors
    public static void sortColors(int[] nums) {
    	int r = 0;
    	int w = 0;
    	int b = 0;
        for(int i=0;i<nums.length;i++){
        	switch(nums[i]){
        		case 0: r++;break;
        		case 1: w++;break;
        		case 2: b++;break;
        	}
        }
        int [] sol = new int[nums.length];
        b = w+r;
        w = r;
        r = 0;
        int index=0;
        for(int i=0;i<nums.length;i++){
        	if(nums[i]==0){
        		sol[r++]=0;
        	}
        	else if(nums[i]==1){
        		sol[w++]=1;
        	}
        	else {
        		sol[b++]=2;
        	}
        }
        for(int i=0;i<nums.length;i++)
        	nums[i] = sol[i];
    }
    
    //37. Sudoku Solver https://discuss.leetcode.com/topic/50252/share-my-3ms-java-solution
    public void solveSudoku(char[][] board) {
        //9rows+9columns+9blocks
        int[] checkers = new int[27];
        List<Integer> emptyList = new ArrayList<Integer>(81);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    fillNum(checkers, i, j, getCharValue(board[i][j]));
                } else {
                    //save empty row&column
                    emptyList.add(i * 9 + j);
                }
            }
        }

        int count = emptyList.size();
        if (count > 0) {
            int[] empty = new int[count];
            for (int i = 0; i < count; i++) {
                empty[i] = emptyList.get(i);
            }
            solveSudoku(checkers, board, empty, count);
        }
    }

    private boolean solveSudoku(int[] checkers, char[][] board, int[] empty, int count) {
        while (count > 0) {
            int k = 0;
            for (; k < count; k++) {
                int idx = empty[k];
                int i = idx / 9;
                int j = idx % 9;
                int num = getUniqueNum(checkers, i, j);
                //fill unique number
                if (num > 0) {
                    empty[k] = empty[count - 1];
                    empty[count - 1] = -1;
                    fillNum(checkers, i, j, num);
                    board[i][j] = getChar(num);
                    count--;
                    break;
                } else if (num == -1) {
                    return false;
                }
            }
            if (count != 0 && k == count) {
                int idx = empty[count - 1];
                int i = idx / 9;
                int j = idx % 9;
                List<Integer> vals = getAvailableNum(checkers, i, j);
                for (Integer val : vals) {
                    char[][] tmpBoard = new char[9][9];
                    copyBoard(tmpBoard, board);
                    int[] tmpCheckers = checkers.clone();
                    int[] tmpEmpty = empty.clone();
                    tmpBoard[i][j] = getChar(val);
                    fillNum(tmpCheckers, i, j, val);
                    if (solveSudoku(tmpCheckers, tmpBoard, tmpEmpty, count - 1)) {
                        copyBoard(board, tmpBoard);
                        return true;
                    }
                }
                return false;
            }
        }
        return true;
    }

    void copyBoard(char[][] dest, char[][] source) {
        for (int i = 0; i < 9; i++) {
            dest[i] = source[i].clone();
        }
    }

    private List<Integer> getAvailableNum(int[] checkers, int i, int j) {
        int[] idxs = getIndexs(i, j);
        int val = checkers[idxs[0]] | checkers[idxs[1]] | checkers[idxs[2]];
        List<Integer> result = new ArrayList<Integer>();
        for (int k = 1; k <= 9; k++) {
            if ((val & (1 << k)) >> k != 1) {
                result.add(k);
            }
        }
        return result;
    }

    private int getUniqueNum(int[] checkers, int i, int j) {
        int[] idxs = getIndexs(i, j);
        int val = checkers[idxs[0]] | checkers[idxs[1]] | checkers[idxs[2]];
        if (val == 1022) {
            //invalid board
            return -1;
        }
        for (int k = 1; k <= 9; k++) {
            if ((val & (1 << k)) >> k != 1) {
                if ((val >> (k + 1)) == (1 << (9 - k)) - 1) {
                    return k;
                } else {
                    break;
                }
            }
        }
        //multi-number
        return 0;
    }

    private void fillNum(int[] checkers, int i, int j, int num) {
        int[] idxs = getIndexs(i, j);
        checkers[idxs[0]] |= (1 << num);
        checkers[idxs[1]] |= (1 << num);
        checkers[idxs[2]] |= (1 << num);
    }

    private int[] getIndexs(int i, int j) {
        return new int[] { i, 9 + j, 18 + (i / 3 * 3 + j / 3) };
    }

    private int getCharValue(char i) {
        if (i == '.') {
            return 0;
        } else {
            return i - '1' + 1;
        }
    }

    private char getChar(int i) {
        return (char) ('1' - 1 + i);
    }
    //23. Merge k Sorted Lists
    //TLE
    public static ListNode mergeKLists(ListNode[] lists) {
    	
    	if(lists==null || lists.length==0) return null;
    	int k = lists.length;
    	if(k==1) return lists[0];
    	
    	int index = 0;
    	ListNode sol = lists[0];
    	while(++index<k && sol==null)
    		sol = lists[index];
    	for(int i=index;i<k;i++){
    		ListNode l1 = sol;
    		ListNode l2 = lists[i];
    		if(l2==null) continue;
    		ListNode t = new ListNode(0);
    		ListNode t1 = t;
    		while(l1!=null && l2!=null){
    			if(l1.val>l2.val){
    				t.next = l2;
    				t = t.next;
    				l2 = l2.next;
    			}
    			else {
    				t.next = l1;
    				t = t.next;
    				l1 = l1.next;
    			}
    		}
    		if(l1!=null) t.next = l1;
    		if(l2!=null) t.next = l2;
    		while(t.next!=null) t = t.next;
    		sol = t1.next;
    	}
        return sol;
    }
	public static ListNode mergeKLists2(ListNode[] lists) {
	    	
	    	if(lists==null || lists.length==0) return null;
	    	int k = lists.length;
	    	if(k==1) return lists[0];
	    	if(lists.length==2)
	    		 return merge2List(lists[0], lists[1]);
	    	ListNode s1 = mergeKLists2(copyList(lists, 0, k/2));
	    	ListNode s2 = mergeKLists2(copyList(lists, k/2, k));
	    	return merge2List(s1, s2);
	}
	public static ListNode[] copyList(ListNode[] lists, int start, int end) {
        if (start >= end || end > lists.length) {
            return null;
        }

        ListNode[] result = new ListNode[end - start];
        int index = 0;
        for (int i = start; i < end; i++) {
            result[index] = lists[i];
            index++;
        }

        return result;
    }
    public static ListNode merge2List(ListNode root1,ListNode root2){
    	if(root1==null) return root2;
    	if(root2==null) return root1;
    	ListNode sol = new ListNode(0);
    	ListNode curr = sol;
    	while(root1!=null && root2!=null){
			if(root1.val>root2.val){
				curr.next = root2;
				curr = curr.next;
				root2 = root2.next;
			}
			else {
				curr.next = root1;
				curr = curr.next;
				root1 = root1.next;
			}
		}
    	if(root1!=null) curr.next = root1;
		if(root2!=null) curr.next = root2;
    	return sol.next;
    }
    //129. Sum Root to Leaf Numbers
    public static int sum_129 = 0; 
    public static int sumNumbers(TreeNode root) {
    	if(root == null) return 0;
    	sumNum(root, 0);
        return sum_129;
    }
    
    public static void sumNum(TreeNode root,int curr){
    	int t = curr*10+root.val;
    	if(root.left==null && root.right==null){
    		sum_129 += t;
    	}
    	else {
    		if(root.left!=null)
    			sumNum(root.left, t);
    		if(root.right!=null)
    			sumNum(root.right, t);
    	}
    	
    }
    //318. Maximum Product of Word Lengths
    public static int maxProduct(String[] words) {
    	if(words==null || words.length==0) return 0;
    	int length = words.length;
    	int [] val = new int[length];
    	for(int i=0;i<length;i++){
    		String t = words[i];
    		val[i] = 0;
    		for(int j=0;j<t.length();j++){
    			val[i] |= 1<<(t.charAt(j)-'a');
    		}
    	}
    	int max = 0;
    	for(int i=0;i<length;i++)
    		for(int j=i+1;j<length;j++)
    			if((val[i]&val[j])==0 && (words[i].length()*words[j].length()>max))
    				max = words[i].length()*words[j].length();
        return max;
    }
    //4. Median of Two Sorted Arrays
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    	int len1 = nums1.length;
        int len2 = nums2.length;
        boolean isEven = ((len1+len2)%2==0);
        if (len1>len2){
            return findMedianSortedArrays(nums2, nums1);
        }

        if (len1==0) return isEven? (nums2[len2/2]+nums2[(len2/2)-1])/2.0:(double)nums2[len2/2];
       
        int start = 0, end = len1, mid = (len1+len2+1)/2;
        while (start<=end){
            int i = start+(end-start)/2;
            int j = mid-i;
            
            if (j>=1 && i<len1 && nums2[j-1]>nums1[i]) start=i+1;
            else if (i>=1 && j<len2 && nums1[i-1]>nums2[j]) end=i-1;
            else {
                int min_right = 0;
                int max_left = 0;
                
                if (j == 0) max_left = nums1[i-1];
                else if (i == 0) max_left = nums2[j-1];
                else max_left = Math.max(nums1[i-1], nums2[j-1]);
                
                if (j == len2) min_right = nums1[i];
                else if (i == len1) min_right = nums2[j];
                else min_right = Math.min(nums1[i],nums2[j]);
               
                if (isEven) return (min_right+max_left)/2.0; 
                else return (double)max_left;
            }
        }
        return 0.0;
    }
    //282. Expression Add Operators
    public static List<String> addOperators(String num, int target) {
    	List<String> sol = new ArrayList<>();
    	if(num.length()==0) return sol;
    	char [] path = new char[num.length()*2-1];
    	char[]digits = num.toCharArray();
    	long t = 0;
    	for(int i=0;i<digits.length;i++){
    		t = t*10+digits[i]-'0';
    		path[i] = digits[i];
    		dvd(sol, path, i+1, 0, t, digits, i+1, target);
    		if(t==0) break;
    	}
    	
    	return sol;
    }
    public static void dvd(List<String> sol,char[]path,int len,long left,long curr,char[]digits,int pos,int target){
    	if(pos==digits.length){
    		if(left+curr==target) sol.add(new String(path,0,len));
    		return ;
    	}
    	long t = 0;
    	int j = len + 1;
    	for(int i=pos;i<digits.length;i++){
    		t = t*10+digits[i]-'0';
    		path[j++] = digits[i];
    		path[len] = '+';
    		dvd(sol, path, j, left+curr, t, digits, i+1, target);
    		path[len] = '-';
    		dvd(sol, path, j, left+curr, -t, digits, i+1, target);
    		path[len] = '*';
    		dvd(sol, path, j, left, curr*t, digits, i+1, target);
    		if(digits[pos]=='0') break;
    	}
    }
    //99. Recover Binary Search Tree
    public void recoverTree(TreeNode root) {
    	Deque<TreeNode> stack = new LinkedList<>();
        boolean firstTime = true;
        TreeNode m = null, n = null, pre = null;
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (stack.isEmpty()) {
                break;
            }
            TreeNode node = stack.pop();
            if (pre != null && pre.val > node.val) {
                if (firstTime) {
                    m = pre;
                    firstTime = false;
                }
                n = node;
            }
            pre = node;
            root = node.right;
        }
        int tmp = m.val;
        m.val = n.val;
        n.val = tmp;
    }
    //20. Valid Parentheses
    public static boolean isValid(String s) {
        if(s.length()==0) return true;
        Stack<Character> stack = new Stack<Character>();
        char[] str = s.toCharArray();
        for(int i=0;i<str.length;i++){
        	if(str[i]=='{'||str[i]=='['||str[i]=='(')
        		stack.add(str[i]);
        	else if((str[i]=='}'||str[i]==']'||str[i]==')')&&stack.isEmpty())
        		return false;
        	else if(str[i]==')'  && stack.pop()!='(')
        		return false;
        	else if(str[i]=='}'  && stack.pop()!='{')
        		return false;
        	else if(str[i]==']'  && stack.pop()!='[')
        		return false;
        }
        if(stack.isEmpty()) return true;
        return false;
    }
    //226. Invert Binary Tree
    public static TreeNode invertTree(TreeNode root) {
    	if(root == null ) return root;
    	if(root.left!=null) root.left = invertTree(root.left);
    	if(root.right!=null) root.right = invertTree(root.right);
    	TreeNode t = root.right;
    	root.right = root.left;
    	root.left = t;
        return root;
    }
    //326. Power of Three
    public static boolean isPowerOfThree(int n) {
        int [] s = {1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683, 59049, 177147, 531441, 1594323, 4782969, 14348907, 43046721, 129140163, 387420489, 1162261467};
        if(Arrays.binarySearch(s, n)<0) return false;
        else return true;
    }
    //306. Additive Number
    public boolean isAdditiveNumber(String num) {
    	if(num.length()==0) return false;
    	
    	for(int i=1;i<num.length()/2;i++){
    		for(int j=1;Math.max(i, j)<num.length()-i-j;j++){
    			if(isValid(i, j, num)) return true;
    		}
    	}
        return false;
    }
    
    public static boolean isValid(int i,int j,String num){
    	if(num.charAt(0)=='0' && i>1) return false;
    	if(num.charAt(i)=='0' && j>1) return false;
    	String curr = "";
    	Long s1 = Long.parseLong(num.substring(0, i));
    	Long s2 = Long.parseLong(num.substring(i,i+j));
    	for(int n=i+j;n<num.length();n+=curr.length()){
    		s2 = s1+s2;
    		s1 = s2-s1;
    		curr = s2.toString();
    		if(!num.startsWith(curr, n)) return false;
    	}
    	return true;
    }
    //160. Intersection of Two Linked Lists
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null || headB==null) return null;
        int a = 0;
        int b = 0;
        ListNode t = headA;
        while(t!=null){
        	t = t.next;
        	a++;
        }
        t = headB;
        while(t!=null){
        	t = t.next;
        	b++;
        }
        boolean flag = false;
        if(a<b){
        	int s = b;
        	b = a;
        	a = s;
        	flag = true;
        }
        ListNode t2 = headB;
        t = headA;
        for(int i=0;i<a-b;i++){
        	if(flag)
        		t2 = t2.next;
        	else
        		t=t.next;
        }
        while(t!=null && t2!=null){
        	if(t==t2)
        		return t;
        	t=t.next;
        	t2=t2.next;
        }
        return null;
    }
    //71. Simplify Path
    public static String simplifyPath(String path) {
    	if(path.length()==0) return  "/";
    	String [] strs = path.split("/");
    	Stack<String> s = new Stack<String>();
    	for(int i=0;i<strs.length;i++){
    		String tst = strs[i].trim();
    		if(tst.equals(".")) continue;
    		else if(tst.equals("..")){
    			if(!s.isEmpty())
    				s.pop();
    		}
    		else if(tst.length()==0)
    			continue;	
    		else s.push(tst);
    	}
    	String sol = "";
    	if(!s.isEmpty())
    		sol = s.pop();
    	while(!s.isEmpty()){
    		sol = s.pop()+"/"+sol;
    	}
        return "/"+sol;
    }
    //68. Text Justification
    public List<String> fullJustify(String[] words, int maxWidth) {
    	List<String> lines = new ArrayList<String>();
        
        int index = 0;
        while (index < words.length) {
            int count = words[index].length();
            int last = index + 1;
            while (last < words.length) {
                if (words[last].length() + count + 1 > maxWidth) break;
                count += words[last].length() + 1;
                last++;
            }
            
            StringBuilder builder = new StringBuilder();
            int diff = last - index - 1;
            if (last == words.length || diff == 0) {
                for (int i = index; i < last; i++) {
                    builder.append(words[i] + " ");
                }
                builder.deleteCharAt(builder.length() - 1);
                for (int i = builder.length(); i < maxWidth; i++) {
                    builder.append(" ");
                }
            } else {
                int spaces = (maxWidth - count) / diff;
                int r = (maxWidth - count) % diff;
                for (int i = index; i < last; i++) {
                    builder.append(words[i]);
                    if (i < last - 1) {
                        for (int j = 0; j <= (spaces + ((i - index) < r ? 1 : 0)); j++) {
                            builder.append(" ");
                        }
                    }
                }
            }
            lines.add(builder.toString());
            index = last;
        }
        
        
        return lines;
    }
    //152. Maximum Product Subarray
    public static int maxProduct(int[] nums) {
        int length = nums.length;
        if(length==0) return 0;
        int max = nums[0];
        int postmax = nums[0];
        int min = nums[0];
        int currmax = max;
        int currmin = max;
        for(int i=1;i<length;i++){
        	max = Math.max(Math.max(currmax*nums[i], currmin*nums[i]), nums[i]);
        	min = Math.min(Math.min(currmin*nums[i], currmax*nums[i]), nums[i]);
        	postmax = Math.max(postmax, max);
        	currmax = max;
        	currmin = min;
        }
        
        return postmax;
    }
    //140. Word Break II
    public static List<String> wordBreak(String s, Set<String> wordDict) {
    	List<String> results = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return results;
        }
        char[] vals = s.toCharArray();
        int maxLen = 0;
        for (String word : wordDict) maxLen = Math.max(maxLen, word.length());
        words(vals, 0, maxLen, new StringBuilder(), wordDict, new HashSet<>(), results);
        return results;
    }
    
    public static boolean words(char[] vals, int index, int maxLen, StringBuilder sb, Set<String> wordDict, Set<Integer> notQualified, List<String> results){
    	 if (index >= vals.length) {
             results.add(sb.toString().trim());
             return true;
         }
         int sbLen = sb.length();
         boolean isPathFound = false;
         for (int i = index; i <= index + maxLen && i < vals.length; i++) {
             String temp = new String(vals, index, i - index + 1);
             if (wordDict.contains(temp)) {
                 if (!notQualified.contains(i + 1)) {
                     sb.append(temp).append(" ");
                     isPathFound = words(vals, i + 1, maxLen, sb, wordDict, notQualified, results) || isPathFound;
                     sb.setLength(sbLen);
                 }
             }
             
         }
         if (!isPathFound) notQualified.add(index);
         return isPathFound;
    }
    //5. Longest Palindromic Substring
    public String longestPalindrome(String s) {
        int length = s.length();
    	if(length==0) return "";
    	char[] str = s.toCharArray();
    	int maxlen = 1;
    	String sol = String.valueOf(str[0]);
    	for(int i=0;i<length;i++){
    		if(i>0&&i<length-1 && str[i-1]==str[i+1]){
    			int j=0;
    			while((i-j-1>=0 && i+j+1<length)&& (str[i-j-1]==str[i+j+1])){
    				j++;
    			}
    			int curr = j*2+1;
    			if(curr>maxlen){
    				maxlen = curr;
    				sol = s.substring(i-j, i+j+1);
    			}
    		}
    		
    		if(i<length-1 && str[i+1]==str[i]){
    			int j=1;
    			while((i-j+1>=0 && i+j<length)&& (str[i-j+1]==str[i+j]) ) j++;
    			j--;
    			if(maxlen<j*2){
    					
    				maxlen = j*2;
    				sol = s.substring(i-j+1, i+j+1);
    			}
    		}
    	}
        return sol;
    }
    //7. Reverse Integer
    public int reverse(int x) {
        long sum = 0;
    	boolean sign = false;
    	if(x<0)
    		sign = true;
    	while(x!=0){
    		sum = sum*10+x%10;
    		x=x/10;
    		if(sum>Integer.MAX_VALUE || sum<Integer.MIN_VALUE ) return 0;
    	}
        return (int)sum;
    }
    //13. Roman to Integer
    public int romanToInt(String s) {
        int sum=0;
        if(s.indexOf("IV")!=-1){sum-=2;}
        if(s.indexOf("IX")!=-1){sum-=2;}
        if(s.indexOf("XL")!=-1){sum-=20;}
        if(s.indexOf("XC")!=-1){sum-=20;}
        if(s.indexOf("CD")!=-1){sum-=200;}
        if(s.indexOf("CM")!=-1){sum-=200;}
        
        char c[]=s.toCharArray();
        int count=0;
        
       for(;count<=s.length()-1;count++){
           if(c[count]=='M') sum+=1000;
           if(c[count]=='D') sum+=500;
           if(c[count]=='C') sum+=100;
           if(c[count]=='L') sum+=50;
           if(c[count]=='X') sum+=10;
           if(c[count]=='V') sum+=5;
           if(c[count]=='I') sum+=1;
           
       }
   
         return sum;
    }
    //17. Letter Combinations of a Phone Number
    public static char[][] phone = {
    	{'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'},
    	{'m','n','o'},{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}
    };
    public List<String> letterCombinations(String digits) {
        List<String> sol = new ArrayList<>();
        if(digits.length()==0) return sol;
    	char[] s = digits.toCharArray();
    	char[] tmp = new char[s.length];
    	combination(sol, s, 0, tmp);
        return sol;
    }
    
    public static void combination(List<String>sol,char[] s,int curr,char[] tmp){
    	if(curr == s.length){
    		sol.add(new String(tmp));
    		return ;
    	}
    	if(s[curr]-'0'>9 || s[curr]-'0'<2) return ;
    	char[] num = phone[s[curr]-'0'-2];
    	for(int i=0;i<num.length;i++){
    		tmp[curr] = num[i];
    		combination(sol, s, curr+1, tmp);
    	}
    }
    //19. Remove Nth Node From End of List
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null ) return head;
        ListNode tmp = head;
        int num = 0;
        while(tmp!=null){
            num++;
            tmp = tmp.next;
        }
        tmp = head;
        ListNode pre = null;
        num = num-n;
        for(int i=0;i<num;i++){
            pre = tmp;
            tmp = tmp.next;
        }
        if(pre==null) return head.next;
        pre.next = tmp.next;
        return head;
    }
    //21. Merge Two Sorted Lists
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null) return l2;
        if(l2==null) return l1;
        ListNode sol = new ListNode(0);
        ListNode s = sol;
        while(l1!=null && l2!=null){
            if(l1.val<l2.val){
                sol.next = l1;
                l1 = l1.next;
                sol = sol.next;
            }
            else {
                sol.next = l2;
                l2 = l2.next;
                sol = sol.next;
            }
        }
        while(l1!=null) {
            sol.next = l1;
            l1 = l1.next;
            sol = sol.next;
        }
        while(l2!=null) {
            sol.next = l2;
            l2 = l2.next;
            sol = sol.next;
        }
        return s.next;
    }
    //36. Valid Sudoku
    public boolean isValidSudoku(char[][] board) {
        boolean[][] map = new boolean[27][10];
       for(int i = 0 ; i < board.length ; i++)
           for(int j = 0 ; j < board[0].length ; j++){
               if(board[i][j] == '.') continue;
               int index = board[i][j] - '0';
               int ik = i, jk = 9 + j, gk = (18 + (i/3)*3 + j/3);
               if(map[ik][index] || map[jk][index] || map[gk][index]) 
                   return false;
               map[ik][index] = map[jk][index] = map[gk][index] = true;
            }
        return true;
    }
    //44. Wildcard Matching
    boolean isMatch_44(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;            
        while (s < str.length()){
            if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            }
            else if (p < pattern.length() && pattern.charAt(p) == '*'){
                starIdx = p;
                match = s;
                p++;
            }
            else if (starIdx != -1){
                p = starIdx + 1;
                match++;
                s = match;
            }
            else return false;
        }
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;
        return p == pattern.length();
    }
    //61. Rotate List
    public static ListNode rotateRight(ListNode head, int k) {
    	if(head==null||head.next==null) return head;
    	ListNode h = head;
    	int length = 1;
    	ListNode tail=head;
    	while(h.next!=null){
    		length++;
    		h = h.next;
    	}
    	tail = h;
    	h = head;
    	if(k%length==0) return head;
    	for(int i=0;i<length-(k%length)-1;i++){
    		h = h.next;
    	}
    	tail.next = head;
    	head = h.next;
    	h.next=null;
		return head;
        
    }
    //63. Unique Paths II
    //TLE
    static int totalpath = 0;
    public static int uniquePathsWithObstacles2(int[][] obstacleGrid) {
    	if(obstacleGrid.length==0 || obstacleGrid[0].length==0) return 0;
    	totalpath = 0;
    	dfs_63(obstacleGrid, 0, 0);
        return totalpath;
    }
    
    public static void dfs_63(int[][] map,int x,int y){
    	if(x==map.length-1 && y==map[0].length-1){
    		totalpath++;
    		return ;
    	}
    	if(x+1<map.length && map[x+1][y]==0){
    		dfs_63(map, x+1, y);
    	}
    	if(y+1<map[0].length && map[x][y+1]==0){
    		dfs_63(map, x, y+1);
    	}
    }
    //63-2 dp
    public int uniquePathsWithObstacles3(int[][] obstacleGrid) {
        int width = obstacleGrid[0].length;
        int[] dp = new int[width];
        dp[0] = 1;
        for (int[] row : obstacleGrid) {
            for (int j = 0; j < width; j++) {
                if (row[j] == 1)
                    dp[j] = 0;
                else if (j > 0)
                    dp[j] += dp[j - 1];
            }
        }
        return dp[width - 1];
    }
    //85. Maximal Rectangle
    public static int maximalRectangle(char[][] matrix) {
        if(matrix.length==0 || matrix[0].length==0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[] left=new int[n];
        int[] right = new int[n];
        int[] height = new int[n];
        Arrays.fill(right, n);
        int sol = 0;
        for(int i=0;i<m;i++){
        	int currleft = 0;
        	int currright = 0;
        	for(int j=0;j<n;j++){
        		if(matrix[i][j]=='1') height[j]++;
        		else height[j] = 0;
        	}
        	for(int j=0;j<n;j++){
        		if(matrix[i][j]=='1') left[j] = Math.max(left[j], currleft);
        		else {
        			left[j]=0;
        			currleft = j+1;
        		}
        	}
        	for(int j=0;j<n;j++){
        		if(matrix[i][j]=='1') right[j] = Math.min(right[j], currright);
        		else {
        			right[j]=n;
        			currright = j;
        		}
        	}
        	for(int j=0;j<n;j++){
        		sol = Math.max((right[j]-left[j])*height[j], sol);
        	}
        	
        }
        return sol;
    }
    //87. Scramble String
    public boolean isScramble(String s1, String s2) {
    	char[] v1 = s1.toCharArray();
        char[] v2 = s2.toCharArray();
        return isScramble(v1, 0, v1.length - 1, v2, 0, v2.length - 1);
    }
    private boolean isScramble(char[] v1, int start1, int end1, char[] v2, int start2, int end2) {
        int[] letters = new int[26];
        boolean isSame = true;
        for (int i = start1, j = start2; i <= end1; i++, j++) {
            letters[v1[i] -'a']++;
            letters[v2[j] -'a']--;
            isSame = isSame && v1[i] == v2[j];
        }
        if (isSame) return true;
        for (int i = 0; i < 26; i++) if (letters[i] != 0) return false;
        for (int i = start1, j = start2; i < end1; i++, j++) {
            if (isScramble(v1, start1, i, v2, start2, j) 
             && isScramble(v1, i + 1, end1, v2, j + 1, end2)) return true;
            if (isScramble(v1, start1, i, v2, end2 - j + start2, end2) 
             && isScramble(v1, i + 1, end1, v2, start2, end2 - j + start2 - 1)) return true;
        }
        return false;
    }
    //90. Subsets II
    
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
    	List<List<Integer>> sol = new ArrayList<>();
    	if(nums.length==0) return sol;
    	Arrays.sort(nums);
    	List<Integer> curr = new ArrayList<>();
    	dfs_90(sol, 0, nums, curr);
        return sol;
    }
    
    public static void dfs_90(List<List<Integer>> sol,int index,int[] nums,List<Integer> curr){
    	if(index == nums.length){
    		sol.add(new ArrayList<Integer>(curr));
    		return ;
    	}
    	curr.add(nums[index]);
    	dfs_90(sol, index+1, nums, curr);
    	curr.remove(curr.size()-1);
    	int next = index+1;
    	while(next<nums.length && nums[next]==nums[index]){
    		next++;
    	}
    	dfs_90(sol, next, nums, curr);
    }
    //92. Reverse Linked List II
    public static ListNode reverseBetween(ListNode head, int m, int n) {
    	if(head == null || head.next == null) return head;
        ListNode thead = new ListNode(-1);
        thead.next = head;
        ListNode sol = thead;
        ListNode tail = thead;
        ListNode phead = thead;
        ListNode ptail = thead;
        for(int i=1;i<m;i++){
        	thead = thead.next;
        }
        phead = thead.next;
        for(int i=1;i<=n;i++){
        	ptail = ptail.next;
        }
        tail = ptail.next;
        ptail.next = null;
        ListNode t = phead.next;
        phead.next = tail;
        while(t!=null){
        	ListNode a = t.next;
        	t.next = phead;
        	phead = t;
        	t = a;
        }
        thead.next = phead;
        return sol.next;
    }
    //96. Unique Binary Search Trees
    public int numTrees(int n) {
    	int [] dp = new int[n+1];
        dp[0]= 1;
        dp[1] = 1;
        for(int level = 2; level <=n; level++)
            for(int root = 1; root<=level; root++)
                dp[level] += dp[level-root]*dp[root-1];
        return dp[n];
    }
    //98. Validate Binary Search Tree
    public static boolean isValidBST(TreeNode root) {
    	if(root==null) return true;
    	long [] path = new long[2];
    	path[0] = Long.MIN_VALUE;
    	path[1] = Long.MAX_VALUE;
    	return isValidBST(root, path);
    }
    
    public static boolean isValidBST(TreeNode root,long[] path) {
    	if(root==null) return true;
    	if(root.val<=path[0]|| root.val>=path[1]){
    		return false;
    	}
    	boolean left = true;
    	boolean right = true;
    	if(root.left!=null){
    		long[] tpath = new long[2];
    		tpath[0] = path[0];
    		tpath[1] = root.val;
    		left = isValidBST(root.left, tpath);
    	}
    	if(root.right!=null){
    		long[] tpath = new long[2];
    		tpath[0] = root.val;
    		tpath[1] = path[1];
    		left = isValidBST(root.left, tpath);
    	}
    	return left && right;
    }
    
    //109. Convert Sorted List to Binary Search Tree
    public TreeNode sortedListToBST(ListNode head) {
        if(head==null ) return null;
        if(head.next==null) return new TreeNode(head.val);
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = head;
        while(fast!=null && fast.next!=null){
        	fast = fast.next.next;
        	pre = slow;
        	slow = slow.next;
        }
        TreeNode root = new TreeNode(slow.val);
        ListNode right = slow.next;
        pre.next = null;
        slow.next = null;
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(right);
        return root;
    }
    //124. Binary Tree Maximum Path Sum
    public static int maxsum = 0;
    public static int maxPathSum(TreeNode root) {
    	if(root==null) return 0;
    	maxsum = 0;
    	dfsMaxPathSum(root);
    	return maxsum;
    }
    
    public static int dfsMaxPathSum(TreeNode root) {
    	if(root==null) return 0;
    	int left = dfsMaxPathSum(root.left);
    	int right = dfsMaxPathSum(root.right);
    	int currsum = root.val;
    	int returnsum = 0;
    	if(left>0){ 
    		currsum+=left;
    		returnsum = left;
    	}
    	if(right>0){
    		currsum+=right;
    		if(right>left)
    			returnsum = right;
    	}
    	if(currsum > maxsum) maxsum = currsum;
    	return root.val+returnsum;
    }
    //139. Word Break
    public static boolean wordBreak2(String s, Set<String> wordDict) {
    	if(s.length()==0 || wordDict.size()==0) return false;
    	Set<String> visited = new HashSet<String>();
    	return wordBreak_139(s, wordDict, visited);
    	
    }
    public static boolean wordBreak_139(String s, Set<String> wordDict,Set<String> visited) {
        if(s.length()==0 || wordDict.size()==0) return false;
        int start = 0;
        int end = 1;
        while(start<s.length() && end<=s.length()){
        	String t = s.substring(start, end);
        	if(wordDict.contains(t) && !visited.contains(s.substring(end))){
        		visited.add(s.substring(end));
        		if(s.substring(end).equals("") || wordBreak_139(s.substring(end),wordDict,visited)){
        			return true;
        		}
        		else {
        			end+=1;
        		}
        	}
        	else {
        		end+=1;
        	}
        }
        if(start==s.length()) return true;
        /*start = 0;
        end = 1;
        while(start<s.length() && end<=s.length()){
        	String t = s.substring(start, end);
        	if(wordDict.contains(t)){
        		start = end;
        		end +=1;
        	}
        	else {
        		end+=1;
        	}
        }
        if(start==s.length()) return true;*/
        return false;
    }
    //144. Binary Tree Preorder Traversal
    //Recursive
    public List<Integer> preorderTraversal(TreeNode root) {
    	List<Integer> sol = new ArrayList<>();
        if(root == null) return sol;
        preorderTraversal(root, sol);
        return sol;
    }
    
    public void preorderTraversal(TreeNode root,List<Integer> sol) {
        if(root == null) return ;
        sol.add(root.val);
        if(root.left!=null) preorderTraversal(root.left, sol);
        if(root.right!=null) preorderTraversal(root.right, sol);
    }
    //iteratively
    public List<Integer> preorderTraversal2(TreeNode root) {
    	List<Integer> sol = new ArrayList<Integer>();
    	if(root == null) return sol;
    	Stack<TreeNode> s = new Stack<TreeNode>();
    	s.add(root);
    	while(!s.isEmpty()){
    		TreeNode t = s.pop();
    		if(t.right!=null) s.push(t.right);
    		if(t.left!=null) s.push(t.left);
    		sol.add(t.val);
    	}
    	return sol;
    }
    //174. Dungeon Game
    public int calculateMinimumHP(int[][] dungeon) {
    	int[][] dp = new int[dungeon.length][dungeon[0].length];

        for(int i=dp.length-1; i>=0; i--) {
            for(int j=dp[0].length-1; j>=0; j--) {
                if(i == dp.length-1 && j == dp[0].length-1) {
                    dp[i][j] = 1 - dungeon[i][j] < 1 ? 1 : 1 - dungeon[i][j];
                } else {
                    int right = j == dp[0].length-1 ? Integer.MAX_VALUE :
                            (dp[i][j+1] - dungeon[i][j] < 1 ? 1 : dp[i][j+1] - dungeon[i][j]);
                    int bottom = i == dp.length-1 ? Integer.MAX_VALUE :
                            dp[i+1][j] - dungeon[i][j] < 1 ? 1 : dp[i+1][j] - dungeon[i][j];
                    dp[i][j] = Math.min(right, bottom);
                }
            }
        }
        return dp[0][0];
    }
    //188. Best Time to Buy and Sell Stock IV
    public int maxProfit(int k, int[] prices) {
    	if (prices == null || prices.length < 2) {
            return 0;
        }
        int n = prices.length;
        int ret = 0;
        if (k >= n / 2) {
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    ret += prices[i] - prices[i - 1];
                }
            }
            return ret;
        }
        int[][] dp = new int[k + 1][n];
        for (int kk = 1; kk <= k; kk++) {
            int max = Integer.MIN_VALUE;
            for (int i = 1; i < n; i++) {
                max = Math.max(max, (i > 1 ? dp[kk - 1][i - 2] : 0) - prices[i - 1]);
                dp[kk][i] = Math.max(dp[kk][i - 1], prices[i] + max);
            }
        }
        return dp[k][n - 1];
    }
    //234. Palindrome Linked List
    public static boolean isPalindrome_234(ListNode head) {
    	if(head == null || head.next == null) return true;
    	ListNode fast = head;
    	ListNode slow = head;
    	ListNode pre = new ListNode(0);
    	ListNode p = pre;
    	pre.next = head;
    	while(fast!=null && fast.next!=null){
    		fast = fast.next.next;
    		slow = slow.next;
    		pre = pre.next;
    	}
    	if(fast == null){
    		pre.next = null;
    		head = reverseList(head);
    		while(head!=null && slow!=null){
    			if(head.val!=slow.val) return false;
    			head = head.next;
    			slow = slow.next;
    		}
    		if(head == null && slow == null) return true;
    		else return false;
    	}
    	else {
    		pre.next = null;
    		head = reverseList(head);
    		slow = slow.next;
    		while(head!=null && slow!=null){
    			if(head.val!=slow.val) return false;
    			head = head.next;
    			slow = slow.next;
    		}
    		if(head == null && slow == null) return true;
    		else return false;
    	}
    }
    //273. Integer to English Words
    public String numberToWords(int num) {
    	if (num == 0) return "Zero";
        String[] lessThan20Words = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] tyWords = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        String[] dexWords = {"Billion", "Million", "Thousand", "Hundred"};
        int[] radix = {1000000000,1000000, 1000, 100};
        StringBuffer sb = new StringBuffer();
        int count = 0;
        for (int i = 0; i < radix.length; ++i) {
            count = num / radix[i];
            if (count == 0) continue;
            sb.append(numberToWords(count) + " ");
            sb.append(dexWords[i] + " ");
            num %= radix[i];
        }
        if (num < 20) {
            sb.append(lessThan20Words[num]);
        }else {
            sb.append(tyWords[num / 10 - 2] + " ");
            sb.append(lessThan20Words[num % 10]);
        }
        return sb.toString().trim();
    }
    
    //343. Integer Break
    public static int integerBreak(int n) {
    	if(n==2) return 1;
    	if(n==3) return 2;
    	int s = n/3;
        int u = n%3;
        if(u==2)
            return (int)Math.pow(3, s)*u;
        if(u==0)
            return (int)Math.pow(3, s);
        if(u==1)
            return (int)Math.pow(3, s-1)*4;
        return 1;
    }
    //289. Game of Life
    public static void gameOfLife(int[][] board) {
    	int x = board.length;
    	int y = 0;
    	if(x!=0)
    	y = board[0].length;
    	if(x==0 || y==0) return ;
        int [][] sol = new int[x][y];
        for(int i=0;i<x;i++){
        	for(int j=0;j<y;j++){
        		if(board[i][j]==1){
        			if(i-1>=0){
        				if(j-1>=0)
        					sol[i-1][j-1] +=1;
        				if(j>=0)
        					sol[i-1][j] +=1;
        				if(j+1<y)
        					sol[i-1][j+1] +=1;
        			}
        			if(i>=0){
        				if(j-1>=0)
        					sol[i][j-1] +=1;
        				
        				if(j+1<y)
        					sol[i][j+1] +=1;
        			}
        			if(i+1<x){
        				if(j-1>=0)
        					sol[i+1][j-1] +=1;
        				if(j>=0)
        					sol[i+1][j] +=1;
        				if(j+1<y)
        					sol[i+1][j+1] +=1;
        			}
        		}
        	}
        }
        /*for(int i=0;i<x;i++){
        	for(int j=0;j<y;j++){
        		System.out.print(sol[i][j]+" ");
        	}
        	System.out.println();
        }*/
        
        for(int i=0;i<x;i++){
        	for(int j=0;j<y;j++){
        		if(board[i][j]==1){
        			if(sol[i][j]==2||sol[i][j]==3)
        				board[i][j] = 1;
        			else if(sol[i][j]<2 || sol[i][j]>3)
        				board[i][j] = 0;
        			
        		}
        		if(board[i][j]==0 && sol[i][j]==3)
        			board[i][j] = 1;
        	}
        }
    }
    //321. Create Maximum Number
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {

        int[] maxNumber = new int[k];

        int[] leftClosest = getClosest(nums1);
        int[] rightClosest = getClosest(nums2);

        List<State> states = new LinkedList<State>();
        List<State> nextStates = new LinkedList<State>();
        states.add(new State(0, 0));

        boolean[][] stateSet = new boolean[nums1.length + 1][nums2.length + 1];
        stateSet[0][0] = true;
        for (int i = 0; i < k; i++) {
            int max = -1;// max digit
            for (State state : states) {
                stateSet[state.x][state.y] = false;
                int remainingDigits = k - i;
                int nextLeft = getNextIndex(leftClosest, state.x, remainingDigits - (nums2.length - state.y));
                int nextRight = getNextIndex(rightClosest, state.y, remainingDigits - (nums1.length - state.x));
                if (nextLeft != -1) {
                    max = Math.max(max, nums1[nextLeft]);
                }
                if (nextRight != -1) {
                    max = Math.max(max, nums2[nextRight]);
                }
            }
            maxNumber[i] = max;

            while (!states.isEmpty()) {
                State state = states.remove(states.size() - 1);
                int remainingDigits = k - i;
                int nextLeft = getNextIndex(leftClosest, state.x, remainingDigits - (nums2.length - state.y));
                int nextRight = getNextIndex(rightClosest, state.y, remainingDigits - (nums1.length - state.x));
                if (nextLeft != -1) {
                    if (nums1[nextLeft] == max && stateSet[nextLeft + 1][state.y] == false) {
                        stateSet[nextLeft + 1][state.y] = true;
                        nextStates.add(new State(nextLeft + 1, state.y));
                    }
                }
                if (nextRight != -1) {
                    if (nums2[nextRight] == max && stateSet[state.x][nextRight + 1] == false) {
                        stateSet[state.x][nextRight + 1] = true;
                        nextStates.add(new State(state.x, nextRight + 1));
                    }
                }
            }
            List<State> temp = states;
            states = nextStates;
            nextStates = temp;
        }

        return maxNumber;
    }

    private class State {
        int x, y;

        public State(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private int getNextIndex(int[] closest, int start, int needed) {
        int result = -1;
        if (start == closest.length) {
            return -1;
        }
        int index = start;
        while (index != -1 && closest.length - index >= needed) {
            result = index;
            index = closest[index];
        }

        return result;
    }

    private int[] getClosest(int[] A) {
        int[] closest = new int[A.length];
           if(A.length==0) {
            return closest;
        }
        closest[A.length - 1] = -1;

        for (int i = A.length - 2; i >= 0; i--) {
            int j = i + 1;
            while (j != -1 && A[i] >= A[j]) {
                j = closest[j];
            }
            closest[i] = j;
        }
        return closest;
    }
    //324. Wiggle Sort II
    public void wiggleSort(int[] nums) {
        int median=findKthLargest(nums,(nums.length+1)/2);
        int odd=1;
        int even=nums.length%2==0?nums.length-2:nums.length-1;
        int[] tmpArr=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            if(nums[i]>median){
                tmpArr[odd]=nums[i];
                odd+=2;
                continue;
            }
            if(nums[i]<median){
                tmpArr[even]=nums[i];
                even-=2;
                continue;
            }
        }
        while(odd<nums.length){
            tmpArr[odd]=median;
            odd+=2;
        }
        while(even>=0){
            tmpArr[even]=median;
            even-=2;
        }
        for(int i=0;i<nums.length;i++){
            nums[i]=tmpArr[i];
        }
    }
    //372. Super Pow
    public static int superPow(int a, int[] b) {
    	if(a==0 || a==1337 ||b==null || b.length==0) return 0;
    	if(a==1) return 1;
    	if(a>1337) return superPow(a%1337, b);
    	List<Integer> indexs = new ArrayList<>();
    	boolean[] visited = new boolean[1337];
    	int curr = a%1337;
    	while(!visited[curr]){
    		visited[curr] = true;
    		indexs.add(curr);
    		curr = (curr*a)%1337;
    	}
    	int t = 0;
    	for(int i=0;i<b.length;i++)
    		t = (t*10+b[i])%indexs.size();
    	if(t==0) t = indexs.size();
    	
        return indexs.get(t-1);
    }
    //373. Find K Pairs with Smallest Sums
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    	List<int[]> sol = new ArrayList<>();
    	if(nums1.length==0 || nums2.length==0 || k==0) return sol;
    	if(nums1.length*nums2.length<=k){
    		for(int i=0;i<nums1.length;i++){
    			for(int j=0;j<nums2.length;j++){
    				int [] tmp = new int[2];
    				tmp[0] = nums1[i];
    				tmp[1] = nums2[j];
    				sol.add(tmp);
    			}
    		}
    	}
    	else {
    		PriorityQueue<int[]> heap = new PriorityQueue<int[]>((a,b)->(a[0]+a[1]-b[0]-b[1]));
    		for(int i=0;i<nums1.length;i++) heap.offer(new int[]{nums1[0],nums1[1],0});
    		while(k-->0 && !heap.isEmpty()){
    			int [] t = heap.poll();
    			sol.add(new int[]{t[0],t[1]});
    			if(t[2]==nums2.length-1) continue;
    			heap.offer(new int[]{t[0],nums2[t[2]+1],t[2]+1});
    		}
    	}
        return sol;
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
		/*System.out.println(Math.pow(4.52, 212));
		System.out.println((System.nanoTime()-time)/1000000+"ms");
		time = System.nanoTime();
		
		System.out.println(myPow3(4.52, 212));*/
		/*char[][] m = {{'q','w'},{'e','r'}};*/
		//List<String> s = new ArrayList<String>(m);
		
/*		System.out.println(solveNQueens(2).size());
		System.out.println(totalNQueens(2));*/
		
		/*System.out.println(multiply2("123","1230"));
		System.out.println(7777*2111);*/
		/*int[] s = {9};
		s = plusOne(s);
		for(int i=0;i<s.length;i++)
		System.out.print(s[i]);*/
		
		/*TreeNode t = new TreeNode(1);
		t.left = new TreeNode(2);
		t.right = new TreeNode(3);
		//t.left.left = new TreeNode(4);
		t.right.left = new TreeNode(5);
		t.left.right = new TreeNode(6);
		t.left.right.right = new TreeNode(8);
		//t.right.right = new TreeNode(7);
		flatten(t);*/
		/*int[][] n = {
				{0,0,0},
				{0,1,0},
				{0,0,0},
		};
		System.out.println(uniquePathsWithObstacles(n));*/
		/*int [] s = {-2,1,-3,4,-1,2,1,-5,4};
		System.out.println(maxSubArray(s));*/
		/*int[]s={
				13,2,0,-14,-20,19,8,-5,-13,-3,20,15,20,5,13,14,-17,-7,12,-6,0,20,
				-19,-1,-15,-2,8,-2,-9,13,0,-3,-18,-9,-9,-19,17,-14,-19,-4,-16,2,
				0,9,5,-7,-4,20,18,9,0,12,-1,10,-17,-11,16,-13,-14,-3,0,2,-18,2,8,
				20,-15,3,-13,-12,-2,-19,11,11,-10,1,1,-10,-2,12,0,17,-19,-7,8,
				-19,-17,5,-5,-10,8,0,-12,4,19,2,0,12,14,-9,15,7,0,-16,-5,16,
				-12,0,2,-16,14,18,12,13,5,0,5,6
				}; 
		System.out.println(threeSumClosest(s,-59));*/
		
		//System.out.println((n&(n-1))==0);
/*		int [] n = {1,2,2,3,4,4,4,3,4,5,6,7,8};
		System.out.println(longestConsecutive(n));
		System.out.println(combinationSum2(n,8));*/
		/*TreeNode t = new TreeNode(1);
		t.left = new TreeNode(2);
		t.right = new TreeNode(3);
		t.left.left = new TreeNode(4);
		t.right.left = new TreeNode(5);
		t.left.right = new TreeNode(6);
		t.left.right.right = new TreeNode(8);
		t.left.right.left = new TreeNode(8);
		t.left.right.right.right = new TreeNode(9);*/
		
		/*System.out.println(maxDepth(t));*/
		/*System.out.println(numDistinct("rabbbit","rabbit"));*/
		/*System.out.println(inorderTraversal(t));*/
		//System.out.println(hammingWeight(2147483647));
		/*int [][] s = {
				{9,9,4},
				{6,6,8},
				{2,1,1}
		};
		System.out.println(longestIncreasingPath2(s));*/
		/*int [] s = {1,1,1,2,3,4,4,5,5,6,7};
		System.out.println(topKFrequent(s,3));*/
		/*String [] ts = {
				"4","13","5","/","+"
		};
		System.out.println(evalRPN2(ts));*/
		/*int []p= {1,1,1,1,2,2,2,2,3,4};
		System.out.println(topKFrequent(p,1));*/
/*		ListNode root = new ListNode(0);
		root.next = new ListNode(0);
		root.next.next = new ListNode(0);*/
		/*root.next.next.next= new ListNode(2);
		root.next.next.next.next = new ListNode(2);
		root.next.next.next.next.next = new ListNode(2);
		root.next.next.next.next.next.next = new ListNode(5);
		root.next.next.next.next.next.next.next = new ListNode(5);*/
		/*ListNode tt = removeElements(root,0);
		while(tt!=null){
			System.out.println(root.val);
			root = root.next;
		}*/
		/*int []m = {2,10,0,0,0,0,0,0,6,0,0,0,0,0};
		System.out.println(canJump(m));*/
/*		TreeNode t = new TreeNode(5);
		t.left = new TreeNode(4);
		t.right = new TreeNode(8);
		t.left.left = new TreeNode(11);
		t.right.left = new TreeNode(13);
		t.right.right = new TreeNode(4);
		t.right.right.left = new TreeNode(5);
		t.right.right.right = new TreeNode(1);
		t.left.left.right = new TreeNode(2);
		t.left.left.left = new TreeNode(7);
		System.out.println(pathSum(t,22));*/
		
		/*int [][] grid = {
				{2},
				{4},
				{3},
				{2}
		};
		System.out.println(minPathSum(grid));*/
/*		int[] gas = {1};
		int[]cost = {1};
		System.out.println(canCompleteCircuit(gas,cost));*/

		/*int [] s1 = {9,2,7,4,1,6,3,5,8};
		int [] s2 = {9,7,4,2,6,8,5,3,1};
		TreeNode t = null;
		System.out.println(t = buildTree106(s1,s2));*/
/*		int [] s= {1,2,3,4,5,6,7,8};
		TreeNode t = sortedArrayToBST(s);*/
/*		int [] s = {1,1,2,3,4,5,5,5};
		int [] s2 = {1,2,2,3,4};
		int p[];
		System.out.println( p = intersect(s,s2));*/
		

/*		int[] s = {5,2,6,1};
		System.out.println(countSmaller(s));*/
/*		int [] nums = {1,3,5};
		NumArray s = new NumArray(nums);
		System.out.println(s.sumRange(0, 2));*/
		/*TreeNode t = new TreeNode(5);
		t.left = new TreeNode(4);
		t.right = new TreeNode(8);
		t.left.left = new TreeNode(11);
		t.right.left = new TreeNode(13);
		t.right.right = new TreeNode(4);
		t.right.right.left = new TreeNode(5);
		t.right.right.right = new TreeNode(1);
		t.left.left.right = new TreeNode(2);
		t.left.left.left = new TreeNode(7);
		System.out.println(levelOrderBottom(t));*/

		/*String [] s = {
				"eat", "tea", "tan", "ate", "nat", "bat"
		};*/
		/*ListNode root = new ListNode(1);
		root.next = new ListNode(2);
		root.next.next = new ListNode(3);
		root.next.next.next= new ListNode(4);
		root.next.next.next.next = new ListNode(5);
		root.next.next.next.next.next = new ListNode(6);
		ListNode t = null;
		t  = reverseList(root);*/
/*		String[] n = {"abcd","dcba","lls","s","sssll"};
		System.out.println(palindromePairs(n));*/
/*		ListNode root = new ListNode(1);
		root.next = new ListNode(1);
		root.next.next = new ListNode(1);
		root.next.next.next= new ListNode(4);
		root.next.next.next.next = new ListNode(4);
		root.next.next.next.next.next = new ListNode(6);*/
		/*Set<String> set = new HashSet<String>();
		
		set.add("hot");
		set.add("dog");
		set.add("cog");
		set.add("pot");
		set.add("dot");
		System.out.println(findLadders("hot","dog",set));*/
		/*System.out.println(isInterleave("aabcc","dbbca","aadbbcbcac"));*/
/*		System.out.println(isIsomorphic2("title","plpty"));*/

/*		Set<String> s1 = new HashSet<String>();
		s1.add("hit");
		
		s1.add("hot");
		s1.add("dot");
		s1.add("lot");
		s1.add("log");
		s1.add("dog");*/
		/*System.out.println(minWindow("ADOBECODEBANC","ABC"));*/
/*		int [] s = {4,3,1,5,2,6};
		System.out.println(missingNumber2(s));*/
/*		int [] nums = {3,2,1,4,5,3,3,2,4,2,1,4};
		nextPermutation(nums);*/
/*		int [] s = {1,2,3,2,6,5,4,3};
		System.out.println(findPeakElement(s));*/
/*		int [] s = {5,23,42,4,1,8,10,11,3};
		System.out.println(lengthOfLIS2(s));*/
/*		TreeNode t = new TreeNode(5);
		t.left = new TreeNode(4);
		t.right = new TreeNode(8);
		t.left.left = new TreeNode(11);
		t.right.left = new TreeNode(13);
		t.right.right = new TreeNode(4);*/
/*		char [][] m = {
				{'0','0','0','1'},
				{'1','1','0','1'},
				{'1','1','1','1'},
				{'0','1','0','1'},
				{'0','1','1','1'},
		};
		System.out.println(maximalSquare(m));*/
/*		int []s = {1,1,1,2,2,2,2,2,2,2,4,4,5,6};
		System.out.println(removeDuplicates2(s));*/
/*		String s = "aabba";
		System.out.println(partition(s));*/
/*		int []s={6,8,3,1,7,2,4,5};
		mergesort_2(s);
		System.out.println(findKthLargest2(s,3));
		for(int i=0;i<s.length;i++)
			System.out.println(s[i]);*/
/*		ListNode root = new ListNode(5);
		root.next = new ListNode(1);
		root.next.next = new ListNode(6);
		root.next.next.next= new ListNode(3);
		root.next.next.next.next = new ListNode(2);
		root.next.next.next.next.next = new ListNode(4);
		root = sortList(root);*/
/*		System.out.println(shortestPalindrome("aacecaaa"));*/
		/*System.out.println(removeDuplicateLetters("bacdacbd"));*/
/*		int [] s = {6,6,7};
		int [] z = searchRange(s,6);
		for(int i:z)
			System.out.println(i);*/
/*		ListNode root = new ListNode(1);
		root.next = new ListNode(2);
		root.next.next = new ListNode(3);
		root.next.next.next= new ListNode(4);
		root.next.next.next.next = new ListNode(5);
		root = swapPairs(root);*/
		/*int [][] s = {
				{5,8},
				{3,5},
				{1,9},
				{4,5},
				{0,2},
				{1,9},
				{7,8},
				{4,9}
		};
		int [] t ;
		System.out.println(t = findOrder(10,s));*/
/*		List[] s = new List[10];
		s[0] = new ArrayList();
		s[0].add(123);*/
/*		int [][] s = {
				{1,0},
				{3,1},
				{2,3},
				{1,2}
		};
		System.out.println(canFinish(4,s));*/
/*		TreeNode t = new TreeNode(1);
		t.left = new TreeNode(2);
		t.right = new TreeNode(3);
		t.left.left = new TreeNode(11);
		t.right.left = new TreeNode(13);
		t.right.right = new TreeNode(4);
		System.out.println(postorderTraversal2(null));*/
/*		int [] s = {2,5,1,2,6,4,3,1,2,1,7};
		System.out.println(maxProfit3(s));*/
/*		System.out.println(findRepeatedDnaSequences2("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));*/
/*		System.out.println(reverseBits(2147483648L));*/
/*		int [] s= {1,5,2,0,-1,-4,6,-3};
		System.out.println(countRangeSum(s,-2,2));*/
/*		ListNode root = new ListNode(1);
		root.next = new ListNode(2);
		root.next.next = new ListNode(3);
		root.next.next.next= new ListNode(3);
		root.next.next.next.next = new ListNode(3);
		root.next.next.next.next.next = new ListNode(3);
		ListNode t ;
		System.out.println(t = deleteDuplicates2(root));*/
/*		System.out.println(strStr("mississippi","issip"));
		String s1 = "asgsgdas";
		s1.indexOf("ss");*/
		/*int [] s = {4,5,5,5,6,7,8,1,2,3,3};
		System.out.println(findMin2(s));*/
		/*System.out.println(reverseVowels(""));*/
		/*String[][] t = {
				{"AXA","EZE"},{"EZE","AUA"},{"ADL","JFK"},{"ADL","TIA"},{"AUA","AXA"},{"EZE","TIA"},{"EZE","TIA"},{"AXA","EZE"},{"EZE","ADL"},{"ANU","EZE"},{"TIA","EZE"},{"JFK","ADL"},{"AUA","JFK"},{"JFK","EZE"},{"EZE","ANU"},{"ADL","AUA"},{"ANU","AXA"},{"AXA","ADL"},{"AUA","JFK"},{"EZE","ADL"},{"ANU","TIA"},{"AUA","JFK"},{"TIA","JFK"},{"EZE","AUA"},{"AXA","EZE"},{"AUA","ANU"},{"ADL","AXA"},{"EZE","ADL"},{"AUA","ANU"},{"AXA","EZE"},{"TIA","AUA"},{"AXA","EZE"},{"AUA","SYD"},{"ADL","JFK"},{"EZE","AUA"},{"ADL","ANU"},{"AUA","TIA"},{"ADL","EZE"},{"TIA","JFK"},{"AXA","ANU"},{"JFK","AXA"},{"JFK","ADL"},{"ADL","EZE"},{"AXA","TIA"},{"JFK","AUA"},{"ADL","EZE"},{"JFK","ADL"},{"ADL","AXA"},{"TIA","AUA"},{"AXA","JFK"},{"ADL","AUA"},{"TIA","JFK"},{"JFK","ADL"},{"JFK","ADL"},{"ANU","AXA"},{"TIA","AXA"},{"EZE","JFK"},{"EZE","AXA"},{"ADL","TIA"},{"JFK","AUA"},{"TIA","EZE"},{"EZE","ADL"},{"JFK","ANU"},{"TIA","AUA"},{"EZE","ADL"},{"ADL","JFK"},{"ANU","AXA"},{"AUA","AXA"},{"ANU","EZE"},{"ADL","AXA"},{"ANU","AXA"},{"TIA","ADL"},{"JFK","ADL"},{"JFK","TIA"},{"AUA","ADL"},{"AUA","TIA"},{"TIA","JFK"},{"EZE","JFK"},{"AUA","ADL"},{"ADL","AUA"},{"EZE","ANU"},{"ADL","ANU"},{"AUA","AXA"},{"AXA","TIA"},{"AXA","TIA"},{"ADL","AXA"},{"EZE","AXA"},{"AXA","JFK"},{"JFK","AUA"},{"ANU","ADL"},{"AXA","TIA"},{"ANU","AUA"},{"JFK","EZE"},{"AXA","ADL"},{"TIA","EZE"},{"JFK","AXA"},{"AXA","ADL"},{"EZE","AUA"},{"AXA","ANU"},{"ADL","EZE"},{"AUA","EZE"}
		};
		System.out.println(findItinerary(t));*/
		/*System.out.println(minDistance("osdfasfsfe","poefowefoefe"));*/
/*		int [][] s = {
				{-2,-3,3},
				{-5,-10,1},
				{10,30,-5}
		};
		System.out.println(calculateMinimumHP(s));*/
/*		TreeNode t = new TreeNode(1);
		t.left = new TreeNode(2);
		t.right = new TreeNode(3);
		t.left.left = new TreeNode(11);
		t.right.left = new TreeNode(13);
		t.right.right = new TreeNode(4);
		System.out.println(levelOrder(t));*/
		/*int s[] = {1,1,2,5,4,3,3,2};
		int t[] = {1,2,2,2,3};
		int []y = intersection(s,t);
		for(int i:y)
		System.out.println(i);*/
		/*TreeNode t = new TreeNode(6);
		t.left = new TreeNode(2);
		t.right = new TreeNode(8);
		t.left.left = new TreeNode(0);
		t.right.left = new TreeNode(7);
		t.right.right = new TreeNode(9);
		t.left.right = new TreeNode(4);
		t.left.right.right = new TreeNode(5);
		t.left.right.left = new TreeNode(3);
		TreeNode t1 = new TreeNode(3);
		TreeNode t2 = new TreeNode(5);
		TreeNode t3 = new TreeNode(0);
		System.out.println(lowestCommonAncestor2(t3,t1,t2).val);*/
		/*System.out.println(longestValidParentheses("))((()(()()))))"));*/
		/*System.out.println(getHint("1123","0111"));*/
		/*System.out.println(divide(6,3));*/
		/*TreeNode t = new TreeNode(1);
		t.left = new TreeNode(2);
		t.right = new TreeNode(2);
		t.left.left = new TreeNode(3);
		t.right.left = new TreeNode(4);
		t.right.right = new TreeNode(3);
		System.out.println(isSymmetric(t));*/
		/*ListNode root = new ListNode(1);
		root.next = new ListNode(2);
		root.next.next = new ListNode(3);
		root.next.next.next= new ListNode(4);
		root.next.next.next.next = new ListNode(5);
		root.next.next.next.next.next = new ListNode(6);
		root.next.next.next.next.next.next = new ListNode(7);
		root.next.next.next.next.next.next.next = new ListNode(8);
		reorderList(root);*/
		/*int [][] s = {
				{0,3},
				{1,3},
				{2,3},
				{4,3},
				{5,4}
		};
		System.out.println(findMinHeightTrees(6,s));*/
		/*int []s = {1,3,5,9,12};
		for(int i=0;i<15;i++)
		System.out.println(i+":"+searchInsert(s,i));*/
		/*int []s = {1,0};
		sortColors(s);*/
		/*ListNode root = new ListNode(1);
		root.next = new ListNode(3);
		root.next.next = new ListNode(4);
		root.next.next.next= new ListNode(8);
		root.next.next.next.next = new ListNode(11);
		root.next.next.next.next.next = new ListNode(21);
		root.next.next.next.next.next.next = new ListNode(24);
		root.next.next.next.next.next.next.next = new ListNode(41);
		ListNode root2 =new ListNode(0);
		root2.next = new ListNode(2);
		root2.next.next = new ListNode(5);
		root2.next.next.next= new ListNode(9);
		root2.next.next.next.next = new ListNode(14);
		root2.next.next.next.next.next = new ListNode(16);
		root2.next.next.next.next.next.next = new ListNode(27);
		root2.next.next.next.next.next.next.next = new ListNode(29);
		ListNode root3 = new ListNode(1);
		root3.next = new ListNode(2);
		root3.next.next = new ListNode(8);
		root3.next.next.next= new ListNode(15);
		root3.next.next.next.next = new ListNode(16);
		root3.next.next.next.next.next = new ListNode(22);
		root3.next.next.next.next.next.next = new ListNode(25);
		root3.next.next.next.next.next.next.next = new ListNode(59);
		ListNode[]s= new ListNode[3];
		s[0] = root;
		s[1] = root2;
		s[2] = root3;
		ListNode t = null;
		System.out.println(t = mergeKLists2(s));*/
		/*TreeNode t = new TreeNode(0);
		t.left = new TreeNode(1);
		t.right = new TreeNode(8);
		t.left.left = new TreeNode(0);
		t.right.left = new TreeNode(7);
		t.right.right = new TreeNode(9);*/
		/*System.out.println(sumNumbers(t));*/
		/*String [] s = {
				"abcw", "baz", "foo", "bar", "xtfn", "abcdef"

		};
		System.out.println(maxProduct(s));*/
		/*System.out.println(addOperators("105",5));*/
		/*System.out.println(isValid("())"));*/
		/*TreeNode t = new TreeNode(4);
		t.left = new TreeNode(2);
		t.right = new TreeNode(7);
		t.left.left = new TreeNode(1);
		t.left.right = new TreeNode(3);
		t.left.right.left = new TreeNode(5);
		t.left.right.right = new TreeNode(8);
		t.right.left = new TreeNode(6);
		t.right.right = new TreeNode(9);
		System.out.println(t = invertTree(t));*/
		/*System.out.println(isPowerOfThree(9));*/
		/*ListNode root3 = new ListNode(1);
		root3.next = new ListNode(2);
		root3.next.next = new ListNode(8);
		root3.next.next.next= new ListNode(15);
		root3.next.next.next.next = new ListNode(16);
		ListNode root = new ListNode(1);
		root.next = new ListNode(3);
		
		System.out.println(getIntersectionNode(root,root3));*/
		/*System.out.println(simplifyPath("/home////foo/"));*/
		/*int []s = {1,-5,3,-7,-3,9,2};
		System.out.println(maxProduct(s));*/
		/*Set<String> hash = new HashSet<String>();
		hash.add("aaaa");
		hash.add("aa");
		hash.add("a");
		hash.add("sand");
		hash.add("dog");
		System.out.println(wordBreak("aaaaaaa",hash));*/
		/*ListNode root = new ListNode(1);
		root.next = new ListNode(2);
		root.next.next = new ListNode(3);
		root.next.next.next= new ListNode(4);
		root.next.next.next.next = new ListNode(5);
		root = rotateRight(root,4);
		while(root!=null){
			System.out.println(root.val);
			root = root.next;
		}*/
		/*int [] s = {1,2,2,2};
		System.out.println(subsetsWithDup(s));*/
		/*ListNode root = new ListNode(1);
		root.next = new ListNode(2);
		root.next.next = new ListNode(3);
		root.next.next.next= new ListNode(4);
		root.next.next.next.next = new ListNode(5);
		root = reverseBetween(root,4,5);
		while(root!=null){
			System.out.println(root.val);
			root = root.next;
		}*/
		/*System.out.println("abc".substring(1, 2));*/
		/*Set<String> set = new HashSet<>();
		set.add("goal");
		set.add("goals");
		set.add("go");
		set.add("special");
		System.out.println(wordBreak2("goalspecials",set));*/
		/*TreeNode t = new TreeNode(4);
		t.left = new TreeNode(2);
		t.right = new TreeNode(7);
		t.left.left = new TreeNode(1);
		t.left.right = new TreeNode(3);
		t.right.left = new TreeNode(6);
		t.right.right = new TreeNode(9);
		BSTIterator i = new BSTIterator(t);
		while(i.hasNext()) System.out.println(i.next());*/
		ListNode root = new ListNode(1);
		/*root.next = new ListNode(1);*/
		/*root.next.next = new ListNode(3);
		root.next.next.next= new ListNode(3);
		root.next.next.next.next = new ListNode(2);
		root.next.next.next.next.next = new ListNode(1);*/
		/*System.out.println(isPalindrome_234(root));*/
		/*System.out.println(integerBreak(2));*/
		int [][]s = {
				{0,1,0,0,1},
				{0,1,1,0,1},
				{0,0,0,1,1},
				{0,0,0,0,1},
				{0,0,0,0,1}
		};
		gameOfLife(s);
		System.out.println();
		System.out.println((System.nanoTime()-time)/1000000+"ms");
	}

}
