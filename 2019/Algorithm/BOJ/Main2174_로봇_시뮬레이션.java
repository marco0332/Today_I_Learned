import java.io.*;
import java.util.*;

public class Main2174_로봇_시뮬레이션 {
	static class Robot {
		int x, y, num, dir;

		public Robot(int x, int y, int num, int dir) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}
	}

	static int[] dr = { 0, -1, 0, 1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] RC = br.readLine().split(" ");
		int C = Integer.parseInt(RC[0]);
		int R = Integer.parseInt(RC[1]);
		int[][] board = new int[R + 1][C + 1];

		String[] NM = br.readLine().split(" ");
		int N = Integer.parseInt(NM[0]);
		int M = Integer.parseInt(NM[1]);
		Robot[] robots = new Robot[N + 1];

		for (int n = 0; n < N; n++) {
			String[] XY = br.readLine().split(" ");
			int x = Integer.parseInt(XY[0]);
			int y = Integer.parseInt(XY[1]);
			char direct = XY[2].charAt(0);

			int dir;
			if (direct == 'E')
				dir = 0;
			else if (direct == 'W')
				dir = 2;
			else if (direct == 'S')
				dir = 1;
			else
				dir = 3;

			robots[n + 1] = new Robot(x, y, n + 1, dir);
			board[y][x] = n + 1;
		}

		boolean check = false;
		for (int m = 0; m < M; m++) {
			String[] input = br.readLine().split(" ");
			int num = Integer.parseInt(input[0]);
			char order = input[1].charAt(0);
			int iter = Integer.parseInt(input[2]);

			if (check)
				continue;

			for (int i = 0; i < iter; i++) {
				if(check)
					break;
				
				if (order == 'L') {
					if (robots[num].dir == 0) robots[num].dir = 3;
					else robots[num].dir--;
				} 
				else if (order == 'R') {
					robots[num].dir = (robots[num].dir + 1) % 4;
				} 
				else {
					Robot cur = robots[num];

					board[cur.y][cur.x] = 0;
					int ny = cur.y + dr[cur.dir];
					int nx = cur.x + dc[cur.dir];

					if (0 < ny && ny <= R && 0 < nx && nx <= C) {
						if (board[ny][nx] == 0) {
							board[ny][nx] = cur.num;
							cur.y = ny;
							cur.x = nx;
						} else if (board[ny][nx] != 0) {
							sb.append("Robot " + cur.num + " crashes into robot " + board[ny][nx]);
							check = true;
						}
					} else {
						sb.append("Robot " + cur.num + " crashes into the wall");
						check = true;
					}
				}
			}
		}
		if (!check)
			sb.append("OK");

		System.out.println(sb);
	}
}
