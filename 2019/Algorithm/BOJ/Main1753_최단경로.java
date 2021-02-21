import java.io.*;
import java.util.*;

public class Main1753_최단경로 {
	static int V, E, K;
	static ArrayList<ArrayList<Edge>> adj;
	static int[] dp;
	static PriorityQueue<Edge> q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = br.readLine().split(" ");
		V = Integer.parseInt(line[0]);
		E = Integer.parseInt(line[1]);
		K = Integer.parseInt(br.readLine());
		
		dp = new int[V+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[K] = 0;
		
		adj = new ArrayList<ArrayList<Edge>>();
		for(int v=0; v<=V; v++) {
			adj.add(new ArrayList<Edge>());
		}
		
		for(int e=0; e<E; e++) {
			line = br.readLine().split(" ");
			
			int u = Integer.parseInt(line[0]);
			int v = Integer.parseInt(line[1]);
			int w = Integer.parseInt(line[2]);
			
			Edge tmpE = new Edge(u,v,w);
			adj.get(u).add(tmpE);
		}
		
		q = new PriorityQueue<Edge>();
		q.add(new Edge(0,K,0));
		
		boolean[] checked = new boolean[V+1];
		while(!q.isEmpty()) {
			Edge e = q.poll();
			
			if(!checked[e.v]) {
				checked[e.v] = true; 
				
				for(Edge nextE : adj.get(e.v)) {
					nextE.w += dp[e.v];
					
					if(dp[nextE.v] > nextE.w) {
						q.add(nextE);
						dp[nextE.v] = nextE.w; 
					}
				}
			}
		}
		
		StringBuilder ans = new StringBuilder();
		for(int v=1; v<=V; v++) {
			if(dp[v] == Integer.MAX_VALUE) ans.append("INF\n");
			else ans.append(dp[v]).append("\n");
		}
		System.out.println(ans.toString());
	}
	
	static class Edge implements Comparable<Edge> {
		int u, v, w;
		
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			if(this.w != o.w) return this.w-o.w;
			else return 0;
		}
	}
}
