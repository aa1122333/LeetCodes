package leetcodeTest;

import java.util.Scanner;

public class Wangyi {
	//n�ֽڳ�ABC���в�����abc acb bac bca cab cba�������ʹ������� 
	// aaaabbbbbbccccc +1
	// aaaabcaaaab  x ��Ϊ�м���abc����
	static long summy = 0;
	//f(n)=2*f(n-1)+f(n-2)
	public static void same(long deep, long last, long maxdeep) {
		if (maxdeep == deep) {
			summy += last;
			return;
		}
		same(deep + 1, last, maxdeep);
		diff(deep + 1, last * 2, maxdeep);

	}
	public static void diff(long deep, long last, long maxdeep) {
		if (maxdeep == deep) {
			summy += last;
			return;
		}
		diff(deep + 1, last, maxdeep);
		same(deep + 1, last, maxdeep);

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			summy=0;
			long n = sc.nextInt();
			if (n == 1) {
				System.out.println(3);
				continue;
			}
			
			if (n == 2) {
				System.out.println(9);
				continue;
			}
			same(1, 1, n);
			long out = 3*summy;
			System.out.println(out);
		}
	}
}
