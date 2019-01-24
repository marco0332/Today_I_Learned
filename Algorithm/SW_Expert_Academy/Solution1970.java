import java.util.*;

public class Solution1970 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] money = {50000,10000,5000,1000,500,100,50,10};
		int len = money.length;
		
		int T = sc.nextInt();
		for(int t=1; t<=T; t++) {
			int N = sc.nextInt();
			
			System.out.printf("#%d\n",t);
			for(int i=0; i<len; i++) {
				System.out.print(N/money[i]+" ");
				N %= money[i];
			}
			System.out.println();
		}
	}
}
