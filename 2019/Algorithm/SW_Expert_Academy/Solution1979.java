import java.io.*;
import java.util.*;

public class Solution1979 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];
			int blank = 0;
			int answer = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				blank = 0;
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1)
						blank++;

					if (map[i][j] == 0 || j == N - 1) {
						if (blank == K) {
							answer++;
							blank = 0;
						} else
							blank = 0;
					}
				}
			}

			for (int j = 0; j < N; j++) {
				blank = 0;
				for (int i = 0; i < N; i++) {
					if (map[i][j] == 1)
						blank++;

					if (map[i][j] == 0 || i == N - 1) {
						if (blank == K) {
							answer++;
							blank = 0;
						} else
							blank = 0;
					}
				}
			}

			System.out.printf("#%d %d\n", t, answer);
		}
	}
}
