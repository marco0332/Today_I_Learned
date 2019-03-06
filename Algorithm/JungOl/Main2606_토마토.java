import java.io.*;
import java.util.*;

public class Main2606_토마토 {
	static class Tomato {
		int h, n, m;

		public Tomato(int h, int n, int m) {
			this.h = h;
			this.n = n;
			this.m = m;
		}
	}

	static int[] dr = { 1, -1, 0, 0, 0, 0 };
	static int[] dc = { 0, 0, 1, -1, 0, 0 };
	static int[] dh = { 0, 0, 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] MNH = br.readLine().split(" ");
		int M = Integer.parseInt(MNH[0]);
		int N = Integer.parseInt(MNH[1]);
		int H = Integer.parseInt(MNH[2]);
		int Target = 0;
		int tomatoN = 0;
		int[][][] box = new int[H][N][M];
		boolean[][][] visited = new boolean[H][N][M];
		Queue<Tomato> q = new LinkedList<Tomato>();
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				String[] line = br.readLine().split(" ");
				for (int m = 0; m < M; m++) {
					box[h][n][m] = Integer.parseInt(line[m]);

					if (box[h][n][m] == 1) {
						q.add(new Tomato(h, n, m));
						tomatoN++;
						Target++;
					} else if (box[h][n][m] == 0)
						Target++;
				}
			}
		}
		
		int answer = 0;
		while (true) {
			int size = q.size();
			if (size == 0 || Target == tomatoN)
				break;

			for (int i = 0; i < size; i++) {
				Tomato cur = q.poll();

				for (int k = 0; k < 6; k++) {
					int nr = cur.n + dr[k];
					int nc = cur.m + dc[k];
					int nh = cur.h + dh[k];

					if (0 <= nr && nr < N && 0 <= nc && nc < M && 0 <= nh && nh < H && !visited[nh][nr][nc]
							&& box[nh][nr][nc] == 0) {
						visited[nh][nr][nc] = true;
						q.add(new Tomato(nh, nr, nc));
						tomatoN++;
					}
				}
			}
			answer++;
		}
		
		if(Target == tomatoN) System.out.println(answer);
		else System.out.println("-1");
	}
}
