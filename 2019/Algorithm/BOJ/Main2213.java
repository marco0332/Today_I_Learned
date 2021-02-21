import java.io.*;
import java.util.*;

public class Main2213 {
	static int N;
	static int[] weight;
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] visited;
	
	static int go(int node, int befoState) {
		visited[node] = true;
		int v1=0, v2=0;
		
		if(befoState == 0) {
			for(int i=0,len=adj.get(node).size(); i<len; i++) {
				if(!visited[adj.get(node).get(i)])
					v1 = go(adj.get(node).get(i), 1);
			}
		}
		for(int i=0,len=adj.get(node).size(); i<len; i++) {
			if(!visited[adj.get(node).get(i)])
				v2 = go(adj.get(node).get(i), 0);
		}
		
		visited[node] = false;
		
		if(befoState == 0) return v1+v2;
		else return weight[node]+v1+v2;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		adj = new ArrayList<ArrayList<Integer>>();
		visited = new boolean[N+1];
		weight = new int[N+1];
		
		adj.add(new ArrayList<Integer>());
		String[] line = br.readLine().split(" ");
		for(int n=1; n<=N; n++) {
			adj.add(new ArrayList<Integer>());
			weight[n] = Integer.parseInt(line[n-1]);
		}
		
		for(int n=0; n<N-1; n++) {
			line = br.readLine().split(" ");
			int u = Integer.parseInt(line[0]);
			int v = Integer.parseInt(line[1]);
			
			adj.get(u).add(v);
			adj.get(v).add(u);
		}
		
		System.out.println(Math.max(go(1, 1), go(1, 0)));
	}
}
