package leetcodeTest;



public class BinaryNode {
	public int key;
	public BinaryNode left;
	public BinaryNode right;
	public BinaryNode(int key){
		this.key = key;
	}
	public   BinaryNode add(BinaryNode root,int key){
		if(root == null) root = new BinaryNode(key);
		if(root.key<key) root.right = add(root.right,key);
		if(root.key>key) root.left = add(root.left,key);
		return root;
	}
	
	public  boolean find(BinaryNode root, int key){
		if(root == null) return false;
		else if(root.key==key) return true;
		return root.key>key?find(root.left,key):find(root.right,key);
	}
	
	public  BinaryNode find_Binary(BinaryNode root, int key){
		if(root == null) return null;
		else if(root.key==key) return root;
		return root.key>key?find_Binary(root.left,key):find_Binary(root.right,key);
	}
	
	
	
	public  BinaryNode remove(BinaryNode root,int key){
		if(root == null) return null;
		if(root.key<key) root.right = remove(root.right,key);
		if(root.key>key) root.left = remove(root.left,key);
		if(root.key==key) {
			if(root.left!=null && root.right!=null){
				root.key = root.findMin(root.right).key;
				root.right = root.find_Binary(root.right, root.key);
			}
			else {
				
				root = root.left==null?root.right:root.left;
			}
		}
		return root;
	}
	public  BinaryNode findMin(BinaryNode root){
		if(root==null) return null;
		if(root.left!=null) return findMin(root.left);
		else return root;
	}
}
