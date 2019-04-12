import java.io.*;
import java.util.*;

public class Solution2383_점심식사시간 {
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
		int N, sidx, pidx, ptotal, answer;
		int[][] map;
		Pos[] stairs;
		int[] person;
		Pos[] pPos;
		
		public Task(BufferedReader br, BufferedWriter bw) {
			this.br = br;
			this.bw = bw;
		}
		
		public void solve() throws IOException {
			int T = Integer.parseInt(br.readLine());
			
			for(int t=1; t<=T; t++) {
				N = Integer.parseInt(br.readLine());
				map = new int[N][N];
				stairs = new Pos[2];
				person = new int[10];
				pPos = new Pos[10];
				sidx = 0;
				pidx = 0;
				ptotal = 0;
				answer = Integer.MAX_VALUE;
				for(int i=0; i<N; i++) {
					String[] line = br.readLine().split(" ");
					for(int j=0; j<N; j++) {
						map[i][j] = Integer.parseInt(line[j]);
						
						if(map[i][j] > 1) {
							stairs[sidx++] = new Pos(i,j);
						} else if(map[i][j] == 1) {
							pPos[ptotal++] = new Pos(i,j);
						}
					}
				}
				
				selectStair();
				bw.write("#"+t+" "+answer+"\n");
			}
		}
		
		public void selectStair() {
			for(int i=0; i<(1<<ptotal); i++) {
				
				for(int j=0; j<ptotal; j++) {
					if((i & 1<<j) != 0) {
						person[ptotal-1-j] = 1;
					}
					else
						person[ptotal-1-j] = 0;
				}
				
				go();
				
			}
		}
		
		public void go() {
			ArrayList<PriorityQueue<Integer>> stairArr = new ArrayList<PriorityQueue<Integer>>();
			stairArr.add(new PriorityQueue<Integer>());
			stairArr.add(new PriorityQueue<Integer>());
			
			for(int i=0; i<ptotal; i++) {
				Pos cur = pPos[i];
				Pos stair = stairs[person[i]];
				int dist = Math.abs(stair.y - cur.y) + Math.abs(stair.x - cur.x);
				
				stairArr.get(person[i]).add(dist);
			}
			
			Queue<Integer> q1 = new LinkedList<Integer>();
			Queue<Integer> q2 = new LinkedList<Integer>();
			
			int time = 0;
			for(time = 0; ; time++) {
				if(stairArr.get(0).size() == 0 && stairArr.get(1).size() == 0 && q1.size() == 0 && q2.size() == 0)
					break;
				
				for(int i=0, size=q1.size(); i<size; i++) {
					int cur = q1.poll();
					
					if(cur > 1) {
						q1.add(cur-1);
					}
				}
				for(int i=0, size=q2.size(); i<size; i++) {
					int cur = q2.poll();
					
					if(cur > 1) {
						q2.add(cur-1);
					}
				}
				
				// 추가
				while(!stairArr.get(0).isEmpty() && stairArr.get(0).peek() <= time && q1.size() < 3) {
					q1.add(map[stairs[0].y][stairs[0].x]);
					stairArr.get(0).poll();
				}
				
				while(!stairArr.get(1).isEmpty() && stairArr.get(1).peek() <= time && q2.size() < 3) {
					q2.add(map[stairs[1].y][stairs[1].x]);
					stairArr.get(1).poll();
				}
			}
			
			answer = Math.min(answer, time);
		}
	}
	
	static class Pos {
		int y, x;
		
		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
