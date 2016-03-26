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
					l[i][j]=(char) (s%10);//Êý×Öµ¹Ðò
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
	//
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
		
	}

}
