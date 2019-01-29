import java.io.*;
import java.util.*;

public class Main2468 {
	static int N;
	static int[][] area;
	static boolean[][] visited;
	
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	
	public static void dfs(int h, int y, int x) {
		visited[y][x] = true;
		
		for(int i=0; i<4; i++) {
			int ny = y + dr[i];
			int nx = x + dc[i];
			
			if(0<=ny && ny<N && 0<=nx && nx<N && !visited[ny][nx] && area[ny][nx]-h>0){
				dfs(h, ny, nx);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		area = new int[N][N];
		for(int i=0; i<N; i++) {
			String[] line = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				area[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		int answer = 0;
		for(int height=0; height<=100; height++) {
			visited = new boolean[N][N];
			int cnt = 0;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j] && area[i][j]-height > 0) {
						dfs(height, i, j);
						cnt++;
					}
				}
			}
			answer = Math.max(answer, cnt);
		}
		System.out.println(answer);
	}
}
