import java.io.*;
import java.util.*;

public class Solution5431 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int[] dp = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C; c++) {
				int person = Integer.parseInt(st.nextToken()) - 1;
				dp[person]++;
			}
			
			System.out.print("#"+t+" ");
			for(int i=0; i<N; i++) {
				if(dp[i] == 0)
					System.out.print((i+1)+" ");
			}
			System.out.println();
		}
	}
}
