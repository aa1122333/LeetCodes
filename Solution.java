package leetcodeTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
		int []diff = {1,2,3,4,5,6,7,8,1,2,3,5,6,7};
		diff =singleNumber3(diff);
		System.out.println(diff[0]+" "+diff[1]);
	}

}
