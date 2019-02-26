import java.io.*;
import java.util.*;

public class Main14501_퇴사 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] time = new int[N];
		int[] val = new int[N];
		int[] dp = new int[N+1];
		for(int n=0; n<N; n++) {
			String[] input = br.readLine().split(" ");
			time[n] = Integer.parseInt(input[0]);
			val[n] = Integer.parseInt(input[1]);
			
			for(int i=n+time[n]; i<=N; i++) {
				dp[i] = Math.max(dp[i], dp[n]+val[n]);
			}
		}
		System.out.println(dp[N]);
	}
}
