import java.io.*;
import java.util.*;

public class Main3190_뱀 {
	static class order {
		int sec;
		char dir;

		public order(int sec, char dir) {
			this.sec = sec;
			this.dir = dir;
		}
	}
	
	static class pos {
		int x, y;
		
		public pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, direct, curX, curY, answer;
	/** 동 남 서 북 */
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int[][] board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Queue<pos> q = new LinkedList<pos>();
		N = Integer.parseInt(br.readLine());
		board = new int[N + 1][N + 1];
		int K = Integer.parseInt(br.readLine());
		for (int k = 0; k < K; k++) {
			String[] rc = br.readLine().split(" ");
			int r = Integer.parseInt(rc[0]);
			int c = Integer.parseInt(rc[1]);

			board[r-1][c-1] = 1;
		}
		board[0][0] = -1;
		q.add(new pos(0,0));

		int L = Integer.parseInt(br.readLine());
		order[] oList = new order[L];
		for (int l = 0; l < L; l++) {
			String[] xc = br.readLine().split(" ");
			int x = Integer.parseInt(xc[0]);
			char tmpc = xc[1].charAt(0);

			oList[l] = new order(x, tmpc);
		}

		int lIdx = 0;
		int runTime = -1;
		int checkTime = 0;
		char nextDir = ' ';
		while (true) {
			if(lIdx < L && runTime == -1) {
				order next = oList[lIdx++];
				
				runTime = next.sec;
				nextDir = next.dir;
			}
			int nextR = curY + dr[direct];
			int nextC = curX + dc[direct];
			answer++;
			
			if(0<=nextR && nextR<N && 0<=nextC && nextC<N && board[nextR][nextC] != -1) {
				q.add(new pos(nextC, nextR));
				
				if(board[nextR][nextC] != 1) {
					pos p = q.poll();
					board[p.y][p.x] = 0; 
				}
				board[nextR][nextC] = -1;
			} 
			else
				break;
			
			curY = nextR;
			curX = nextC;
			checkTime++;
			if(checkTime == runTime) {
				runTime = -1;
				
				if(nextDir == 'D') {
					direct = (direct+1)%4;
				} else if(nextDir == 'L') {
					if(direct==0) direct = 3;
					else direct--;
				}
			}
		}
		
		System.out.println(answer);
	}
}
