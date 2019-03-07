import java.io.*;
import java.util.*;

public class Main2638_치즈 {
	static class Pos implements Comparable<Pos>{
		int y, x, depth;
		
		public Pos(int y, int x, int depth) {
			this.y = y;
			this.x = x;
			this.depth = depth;
		}

		@Override
		public int compareTo(Pos o) {
			if(this.depth < o.depth) return -1;
			else if(this.depth > o.depth) return 1;
			else return 0;
		}
	}
	
	static int N, M;
	static int[][] map;
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	static int[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+2][M+2];
		visited = new int[N+2][M+2];
		for(int i=1; i<=N; i++) {
			String[] line = br.readLine().split(" ");
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(line[j-1]);
			}
		}
		
		/** depth 가 낮은 것 부터 꺼내오기 */
		PriorityQueue<Pos> q = new PriorityQueue<Pos>();
		q.add(new Pos(0,0,0));
		int time = 0;
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			if(time < cur.depth)
				time = cur.depth;
			
			for(int i=0; i<4; i++) {
				int nr = cur.y + dr[i];
				int nc = cur.x + dc[i];
				
				/** 다음이 치즈면? 현재 depth + 1 */
				/** 다음이 공기면? 현재 depth */
				if(0<=nr && nr<=N && 0<=nc && nc<=M) {
					if(visited[nr][nc] == 0 && map[nr][nc] == 0) {
						q.add(new Pos(nr,nc,cur.depth));
						visited[nr][nc] = 1;
					}
					else if(visited[nr][nc] < 2 && map[nr][nc] == 1) {
						visited[nr][nc]++;
						
						if(visited[nr][nc] == 2) {
							q.add(new Pos(nr,nc,cur.depth+1));
							map[nr][nc] = 0;
						}
					}
				}
			}
		}
		
		System.out.println(time);
	}
}
