import java.io.*;
import java.util.*;

public class Solution1494_사랑의_카운슬러 {
	static class Pos {
		long x, y;

		public Pos(long x, long y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N;
	static Pos[] pos;
	static long answer;

	static long dist(Pos p) {
		return p.x * p.x + p.y * p.y;
	}

	static void find(int idx, int cnt, long sumX, long sumY) {
		if(cnt == 0) {
			for(int i=idx; i<N; i++) {
				sumX -= pos[i].x;
				sumY -= pos[i].y;
			}
			
			answer = Math.min(answer, dist(new Pos(sumX, sumY)));
			return;
		}
		for(int i=idx; i<N; i++) {
			sumX += pos[i].x;
			sumY += pos[i].y;
			find(i+1, cnt-1, sumX, sumY);
			sumX -= pos[i].x<<1;
			sumY -= pos[i].y<<1;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			pos = new Pos[N];
			answer = Long.MAX_VALUE;

			for (int n = 0; n < N; n++) {
				String[] XY = br.readLine().split(" ");
				int x = Integer.parseInt(XY[0]);
				int y = Integer.parseInt(XY[1]);

				pos[n] = new Pos(x, y);
			}

			find(0, N>>1, 0, 0);

			sb.append("#" + t + " " + answer);
			System.out.println(sb);
		}
	}
}
