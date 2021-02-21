import java.io.*;
import java.util.*;

public class Solution2005 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] fibo = new int[2][N+1];
			fibo[0][1] = 1;
			
			System.out.println("#"+t);
			for(int r=1; r<=N; r++) {
				int curR = r%2;
				int befoR = (r+1)%2;
				fibo[curR][1] = 1;
				System.out.print(fibo[curR][1]+" ");
				for(int i=2; i<=r; i++) {
					fibo[curR][i] = fibo[befoR][i-1] + fibo[befoR][i];
					System.out.print(fibo[curR][i]+" ");
				}
				System.out.println();
			}
		}
	}
}
