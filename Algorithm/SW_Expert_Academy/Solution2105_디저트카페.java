import java.io.*;
import java.util.*;

public class Solution2105_디저트카페 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Task solver = new Task(br,bw);
		solver.solve();
		bw.close();
	}
	
	static class Task {
		BufferedReader br;
		BufferedWriter bw;
		int T, N, answer;
		int[][] map;
		int[] dr = {1,1,-1,-1};
		int[] dc = {1,-1,-1,1};
		boolean[] typeCheck;
		int[][] visited;
		int sr, sc;
		
		public Task(BufferedReader br, BufferedWriter bw) {
			this.br = br;
			this.bw = bw;
		}
		
		public void preprocess() throws IOException {
			N = Integer.parseInt(br.readLine());
			typeCheck = new boolean[101];
			visited = new int[N][N];
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				String[] line = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(line[j]);
				}
			}
			answer = 1;
		}
		
		public boolean isOk(int r, int c) {
			if(r==0 && c==0) return false;
			else if(r==0 && c==N-1) return false;
			else if(r==N-1 && c==0) return false;
			else if(r==N-1 && c==N-1) return false;
			else if(!(0<=r && r<N && 0<=c && c<N)) return false;
			
			return true;
		}
		
		public void solve() throws IOException {
			T = Integer.parseInt(br.readLine());
			
			for(int t=1; t<=T; t++) {
				preprocess();
				
				for(int r=0; r<N; r++) {
					for(int c=1; c<N; c++) {
						visited[r][c] = 1;
						typeCheck[map[r][c]] = true;
						sr = r; sc = c;

						go(r,c,0,0,0);
						
						typeCheck[map[r][c]] = false;
						visited[r][c] = 0;
					}
				}
				
				if(answer == 1) bw.write("#"+t+" "+-1+"\n");
				else bw.write("#"+t+" "+answer+"\n");
			}
		}
		
		public void go(int r, int c, int cnt, int dir, int turn) {
			if(cnt > 0 && sr==r && sc==c) {
				answer = Math.max(answer, cnt);
				return;
			}
			
			int nr, nc;
			if(turn < 3) {
				int ndir = dir+1;
				nr = r + dr[ndir];
				nc = c + dc[ndir];
				
				if(isOk(nr,nc)) {
					if(visited[nr][nc] == 0 && !typeCheck[map[nr][nc]]) {
						visited[nr][nc] = -1;
						typeCheck[map[nr][nc]] = true;
						
						go(nr,nc,cnt+1,ndir,turn+1);
						
						typeCheck[map[nr][nc]] = false;
						visited[nr][nc] = 0;
					} else if(visited[nr][nc] == 1) {
						go(nr,nc,cnt+1,ndir,turn+1);
					}
				}
			}
			nr = r + dr[dir];
			nc = c + dc[dir];
			
			if(isOk(nr,nc)) {
				if(visited[nr][nc] == 0 && !typeCheck[map[nr][nc]]) {
					visited[nr][nc] = -1;
					typeCheck[map[nr][nc]] = true;
					
					go(nr,nc,cnt+1,dir,turn);
					
					typeCheck[map[nr][nc]] = false;
					visited[nr][nc] = 0;
				} else if(visited[nr][nc] == 1) {
					go(nr,nc,cnt+1,dir,turn);
				}
			}
		}
	}
}