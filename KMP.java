package leetcodeTest;

public class KMP {
	
	public int[] getNextArray(char[] str){
		int[] next = new int[str.length];
		
		if(str.length<=1) return  new int[]{-1};
		next[0] = -1;
		next[1] = 0;
		int pos = 2;
		int t = 0;
		while(pos<str.length){
			if(str[pos-1]==str[t]){
				next[pos++] = ++t;
			}
			else if(t>0){
				t = next[t];
			}
			else {
				next[pos++] = 0;
			}
		}
		return next;
	}
	//s1=text s2=pattern 
	public int doKmp(String s1,String s2){
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int [] next = getNextArray(str2);
		int i = 0;
		int j = 0;
		while(i<s1.length()){
			if(str1[i]==str2[j]){
				i++;
				j++;
				if(j==str2.length)
					return i-1;
			}
			else {
				j = next[j];
				if(j==-1){
					j++;
					i++;
				}
					
			}
		}
		return 0;
	}
	public static void main(String[] args) {
		KMP p = new KMP();
		String s1 = "abababbababaaassabbabsaasba";
		String s2 = "abbab";
		System.out.println(p.doKmp(s1,s2));
	}
}
