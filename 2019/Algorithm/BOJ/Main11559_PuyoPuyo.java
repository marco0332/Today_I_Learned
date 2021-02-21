import java.io.*;
import java.util.*;

public class Main11559_PuyoPuyo {
	static char[][] map = new char[12][6];
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	static final int R = 12, C=6;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int i=0; i<R; i++) {
			String line = br.readLine();
			for(int j=0;j<C; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		int ans = 0;
		while(true) {
			if(!go()) 
				break;
			
			update();
			ans++;
		}
		bw.write(ans+"\n");
		bw.flush();
		bw.close();
	}
	
	public static boolean go() {
		boolean ret = false;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				
				if(map[i][j] != '.') {
					if(check(i,j)) {
						ret = true;
//						bomb(i,j);
					}
				}
				
			}
		}
		
		return ret;
	}
	
	public static boolean check(int r, int c) {
		boolean[][] visited = new boolean[R][C];
		Queue<Pos> q = new LinkedList<Pos>();
		ArrayList<Pos> posList = new ArrayList<Pos>();
		
		int cnt = 1;
		char base = map[r][c];
		
		visited[r][c] = true;
		q.add(new Pos(r,c));
		posList.add(new Pos(r,c));
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = cur.r+dr[i];
				int nc = cur.c+dc[i];
				
				if(0<=nr && nr<R && 0<=nc && nc<C && map[nr][nc] == base && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new Pos(nr,nc));
					posList.add(new Pos(nr,nc));
					cnt++;
				}
			}
		}
		
		if(cnt >= 4) {
			for(int i=0,size=posList.size(); i<size; i++) {
				map[posList.get(i).r][posList.get(i).c] = '.';
			}
			
			return true;
		}
		else return false;
	}
	
	public static void bomb(int r, int c) {
		Queue<Pos> q = new LinkedList<Pos>();
		q.add(new Pos(r,c));
		int cnt = 1;
		char base = map[r][c];
		map[r][c] = '.';
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = cur.r+dr[i];
				int nc = cur.c+dc[i];
				
				if(0<=nr && nr<R && 0<=nc && nc<C && map[nr][nc] == base) {
					map[nr][nc] = '.';
					q.add(new Pos(nr,nc));
					cnt++;
				}
			}
		}
	}
	
	public static void update() {
		for(int i=R-1; i>=0; i--) {
			for(int j=0; j<C; j++) {
				if(map[i][j] != '.') {
					int r = i;
					while(r+1<R && map[r+1][j] == '.') {
						r++;
						map[r][j] = map[r-1][j];
						map[r-1][j] = '.';
					}
				}
			}
		}
	}
	
	static class Pos {
		int r, c;
		
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
