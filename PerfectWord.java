package leetcodeTest;

public class PerfectWord {
	
	public static boolean isPerfectNumber(int num){
		int front = 2;
		int end = Integer.MAX_VALUE;
		int sum = 1;
		while(front<end){
			if(num%front==0){
				end = num/front;
				sum = sum + front + end;
			}
			front++;
		}
		if(sum==num) return true;
		else return false;
	}
	
	public static boolean isPerfectNumber2(int num){
		double front = Math.sqrt(num);
		int end = Integer.MAX_VALUE;
		int sum = 1;
		
		for(int i=2;i<front;i++){
			if(num%i==0){
				sum+=i;
			}
		}
		int p = (int)Math.ceil(front);
		for(int i=p;i>1;i--){
			if(num%i==0 ) {
				sum+=(num/i);
			}
		}
		if(sum==num) return true;
		else return false;
	}
	
	public static boolean lucas_lehmer_Test(int k){
		if(k<=2) return k==2;
		long s = 4;
		long m = mersenne(k);
		for(int i=0;i<k-2;i++){
			s=s*s-2;
			if(m!=0)
				s%=m;
		}
		return s==0;
				
	}
	public static long mersenne(int k){
		return (1L<<k) -1L;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Long  time = System.nanoTime();
		
		System.out.println(isPerfectNumber2(45734521));
		System.out.println((System.nanoTime()-time)/1000000+"ms");
		time = System.nanoTime();
		System.out.println(isPerfectNumber(45734521));
		System.out.println((System.nanoTime()-time)/1000000+"ms");
	}

}
