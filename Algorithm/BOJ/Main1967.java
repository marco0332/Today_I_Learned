import java.io.*;
import java.util.*;

//class edge {
//	int cost;
//	int node;
//
//	public edge(int cost, int node) {
//		this.cost = cost;
//		this.node = node;
//	}
//}

public class Main1967 {

	static ArrayList<ArrayList<edge>> adj;
	static int[] dp;
	static int ans = 0;

	public static int dfs(int cur) {
		if (adj.get(cur).size() == 0)
			return 0;

		int size = adj.get(cur).size();
		int M1 = 0, M2 = 0;
		int tmp = 0;

		for (int i = 0; i < size; i++) {
			int ver = adj.get(cur).get(i).node;

			tmp = dfs(ver) + adj.get(cur).get(i).cost;
			if (M1 < tmp) {
				M2 = M1;
				M1 = tmp;
			} 
			else if (M2 < tmp)
				M2 = tmp;
			
			dp[cur] = Math.max(dp[cur], dp[ver]);
		}
		
		dp[cur] = Math.max(dp[cur], M1+M2);
		if(ans < dp[cur]) ans = dp[cur];
		
		return M1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		adj = new ArrayList<ArrayList<edge>>();
		dp = new int[N + 1];
		Arrays.fill(dp, -1);
		for (int n = 0; n <= N; n++) {
			adj.add(new ArrayList<edge>());
		}

		for (int n = 0; n < N - 1; n++) {
			String[] v = br.readLine().split(" ");

			int p = Integer.parseInt(v[0]);
			int c = Integer.parseInt(v[1]);
			int cost = Integer.parseInt(v[2]);

			adj.get(p).add(new edge(cost, c));
		}
		dfs(1);
		System.out.println(ans);
	}
}
