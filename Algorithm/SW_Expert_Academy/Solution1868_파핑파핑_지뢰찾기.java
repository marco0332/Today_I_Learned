import java.io.*;
import java.util.*;

public class Solution1868_파핑파핑_지뢰찾기 {
	static class Pos {
		int y, x;

		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int N, blank, answer;
	static char[][] map;
	static int[][] val;
	static boolean[][] visited;
	static int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dc = { -1, 0, 1, 1, 1, 0, -1, -1 };
	static Queue<Pos> q;

	public static void bfs(int i, int j) {
		q.add(new Pos(i, j));
		visited[i][j] = true;
		answer++;
		blank--;

		while (!q.isEmpty()) {
			Pos cur = q.poll();

			for (int k = 0; k < 8; k++) {
				int ni = cur.y + dr[k];
				int nj = cur.x + dc[k];

				if (0 <= ni && ni < N && 0 <= nj && nj < N && !visited[ni][nj] && map[ni][nj] != '*') {
					visited[ni][nj] = true;
					blank--;

					if (val[ni][nj] == 0)
						q.add(new Pos(ni, nj));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			val = new int[N][N];
			visited = new boolean[N][N];
			blank = N * N;

			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j);
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if (map[i][j] == '*') {
						blank--;
						for (int k = 0; k < 8; k++) {
							int ni = i + dr[k];
							int nj = j + dc[k];

							if (0 <= ni && ni < N && 0 <= nj && nj < N && map[ni][nj] == '.') {
								val[ni][nj]++;
							}
						}
					}
				}
			}

			answer = 0;
			q = new LinkedList<Pos>();
			for (int i = 1; i < N - 1; i++) {
				for (int j = 1; j < N - 1; j++) {
					if (!visited[i][j] && val[i][j] == 0 && map[i][j] != '*')
						bfs(i, j);
				}
			}

			for (int i = 0; i < N; i++) {
				if (!visited[i][0] && val[i][0] == 0 && map[i][0] != '*')
					bfs(i, 0);
				if (!visited[i][N - 1] && val[i][N - 1] == 0 && map[i][N - 1] != '*')
					bfs(i, N - 1);
				if (!visited[0][i] && val[0][i] == 0 && map[0][i] != '*')
					bfs(0, i);
				if (!visited[N - 1][i] && val[N - 1][i] == 0 && map[N - 1][i] != '*')
					bfs(N - 1, i);
			}

			answer += blank;
			sb.append("#" + t + " " + answer);
			System.out.println(sb);
		}
	}
}
