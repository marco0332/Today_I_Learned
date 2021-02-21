import java.io.*;
import java.util.*;

public class Main1350_최대신장트리 {
	static int N, M;
	static ArrayList<ArrayList<Edge>> adj;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N];
		adj = new ArrayList<ArrayList<Edge>>();
		for (int n = 0; n < N; n++) {
			adj.add(new ArrayList<Edge>());
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());

			adj.get(u).add(new Edge(u, v, w));
			adj.get(v).add(new Edge(v, u, w));
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(0, 0, 0));

		int answer = 0;
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			if (!visited[cur.end]) {
				visited[cur.end] = true;
				answer += cur.weight;

				for (Edge next : adj.get(cur.end)) {
					if (!visited[next.end]) {
						pq.add(next);
					}
				}
			}
		}
		System.out.println(answer);
	}

	static class Edge implements Comparable<Edge> {
		int start, end;
		int weight;

		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			if (this.weight != o.weight)
				return o.weight - this.weight;
			else
				return 0;
		}
	}
}
