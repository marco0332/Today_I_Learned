import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
	int num;
	int weight;
	
	public Node(int num, int weight) {
		this.num = num;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		return this.weight - o.weight;
	}
}

public class Solution3124_최소스패닝트리 {
	static ArrayList<ArrayList<Node>> adj;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			String[] VE = br.readLine().split(" ");
			int V = Integer.parseInt(VE[0]);
			int E = Integer.parseInt(VE[1]);
			
			visited = new boolean[V+1];
			adj = new ArrayList<ArrayList<Node>>();
			for(int v=0; v<=V; v++) {
				adj.add(new ArrayList<Node>());
			}
			
			for(int e=0; e<E; e++) {
				String[] input = br.readLine().split(" ");
				
				int u = Integer.parseInt(input[0]);
				int v = Integer.parseInt(input[1]);
				int w = Integer.parseInt(input[2]);
				
				adj.get(u).add(new Node(v,w));
				adj.get(v).add(new Node(u,w));
			}
			
			long answer = 0;
			int cnt = -1;
			
			PriorityQueue<Node> q = new PriorityQueue<Node>();
			q.add(new Node(1, 0));
			while(cnt < V-1) {
				Node now = q.poll();
				if(visited[now.num]) continue; 
				visited[now.num] = true; 
				answer += now.weight;
				
				for(int i=0,size=adj.get(now.num).size(); i<size; i++) {
					if(!visited[adj.get(now.num).get(i).num]) {
						q.add(adj.get(now.num).get(i));
					}
				}
				cnt++;
			}
			
			sb.append("#"+t+" "+answer+"\n");
		}
		System.out.println(sb);
	}
}
