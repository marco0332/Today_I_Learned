import java.io.*;
import java.util.*;

public class Main1733_오목 {
	static int[] dr = { -1, 0, 1, 1 };
	static int[] dc = { 1, 1, 1, 0 };
	static int[] ndr = { -1, -1, 0, 1 };
	static int[] ndc = { 0, -1, -1, -1 };
	final static int N = 19;

	public static boolean isOk(int r, int c) {
		if (0 <= r && r < N && 0 <= c && c < N)
			return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] pan = new int[19][19];
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				pan[i][j] = Integer.parseInt(line[j]);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (pan[i][j] > 0) {
					int cur = pan[i][j];

					for (int k = 0; k < 4; k++) {
						int cnt = 1;
						int nr = i;
						int nc = j;

						while (isOk(nr + dr[k], nc + dc[k])) {
							nr += dr[k];
							nc += dc[k];

							if (pan[nr][nc] == cur)
								cnt++;
							else
								break;
						}

						if (cnt == 5) {
							int verseDir = 3 - k;
							int ncnt = 0;
							nr = i;
							nc = j;
							while (isOk(nr + ndr[verseDir], nc + ndc[verseDir])) {
								nr += ndr[verseDir];
								nc += ndc[verseDir];

								if (pan[nr][nc] == cur)
									ncnt++;
								else
									break;
							}
							if (ncnt == 0) {
								System.out.println(cur);
								System.out.println((i + 1) + " " + (j + 1));
								System.exit(0);
							}
						}
					}
				}
			}
		}
		System.out.println(0);
	}
}
