import java.io.*;
import java.util.*;

public class Main1167 {

	static ArrayList<nodes> adj;
	static int answer;
	static int[] visited;

	public static int dfs(int ver) {
		if (adj.get(ver).size == 0)
			return 0;

		visited[ver] = 1;

		nodes cur = adj.get(ver);
		int[] len = { 0, 0 };
		for (int i = 0, size = cur.size; i < size; i++) {
			if (visited[cur.childs.get(i).ver] == 0) {
				int val = dfs(cur.childs.get(i).ver) + cur.childs.get(i).dist;
				if (len[0] < val) {
					len[1] = len[0];
					len[0] = val;
				} else if (len[1] < val)
					len[1] = val;
			}
		}
		
		visited[ver] = 0;

		answer = Math.max(answer, len[0] + len[1]);
		return len[0];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		visited = new int[N + 1];

		adj = new ArrayList<nodes>();
		for (int n = 0; n <= N; n++) {
			adj.add(new nodes());
		}

		for (int n = 0; n < N; n++) {
			String[] data = br.readLine().split(" ");
			int ver = Integer.parseInt(data[0]);

			for (int i = 1, size = data.length - 1; i < size; i += 2) {
				adj.get(ver).childs.add(new edge(Integer.parseInt(data[i]), Integer.parseInt(data[i + 1])));
				adj.get(ver).size++;
			}
		}

		dfs(1);
		System.out.println(answer);
	}

}

//class nodes {
//	int size;
//	ArrayList<edge> childs;
//
//	public nodes() {
//		size = 0;
//		this.childs = new ArrayList<edge>();
//	}
//}
//
//class edge {
//	int ver;
//	int dist;
//
//	public edge(int ver, int dist) {
//		this.ver = ver;
//		this.dist = dist;
//	}
//}