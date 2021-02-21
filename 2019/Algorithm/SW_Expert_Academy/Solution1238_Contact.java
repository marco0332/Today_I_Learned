import java.io.*;
import java.util.*;

public class Solution1238_Contact {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Task solver = new Task(br, bw);
		solver.solve();
		bw.close();
	}

	static class Task {
		BufferedReader br;
		BufferedWriter bw;
		int N = 100;
		boolean[] visited;
		int start, answer, ansDepth;
		ArrayList<ArrayList<Integer>> adj;

		public Task(BufferedReader br, BufferedWriter bw) {
			this.br = br;
			this.bw = bw;
			adj = new ArrayList<ArrayList<Integer>>();
		}

		public void solve() throws IOException {
			for (int t = 1; t <= 10; t++) {
				String[] line = br.readLine().split(" ");
				int len = Integer.parseInt(line[0]);
				start = Integer.parseInt(line[1]);
				
				for (int n = 0; n <= N; n++) {
					adj.add(new ArrayList<Integer>());
				}
				answer = 0;
				ansDepth = 0;
				visited = new boolean[101];
				
				line = br.readLine().split(" ");
				for (int i = 0; i < len; i += 2) {
					adj.get(Integer.parseInt(line[i])).add(Integer.parseInt(line[i + 1]));
				}
				
				bfs(start, 0);
				bw.write("#"+t+" "+answer+"\n");
			}
		}
		
		public void bfs(int start, int depth) {
			Queue<Node> q = new LinkedList<Node>();
			q.add(new Node(start, depth));
			visited[start] = true;
			
			while(!q.isEmpty()) {
				Node cur = q.poll();
				
				if(ansDepth < cur.depth) {
					answer = cur.node;
					ansDepth = cur.depth;
				} else if(ansDepth == cur.depth && answer < cur.node) {
					answer = cur.node;
				}
				
				for(int i=0,size=adj.get(cur.node).size(); i<size; i++) {
					int next = adj.get(cur.node).get(i);
					
					if(!visited[next]) {
						visited[next] = true;
						q.add(new Node(next,cur.depth+1));
					}
				}
			}
		}
		
		static class Node {
			int node, depth;
			
			public Node(int node, int depth) {
				this.node = node;
				this.depth = depth;
			}
		}
	}
}
