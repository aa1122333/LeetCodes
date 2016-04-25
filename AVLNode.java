package leetcodeTest;

import java.util.HashSet;


public class AVLNode<K extends Comparable<K>,V>implements Comparable<AVLNode<K,V>>  {
	public K key;
	public int height;
	public AVLNode<K,V> left;
	public AVLNode<K,V> right;
	public AVLNode(K key){
		this.key = key;
	}
	public HashSet<V> attach = new HashSet<V>();
	
	@Override
	public int compareTo(AVLNode<K,V> root){
		return this.key.compareTo(root.key);
	}
	public AVLNode(K key,V value,AVLNode<K,V> left,AVLNode<K,V> right){
		this.key = key;
		this.attach.add(value);
		this.left = left;
		this.right = right;
	}
	public int Height(AVLNode<K, V> root){
		return root==null?-1:root.height;
	}
	public  AVLNode<K,V> findMin(AVLNode<K,V> root){
		if(root==null) return null;
		if(root.left!=null) return findMin(root.left);
		else return root;
	}
	//Rotate :first condition ×ó×óÐý×ª
	public AVLNode<K,V> RotateLL(AVLNode<K,V> node){
		AVLNode<K,V> top = node.left;
		node.left = top.right;
		top.right = node;
		node.height = Math.max(Height(node.left), Height(node.right)) + 1 ;
		top.height = Math.max(Height(top.left), Height(top.right)) + 1;
		return top;
	}
	
	//Rotate :second condition ÓÒÓÒÐý×ª
	public AVLNode<K,V> RotateRR(AVLNode<K,V> node){
		AVLNode<K,V> top = node.right;
		node.right = top.left;
		top.left = node;
		node.height = Math.max(Height(node.left), Height(node.right))+1;
		top.height = Math.max(Height(top.left), Height(top.right))+1;
		return top;
	}
	
	//Rotate :third condition 
	public AVLNode<K,V> RotateLR(AVLNode<K,V> node){
		node.left = RotateRR(node.left);
		return RotateLL(node);
	}
	
	//Rotate :fourth condition
	public AVLNode<K,V> RotateRL(AVLNode<K,V> node){
		node.right = RotateLL(node.right);
		return RotateRR(node);
	}
	
	//Add nodes
	public AVLNode<K,V> Add(K key,V value,AVLNode<K,V> root){
		if(root == null)
			root = new AVLNode<K,V>(key,value,null,null);
		
		//add to left
		if(key.compareTo(root.key)<0){
			root.left = Add(key, value, root.left);
			
			//need to Rotate?
			if(Height(root.left)-Height(root.right)==2){
				if(key.compareTo(root.left.key)<0)
					root = RotateLL(root);
				else 
					root = RotateLR(root);
			}
		}
		//add to right
		if(key.compareTo(root.key)>0){
			root.right = Add(key, value, root.right);
			
			//need to Rotate?
			if(Height(root.right)-Height(root.left)==2){
				if(key.compareTo(root.right.key)>0)
					root = RotateRR(root);
				else root = RotateRL(root);
			}
		}
		
		if(key.compareTo(root.key)==0)
			root.attach.add(value);
		root.height = Math.max(Height(root.left), Height(root.right))+1;
		return root;
	}
	
	//delete nodes
	public AVLNode<K,V> Remove(K key,V value,AVLNode<K,V> root){
		if(root == null)
			return null;
		if(key.compareTo(root.key)<0){
			root.left = Remove(key, value, root.left);
			if(Height(root.left)-Height(root.right) == 2){
				if(key.compareTo(root.left.key)<0)
					root = RotateLL(root);
				else 
					root = RotateLR(root);
			}
		}
		if(key.compareTo(root.key)>0){
			root.right = Remove(key, value, root.right);
			if(Height(root.right)-Height(root.left) == 2){
				if(key.compareTo(root.right.key)>0)
					root = RotateRR(root);
				else 
					root = RotateRL(root);
			}
		}
		
		if(key.compareTo(root.key)==0){
			if(root.attach.size()>1)
				root.attach.remove(value);
			else 
				if(root.left!=null && root.right!=null){
					root.key = findMin(root.right).key;
					root.right = Remove(root.key, value, root.right);
				}
				else {
					root = root.left == null?root.right:root.left;
					if(root == null)
						return null;
				}
		}
		root.height = Math.max(Height(root.left), Height(root.right))+1;
		return root;
	}
	
	//find nodes
	public AVLNode<K,V> find(K key,AVLNode<K,V> root){
		if(root == null) return null;
		if(root.key == key) return root;
		else return root.key.compareTo(key)>0?find(key,root.left):find(key,root.right);
	}
	
	//find range
	public HashSet<V> SearchRange(K min,K max,HashSet<V> hashSet,AVLNode<K,V> root){
		if(root == null)
			 return hashSet;
		if(min.compareTo(root.key)<0)
			SearchRange(min, max, hashSet, root.left);
		if(min.compareTo(root.key)<=0&&max.compareTo(root.key)>=0){
			for(V item :root.attach)
				hashSet.add(item);
		}
		if(min.compareTo(root.key)>0||max.compareTo(root.key)>0)
			SearchRange(min, max, hashSet, root.right);
		return hashSet;
	}
}
