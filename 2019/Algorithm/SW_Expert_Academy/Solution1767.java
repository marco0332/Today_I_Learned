import java.io.*;
import java.util.*;

class node
{
	int r,c;
	
	public node(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
}

public class Solution1767 {
	static int N, coreN, maxConnected, minLine;
	static int[][] area;
	static node[] core;
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	public static void dfs(int idx, int Connected, int Line) {
		// 코어 모두 다 확인했을 경우 답이랑 비교
		if(idx == coreN) {
			if(maxConnected < Connected) {
				maxConnected = Connected;
				minLine = Line;
			}
			else if(maxConnected == Connected && minLine > Line)
				minLine = Line;
			
			return;
		}
		
		// 코어가 모서리에 있을 경우
		else if(core[idx].r == 0 || core[idx].r == N-1 || core[idx].c == 0 || core[idx].c == N-1) {
			dfs(idx+1, Connected+1, Line);
			
			return;
		}
		
		// 코어 체크
		for(int i=0; i<4; i++) {
			int nr = core[idx].r, nc = core[idx].c, len = 0; 
			boolean check = true;
			
			while(true) {
				nr += dr[i]; nc += dc[i];
				if(nr<0 || N<=nr || nc<0 || N<=nc) break;
				if(area[nr][nc] != 0) {
					check = false;
					break;
				}
				area[nr][nc] = -1;
				len++;
			}
			
			if(check) 
				dfs(idx+1, Connected+1, Line+len);
			
			while(nr - dr[i] != core[idx].r || nc - dc[i] != core[idx].c) {
				nr -= dr[i];
				nc -= dc[i];
				
				area[nr][nc] = 0;
			}
		}
		// 코어를 연결하지 않을 경우
		dfs(idx+1, Connected, Line);
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			area = new int[N][N];
			core = new node[12];
			coreN = maxConnected = 0;
			minLine = 987654321;
			
			for(int r=0; r<N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<N; c++) {
					area[r][c] = Integer.parseInt(st.nextToken());
					
					if(area[r][c] == 1)
						core[coreN++] = new node(r,c);
				}
			}
			
			dfs(0, 0, 0);
			System.out.printf("#%d %d\n", t, minLine);
		}
		
	}
	
}
