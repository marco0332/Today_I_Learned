import java.io.*;
import java.util.*;

class Node {
	int x, y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Edge implements Comparable<Edge> {
	long weight;
	int num;

	public Edge(long weight, int num) {
		this.weight = weight;
		this.num = num;
	}

	@Override
	public int compareTo(Edge e) {
		if (this.weight < e.weight)
			return -1;
		else if (this.weight == e.weight)
			return 0;
		else
			return 1;
	}
}

public class Solution1251_하나로 {
	static boolean[] visited;
	static Node[] arr;
	static PriorityQueue<Edge> q;

	public static long dist(int u, int v) {
		return (long)(arr[u].x - arr[v].x) * (arr[u].x - arr[v].x) + (long)(arr[u].y - arr[v].y) * (arr[u].y - arr[v].y);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			long answer = 0;

			arr = new Node[N];
			visited = new boolean[N];

			String[][] input = new String[N][2];
			for (int n = 0; n < 2; n++) {
				input[n] = br.readLine().split(" ");
			}
			for (int n = 0; n < N; n++) {
				int x = Integer.parseInt(input[0][n]);
				int y = Integer.parseInt(input[1][n]);
				arr[n] = new Node(x, y);
			}
			double E = Double.parseDouble(br.readLine());

			q = new PriorityQueue<Edge>();
			q.add(new Edge(0, 0));
			int cnt = -1;

			while (cnt < N - 1) {
				Edge now = q.poll();
				if (!visited[now.num]) {
					answer += now.weight;
					visited[now.num] = true;

					for (int i = 0; i < N; i++) {
						if (!visited[i] && i != now.num) {
							q.add(new Edge(dist(i, now.num), i));
						}
					}
					cnt++;
				}
			}
			answer = Math.round(answer*E);
			sb.append("#" + t + " " + answer + "\n");
		}
		System.out.println(sb);
	}
}
