package leetcodeTest;

public class NumArray {
	int [] nums;
	int [] sum;
    /*
     * public NumArray(int[] nums) {
        this.nums = nums;
    }

    public int sumRange(int i, int j) {
    	int sum=0;
    	for(int k=i;k<=j;k++){
    		sum+=nums[k];
    	}
        return sum;
    }*/
    public NumArray(int[] nums) {
    	sum = new int[nums.length+1];
    	if(nums.length!=0) sum[0]=nums[0];
        for(int i=0;i<nums.length;i++){
        	sum[i+1]=nums[i]+sum[i];
        }
    }
    public int sumRange(int i, int j) {
    	
        return sum[j+1]-sum[i];
    }
    public static  void main(String[] args) {
    	int []s = {2,3,4,5,6,8};
    	NumArray nm = new NumArray(s);
    	System.out.println(nm.sumRange(0, 2));
    	int [] s2 = {9,8,7,6,5};
    	NumArray nm2 = new NumArray(s2);
    	System.out.println(nm2.sumRange(0, 2));
    	System.out.println(nm.sumRange(0, 2));
    	System.out.println(nm2.sumRange(2, 2));
    }
}
