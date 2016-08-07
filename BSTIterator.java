package leetcodeTest;

import java.util.ArrayList;
import java.util.List;

import leetcodeTest.Solution.TreeNode;

public class BSTIterator {
	
	List<Integer> nums = new ArrayList<Integer>();
	int index = 0;
	public BSTIterator(TreeNode root) {
		nums.clear();
		index = 0;
        addnums(root);
    }
	
	public void addnums(TreeNode root){
		if(root == null) return ;
		addnums(root.left);
		nums.add(root.val);
		addnums(root.right);
	}

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return index<nums.size();
    }

    /** @return the next smallest number */
    public int next() {
        return nums.get(index++);
    }
}
