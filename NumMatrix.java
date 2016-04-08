package leetcodeTest;

public class NumMatrix {
	int[][] sum ;
	public NumMatrix(int[][] matrix) {
        if(matrix.length!=0){
        	sum = new int[matrix.length+1][matrix[0].length+1];
	        for(int i=matrix.length-1;i>=0;i--){
	        	for(int j=0;j<matrix[0].length;j++){
	        		sum[i][j+1]= sum[i][j]+sum[i+1][j+1]-sum[i+1][j]+matrix[i][j];
	        	}
	        }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
    	if(sum!=null)
    		return sum[row1][col2+1]-sum[row1][col1]-sum[row2+1][col2+1]+sum[row2+1][col1];
    	else 
    		return 0;
    }
	public static void main(String[] args) {
		int [][] m = {
				{}
					 };
		NumMatrix nm = new NumMatrix(m);
		System.out.println(nm.sumRegion(0, 0, 0, 0));
		// TODO Auto-generated method stub

	}

}
