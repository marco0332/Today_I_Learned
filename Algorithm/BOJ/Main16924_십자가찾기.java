import java.io.*;
import java.util.*;

public class Main16924_십자가찾기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Task solver = new Task(br, bw);
		solver.solve();
		bw.close();
	}

	static class Ans {
		int n, m, size;

		public Ans(int n, int m, int size) {
			this.n = n;
			this.m = m;
			this.size = size;
		}
	}

	static class Task {
		BufferedReader br;
		BufferedWriter bw;
		int[] dr = { 0, 0, 1, -1 };
		int[] dc = { 1, -1, 0, 0 };

		public Task(BufferedReader br, BufferedWriter bw) {
			this.br = br;
			this.bw = bw;
		}

		public void solve() throws IOException {
			String[] line = br.readLine().split(" ");
			int N = Integer.parseInt(line[0]);
			int M = Integer.parseInt(line[1]);
			int[][] map = new int[N][M];
			int total = 0;
			for (int n = 0; n < N; n++) {
				line = br.readLine().split("");
				for (int m = 0; m < M; m++) {
					map[n][m] = line[m].charAt(0) == '*' ? 1 : 0;
					if (map[n][m] == 1)
						total++;
				}
			}

			ArrayList<Ans> ans = new ArrayList<Ans>();
			for (int n = 1; n < N - 1; n++) {
				for (int m = 1; m < M - 1; m++) {
					if (map[n][m] == 1 || map[n][m] == -1) {
						for (int size = 1;; size++) {
							boolean flag = true;

							for (int i = 1; i <= size; i++) {
								for (int j = 0; j < 4; j++) {
									if (!(0 <= n + dr[j] * i && n + dr[j] * i < N && 0 <= m + dc[j] * i
											&& m + dc[j] * i < M)) {
										flag = false;
										break;
									}
									if (map[n + dr[j] * i][m + dc[j] * i] == 0) {
										flag = false;
										break;
									}
								}
								if(!flag)
									break;
							}

							if (!flag)
								break;
							else {
								if (map[n][m] == 1) {
									total--;
									map[n][m] = -1;
								}
								for (int i = 1; i <= size; i++) {
									for (int j = 0; j < 4; j++) {
										if (map[n + dr[j] * i][m + dc[j] * i] == 1) {
											total--;
											map[n + dr[j] * i][m + dc[j] * i] = -1;
										}
									}
								}

								ans.add(new Ans(n + 1, m + 1, size));
							}

						}
					}
				}
			}

			if (total > 0 || ans.size() == 0) {
				bw.write(-1 + "\n");
			} else {
				bw.write(ans.size() + "\n");
				for (int i = 0, size = ans.size(); i < size; i++) {
					bw.write(ans.get(i).n + " " + ans.get(i).m + " " + ans.get(i).size + "\n");
				}
			}
		}
	}
}
