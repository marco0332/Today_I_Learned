import java.io.*;
import java.util.*;

public class Main16920_확장게임 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Task solver = new Task(br, bw);
		solver.solve();
		bw.close();
	}

	static class Pos implements Comparable<Pos> {
		int y, x, turn, s;

		public Pos(int y, int x, int turn, int s) {
			this.y = y;
			this.x = x;
			this.turn = turn;
			this.s = s;
		}

		@Override
		public int compareTo(Pos o) {
			if (this.turn != o.turn)
				return this.turn - o.turn;
			else if (this.s != o.s)
				return o.s - this.s;
			else
				return 0;
		}
	}

	static class Task {
		BufferedReader br;
		BufferedWriter bw;
		int N, M, P, total;
		int[] Si, answer;
		int[][] map;
		boolean[][] visited;
		int[] dr = { 0, 0, 1, -1 };
		int[] dc = { 1, -1, 0, 0 };

		public Task(BufferedReader br, BufferedWriter bw) {
			this.br = br;
			this.bw = bw;
		}

		public void solve() throws IOException {
			String[] line = br.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			M = Integer.parseInt(line[1]);
			P = Integer.parseInt(line[2]);

			map = new int[N][M];
			visited = new boolean[N][M];
			Si = new int[P + 1];
			answer = new int[P + 1];
			PriorityQueue<Pos> q = new PriorityQueue<Pos>();
			line = br.readLine().split(" ");
			for (int p = 1; p <= P; p++) {
				Si[p] = Integer.parseInt(line[p - 1]);
			}

			for (int n = 0; n < N; n++) {
				line = br.readLine().split("");
				for (int m = 0; m < M; m++) {
					if (line[m].charAt(0) == '#') {
						map[n][m] = -1;
						visited[n][m] = true;
					} else if (0 < line[m].charAt(0) - '0' && line[m].charAt(0) - '0' <= 9) {
						map[n][m] = Integer.parseInt(line[m]);
						q.add(new Pos(n, m, map[n][m], Si[map[n][m]]));
						visited[n][m] = true;
						total++;
						answer[map[n][m]]++;
					} else
						total++;
				}
			}

			while (!q.isEmpty()) {
				if (total == 0)
					break;

				Pos cur = q.poll();
				if (cur.s == 0)
					continue;
				int nextTurn = (cur.turn - 1) % P + 1;

				for (int i = 0; i < 4; i++) {
					int ny = cur.y + dr[i];
					int nx = cur.x + dc[i];

					if (0 <= ny && ny < N && 0 <= nx && nx < M && !visited[ny][nx]) {
						visited[ny][nx] = true;
						answer[(cur.turn - 1) % P + 1]++;
						map[ny][nx] = cur.turn;
						total--;
						if (cur.s - 1 > 0)
							q.add(new Pos(ny, nx, cur.turn, cur.s - 1));
						q.add(new Pos(ny, nx, cur.turn + P, Si[(cur.turn-1)%P+1]));
					}
				}
			}

			for (int p = 1; p <= P; p++) {
				bw.write(answer[p] + " ");
			}
		}
	}
}
