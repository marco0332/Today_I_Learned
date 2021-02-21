import java.io.*;
import java.util.*;

public class Main2589_보물섬 {
	static class Pos {
		int y, x;
		
		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	static boolean[][] board;
	static int R, C;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] RC = br.readLine().split(" ");
		R = Integer.parseInt(RC[0]);
		C = Integer.parseInt(RC[1]);
		int answer = Integer.MIN_VALUE;
		
		board = new boolean[R][C];
		for(int r=0; r<R; r++) {
			String[] line = br.readLine().split("");
			for(int c=0; c<C; c++) {
				if(line[c].charAt(0) == 'L') board[r][c] = true;
				else board[r][c] = false;
			}
		}
		
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(board[r][c]) {
					
					int cnt = -1;
					Queue<Pos> q = new LinkedList<Pos>();
					boolean[][] visited = new boolean[R][C];
					
					q.add(new Pos(r,c));
					visited[r][c] = true;
					while(true) {
						if(q.isEmpty()) break;
						cnt++;
						
						for(int i=0, size=q.size(); i<size; i++) {
							Pos cur = q.poll();
							
							for(int j=0; j<4; j++) {
								int ny = cur.y + dr[j];
								int nx = cur.x + dc[j];
								
								if(0<=ny && ny<R && 0<=nx && nx<C && !visited[ny][nx] && board[ny][nx]) {
									visited[ny][nx] = true;
									q.add(new Pos(ny, nx));
								}
							}
						}
					}
					
					answer = Math.max(answer, cnt);
				}
			}
		}
		
		System.out.println(answer);
	}
}
