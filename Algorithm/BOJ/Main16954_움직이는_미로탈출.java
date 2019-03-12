import java.io.*;
import java.util.*;

public class Main16954_움직이는_미로탈출 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Task solver = new Task(br, bw);
		solver.solve();
		bw.close();
	}

	static class Person {
		int y, x, cnt;

		public Person(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}

	static class Task {
		BufferedReader br;
		BufferedWriter bw;
		boolean[][] map;
		int[] dr = { -1, -1, -1, 0, 0, 0, 1, 1 };
		int[] dc = { -1, 0, 1, -1, 0, 1, -1, 1 };

		public Task(BufferedReader br, BufferedWriter bw) {
			this.br = br;
			this.bw = bw;
			map = new boolean[9][8];
		}

		public void solve() throws IOException {
			for (int i = 1; i <= 8; i++) {
				String line = br.readLine();
				for (int j = 0; j < 8; j++) {
					map[i][j] = line.charAt(j) == '.' ? false : true;
				}
			}

			if (bfs(8, 0, 0))
				bw.write(1 + "\n");
			else
				bw.write(0 + "\n");
		}

		public boolean bfs(int y, int x, int cnt) {
			Queue<Person> q = new LinkedList<Person>();
			q.add(new Person(y, x, cnt));
			int safeDay = 0;

			while (true) {
				if(q.size() == 0)
					break;
				
				for (int s = 0, size = q.size(); s < size; s++) {
					Person cur = q.poll();
					if(map[cur.y][cur.x]) continue;
					if (safeDay >= 7 || (cur.y == 1 && cur.x == 7)) {
						return true;
					}
					if (safeDay < cur.cnt) {
						safeDay = cur.cnt;
					}

					for (int i = 0; i < 8; i++) {
						int ny = cur.y + dr[i];
						int nx = cur.x + dc[i];

						if (0 < ny && ny <= 8 && 0 <= nx && nx < 8 && !map[ny][nx]) {
							q.add(new Person(ny, nx, cnt + 1));
						}
					}
				}
				
				for(int i=8; i>0; i--) {
					for(int j=0; j<8; j++) {
						map[i][j] = map[i-1][j];
					}
				}
			}

			return false;
		}
	}
}
