import java.io.*;
import java.util.*;

public class Main13460_구슬탈출2 {
	static class Boll {
		int y, x;

		public Boll() {}
		public Boll(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static class RB {
		Boll red, blue;
		int changeDirCnt, befoDir;
		
		public RB(Boll red, Boll blue, int changeDirCnt, int befoDir) {
			this.red = red;
			this.blue = blue;
			this.changeDirCnt = changeDirCnt;
			this.befoDir = befoDir;
		}
	}

	static char[][] map;
	static int N, M;
	static int hy, hx;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int Answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		Boll red = null;
		Boll blue = null;
		map = new char[N][M];
		for (int n = 0; n < N; n++) {
			String line = br.readLine();
			for (int m = 0; m < M; m++) {
				map[n][m] = line.charAt(m);

				if (map[n][m] == 'R') {
					red = new Boll(n, m);
					map[n][m] = '.';
				} else if (map[n][m] == 'B') {
					blue = new Boll(n, m);
					map[n][m] = '.';
				} else if (map[n][m] == 'O') {
					hy = n;
					hx = m;
				}
			}
		}
		RB rb = new RB(red, blue, 1, -1);

		Queue<RB> q = new LinkedList<RB>();
		q.add(rb);

		while (!q.isEmpty()) {
			RB cur = q.poll();
			Boll r = cur.red;
			Boll b = cur.blue;

			for(int i=0; i<4; i++) {
				if(cur.befoDir == i)
					continue;
				
				Boll nr = new Boll();
				nr.x = r.x;
				nr.y = r.y;
				Boll nb = new Boll();
				nb.x = b.x;
				nb.y = b.y;
				
				while(0<=nr.x + dc[i] && nr.x + dc[i]<M && 0<=nr.y + dr[i] && nr.y + dr[i]<N && map[nr.y+dr[i]][nr.x+dc[i]] != '#' && !(nr.x + dc[i] == nb.x && nr.y + dr[i] == nb.y)) {
					nr.x = nr.x + dc[i];
					nr.y = nr.y + dr[i];
					
					if(map[nr.y][nr.x] == 'O') {
						nr.x = -1;
						nr.y = -1;
						break;
					}
				}
				
				while(0<=nb.x + dc[i] && nb.x + dc[i]<M && 0<=nb.y + dr[i] && nb.y + dr[i]<N && map[nb.y+dr[i]][nb.x+dc[i]] != '#' && !(nb.x + dc[i] == nr.x && nb.y + dr[i] == nr.y)) {
					nb.x = nb.x + dc[i];
					nb.y = nb.y + dr[i];
					
					if(map[nb.y][nb.x] == 'O') {
						nb.x = -1;
						nb.y = -1;
						break;
					}
				}
				
				while(0<=nr.x + dc[i] && nr.x + dc[i]<M && 0<=nr.y + dr[i] && nr.y + dr[i]<N && map[nr.y+dr[i]][nr.x+dc[i]] != '#' && !(nr.x + dc[i] == nb.x && nr.y + dr[i] == nb.y)) {
					nr.x = nr.x + dc[i];
					nr.y = nr.y + dr[i];
					
					if(map[nr.y][nr.x] == 'O') {
						nr.x = -1;
						nr.y = -1;
						break;
					}
				}
				
				if(nb.x != -1 && nb.y != -1) {
					if(nr.x == -1 && nr.y == -1) {
						Answer = cur.changeDirCnt;
						q.clear();
						break;
					}
					else if(cur.changeDirCnt < 10){
						if(r.x != nr.x || r.y != nr.y || b.x != nb.x || b.y != nb.y)
							q.add(new RB(new Boll(nr.y, nr.x), new Boll(nb.y, nb.x), cur.changeDirCnt+1, (i+2)%4));
					}
				}
			}
		}

		if (Answer == Integer.MAX_VALUE)
			System.out.println("-1");
		else
			System.out.println(Answer);
	}
}
