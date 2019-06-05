import java.io.*;
import java.util.*;

public class Main11657_타임머신 {

	static int N, M;
	static int[] dp;
	static Map<Edge, Integer> edge;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		
		dp = new int[N+1];
		edge = new HashMap<Edge, Integer>();
		for(int m=0; m<M; m++) {
			line = br.readLine().split(" ");

			int u = Integer.parseInt(line[0]);
			int v = Integer.parseInt(line[1]);
			int w = Integer.parseInt(line[2]);
			Edge e = new Edge(u,v);
			
			if(edge.get(e) == null || edge.get(e) > w) {
				edge.put(e, w);
			}
		}
		
		init();
		for(int n=0; n<N-1; n++) {
			relax();
		}
		if(!find()) System.out.println(-1);
		else {
			for(int n=2; n<=N; n++) {
				if(dp[n] == Integer.MAX_VALUE) dp[n] = -1;
				System.out.println(dp[n]);
			}
		}
	}
	
	static void init() {
		dp[1] = 0;
		for(int n=2; n<=N; n++) {
			dp[n] = Integer.MAX_VALUE;
		}
	}
	
	static void relax() {
		for(Edge e : edge.keySet()) {
			if(dp[e.u] != Integer.MAX_VALUE && dp[e.u] + edge.get(e) < dp[e.v]) {
				dp[e.v] = dp[e.u] + edge.get(e); 
			}
		}
	}

	static boolean find() {
		for(Edge e : edge.keySet()) {
			if(dp[e.u] != Integer.MAX_VALUE && dp[e.u] + edge.get(e) < dp[e.v]) {
				return false;
			}
		}
		return true;
	}
	
	static class Edge {
		int u, v, w;
		
		public Edge(int u, int v) {
			this.u = u;
			this.v = v;
		}
		
		@Override
		public boolean equals(Object o) {
			if(this == o) return true;
			if(!(o instanceof Edge)) return false;
			Edge key = (Edge) o;
			return u == key.u && v == key.v;
		}
		
		@Override
		public int hashCode() {
			int result = u;
			result = 1234 * result + v;
			return result;
		}
	}
}
