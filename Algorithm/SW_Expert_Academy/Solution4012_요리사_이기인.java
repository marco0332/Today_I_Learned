import java.io.*;
import java.util.*;

public class Solution4012_요리사 {
	static int N;
	static int[][] adj;
	static int answer;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			adj = new int[N][N];
			visited = new boolean[N];
			answer = Integer.MAX_VALUE;
			
			for(int i=0; i<N; i++) {
				String[] line = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					adj[i][j] = Integer.parseInt(line[j]);
					adj[j][i] += adj[i][j];
				}
			}
			
			visited[0] = true;
			go(1, N/2-1);
			bw.write("#"+t+" "+answer+"\n");
		}
		bw.close();
	}
	
	public static void go(int idx, int cnt) {
		if(cnt == 0) {
			int valA = 0, valB = 0;
			
			for(int i=0; i<N; i++) {
				if(visited[i]) {
					for(int j=i+1; j<N; j++) {
						if(visited[j]) {
							valA += adj[i][j];
						}
					}
				}
				else {
					for(int j=i+1; j<N; j++) {
						if(!visited[j]) {
							valB += adj[i][j];
						}
					}
				}
			}
			
			answer = Math.min(answer, Math.abs(valA-valB));
			return;
		}
		
		for(int i=idx; i<=N-cnt; i++) {
			visited[i] = true;
			go(i+1, cnt-1);
			visited[i] = false;
		}
	}
}
