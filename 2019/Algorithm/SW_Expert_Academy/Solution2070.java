import java.util.Scanner;

public class Solution2070 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int t=1; t<=T; t++) {
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			
			if(v1 < v2) System.out.println("#"+t+" "+"<");
			else if(v1 > v2) System.out.println("#"+t+" "+">");
			else System.out.println("#"+t+" "+"=");
		}
	}
}
