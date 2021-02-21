import java.io.*;
import java.util.*;

public class Main2283_RGB마을 {
	static int N;
	static int[][] cost;
	static int answer = 1000000;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		cost = new int[N][3];
		dp = new int[3][N+1];
		for(int i=0; i<3; i++) {
			Arrays.fill(dp[i],Integer.MAX_VALUE);
		}
		
		for(int i=0; i<N; i++) {
			String[] line = br.readLine().split(" ");
			for(int j=0; j<3; j++) {
				cost[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		for(int i=0; i<3; i++) {
			go(1, i, cost[0][i]);
		}
		bw.write(Math.min(Math.min(dp[0][N], dp[1][N]), dp[2][N])+"\n");
		bw.flush();
		bw.close();
	}
	
	public static void go(int idx, int color, int sum) {
		dp[color][idx] = sum;
		if(idx == N) {
			return;
		}
		
		for(int i=0; i<3; i++) {
			if(color == i) continue;
			if(sum+cost[idx][i] < dp[i][idx+1])
				go(idx+1, i, sum+cost[idx][i]);
		}
	}
}
