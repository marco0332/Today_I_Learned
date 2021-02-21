import java.io.*;
import java.util.*;

public class Main1987 {
	static int R, C, answer;
	static char[][] map;
	static boolean[] alpha;
	static boolean[][] visited;
	
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	
	public static void dfs(int y, int x, int depth) {
		alpha[map[y][x]-'A'] = true;
		visited[y][x] = true;
		
		for(int i=0; i<4; i++) {
			int ny = y + dr[i];
			int nx = x + dc[i];
			
			if(0<=ny && ny<R && 0<=nx && nx<C && !alpha[map[ny][nx]-'A'] && !visited[ny][nx]) {
				dfs(ny,nx,depth+1);
			}
		}
		answer = Math.max(answer, depth);
		
		visited[y][x] = false;
		alpha[map[y][x]-'A'] = false;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		map = new char[R][C];
		alpha = new boolean[26];
		visited = new boolean[R][C];
		sc.nextLine();
		
		for(int r=0; r<R; r++) {
			String line = sc.nextLine();
			for(int c=0; c<C; c++) {
				map[r][c] = line.charAt(c);
			}
		}
		dfs(0,0,1);
		System.out.println(answer);
	}
}
