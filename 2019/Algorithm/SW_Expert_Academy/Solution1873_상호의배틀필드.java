import java.io.*;
import java.util.*;

public class Solution1873_상호의배틀필드 {
	/** 땡크 객체 */
	static class TTank {
		int y;
		int x;
		int dir;

		public TTank() {
		}

		public TTank(int y, int x, int dir) {
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
	}

	/** 방향 설정 */
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			/** Input */
			String[] HW = br.readLine().split(" ");
			int H = Integer.parseInt(HW[0]);
			int W = Integer.parseInt(HW[1]);
			TTank myT = new TTank();

			char[][] map = new char[H][W];
			for (int h = 0; h < H; h++) {
				String[] line = br.readLine().split("");
				for (int w = 0; w < W; w++) {
					map[h][w] = line[w].charAt(0);

					/** 땡크 객체 생성 */
					if (map[h][w] == '^') {
						myT = new TTank(h, w, 0);
						map[h][w] = '.';
					} else if (map[h][w] == 'v') {
						myT = new TTank(h, w, 2);
						map[h][w] = '.';
					} else if (map[h][w] == '<') {
						myT = new TTank(h, w, 3);
						map[h][w] = '.';
					} else if (map[h][w] == '>') {
						myT = new TTank(h, w, 1);
						map[h][w] = '.';
					}
				}
			}

			/** 위치 초기화 */
			int ny = myT.y;
			int nx = myT.x;
			
			/** 명령 수행 */
			int N = Integer.parseInt(br.readLine());
			String[] orders = br.readLine().split("");
			for (int n = 0; n < N; n++) {
				int y = myT.y;
				int x = myT.x;

				int cnt = 3;
				switch (orders[n].charAt(0)) {
				case 'U':
					cnt--;
				case 'D':
					cnt--;
				case 'L':
					cnt--;
				case 'R':
					if (cnt == 3)
						myT.dir = 1;
					else if (cnt == 2)
						myT.dir = 3;
					else if (cnt == 1)
						myT.dir = 2;
					else
						myT.dir = 0;

					ny = y + dr[myT.dir];
					nx = x + dc[myT.dir];
					if (0 <= ny && ny < H && 0 <= nx && nx < W && map[ny][nx] == '.') {
						myT.y = ny;
						myT.x = nx;
					}
					
					break;
				case 'S':
					int idx = 1;
					while(true) {
						ny = y + dr[myT.dir]*idx;
						nx = x + dc[myT.dir]*idx;
						idx++;
						
						if(0<=ny && ny<H && 0<=nx && nx<W) {
							if(map[ny][nx] == '.' || map[ny][nx] == '-') 
								continue;
							else if(map[ny][nx] == '*') {
								map[ny][nx] = '.';
								break;
							}
							else
								break;
						}
						else
							break;
					}
				}
			}
			
			/** 땡크 위치 맵에 입력 */
			if(myT.dir == 0) {
				map[myT.y][myT.x] = '^'; 
			}
			else if(myT.dir == 1) {
				map[myT.y][myT.x] = '>';
			}
			else if(myT.dir == 2) {
				map[myT.y][myT.x] = 'v';
			}
			else if(myT.dir == 3) {
				map[myT.y][myT.x] = '<';
			}
			
			/** Output */
			sb.append("#"+t+" ");
			for(int h=0; h<H; h++) {
				for(int w=0; w<W; w++) {
					sb.append(map[h][w]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
}
