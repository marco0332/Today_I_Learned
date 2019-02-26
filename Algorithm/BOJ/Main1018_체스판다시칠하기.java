import java.io.*;
import java.util.*;

public class Main1018_체스판다시칠하기 {
	static int M, N;
	static int answer = Integer.MAX_VALUE;
	static char[][] board;
	static char[] state = {'B', 'W'};
	
	static void go(int M, int N, int s) {
		int cnt = 0;
		for(int m=M; m<M+8; m++) {
			for(int n=N; n<N+8; n++) {
				if(board[m][n] != state[s])
					cnt++;
				
				s = (s+1)%2;
			}
			s = (s+1)%2;
		}
		
		answer = Math.min(answer, cnt);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] MN = br.readLine().split(" ");
		M = Integer.parseInt(MN[0]);
		N = Integer.parseInt(MN[1]);
		board = new char[M][N];
		
		for(int m=0; m<M; m++) {
			String[] line = br.readLine().split("");
			for(int n=0; n<N; n++) {
				board[m][n] = line[n].charAt(0);
			}
		}
		
		for(int m=0; m<=M-8; m++) {
			for(int n=0; n<=N-8; n++) {
				go(m, n, 1);
				go(m, n, 0);
			}
		}
		System.out.println(answer);
	}
}
