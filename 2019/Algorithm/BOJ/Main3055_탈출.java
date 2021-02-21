import java.io.*;
import java.util.*;

public class Main3055_탈출 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int sr=0,sc=0;
		int[] dr = {0,0,1,-1};
		int[] dc = {1,-1,0,0};
		
		Queue<Pos> q = new LinkedList<Pos>();
		char[][] map = new char[R][C];
		for(int r=0; r<R; r++) {
			String line = br.readLine();
			for(int c=0; c<C; c++) {
				map[r][c] = line.charAt(c);
				
				if(map[r][c] == 'S') {
					sr = r;
					sc = c;
				} else if(map[r][c] == '*') {
					q.add(new Pos(r,c));
				}
			}
		}
		q.add(new Pos(sr,sc));
		
		int cnt = 0;
		while(true) {
			cnt++;
			int size = q.size();
			if(size == 0)
				break;
			
			for(int i=0; i<size; i++) {
				Pos cur = q.poll();
				
				for(int j=0; j<4; j++) {
					int nr = cur.r + dr[j];
					int nc = cur.c + dc[j];
					
					if(0<=nr && nr<R && 0<=nc && nc<C) {
						if(map[nr][nc] == '.') {
							map[nr][nc] = map[cur.r][cur.c];
							q.add(new Pos(nr,nc));
						} else if(map[nr][nc] == 'D' && map[cur.r][cur.c] == 'S') {
							System.out.println(cnt);
							return;
						}
					}
				}
			}
		}
		System.out.println("KAKTUS");
	}
	
	static class Pos {
		int r, c;
		
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
