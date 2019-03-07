import java.io.*;
import java.util.*;

public class Main16918_봄버맨 {
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
		int R, C, N;
		int bomb = -1;
		int[][] map;
		int[] dr = { 0, 0, 1, -1 };
		int[] dc = { 1, -1, 0, 0 };

		public Task(BufferedReader br, BufferedWriter bw) {
			this.br = br;
			this.bw = bw;
		}

		public void solve() throws IOException {
			String[] line = br.readLine().split(" ");
			R = Integer.parseInt(line[0]);
			C = Integer.parseInt(line[1]);
			N = Integer.parseInt(line[2]);

			map = new int[R][C];
			for (int r = 0; r < R; r++) {
				line = br.readLine().split("");
				for (int c = 0; c < C; c++) {
					map[r][c] = line[c].charAt(0) == 'O' ? 1 : 0;
				}
			}

			for (int n = 2; n <= N; n++) {
				go(n % 2);
			}
			print();
		}

		public void go(int state) {
			if(state == 1)
				bomb *= -1;
			
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					
					if (state == 0 && map[r][c] == 0)
						map[r][c] = bomb;
					else if (state == 1 && map[r][c] == bomb) {
						map[r][c] = 0;
						for (int i = 0; i < 4; i++) {
							int nr = r + dr[i];
							int nc = c + dc[i];

							if (0 <= nr && nr < R && 0 <= nc && nc < C && map[nr][nc] != bomb) {
								map[nr][nc] = 0;
							}
						}
					}
					
				}
			}
		}

		public void print() throws IOException {
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					bw.write(map[r][c] != 0 ? 'O' : '.');
				}
				bw.newLine();
			}
		}
	}
}
