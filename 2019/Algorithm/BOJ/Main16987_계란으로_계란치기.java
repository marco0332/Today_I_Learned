import java.io.*;
import java.util.*;

public class Main16987_계란으로_계란치기 {
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
		Egg[] eggs;
		int N;
		int answer = 0;

		public Task(BufferedReader br, BufferedWriter bw) {
			this.br = br;
			this.bw = bw;
		}

		public void solve() throws IOException {
			N = Integer.parseInt(br.readLine());
			eggs = new Egg[N];
			for (int n = 0; n < N; n++) {
				String[] line = br.readLine().split(" ");

				eggs[n] = new Egg(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
			}

			dfs(0);
			bw.write(answer + "\n");
		}

		public void dfs(int idx) {
			while (idx < N && eggs[idx].s <= 0)
				idx++;

			if (idx < N) {
				for (int i = 0; i < N; i++) {
					if (i == idx || eggs[i].s <= 0)
						continue;

					eggs[i].s -= eggs[idx].w;
					eggs[idx].s -= eggs[i].w;

					dfs(idx + 1);

					eggs[idx].s += eggs[i].w;
					eggs[i].s += eggs[idx].w;
				}
			}
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if (eggs[i].s <= 0)
					cnt++;
			}

			answer = Math.max(answer, cnt);
		}
	}

	static class Egg {
		int s, w;

		public Egg(int s, int w) {
			this.s = s;
			this.w = w;
		}
	}
}
