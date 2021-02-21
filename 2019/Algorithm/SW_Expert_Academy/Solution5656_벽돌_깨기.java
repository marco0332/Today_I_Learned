import java.io.*;
import java.util.*;

public class Solution5656_벽돌_깨기 {
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
		int N, W, H;
		int[][] map;
		int answer, total;
		int[] dr = { 0, 0, 1, -1 };
		int[] dc = { 1, -1, 0, 0 };

		public Task(BufferedReader br, BufferedWriter bw) {
			this.br = br;
			this.bw = bw;
		}

		public void solve() throws IOException {
			int T = Integer.parseInt(br.readLine());
			for (int t = 1; t <= T; t++) {
				String[] line = br.readLine().split(" ");
				N = Integer.parseInt(line[0]);
				W = Integer.parseInt(line[1]);
				H = Integer.parseInt(line[2]);

				answer = Integer.MAX_VALUE;
				total = 0;
				map = new int[H][W];
				for (int h = 0; h < H; h++) {
					line = br.readLine().split(" ");
					for (int w = 0; w < W; w++) {
						map[h][w] = Integer.parseInt(line[w]);
						if (map[h][w] > 0)
							total++;
					}
				}

				go(map, 0, 0);
				if (answer == Integer.MAX_VALUE)
					bw.write("#" + t + " " + 0 + "\n");
				else
					bw.write("#" + t + " " + answer + "\n");
			}
		}

		public void go(int[][] befoMap, int cnt, int sum) {
			if (cnt == N) {
				answer = Math.min(answer, total - sum);
				return;
			}

			int[][] tmpMap = new int[H][W];

			for (int i = 0; i < W; i++) {
				for (int h = 0; h < H; h++) {
					for (int w = 0; w < W; w++) {
						tmpMap[h][w] = befoMap[h][w];
					}
				}

				int cur = bomb(tmpMap, i);
				if (cur > 0) {
					down(tmpMap);
					go(tmpMap, cnt + 1, sum + cur);
				}
			}
		}

		public int bomb(int[][] tmpMap, int idx) {
			Queue<Pos> q = new LinkedList<>();
			int cnt = 0;
			for (int h = 0; h < H; h++) {
				if (tmpMap[h][idx] > 0) {
					q.add(new Pos(h, idx, tmpMap[h][idx]));
					tmpMap[h][idx] = 0;
					cnt++;
					break;
				}
			}

			while (!q.isEmpty()) {
				Pos cur = q.poll();

				for (int j = 1; j < cur.d; j++) {
					for (int i = 0; i < 4; i++) {
						int nr = cur.r + dr[i] * j;
						int nc = cur.c + dc[i] * j;

						if (0 <= nr && nr < H && 0 <= nc && nc < W) {
							if (tmpMap[nr][nc] == 1) {
								cnt++;
								tmpMap[nr][nc] = 0;
							} else if (tmpMap[nr][nc] > 1) {
								cnt++;
								q.add(new Pos(nr, nc, tmpMap[nr][nc]));
								tmpMap[nr][nc] = 0;
							}
						}
					}
				}
			}
			return cnt;
		}

		public void down(int[][] tmpMap) {
			for (int w = 0; w < W; w++) {
				for (int h = H - 2; h >= 0; h--) {
					int curH = h;
					while (curH <= H - 2 && tmpMap[curH][w] > 0 && tmpMap[curH + 1][w] == 0) {
						tmpMap[curH + 1][w] = tmpMap[curH][w];
						tmpMap[curH][w] = 0;
						curH++;
					}
				}
			}
		}
	}

	static class Pos {
		int r, c, d;

		public Pos(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
}
