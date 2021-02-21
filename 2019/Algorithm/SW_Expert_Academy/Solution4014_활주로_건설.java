import java.io.*;
import java.util.*;

public class Solution4014_활주로_건설 {
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

		public Task(BufferedReader br, BufferedWriter bw) {
			this.br = br;
			this.bw = bw;
		}

		public void solve() throws IOException {
			int T = Integer.parseInt(br.readLine());
			for (int t = 1; t <= T; t++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int N = Integer.parseInt(st.nextToken());
				int X = Integer.parseInt(st.nextToken());
				double[][] map = new double[N + 1][N + 1];
				for (int i = 1; i <= N; i++) {
					st = new StringTokenizer(br.readLine());
					for (int j = 1; j <= N; j++) {
						map[i][j] = Integer.parseInt(st.nextToken());
					}
				}

				
			}
		}
	}
}
