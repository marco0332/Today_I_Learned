import java.io.*;
import java.util.*;

public class Main1937_욕심쟁이판다 {
	static int N, answer;
	static int[][] dp;
	static int[][] map;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static int go(int i, int j) {
		if(dp[i][j] != 0)
			return dp[i][j];
		
		dp[i][j] = 1;
		
		for(int k=0; k<4; k++) {
			int nr = i+dr[k];
			int nc = j+dc[k];
			
			if(0<=nr && nr<N && 0<=nc && nc<N && map[i][j] < map[nr][nc]) {
				dp[i][j] = Math.max(dp[i][j], go(nr,nc)+1);
			}
		}
		
		return dp[i][j];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][N];

		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				go(i, j);
				
				if(answer < dp[i][j])
					answer = dp[i][j];
			}
		}
		System.out.println(answer);
	}
}
