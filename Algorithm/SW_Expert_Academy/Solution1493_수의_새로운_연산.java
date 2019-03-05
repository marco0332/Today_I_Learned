import java.io.*;
import java.util.*;

public class Solution1493_수의_새로운_연산 {
	static class Pos {
		int y, x;

		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static int PtoV(Pos p) {
		int N = p.y-1 + p.x-1;
		return 1 + N * (N + 1) / 2 + p.x-1;
	}

	public static Pos VtoP(int val) {
		int i = 0;
		int jump = 0;
		for (; i <= 1000; i++) {
			if (val <= 1 + i * (i + 1) / 2 + i) {
				jump = val - (1 + i * (i + 1) / 2);
				break;
			}
		}

		return new Pos(i - jump+1, jump+1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			String[] XY = br.readLine().trim().split(" ");
			int x = Integer.parseInt(XY[0]);
			int y = Integer.parseInt(XY[1]);

			Pos p1 = VtoP(x);
			Pos p2 = VtoP(y);
			p1.x += p2.x;
			p1.y += p2.y;
			sb.append("#" + t + " " + PtoV(p1));
			System.out.println(sb);
		}
	}
}
