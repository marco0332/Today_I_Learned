import java.io.*;

public class Main12100_2048_easy {
	static int N;

	/** 상 좌 하 우 0 1 2 3 */
	public static int[][] find(int[][] board, int direction) {
		int[][] res = new int[N][N];

		if (direction == 0) {
			for (int j = 0; j < N; j++) {
				int idx = 0;
				int befo = -1;

				for (int i = 0; i < N; i++) {
					if (board[i][j] == 0)
						continue;

					if (befo == -1)
						befo = board[i][j];
					else if (befo == board[i][j]) {
						res[idx++][j] = befo * 2;
						befo = -1;
					} else {
						res[idx++][j] = befo;
						befo = board[i][j];
					}
				}
				if(befo != -1)
					res[idx++][j] = befo;
			}
		}

		else if (direction == 1) {
			for (int i = 0; i < N; i++) {
				int idx = 0;
				int befo = -1;

				for (int j = 0; j < N; j++) {
					if (board[i][j] == 0)
						continue;

					if (befo == -1)
						befo = board[i][j];
					else if (befo == board[i][j]) {
						res[i][idx++] = befo * 2;
						befo = -1;
					} else {
						res[i][idx++] = befo;
						befo = board[i][j];
					}
				}
				if(befo != -1)
					res[i][idx++] = befo;
			}
		}

		else if (direction == 2) {
			for (int j = 0; j < N; j++) {
				int idx = N - 1;
				int befo = -1;

				for (int i = N - 1; i >= 0; i--) {
					if (board[i][j] == 0)
						continue;

					if (befo == -1)
						befo = board[i][j];
					else if (befo == board[i][j]) {
						res[idx--][j] = befo * 2;
						befo = -1;
					} else {
						res[idx--][j] = befo;
						befo = board[i][j];
					}
				}
				if(befo != -1)
					res[idx--][j] = befo;
			}
		}

		else if (direction == 3) {
			for (int i = 0; i < N; i++) {
				int idx = N - 1;
				int befo = -1;

				for (int j = N - 1; j >= 0; j--) {
					if (board[i][j] == 0)
						continue;

					if (befo == -1)
						befo = board[i][j];
					else if (befo == board[i][j]) {
						res[i][idx--] = befo * 2;
						befo = -1;
					} else {
						res[i][idx--] = befo;
						befo = board[i][j];
					}
				}
				if(befo != -1)
					res[i][idx--] = befo;
			}
		}

		return res;
	}

	public static int go(int[][] board, int cnt) {
		int Max = Integer.MIN_VALUE;
		if (cnt == 5) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (Max < board[i][j])
						Max = board[i][j];
				}
			}
		} else {
			int[][] tmp = new int[N][N];
			for (int direction = 0; direction < 4; direction++) {
				tmp = find(board, direction);
				Max = Math.max(Max, go(tmp, cnt + 1));
			}
		}
		
		return Max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		int[][] board = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(line[j]);
			}
		}

		System.out.println(go(board, 0));
	}
}
