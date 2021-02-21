import java.io.*;
import java.util.*;

public class Main1260 {
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> adj;
	
	public static void dfs(int V) {
		System.out.print(V+" ");
		visited[V] = true;
		
		for(int nextV : adj.get(V)) {
			if(visited[nextV] == false) {
				dfs(nextV);
			}
		}
	}
	
	public static void bfs(int V) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.add(V);
		visited[V] = true;
		while(!q.isEmpty()) {
			int ver = q.poll();
			System.out.print(ver+" ");
			
			for(int nextV : adj.get(ver)) {
				if(visited[nextV] == false) {
					visited[nextV] = true;
					q.add(nextV);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		adj = new ArrayList<ArrayList<Integer>>();
		
		for(int n=0; n<=N; n++) {
			adj.add(new ArrayList<Integer>());
		}
		
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj.get(u).add(v);
			adj.get(v).add(u);
		}
		
		for(int n=0; n<N; n++) {
			Collections.sort(adj.get(n));
		}
		
		visited = new boolean[N+1];
		dfs(V);
		System.out.println();
		visited = new boolean[N+1];
		bfs(V);
	}
}
