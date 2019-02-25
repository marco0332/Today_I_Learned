import java.io.*;
import java.util.*;

public class Main2644_촌수계산 {
	static int N;
	static int[] root, parent;

	public static int find(int idx) {
		if (parent[idx] == 0)
			return idx;
		return root[idx] = find(parent[idx]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		String[] se = br.readLine().split(" ");
		int S = Integer.parseInt(se[0]);
		int E = Integer.parseInt(se[1]);

		root = new int[N + 1];
		parent = new int[N + 1];
		int M = Integer.parseInt(br.readLine());
		for (int m = 0; m < M; m++) {
			String[] line = br.readLine().split(" ");

			int u = Integer.parseInt(line[0]);
			int v = Integer.parseInt(line[1]);
			parent[v] = u;
		}

		if (find(S) != find(E))
			System.out.println("-1\n");
		else {
			int tmpS = S, tmpE = E;
			ArrayList<Integer> s = new ArrayList<>();
			ArrayList<Integer> e = new ArrayList<>();

			while (parent[tmpS] != 0) {
				s.add(tmpS);
				tmpS = parent[tmpS];
			}
			s.add(tmpS);

			while (parent[tmpE] != 0) {
				e.add(tmpE);
				tmpE = parent[tmpE];
			}
			e.add(tmpE);
			
			int idxS = s.size()-1;
			int idxE = e.size()-1;
			while(idxS >= 0 && idxE >= 0 && s.get(idxS) == e.get(idxE)) {
				idxS--;
				idxE--;
			}
			
			System.out.println(idxS+idxE+2);
		}
	}
}
