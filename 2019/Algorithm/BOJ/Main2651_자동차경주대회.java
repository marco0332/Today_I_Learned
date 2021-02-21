import java.io.*;
import java.util.*;

public class Main2651_자동차경주대회 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Task solver = new Task(br,bw);
		solver.solve();
		bw.close();
	}
	
	static class Task {
		BufferedReader br;
		BufferedWriter bw;
		int D, N, totalDist;
		int[] distArr, timeArr;
		int[][] dp;
		ArrayList<Integer> ans;
		
		public Task(BufferedReader br, BufferedWriter bw) {
			this.br = br;
			this.bw = bw;
		}
		
		public void preprocess() throws IOException {
			D = Integer.parseInt(br.readLine());
			N = Integer.parseInt(br.readLine());
			distArr = new int[N+2];
			timeArr = new int[N+2];
			// 인덱스0 : 정비 시간 , 인덱스1 : 들어온 곳
			dp = new int[N+2][2];
			ans = new ArrayList<Integer>();
			for(int n=0; n<N+2; n++) {
				dp[n][0] = Integer.MAX_VALUE;
			}
			
			String[] line = br.readLine().split(" ");
			for(int n=0; n<N+1; n++) {
				distArr[n+1] = Integer.parseInt(line[n]) + distArr[n];
			}
			
			line = br.readLine().split(" ");
			for(int n=0; n<N; n++) {
				timeArr[n+1] = Integer.parseInt(line[n]);
			}
		}
		
		public void solve() throws IOException {
			preprocess();
			
			go();

			int idx = N+1;
			while(idx != 0) {
				idx = dp[idx][1];
				ans.add(idx);
			}
			
			bw.write(dp[N+1][0]+"\n");
			bw.write(ans.size()-1+"\n");
			for(int i=ans.size()-2; i>=0; i--) {
				bw.write(ans.get(i)+" ");
			}
		}
		
		public void go() {
			dp[0][0] = 0;
			for(int i=1; i<N+2; i++) {
				for(int j=i-1; j>=0; j--) {
					if(distArr[i]-distArr[j] <= D) {
						if(dp[j][0] + timeArr[i] < dp[i][0]) {
							dp[i][0] = dp[j][0] + timeArr[i];
							dp[i][1] = j;
						}
					}
				}
			}
		}
	}
}