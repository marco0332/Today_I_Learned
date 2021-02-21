import java.util.*;

public class Solution2029 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t=1; t<=T; t++) {
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			
			System.out.println("#"+t+" "+v1/v2+" "+v1%v2);
		}
	}
}
