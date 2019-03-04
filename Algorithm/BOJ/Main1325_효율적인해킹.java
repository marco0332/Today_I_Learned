import java.io.*;
import java.util.*;

public class Main1325_효율적인해킹 {
	static ArrayList<ArrayList<Integer>> adj;
	static int[] visited;
	static int[] count;
	static int root;
	
	public static void dfs(int node) {
		visited[node] = root;
		
		for(int next : adj.get(node)) {
			if(visited[next] != root) {
				count[next]++;
				dfs(next);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] NM = br.readLine().split(" ");
		int N = Integer.parseInt(NM[0]);
		int M = Integer.parseInt(NM[1]);
		int MAX = 0;
		visited = new int[N+1];
		adj = new ArrayList<ArrayList<Integer>>();
		for(int n=0; n<=N; n++) {
			adj.add(new ArrayList<Integer>());
		}
		
		for(int m=0; m<M; m++) {
			String[] AB = br.readLine().split(" ");
			int A = Integer.parseInt(AB[0]);
			int B = Integer.parseInt(AB[1]);
			
			adj.get(A).add(B);
		}
		
		count = new int[N+1];
		int idx = 0;
		for(int n=1; n<=N; n++) {
			root = n;
			dfs(n);
		}
		MAX = Arrays.stream(count).max().getAsInt();
		
		for(int i=1; i<=N; i++) {
			if(count[i] == MAX)
				sb.append(i).append(" ");
		}
		System.out.println(sb);
	}
}
