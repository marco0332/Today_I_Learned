import java.io.*;
import java.util.*;

public class Main14502 {
	static int N, M, blank, answer = 0;
	static ArrayList<Node> virus;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int [][] tMap;
	
	public static void go(int[][] map, int cWall) {
		if(cWall == 3) {
			// bfs
			Queue<Node> q = new LinkedList<Node>();
			for(int i=0; i<virus.size(); i++) {
				q.add(virus.get(i));
			}
			
			tMap = new int[N][M];
			for(int n=0; n<N; n++) {
				System.arraycopy(map[n], 0, tMap[n], 0, M);
			}
			
			int safety = blank - 3;
			while(!q.isEmpty()) {
				Node pos = q.poll();
				
				for(int i=0; i<4; i++) {
					int nR = pos.r + dr[i];
					int nC = pos.c + dc[i];
					
					if(0<=nR && nR<N && 0<=nC && nC<M && tMap[nR][nC] == 0) {
						tMap[nR][nC] = 2;
						safety--;
						q.add(new Node(nR,nC));
					}
				}
			}
			if(answer < safety) 
				answer = safety;
		}
		else {
			for(int r=0; r<N; r++) {
				for(int c=0; c<M; c++) {
					if(map[r][c] != 0) continue;
					
					map[r][c] = 1;
					go(map, cWall + 1);
					map[r][c] = 0;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		blank = 0;
		
		virus = new ArrayList<Node>();
		
		int[][] map = new int[N][M];
		for(int n=0; n<N; n++) {
			st = new StringTokenizer(br.readLine());
			for(int m=0; m<M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
				
				if(map[n][m] == 2)
					virus.add(new Node(n,m));
				else if(map[n][m] == 0)
					blank++;
			}
		}
		
		go(map, 0);
		System.out.println(answer);
	}
}

class Node{
	int r,c;
	
	public Node(int r, int c) {
		this.r = r;
		this.c = c;
	}
}