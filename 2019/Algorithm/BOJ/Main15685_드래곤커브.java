import java.io.*;
import java.util.*;

public class Main15685_드래곤커브 {
	static class Pos{
		int x, y;
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, dir, curX, curY;
	static boolean[][] board;
	static int[] dr = {0,-1,0,1};
	static int[] dc = {1,0,-1,0};
	
	public static Pos curve(int x, int y, int dir, int g) {
		if(g == 0) {
			board[y][x] = true;
			board[y+dr[dir]][x+dc[dir]] = true;
			
			return new Pos(x+dc[dir], y+dr[dir]);
		} else {
			Pos cur = curve(x, y, dir, g-1);
			
			if(dir == 0) dir = 3;
			else dir--;
			
			int nx = cur.x+cur.y-y;
			int ny = -cur.x+cur.y+x;
			curve(nx, ny, dir, g-1);
			cur = new Pos(nx, ny);
			
			return cur;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		board = new boolean[101][101];
		for(int n=0; n<N; n++) {
			String[] input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0]);
			int y = Integer.parseInt(input[1]);
			int d = Integer.parseInt(input[2]);
			int g = Integer.parseInt(input[3]);
			curve(x, y, d, g);
		}
		
		int answer = 0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(board[i][j] && board[i][j+1] && board[i+1][j] && board[i+1][j+1])
					answer++;
			}
		}
		
		System.out.println(answer);
	}
}
