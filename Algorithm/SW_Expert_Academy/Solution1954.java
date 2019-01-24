import java.io.*;
import java.util.*;

public class Solution1954 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			int N = sc.nextInt();
			int[] dr = { 0, 1, 0, -1 };
			int[] dc = { 1, 0, -1, 0 };
			int[][] map = new int[N][N];

			int r = 0;
			int c = 0;
			int dir = 0;
			for (int i = 1; i <= N * N; i++) {
				map[r][c] = i;

				int nr = r + dr[dir];
				int nc = c + dc[dir];
				if (0 <= nr && nr < N && 0 <= nc && nc < N && map[nr][nc] == 0) {
					r = nr;
					c = nc;
				} else {
					dir = (dir+1)%4;
					r = r + dr[dir];
					c = c + dc[dir];
				}
			}

			System.out.println("#" + t);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j]+" ");
				}
				System.out.println();
			}
		}
	}
}
