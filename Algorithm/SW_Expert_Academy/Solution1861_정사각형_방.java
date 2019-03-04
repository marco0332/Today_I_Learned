import java.io.*;

public class Solution1861_정사각형_방 {
	static int answer, tmp, N;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };
	static int[][] board;
	static int[][] visited;

	public static int dfs(int r, int c) {
		if (visited[r][c] == 0) {
			visited[r][c] = 1;

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (0 <= nr && nr < N && 0 <= nc && nc < N && board[nr][nc] - board[r][c] == 1) {
					visited[r][c] = Math.max(visited[r][c], dfs(nr, nc) + 1);
				}
			}
		}
		return visited[r][c];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			visited = new int[N][N];
			answer = 0;
			int idx = -1;

			for (int i = 0; i < N; i++) {
				String[] line = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(line[j]);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					tmp = dfs(i, j);
					if (tmp > answer) {
						answer = tmp;
						idx = board[i][j];
					} else if (tmp == answer && board[i][j] < idx) {
						idx = board[i][j];
					}
				}
			}

			sb.append("#" + t + " " + idx + " " + answer);
			System.out.println(sb);
		}
	}
}
