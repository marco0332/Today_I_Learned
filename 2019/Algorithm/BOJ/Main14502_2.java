import java.io.*;
import java.util.*;

class Axis{
	int x, y;
	
	public Axis(int y, int x) {
		this.x = x;
		this.y = y;
	}
}

public class Main14502_2 {
	static int N, M, space, answer;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	
	public static void dfs(int y, int x, int cnt) {
		map[y][x] = 1;
		if(cnt == 3) {
			int[][] tmap = new int[N][M];
			Queue<Axis> q = new LinkedList<>();
			int tSpace = space;
			for(int n=0; n<N; n++) {
				for(int m=0; m<M; m++) {
					tmap[n][m] = map[n][m];
					
					if(tmap[n][m] == 2) 
						q.add(new Axis(n,m));
				}
			}
			
			while(!q.isEmpty()) {
				Axis cur = q.poll();
				
				for(int i=0; i<4; i++) {
					int ny = cur.y + dr[i];
					int nx = cur.x + dc[i];
					
					if(0<=ny && ny<N && 0<=nx && nx<M && tmap[ny][nx] == 0) {
						tmap[ny][nx] = 2;
						tSpace--;
						q.add(new Axis(ny,nx));
					}
				}
			}
			answer = Math.max(answer, tSpace-3);
		} else {
			if((x+1)%M != 0) {
				for(int j=x+1; j<M; j++) {
					if(map[y][j] == 0)
						dfs(y, j, cnt+1);
				}
			}
			for(int i=y+1; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] == 0)
						dfs(i, j, cnt+1);
				}
			}
		}
		map[y][x] = 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		map = new int[N][M];
		
		for(int n=0; n<N; n++) {
			String[] line = br.readLine().split(" ");
			for(int m=0; m<M; m++) {
				map[n][m] = Integer.parseInt(line[m]);
				
				if(map[n][m] == 0) space++;
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0)
					dfs(i, j, 1);
			}
		}
		System.out.println(answer);
	}
}
