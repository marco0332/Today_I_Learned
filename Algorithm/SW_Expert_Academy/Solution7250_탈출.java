import java.io.*;
import java.util.*;

public class Solution7250_탈출 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Task solver = new Task(br, bw);
		solver.solve();
		bw.close();
	}

	static class Pos {
		/** state는 S가 1, V가 2, F가 3 */
		int y, x, state, k;

		public Pos(int y, int x, int state, int k) {
			this.y = y;
			this.x = x;
			this.state = state;
			this.k = k;
		}
	}

	static class Task {
		BufferedReader br;
		BufferedWriter bw;
		int N, M, K;
		char[][] map;
		boolean[][][] visitedS;
		boolean[][] visitedV;
		boolean[][] visitedF;
		Queue<Pos> q;
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
				M = Integer.parseInt(line[1]);
				K = Integer.parseInt(line[2]);
				q = new LinkedList<Pos>();
				map = new char[N][M];
				visitedS = new boolean[K+1][N][M];
				visitedV = new boolean[N][M];
				visitedF = new boolean[N][M];

				for (int n = 0; n < N; n++) {
					line = br.readLine().split("");
					for (int m = 0; m < M; m++) {
						map[n][m] = line[m].charAt(0);

						if (map[n][m] == 'S')
							q.add(new Pos(n, m, 1, 0));
						else if (map[n][m] == 'V')
							q.add(new Pos(n, m, 2, 0));
						else if (map[n][m] == 'F')
							q.add(new Pos(n, m, 3, 0));
					}
				}

				bw.write("#" + t + " " + bfs() + "\n");
			}
		}

		public int bfs() {
			int cnt = 0;
			int scot = 0;
			int versus = 0;

			while (true) {
				int size = q.size();
				if (size == 0)
					break;
				cnt++;

				for (int s = 0; s < size; s++) {
					Pos cur = q.poll();

					for (int i = 0; i < 4; i++) {
						int ny = cur.y + dr[i];
						int nx = cur.x + dc[i];

						if (0 <= ny && ny < N && 0 <= nx && nx < M) {
							if (cur.state == 1) {
								if(map[ny][nx] == 'E') {
									scot = cnt;
									break;
								} else if(map[ny][nx] == 'A' && !visitedS[cur.k][ny][nx]) {
									visitedS[cur.k][ny][nx] = true;
									q.add(new Pos(ny,nx,1,0));
								} else if(map[ny][nx] == 'W' && cur.k < K && !visitedS[cur.k+1][ny][nx]) {
									visitedS[cur.k+1][ny][nx] = true;
									q.add(new Pos(ny,nx,1,cur.k+1));
								}
							} else if (cur.state == 2) {
								if(map[ny][nx] == 'E') {
									versus = cnt;
									break;
								} else if((map[ny][nx] == 'A' || map[ny][nx] == 'F') && !visitedV[ny][nx]) {
									visitedV[ny][nx] = true;
									q.add(new Pos(ny,nx,2,0));
								}
							} else if (cur.state == 3) {
								if(map[ny][nx] != 'W' && map[ny][nx] != 'X' && map[ny][nx] != 'E' && !visitedF[ny][nx]) {
									visitedF[ny][nx] = true;
									q.add(new Pos(ny,nx,3,0));
									map[ny][nx] = 'F';
								}
							}
						}
					}
					
					if(versus > 0) {
						return -1;
					} else if(scot > 0) {
						return scot;
					}
				}
			}
			return -1;
		}
	}
}
