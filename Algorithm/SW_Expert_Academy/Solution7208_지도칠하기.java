import java.io.*;
import java.util.*;

public class Solution7208_지도칠하기 {
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
		int T, N;
		ArrayList<ArrayList<Integer>> adj;
		int[] colors;
		int[] colorTmp;
		int answer;
		
		public Task(BufferedReader br, BufferedWriter bw) {
			this.br = br;
			this.bw = bw;
		}
		
		public void solve() throws IOException {
			T = Integer.parseInt(br.readLine());
			for(int t=1; t<=T; t++) {
				N = Integer.parseInt(br.readLine());
				
				answer = Integer.MAX_VALUE;
				colors = new int[N];
				colorTmp = new int[N];
				adj = new ArrayList<ArrayList<Integer>>();
				String[] line = br.readLine().split(" ");
				for(int n=0; n<N; n++) {
					adj.add(new ArrayList<Integer>());
					colors[n] = Integer.parseInt(line[n]);
					colorTmp[n] = colors[n];
				}
				
				for(int i=0; i<N; i++) {
					line = br.readLine().split(" ");
					for(int j=0; j<N; j++) {
						int state = Integer.parseInt(line[j]);
						if(state == 1) {
							adj.get(i).add(j);
						}
					}
				}
				
				for(int n=0; n<N; n++) {
					go(n, 0, 0);
				}
				bw.write("#"+t+" "+answer+"\n");
			}
		}
		
		public void go(int idx, int cnt, int visited) {
			if(visited == N) {
				answer = Math.min(answer, cnt);
				return;
			}
			
			for (int n = 1; n <= 4; n++) {
				int nextCnt = cnt;
				if(n == colorTmp[idx]) 
					nextCnt = cnt -1;
				colorTmp[idx] = n;
				
				boolean flag = true;
				for (int i = 0, size = adj.get(idx).size(); i < size; i++) {
					if(colorTmp[adj.get(idx).get(i)] == colorTmp[idx]) {
						flag = false;
						break;
					}
				}
				
				if(flag) {
					go((idx+1)%N, nextCnt+1, visited+1);
				}
				colorTmp[idx] = colors[idx];
			}
		}
	}
}
