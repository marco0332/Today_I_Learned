import java.io.*;
import java.util.*;

public class Main1840_치즈 {
	static class Pos {
		int y, x;

		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int N, M;
	static int[][] map;
	static Queue<Pos> q;
	static Queue<Pos> q2;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };
	static boolean[][] visited;

	public static void bfs(int r, int c) {
		q.add(new Pos(r, c));

		while (!q.isEmpty()) {
			Pos cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = cur.y + dr[i];
				int nc = cur.x + dc[i];

				if (0 <= nr && nr < N && 0 <= nc && nc < M && !visited[nr][nc] && map[nr][nc] == 0) {
					visited[nr][nc] = true;
					q.add(new Pos(nr, nc));
				}
			}
		}
	}

	public static void bfs2(int r, int c) {
		q2.add(new Pos(r, c));

		while (!q2.isEmpty()) {
			Pos cur = q2.poll();

			for (int i = 0; i < 4; i++) {
				int nr = cur.y + dr[i];
				int nc = cur.x + dc[i];

				if (0 <= nr && nr < N && 0 <= nc && nc < M) {
					if (!visited[nr][nc] && map[nr][nc] == 0) {
						q2.add(new Pos(nr, nc));
						visited[nr][nc] = true;
					}
					else if(!visited[nr][nc] && map[nr][nc] == 1) {
						q.add(new Pos(nr,nc));
						visited[nr][nc] = true;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		q = new LinkedList<Pos>();
		q2 = new LinkedList<Pos>();
		map = new int[N][M];
		visited = new boolean[N][M];
		int total = 0;

		for (int n = 0; n < N; n++) {
			String[] line = br.readLine().split(" ");
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(line[m]);

				if (map[n][m] == 1)
					total++;
			}
		}
		visited[0][0] = true;
		bfs(0, 0);

		for (int n = 1; n < N - 1; n++) {
			for (int m = 1; m < M - 1; m++) {
				if (map[n][m] == 1) {
					for (int i = 0; i < 4; i++) {
						int nr = n + dr[i];
						int nc = m + dc[i];

						if (visited[nr][nc] && map[nr][nc] == 0) {
							q.add(new Pos(n, m));
							visited[n][m] = true;
							break;
						}
					}
				}
			}
		}

		int cnt = 0;
		int last = 0;
		while (true) {
			int size = q.size();
			if (size == 0)
				break;

			cnt++;
			last = total;

			for (int i = 0; i < size; i++) {
				Pos cur = q.poll();
				map[cur.y][cur.x] = 0; 
				total--;

				for (int j = 0; j < 4; j++) {
					int nr = cur.y + dr[j];
					int nc = cur.x + dc[j];

					if (!visited[nr][nc] && map[nr][nc] == 0) {
						bfs2(nr, nc);
					} else if (!visited[nr][nc] && map[nr][nc] == 1) {
						q.add(new Pos(nr, nc));
						visited[nr][nc] = true;
					}
				}
			}
		}

		System.out.println(cnt);
		System.out.println(last);
	}
}
