import java.io.*;
import java.util.*;

public class Main1863_종교 {
	static int[] parent, depth, cnt;
	
	public static int find(int node) {
		if(parent[node] == 0) return node;
		else return parent[node] = find(parent[node]);
	}
	
	public static void union(int u, int v) {
		u = find(u);
		v = find(v);
		
		if(u != v) {
			if(depth[u] < depth[v]) {
				int tmp = u;
				u = v;
				v = tmp;
			}
			else if(depth[u] == depth[v])
				depth[u]++;
			
			parent[v] = u;
			cnt[u] += cnt[v];
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] NM = br.readLine().split(" ");
		int N = Integer.parseInt(NM[0]);
		int M = Integer.parseInt(NM[1]);
		parent = new int[N+1];
		depth = new int[N+1];
		cnt = new int[N+1];
		
		for(int m=0; m<M; m++) {
			String[] line = br.readLine().split(" ");
			int u = Integer.parseInt(line[0]);
			int v = Integer.parseInt(line[1]);
			
			union(u, v);
		}
		
		int answer = 0;
		for(int n=1; n<=N; n++) {
			if(parent[n] == 0)
				answer++;
		}
		System.out.println(answer);
	}
}
