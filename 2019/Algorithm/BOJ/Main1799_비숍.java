import java.io.*;
import java.util.*;

public class Main1799_비숍 {
	static int N, MAX, answer;
	static int[][] stateC = {{0, 1}, {1, 0}};
	static int[][] board;
	
	public static void go(int r, int c, int cnt, int state) {
		int nr = r;
		int nc = c;
		while(nr < N) {
			while (nr < N && nc < N && (board[nr][nc] == 0 || board[nr][nc] > 1)) {
				nc += 2;

				if (nc >= N) {
					nr++;
					nc = stateC[state][nr%2];
				}
			}
			
			if(nr >= N)
				break;
			
			board[nr][nc] = -1;
			answer = Math.max(answer, cnt+1);
			for(int i=1; i<N; i++) {
				if(nr+i<N && nc+i<N && board[nr+i][nc+i] >= 1) board[nr+i][nc+i]++;
				if(nr+i<N && nc-i>=0 && board[nr+i][nc-i] >= 1) board[nr+i][nc-i]++;
				if(nr-i>=0 && nc+i<N && board[nr-i][nc+i] >= 1) board[nr-i][nc+i]++;
				if(nr-i>=0 && nc-i>=0 && board[nr-i][nc-i] >= 1) board[nr-i][nc-i]++;
			}
			
			r = nr;
			c = nc;
			
			nc += 2;
			if(nc >= N) {
				nr++;
				nc = stateC[state][nr%2];
			}
			
			if(nr < N)
				go(nr, nc, cnt+1, state);
			
			board[r][c] = 1;
			for(int i=1; i<N; i++) {
				if(r+i<N && c+i<N && board[r+i][c+i] > 1) board[r+i][c+i]--;
				if(r+i<N && c-i>=0 && board[r+i][c-i] > 1) board[r+i][c-i]--;
				if(r-i>=0 && c+i<N && board[r-i][c+i] > 1) board[r-i][c+i]--;
				if(r-i>=0 && c-i>=0 && board[r-i][c-i] > 1) board[r-i][c-i]--;
			}
		}
		
//		answer = Math.max(answer, cnt);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		MAX = N*N;
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(input[j]);
			}
		}

		if(N == 1) System.out.println(board[0][0]);
		else {
			go(0, 0, 0, 0);
			int tmpAnswer = answer;
			answer = 0;
			go(0, 1, 0, 1);
			answer += tmpAnswer;
			
			System.out.println(answer);
		}
	}
}