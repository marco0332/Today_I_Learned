import java.io.*;
import java.util.*;

public class Solution3752_가능한_시험_점수 {
	static int[] arr;
	static boolean[] visited;
	static int N, answer, MAX;
	
	public static void go(int idx) {
		if(idx >= N) {
			for(int i=0; i<=MAX; i++) {
				if(visited[i])
					answer++;
			}
			return;
		}
		
		for(int i=MAX-1; i>=0; i--) {
			if(visited[i]) {
				visited[i+arr[idx]] = true;
			}
		}
		go(idx+1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringBuilder sb = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			String[] line = br.readLine().split(" ");
			arr = new int[N];
			MAX = 0;
			for(int n=0; n<N; n++) {
				arr[n] = Integer.parseInt(line[n]);
				MAX += arr[n];
			}
			answer = 0;
			visited = new boolean[MAX+1];
			visited[0] = true;
			
			go(0);
			sb.append("#"+t+" "+answer);
			System.out.println(sb);
		}
	}
}
