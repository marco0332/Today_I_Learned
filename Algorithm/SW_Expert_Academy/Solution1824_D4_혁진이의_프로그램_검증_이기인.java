import java.io.*;
import java.util.*;

public class Solution1824_혁진이의_프로그램_검증 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Task solver = new Task(br, bw);
		solver.solve();
		bw.close();
	}

	static class Task {
		BufferedReader br;
		BufferedWriter bw;
		int R, C;
		char[][] map;
		int[] dr = { 0, 0, 1, -1 };
		int[] dc = { 1, -1, 0, 0 };
		int memory;
		boolean[][][][] visited;

		public Task(BufferedReader br, BufferedWriter bw) {
			this.br = br;
			this.bw = bw;
		}

		public void solve() throws IOException {
			int T = Integer.parseInt(br.readLine());
			for (int t = 1; t <= T; t++) {
				String[] line = br.readLine().split(" ");
				R = Integer.parseInt(line[0]);
				C = Integer.parseInt(line[1]);
				map = new char[R][C];
				visited = new boolean[4][16][R][C];
				for (int r = 0; r < R; r++) {
					String input = br.readLine();
					for (int c = 0; c < C; c++) {
						map[r][c] = input.charAt(c);
					}
				}
				memory = 0;

				String answer = bfs(0, 0, 0) ? "YES" : "NO";
				bw.write("#" + t + " " + answer + "\n");
			}
		}

		public boolean bfs(int r, int c, int dir) {
			Queue<Pos> q = new LinkedList<Pos>();
			q.add(new Pos(r, c, dir, 0));
			boolean answer = false;

			while (!q.isEmpty()) {
				Pos cur = q.poll();
				if(cur.r<0) cur.r = R-1;
				else if(cur.r>=R) cur.r = 0;
				else if(cur.c<0) cur.c = C-1;
				else if(cur.c>=C) cur.c = 0;
				
				if(cur.memory>15) cur.memory = 0;
				else if(cur.memory<0) cur.memory = 15;
				
				if(visited[cur.dir][cur.memory][cur.r][cur.c]) {
					continue;
				}
				visited[cur.dir][cur.memory][cur.r][cur.c] = true;
				
				if(0<=map[cur.r][cur.c]-'0' && map[cur.r][cur.c]-'0' <=9) {
					q.add(new Pos(cur.r+dr[cur.dir],cur.c+dc[cur.dir],cur.dir,map[cur.r][cur.c]-'0'));
				} else if(map[cur.r][cur.c] == '>') {
					q.add(new Pos(cur.r+dr[0],cur.c+dc[0],0,cur.memory));
				} else if(map[cur.r][cur.c] == '<') {
					q.add(new Pos(cur.r+dr[1],cur.c+dc[1],1,cur.memory));
				} else if(map[cur.r][cur.c] == 'v') {
					q.add(new Pos(cur.r+dr[2],cur.c+dc[2],2,cur.memory));
				} else if(map[cur.r][cur.c] == '^') {
					q.add(new Pos(cur.r+dr[3],cur.c+dc[3],3,cur.memory));
				} else if(map[cur.r][cur.c] == '_') {
					int nDir = cur.memory == 0 ? 0 : 1;
					q.add(new Pos(cur.r+dr[nDir],cur.c+dc[nDir],nDir,cur.memory));
				} else if(map[cur.r][cur.c] == '|') {
					int nDir = cur.memory == 0 ? 2 : 3;
					q.add(new Pos(cur.r+dr[nDir],cur.c+dc[nDir],nDir,cur.memory));
				} else if(map[cur.r][cur.c] == '.') {
					q.add(new Pos(cur.r+dr[cur.dir],cur.c+dc[cur.dir],cur.dir,cur.memory));
				} else if(map[cur.r][cur.c] == '?') {
					for(int i=0; i<4; i++) {
						int nr = cur.r + dr[i];
						int nc = cur.c + dc[i];
						int nDir = i;
						q.add(new Pos(nr,nc,nDir,cur.memory));
					}
				} else if(map[cur.r][cur.c] == '@') {
					answer = true;
					break;
				} else if(map[cur.r][cur.c] == '+') {
					q.add(new Pos(cur.r+dr[cur.dir],cur.c+dc[cur.dir],cur.dir,cur.memory == 15 ? 0 : cur.memory+1));
				} else if(map[cur.r][cur.c] == '-') {
					q.add(new Pos(cur.r+dr[cur.dir],cur.c+dc[cur.dir],cur.dir,cur.memory == 0 ? 15 : cur.memory-1));
				}
			}
			
			return answer;
		}
	}

	static class Pos {
		int r, c, memory, dir;

		public Pos(int r, int c, int dir, int memory) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.memory = memory;
		}
	}
}
