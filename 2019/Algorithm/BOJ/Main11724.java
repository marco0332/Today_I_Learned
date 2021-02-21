import java.io.*;
import java.util.*;

public class Main11724 {
	static int N, M, answer, total;
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] visited;
	
	public static void dfs(int node) {
		visited[node] = true;
		
		for(int i=0, size=adj.get(node).size(); i<size; i++) {
			int next = adj.get(node).get(i);
			if(!visited[next])
				dfs(next);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		visited = new boolean[N+1];
		adj = new ArrayList<ArrayList<Integer>>();
		for(int n=0; n<=N; n++) {
			adj.add(new ArrayList<Integer>());
		}
		
		for(int m=0; m<M; m++) {
			String[] line = br.readLine().split(" ");
			
			int u = Integer.parseInt(line[0]);
			int v = Integer.parseInt(line[1]);
			adj.get(u).add(v);
			adj.get(v).add(u);
		}
		
		for(int n=1; n<=N; n++) {
			if(!visited[n]) {
				dfs(n);
				answer++;
			}
		}
		System.out.println(answer);
	}
}
