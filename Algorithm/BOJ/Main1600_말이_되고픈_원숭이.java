import java.io.*;
import java.util.*;

public class Main1600_말이_되고픈_원숭이 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Task solver = new Task(br, bw);
		solver.solve();
		bw.flush();
		bw.close();
	}

	static class Task {
		BufferedReader br;
		BufferedWriter bw;
		int K, W, H;
		boolean[][] map;
		int[][][] visited;
		int[] dr = { 0, 0, 1, -1 };
		int[] dc = { 1, -1, 0, 0 };
		int[] Hdr = { -1, -2, -2, -1, 1, 2, 2, 1 };
		int[] Hdc = { -2, -1, 1, 2, 2, 1, -1, -2 };

		public Task(BufferedReader br, BufferedWriter bw) {
			this.br = br;
			this.bw = bw;
		}

		public void solve() throws IOException {
			K = Integer.parseInt(br.readLine());
			String[] line = br.readLine().split(" ");
			W = Integer.parseInt(line[0]);
			H = Integer.parseInt(line[1]);
			visited = new int[H][W][K+1];

			// false면 평지, true면 장애물
			map = new boolean[H][W];
			for (int h = 0; h < H; h++) {
				line = br.readLine().split(" ");
				for (int w = 0; w < W; w++) {
					map[h][w] = line[w].equals("0") ? false : true;
					
					for(int k=0; k<=K; k++) {
						visited[h][w][k] = Integer.MAX_VALUE;
					}
				}
			}

			go(0, 0, 0);
			System.out.println(-1);
		}

		public void go(int r, int c, int k) throws IOException {
			Queue<Monkey> q = new LinkedList<Monkey>();
			q.add(new Monkey(r, c, k, 0));
			visited[0][0][0] = 0;

			while (!q.isEmpty()) {
				Monkey cur = q.poll();
				if (visited[cur.r][cur.c][cur.k] < cur.cnt)
					continue;

				if (cur.k < K) {
					for (int i = 0; i < 8; i++) {
						int nr = cur.r + Hdr[i];
						int nc = cur.c + Hdc[i];
						int nk = cur.k+1;

						if (isOk(nr, nc)) {
							if(nr==H-1 && nc==W-1) {
								if(visited[nr][nc][nk] > cur.cnt+1) {
									System.out.println(cur.cnt+1);
									System.exit(0);
								}
							} else if (visited[nr][nc][nk] > cur.cnt+1) {
								visited[nr][nc][nk] = cur.cnt+1;
								q.add(new Monkey(nr, nc, nk, cur.cnt+1));
							}
						}
					}
				}
				for(int i=0; i<4; i++) {
					int nr = cur.r + dr[i];
					int nc = cur.c + dc[i];
					
					if(isOk(nr,nc)) {
						if(nr==H-1 && nc==W-1) {
							if(visited[nr][nc][cur.k] > cur.cnt+1) {
								System.out.println(cur.cnt+1);
								System.exit(0);
							}
						} else if (visited[nr][nc][cur.k] > cur.cnt + 1) {
							visited[nr][nc][cur.k] = cur.cnt + 1;
							q.add(new Monkey(nr, nc, cur.k, cur.cnt + 1));
						}
					}
				}
			}
		}

		public boolean isOk(int r, int c) {
			if (!(0 <= r && r < H && 0 <= c && c < W) || map[r][c])
				return false;
			else
				return true;
		}
	}

	static class Monkey {
		int r, c, k, cnt;

		public Monkey(int r, int c, int k, int cnt) {
			this.r = r;
			this.c = c;
			this.k = k;
			this.cnt = cnt;
		}
	}
}
