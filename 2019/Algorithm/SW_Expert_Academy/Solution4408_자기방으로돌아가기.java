import java.io.*;
import java.util.*;

public class Solution4408_자기방으로돌아가기 {
	static int N, answer;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine().trim());
			answer = 0;
			dp = new int[201];
			
			for(int n=0; n<N; n++) {
				String[] input = br.readLine().split(" ");
				int a = (Integer.parseInt(input[0])+1)/2;
				int b = (Integer.parseInt(input[1])+1)/2;
				if(a > b) {
					a += b;
					b = a-b;
					a -= b;
				}
				
				for(int i=a; i<=b; i++) {
					dp[i]++;
				}
			}
			
			for(int i=1; i<=200; i++) {
				answer = Math.max(answer, dp[i]);
			}
			sb.append("#"+t+" "+answer+"\n");
		}
		System.out.println(sb);
	}
}
