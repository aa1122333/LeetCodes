package leetcodeTest;

import java.io.ObjectInputStream.GetField;
import java.lang.Character.Subset;

public class LCS {
	
	public static int getMax(String str1,String str2){
		int l1 = str1.length();
		int l2 = str2.length();
		int [][] p = new int [l1+1][l2+1];
		int maxLength = 0;
		int maxx=0,maxy=0;
		for(int i=1;i<l1;i++){
			for(int j=1;j<l2;j++){
				if(str1.charAt(i-1)==str2.charAt(j-1) && p[i-1][j-1]!=0){
					p[i][j] = 1+p[i-1][j-1];
					if(p[i][j]>maxLength){ 
						maxLength = p[i][j];
						maxx=i;
						maxy=j;
					}
				}
				else if(str1.charAt(i-1)==str2.charAt(j-1))
					p[i][j] = 1;
				else 
					p[i][j] = 0;
				
			}
		}
		System.out.println("resultArray: ");  
        for(int i = 1; i <= l1; i++){  
            for(int j = 1; j <= l2; j++){  
                System.out.print(p[i][j] + ", ");  
            }  
            System.out.println();  
        }  
        System.out.println(str2.substring(maxx,maxy));
        /*for(int i = 0; i <= l1; i++)  
            for(int j = 0; j <= l2; j++){  
                if(p[i][j] > maxLength)  
                    maxLength = p[i][j];  
            }  */
		return maxLength;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1="abaab";
		String s2="ccbcabaababaab";
		System.out.println(getMax(s1, s2));
	}

}
