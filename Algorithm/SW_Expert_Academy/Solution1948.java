import java.io.*;
import java.util.*;

public class Solution1948 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int[] mDay = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			int M1 = Integer.parseInt(st.nextToken());
			int D1 = Integer.parseInt(st.nextToken());
			int M2 = Integer.parseInt(st.nextToken());
			int D2 = Integer.parseInt(st.nextToken());

			int answer = 0;

			if (M1 == M2)
				answer = D2 - D1 + 1;
			else {
				for (int i = M1 + 1; i < M2; i++) {
					answer += mDay[i];
				}
				answer += mDay[M1] - D1;
				answer += D2 + 1;
			}
			System.out.printf("#%d %d\n", t, answer);
		}
	}
}
