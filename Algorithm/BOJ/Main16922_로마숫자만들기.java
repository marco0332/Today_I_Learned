import java.io.*;
import java.util.*;

public class Main16922_로마숫자만들기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Task solver = new Task(br, bw);
		solver.solve();
		bw.close();
	}

	static class Task {
		BufferedReader br;
		BufferedWriter bw;
		int N;
		int[] roma = { 1, 5, 10, 50 };
		int[] dp;

		public Task(BufferedReader br, BufferedWriter bw) {
			this.br = br;
			this.bw = bw;
		}

		public void solve() throws IOException {
			N = Integer.parseInt(br.readLine());
			dp = new int[1001];
			for (int i = 0; i < 4; i++) {
				dp[roma[i]]++;
			}

			int answer = go(N - 1);
			bw.write(answer + "\n");
		}

		public int go(int cnt) {
			if (cnt == 0) {
				int res = 0;
				for (int i = 1; i <= 1000; i++) {
					res += dp[i];
				}
				return res;
			}

			for (int idx = 1000; idx > 0; idx--) {
				if (dp[idx] == 1) {
					for (int i = 0; i < 4; i++) {
						if(idx+roma[i] <=1000)
							dp[idx + roma[i]] = 1;
					}
					dp[idx] = 0;
				}
			}

			return go(cnt - 1);
		}
	}
}
