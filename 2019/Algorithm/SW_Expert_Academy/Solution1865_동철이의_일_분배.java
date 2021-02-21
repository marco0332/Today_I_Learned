import java.io.*;
import java.util.*;

public class Solution1865_동철이의_일_분배 {
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
		int N;
		double[][] map;
		double ans;
		boolean[] visited;
		
		public Task(BufferedReader br, BufferedWriter bw) {
			this.br = br;
			this.bw = bw;
		}
		
		public void solve() throws IOException {
			int T = Integer.parseInt(br.readLine());
			
			for(int t=1; t<=T; t++) {
				N = Integer.parseInt(br.readLine());
				map = new double[N][N];
				visited = new boolean[N];
				for(int i=0; i<N; i++) {
					String[] line = br.readLine().split(" ");
					for(int j=0; j<N; j++) {
						map[i][j] = Integer.parseInt(line[j])*0.01;
					}
				}
				
				ans = 1;
				for(int i=0; i<N; i++) {
					ans *= map[i][i];
				}
				
				go(0, 1);
				bw.write(String.format("#%d %.6f\n", t, ans*100));
			}
		}
		
		public void go(int idx, double val) {
			if(idx == N) {
				ans = val;
				return;
			}
			
			for(int i=0; i<N; i++) {
				if(!visited[i] && val*map[idx][i] > ans) {
					visited[i] = true;
					go(idx+1, val*map[idx][i]);
					visited[i] = false;
				}
			}
		}
	}
}
