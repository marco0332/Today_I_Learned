//8
//1 2
//3 1
//1 4
//5 6
//1 5
//5 7
//5 8

//11

import java.io.*;
import java.util.*;

public class Main1693 {
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] visited;
	static int[][] dp;
	static int answer = 987654321, type;

	public static int log2(int x) {
		return (int) (Math.log(x) / Math.log(2)) + 1;
	}

	public static int go(int cur, int nowColor) {
		if (dp[cur][nowColor] != -1)
			return dp[cur][nowColor];

		visited[cur] = true;

		int tmpAns = nowColor;
		for (int i = 0, size = adj.get(cur).size(); i < size; i++) {
			if (!visited[adj.get(cur).get(i)]) {
				int minVal = 987654321;

				for (int j = 1; j <= type; j++) {
					if (j != nowColor)
						minVal = Math.min(minVal, go(adj.get(cur).get(i), j));
				}
				tmpAns += minVal;
			}
		}

		visited[cur] = false;
		return dp[cur][nowColor] = tmpAns;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		dp = new int[N + 1][30];
		visited = new boolean[N + 1];
		type = log2(N);

		adj = new ArrayList<ArrayList<Integer>>();
		for (int n = 0; n < N + 1; n++) {
			adj.add(new ArrayList<Integer>());
			Arrays.fill(dp[n], -1);
		}

		for (int n = 0; n < N - 1; n++) {
			String[] tmp = br.readLine().split(" ");
			int u = Integer.parseInt(tmp[0]);
			int v = Integer.parseInt(tmp[1]);

			adj.get(u).add(v);
			adj.get(v).add(u);
		}

		for (int n = 1; n <= type; n++) {
			answer = Math.min(answer, go(1, n));
		}
		System.out.println(answer);
	}
}
