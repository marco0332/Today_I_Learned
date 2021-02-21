import java.io.*;
import java.util.*;

public class Solution5213_진수의_홀수_약수 {
	static long[] dp = new long[1000001];
	
	public static void preprocess() {
		Arrays.fill(dp, 1);
		dp[0] = 0;
		
		for(int i=3; i<=1000000; i+=2) {
			for(int j=i; j<=1000000; j+=i) {
				dp[j] += i;
			}
		}
		
		for(int i=1; i<=1000000; i++) {
			dp[i] += dp[i-1];
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		preprocess();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			String[] xy = br.readLine().split(" ");
			int x = Integer.parseInt(xy[0]);
			int y = Integer.parseInt(xy[1]);

			long answer = 0;
			answer = dp[y] - dp[x-1];
			sb.append("#" + t + " " + answer + "\n");
		}
		System.out.println(sb);
	}
}
