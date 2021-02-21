import java.io.*;
import java.util.*;

public class Main2097_지하철 {
	static int N, M;
	static int[][] adj;
	static int[] dp;
	static ArrayList<Integer> tmp, ans;
	static boolean find;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		adj = new int[N][N];
		dp = new int[N];
		tmp = new ArrayList<Integer>();
		ans = new ArrayList<Integer>();
		for(int i=0; i<N; i++) {
			line = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				adj[i][j] = Integer.parseInt(line[j]);
			}
		}

		PriorityQueue<Edge> q = new PriorityQueue<Edge>();
		q.add(new Edge(0, 0, 0));

		while (!q.isEmpty()) {
			Edge cur = q.poll();
			if (dp[cur.e] == 0) {
				dp[cur.e] = cur.w; 
				
				for (int i = 1; i < N; i++) {
					if (i != cur.e) {
						q.add(new Edge(cur.e, i, cur.w+adj[cur.e][i]));
					}
				}
			}
		}
		bw.write(dp[M-1]+"\n");	
		backtrack(M-1);
		for(int i=0,size=ans.size(); i<size; i++) {
			bw.write(ans.get(i)+" ");
		}
		bw.close();
	}
	
	public static void backtrack(int node) {
		if(node == 0) {
			tmp.add(0);
			for(int i=0,size=tmp.size(); i<size; i++) {
				ans.add(tmp.get(size-1-i)+1);
			}
			find = true;
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!find && node != i && adj[i][node] + dp[i] == dp[node]) {
				tmp.add(node);
				backtrack(i);
				tmp.remove(tmp.size()-1);
			}
		}
	}

	static class Edge implements Comparable<Edge> {
		int s, e, w;

		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			if (this.w < o.w)
				return -1;
			else if (this.w > o.w)
				return 1;
			else
				return 0;
		}
	}
}
