import java.io.*;
import java.util.*;

public class Solution3289_서로소_집합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Task solver = new Task(br,bw);
		solver.solve();
		bw.close();
	}
	
	static class Task {
		BufferedReader br;
		BufferedWriter bw;
		int N, M;
		int[] parent;
		StringBuilder ans;
		
		public Task(BufferedReader br, BufferedWriter bw) {
			this.br = br;
			this.bw = bw;
		}
		
		public void preprocess() throws IOException {
			String[] line = br.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			M = Integer.parseInt(line[1]);
			parent = new int[N+1];
			for(int n=0; n<=N; n++) {
				parent[n] = n;
			}
			ans = new StringBuilder();
		}
		
		public void solve() throws IOException {
			int T = Integer.parseInt(br.readLine());
			for(int t=1; t<=T; t++) {
				preprocess();
				ans.append("#"+t+" ");
				
				for(int m=0; m<M; m++) {
					String[] line = br.readLine().split(" ");
					
					int order = Integer.parseInt(line[0]);
					int u = Integer.parseInt(line[1]);
					int v = Integer.parseInt(line[2]);
					if(order == 0) union(u,v);
					else if(order == 1) check(u,v);
				}
				ans.append("\n");
				bw.write(ans.toString());
			}
		}
		
		public int find(int idx) {
			if(parent[idx] == idx) return idx;
			return parent[idx] = find(parent[idx]);
		}
		
		public void union(int u, int v) {
			u = find(u);
			v = find(v);
			
			if(u != v) {
				parent[v] = u;
			}
		}
		
		public void check(int u, int v) {
			u = find(u);
			v = find(v);
			
			if(u != v) ans.append("0");
			else ans.append("1");
		}
	}
}
