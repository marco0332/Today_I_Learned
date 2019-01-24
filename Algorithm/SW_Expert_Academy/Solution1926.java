import java.io.*;
import java.util.*;

public class Solution1926 {

	public static int isThree(int val) {
		int cnt = 0;
		
		while(val > 0) {
			int v = val%10;
			
			if(v != 0 && v%3 == 0) cnt++;
			val/=10;
		}
		
		return cnt;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();		
		for(int n=1; n<=N; n++) {
			int check = isThree(n);
			if(check == 0)
				System.out.print(n+" ");
			else
				System.out.print((new String(new char[check]).replace("\0", "-"))+" ");
		}
	}

}
