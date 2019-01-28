import java.io.*;
import java.util.*;

public class Main1012 {
	static int M, N, K;
	static int[][] map;
	static boolean[][] visited;
	static int answer;

	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	
	public static void dfs(int y, int x) {
		visited[y][x] = true;
		
		for(int i=0; i<4; i++) {
			int ny = y + dr[i];
			int nx = x + dc[i];
			
			if(0<=ny && ny<N && 0<=nx && nx<M && !visited[ny][nx] && map[ny][nx] == 1) {
				dfs(ny,nx);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			String[] MNK = br.readLine().split(" ");
			M = Integer.parseInt(MNK[0]);
			N = Integer.parseInt(MNK[1]);
			K = Integer.parseInt(MNK[2]);
			map = new int[N][M];
			visited = new boolean[N][M];
			answer = 0;

			String[] xy;
			for (int k = 0; k < K; k++) {
				xy = br.readLine().split(" ");

				int x = Integer.parseInt(xy[0]);
				int y = Integer.parseInt(xy[1]);

				map[y][x] = 1;
			}
			
			for(int r=0; r<N; r++) {
				for(int c=0; c<M; c++) {
					if(!visited[r][c] && map[r][c] == 1) {
						dfs(r,c);
						answer++;
					}
				}
			}
			
			System.out.println(answer);
		}
	}
}
