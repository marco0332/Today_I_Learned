import java.io.*;
import java.util.*;

public class Solution1808_지희의_고장난_계산기 {
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
		final int MAX = 1000000;
		int T, N, X;
		boolean[] button;
		ArrayList<Integer> arr;
		int[] dp;

		public Task(BufferedReader br, BufferedWriter bw) {
			this.br = br;
			this.bw = bw;
		}

		public void solve() throws IOException {
			T = Integer.parseInt(br.readLine());
			for (int t = 1; t <= T; t++) {
				dp = new int[1000001];
				Arrays.fill(dp, Integer.MAX_VALUE);
				arr = new ArrayList<Integer>();
				button = new boolean[10];
				
				String[] line = br.readLine().split(" ");
				for (int i = 0; i < 10; i++) {
					button[i] = line[i].charAt(0) == '1' ? true : false;

					if (button[i]) {
						dp[i] = 1;

						if (i > 1)
							arr.add(i);
					}
				}
				X = Integer.parseInt(br.readLine());

				for (int i = 1; i <= X; i++) {
					go(i);
				}
				
				if(dp[X] == Integer.MAX_VALUE) bw.write("#"+t+" -1\n");
				else bw.write("#"+t+" "+(dp[X]+1)+"\n");
			}
		}

		public void go(int val) {
			int tmp = val;
			int cnt = 0;
			int cur;
			boolean flag = true;
			while (tmp > 0) {
				cur = tmp % 10;

				if (!button[cur]) {
					flag = false;
					break;
				}
				tmp /= 10;
				cnt++;
			}

			if (flag) {
				dp[val] = cnt;
			}
			
			cur = (int)Math.sqrt(val);
			if(cur*cur == val && dp[cur] != Integer.MAX_VALUE && dp[cur]*2+1 < dp[val]) {
				dp[val] = dp[cur]*2+1;
			}
			
			if(X % val == 0 && dp[val] != Integer.MAX_VALUE && dp[X/val] != Integer.MAX_VALUE) {
				dp[X] = Math.min(dp[X], dp[val]+dp[X/val]+1);
			}

			if (dp[val] != Integer.MAX_VALUE) {
				Queue<Integer> q = new LinkedList<Integer>();

				q.add(val);
				while (!q.isEmpty()) {
					cur = q.poll();

					for (int i = 0, size = arr.size(); i < size; i++) {
						tmp = cur * arr.get(i);

						if (tmp <= X && dp[cur] != Integer.MAX_VALUE && dp[cur]+2 < dp[tmp]) {
							dp[tmp] = dp[cur] + 2;
							q.add(tmp);
						}
					}
				}
			}
		}
	}
}
