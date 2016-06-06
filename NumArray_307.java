package leetcodeTest;



public class NumArray_307 {
	
/*	public Node root;*/
	int [] nums;
	int [] bit;

	
	public NumArray_307(int[] nums) {

		this.nums = nums;
		this.bit = new int[nums.length+1];
        for(int i=0;i<nums.length;i++)
        	addVal(i,nums[i]);
        
    }

    public void update(int i, int val) {
        int delta = val-nums[i];
        addVal(i, delta);
        nums[i] = val;
    }

    public int sumRange(int i, int j) {
    	int sum1 = sumOfAll( i-1);
    	int sum2 = sumOfAll( j);
    	
        return sum2-sum1;
    }
    
    public void addVal(int index,int curr){
    	
    	for(int i= index+1;i<bit.length;i+=i&-i){
    		bit[i]+=curr;
    	}
    }
    
    public int sumOfAll(int index){
    	int sum=0;
    	for(int i=index+1;i>0;i-=i&-i){
    		sum+=bit[i];
    	}
    	return sum;
    }
    /*public class Node{
    	int left,right;
    	Node leftchild;
    	Node rightchild;
    	int currsum;
    	
    	public void createTree(int l,int r,Node root,int[]nums){
    		root = new Node();
    		root.left = l;
    		root.right = r;
    		int mid = (l+r)>>1;
    		createTree(l,mid,root.leftchild,);
    		createTree(mid, r, root.rightchild);
    	}
    }*/
    public static  void main(String[] args) {
    	int [] nums = {1,3,5,7};
		NumArray_307 s = new NumArray_307(nums);
		System.out.println(s.sumRange(0, 1));
		s.update(1, 2);
		System.out.println(s.sumRange(0, 1));
    }
    
}
