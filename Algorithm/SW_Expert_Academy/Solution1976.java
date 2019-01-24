import java.util.*;

public class Solution1976 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t=1; t<=T; t++) {
			int H1 = sc.nextInt();
			int M1 = sc.nextInt();
			int H2 = sc.nextInt();
			int M2 = sc.nextInt();
			
			int H = H1 + H2;
			int M = M1 + M2;
			if(M >= 60) {
				H += M/60;
				M %= 60;
			}
			if(H > 12) {
				H %= 12;
				if(H==0) H = 12;
			}
			
			System.out.printf("#%d %d %d\n",t,H,M);
		}
	}
}
