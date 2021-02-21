import java.io.*;
import java.util.*;

public class Solution1946 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t=1; t<=T; t++) {
			int N = sc.nextInt();
			
			System.out.printf("#%d\n",t);
			int cnt = 0;
			for(int n=0; n<N; n++) {
				char ch = sc.next().charAt(0);
				int c = sc.nextInt();
				
				for(int i=0; i<c; i++) {
					System.out.print(ch);
					cnt++;
					
					if(cnt == 10) {
						cnt = 0;
						System.out.println();
					}
				}
			}
			System.out.println();
		}
	}
}
